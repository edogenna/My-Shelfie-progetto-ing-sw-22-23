package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Constant;
import it.polimi.ingsw.Network.client.RmiClientInterface;
import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.Network.messages.Answers.MoveAnswer;
import it.polimi.ingsw.Network.messages.Answers.NumberOfPlayersAnswer;
import it.polimi.ingsw.Network.messages.Answers.UsernameAnswer;
import it.polimi.ingsw.Network.messages.ErrorMessages.*;
import it.polimi.ingsw.controller.Controller;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.*;

import static java.lang.Thread.sleep;

public class ServerManager implements Runnable{
    public static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;
    private static final int DEFAULT_BOARD = 1;
    private static final int MILLIS_TO_WAIT = 10;
    private static final int MILLIS_IN_SECOND = 1000;
    private final Map<Integer, Socket> socketClients = new HashMap<>();
    private final Map<Integer, Socket> socketChatClients = new HashMap<>();
    private final Map<Integer, RmiClientInterface> rmiClients = new HashMap<>();
    private final Map<Integer, String> answers = new HashMap<>();
    private final Map<Integer, Boolean> answerReady = new HashMap<>();
    private final Map<Integer, String> lobby = new HashMap<>();
    private final Map<Integer, String> nicknames = new HashMap<>();
    //private final Map<Integer, Controller> activeMatches = new HashMap<>();
    Controller activeMatch;
    //private final List<Integer> awayFromKeyboardOrDisconnected = new ArrayList<>();
    Converter converter = new Converter();
    private SocketServer socketServer;
    private RmiServer rmiServer;
    private SocketServerChat socketServerChat;

    private int idClient = 0;
    private int idClientChat = 0;
    private boolean firstPlayer = true;
    int numberOfPlayers = 0;
    //private int chosenBoard = 0;

    public ServerManager() {
    }

    void addClient(Socket client) {
        socketClients.put(idClient, client);
        answerReady.put(idClient, true);
        idClient++;
    }

    void addChatClient(Socket client) {
        socketClients.put(idClient, client);
        idClient++;
    }


    void addClient(RmiClientInterface client) {
        rmiClients.put(idClient, client);
        answerReady.put(idClient, true);
        idClient++;
    }

    public String getNickname(int playerId) {
        return nicknames.get(playerId);
    }

    private int getNumberByUsernameFromLobby(String value) {
        for (Map.Entry<Integer, String> entry : lobby.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return -1;
    }


    public int getNumber(Socket client){
        int x = -1;
        for (Map.Entry<Integer, Socket> entry : socketClients.entrySet())
            if (entry.getValue() == client)
                x = entry.getKey();
        return x;
    }

    public int getNumber(RmiClientInterface client) {
        for (Map.Entry<Integer, RmiClientInterface> entry : rmiClients.entrySet())
            if (entry.getValue() == client)
                return entry.getKey();
        throw new NoSuchElementException();
    }

    protected void setAnswer(int client, String answer) {
        answers.put(client, answer);
        answerReady.put(client, true);
    }

    protected String sendMessageAndWaitForAnswer(int number, Message message) {
        String serializedMessage = converter.convertToJSON(message);
        while (!answerReady.get(number)) {
            try {
                sleep(MILLIS_TO_WAIT);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        answerReady.put(number, false);
        Communication communication;
        if (socketClients.containsKey(number)) {
            communication = new SocketCommunication(serializedMessage, socketClients.get(number), socketServer, number, this);
            new Thread(communication).start();
        } else if (rmiClients.containsKey(number)) {
            communication = new RmiCommunication(serializedMessage, rmiClients.get(number), rmiServer, number, this);
            new Thread(communication).start();
        } else {
            System.out.println("Unregistered Client");
            return "ERROR";
        }

        int counter = 0;
        while (!answerReady.get(number)) {
            try {
                sleep(MILLIS_TO_WAIT);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            counter++;
            if (counter % MILLIS_TO_WAIT == 0) {
                if (rmiClients.containsKey(number)) {
                    try {
                        rmiClients.get(number).testAliveness();
                    } catch (RemoteException e) {
                        System.out.println("Unable to reach client " + e.getMessage());
//                        rmiServer.unregister(rmiClients.get(number));
                        return "ERROR";
                    }
                }
                if (socketClients.containsKey(number)) {
                    try {
                        socketClients.get(number).getInetAddress().isReachable(MILLIS_IN_SECOND);
                    } catch (IOException e) {
                        System.out.println("Unable to reach client " + e.getMessage());
                        //socket client removed from socket server;
                        return "ERROR";
                    }
                }
            }
        }
        return answers.get(number);
    }

    private String login(int number){
        String answer = sendMessageAndWaitForAnswer(number, new ChooseUsernameMessage());
        Message m = converter.convertFromJSON(answer);

        while (isUsernameTaken(((UsernameAnswer) m).getS(), number)){
            answer = sendMessageAndWaitForAnswer(number, new NotValidUsernameError());
            m = converter.convertFromJSON(answer);
        }
        return ((UsernameAnswer) m).getS();
    }

    protected void clientLogin(int number){
        String username = login(number);

        if(this.firstPlayer){
            this.firstPlayer = false;
            String answer = sendMessageAndWaitForAnswer(number, new FirstPlayerMessage());
            Message m = converter.convertFromJSON(answer);
            activeMatch = new Controller(((NumberOfPlayersAnswer) m).getNum());
            this.numberOfPlayers = ((NumberOfPlayersAnswer) m).getNum();
        }
        lobby.put(number, username);
        notifyNewConnection(this.numberOfPlayers);

        if(lobby.size() == this.numberOfPlayers){
            startGame();
        }
    }

    private void notifyNewConnection(int numberOfPlayers) {
        Integer[] clients = lobby.keySet().toArray(new Integer[0]);
        for (int i : clients) {
            sendMessageAndWaitForAnswer(i, new LobbyMessage(lobby.size(), numberOfPlayers));
            if(clients.length < this.numberOfPlayers) {
                sendMessageAndWaitForAnswer(i, new WaitingMessage());
            }
            else if (clients.length == numberOfPlayers) {
                sendMessageAndWaitForAnswer(i, new StartingGameMessage());
            }
        }
    }

    /**
     * This method check if a username has already been taken by another client, if not it is added to a map of usernames
     * @author Alessandro Fornara
     * @param nickname username
     * @return true if the username has already been taken, false otherwise
     */
    public boolean isUsernameTaken(String nickname, int idClient){
        for(String s: nicknames.values()){
            if(nickname.equals(s)){
                return true;
            }
        }
        nicknames.put(idClient, nickname);
        return false;
    }

    private void startGame() {
        activeMatch = new Controller(this.numberOfPlayers);
        boolean win = false;

        for (Integer i : this.lobby.keySet()) {
            activeMatch.setUsernamePlayer(lobby.get(i));
        }
        activeMatch.setFirstPlayer();
        while (!win) {

//            saveGame();
//            System.out.println("Game has been saved");
            String activeUsername = activeMatch.getActivePlayerUsername();

            //Sending graphical info on the game's status
            for (Integer i : this.lobby.keySet()) {
                sendMessageAndWaitForAnswer(i, new GraphicalGameInfo(activeMatch.getBoard(), activeMatch.getCommonCardsDesigns(), activeMatch.getPlayerBookshelf(this.lobby.get(i)), activeMatch.getPlayerPersonalCard(this.lobby.get(i)), activeUsername));
            }

            //Sending to the active player a move request and handling the answer
            int x = getNumberByUsernameFromLobby(activeUsername);
            String answer = sendMessageAndWaitForAnswer(x, new MoveMessage(activeUsername));
            Message m = converter.convertFromJSON(answer);
            handleMoveAnswer(x, m);
            win = activeMatch.finishTurn();

            if (win) {
                int points = activeMatch.declareWinner();
                for (Integer j : this.lobby.keySet()) {
                    sendMessageAndWaitForAnswer(j, new WinMessage(activeMatch.getActivePlayerUsername(), points));
                }
            }

        }
    }

    private void handleMoveAnswer(int number, Message m) {
        int done = 6;
        boolean exit = false;
        while (!exit) {

            if (done == 1) {
                m = converter.convertFromJSON(sendMessageAndWaitForAnswer(number, new EmptyPositionError()));
            } else if (done == 2) {
                m = converter.convertFromJSON(sendMessageAndWaitForAnswer(number, new NotEnoughSpaceBookshelfError()));
            } else if (done == 3) {
                m = converter.convertFromJSON(sendMessageAndWaitForAnswer(number, new NoFreeSideError()));
            } else if (done == 4) {
                m = converter.convertFromJSON(sendMessageAndWaitForAnswer(number, new NotAdjacTiles()));
            } else if (done == 5) {
                m = converter.convertFromJSON(sendMessageAndWaitForAnswer(number, new NotEnoughSpaceColumnError()));
            }
            while (activeMatch.dummyInput(((MoveAnswer) m).getS())) {
                m = converter.convertFromJSON(sendMessageAndWaitForAnswer(number, new NotValidMoveError()));
            }
            String[] tiles = ((MoveAnswer) m).getS().split(",");
            int i = tiles.length;
            //i = number of tiles * 2 + 1;
            switch (i) {
                case 3:
                    //we have taken 1 tile;
                    done = activeMatch.pickCard(tiles[0].charAt(0) - 'a', Integer.parseInt(tiles[1]), Integer.parseInt(tiles[2]));
                    break;
                case 5:
                    //we have taken 2 tiles;
                    done = activeMatch.pickCard(tiles[0].charAt(0) - 'a', Integer.parseInt(tiles[1]), tiles[2].charAt(0) - 'a', Integer.parseInt(tiles[3]), Integer.parseInt(tiles[4]));
                    break;
                case 7:
                    //we have taken 3 tiles;
                    done = activeMatch.pickCard(tiles[0].charAt(0) - 'a', Integer.parseInt(tiles[1]), tiles[2].charAt(0) - 'a', Integer.parseInt(tiles[3]), tiles[4].charAt(0) - 'a', Integer.parseInt(tiles[5]), Integer.parseInt(tiles[6]));
                    break;
            }

            if (done == 0) {
                int points1 = activeMatch.controlCommonCards(0);
                int points2 = activeMatch.controlCommonCards(1);
                if (points1 != 0) {
                    for (Integer j : this.lobby.keySet()) {
                        sendMessageAndWaitForAnswer(j, new CommonCardMessage(this.lobby.get(number), 1, points1));
                    }
                }
                if (points2 != 0) {
                    for (Integer j : this.lobby.keySet()) {
                        sendMessageAndWaitForAnswer(j, new CommonCardMessage(this.lobby.get(number), 2, points2));
                    }
                }
                exit = true;
            }
        }
    }

    @Override
    public void run() {
        socketServer = new SocketServer(this, Constant.PORT_SOCKET_GAME);
        socketServerChat = new SocketServerChat(this, Constant.PORT_SOCKET_CHAT);
        try {
            rmiServer = new RmiServer(this, Constant.PORT_RMI_GAME);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        new Thread(socketServer).start();
        new Thread(rmiServer).start();
    }
}