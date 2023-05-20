package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Constant;
import it.polimi.ingsw.Network.client.RmiClientInterface;
import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.Network.messages.Answers.MoveAnswer;
import it.polimi.ingsw.Network.messages.Answers.NumberOfPlayersAnswer;
import it.polimi.ingsw.Network.messages.Answers.UsernameAnswer;
import it.polimi.ingsw.Network.messages.ErrorMessages.*;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Utils;

import java.io.*;
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
    private final int secondsDuringTurn = 120;
    private static final String RECONNECT = "Reconnect";
    private final Map<Integer, Socket> socketClients = new HashMap<>();
    private final Map<Integer, RmiClientInterface> rmiClients = new HashMap<>();
    private final Map<Integer, String> answers = new HashMap<>();
    private final Map<Integer, Boolean> answerReady = new HashMap<>();
    private final Map<Integer, String> lobby = new HashMap<>();
    private final Map<Integer, String> nicknames = new HashMap<>();
    Controller activeMatch;
    private final List<Integer> afkPlayers = new ArrayList<>();
    private final List<Integer> disconnectedPlayers = new ArrayList<>();
    private final Map<Integer, Socket> socketChatClients = new HashMap<>();
    private SocketServer socketServer;
    private RmiServer rmiServer;
    private int idClient;
    private boolean firstPlayer;
    private int numberOfPlayers;


    public ServerManager() {
        this.firstPlayer = true;
        this.numberOfPlayers = 0;
        this.idClient = 0;
    }

    void addClient(Socket client) {
        socketClients.put(idClient, client);
        answerReady.put(idClient, true);
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

    void removeClient(Socket client) {
        try {
            int number = getNumber(client);
            socketClients.remove(number);
            removeClient(number);
        } catch (NoSuchElementException e) {
            //Do nothing
        }
    }

    void removeClient(RmiClientInterface client) {
        try {
            int number = getNumber(client);
            rmiClients.remove(number);
            removeClient(number);
        } catch (NoSuchElementException e) {
            //Do nothing
        }
    }

    private void removeClient(int number) {
        if (lobby.containsKey(number))
            removeClientFromLobby(number);
/*        else if (lobby.containsKey(number)) {
            awayFromKeyboardOrDisconnected.add(number);
            lobby.get(number).disconnect(nicknames.get(number));
        }*/
        System.out.println("Client " + number + " removed.");
    }

    private void removeClientFromLobby(int number) {
        String name = lobby.get(number);
        lobby.remove(number);
        Integer[] clients = lobby.keySet().toArray(new Integer[0]);
        for (int i : clients) {
            //TODO: new message for disconnection to send everyone; name + "is disconnected";
            sendMessageAndWaitForAnswer(i, new NotValidUsernameError());
//            notifyTimeLeft(i, clients.length);//to be removed?
        }
    }

    protected String sendMessageAndWaitForAnswer(int number, Message message) {
        if (isAwayFromKeyboard(number)){
            //TODO: create new message / i'm doubtful
            return "the player" + number + "is disconnected";
        }

        String serializedMessage = Converter.convertToJSON(message);
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
            //TODO: new ERROR message
            return "ERROR registration";
        }

        boolean isTimeExceeded = false;
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
                        rmiServer.unregister(rmiClients.get(number));
                        return "ERROR";
                    }
                }
                if (socketClients.containsKey(number)) {
                    try {
                        socketClients.get(number).getInetAddress().isReachable(MILLIS_IN_SECOND);
                    } catch (IOException e) {
                        System.out.println("Unable to reach client " + e.getMessage());
                        //socketServer.unregister(socketClients.get(number));
                        return "ERROR";
                    }
                }
            }
            if (counter > secondsDuringTurn * MILLIS_IN_SECOND / MILLIS_TO_WAIT) {
                isTimeExceeded = true;
                break;
            }
        }
        if (isTimeExceeded) {
            communication.setTimeExceeded();
            if (lobby.containsKey(number)) {
                afkPlayers.add(number);
//                activeMatches.get(number).disconnect(nicknames.get(number));
            }
            return "Error";
        }
        return answers.get(number);
    }

    private String login(int number){
        String answer = sendMessageAndWaitForAnswer(number, new ChooseUsernameMessage());
        Message m = Converter.convertFromJSON(answer);

        while (isUsernameTaken(((UsernameAnswer) m).getString(), number)){
            answer = sendMessageAndWaitForAnswer(number, new NotValidUsernameError());
            m = Converter.convertFromJSON(answer);
        }
        return ((UsernameAnswer) m).getString();
    }

    protected void addClientToLobby(int number) throws IOException {
        String username = login(number);

        if(this.firstPlayer){
            this.firstPlayer = false;
            String answer = sendMessageAndWaitForAnswer(number, new FirstPlayerMessage());
            Message m = Converter.convertFromJSON(answer);
            this.numberOfPlayers = ((NumberOfPlayersAnswer) m).getNum();
        }
        lobby.put(number, username);
        notifyNewConnection(this.numberOfPlayers);

        if(lobby.size() == this.numberOfPlayers){
            startGame();
        }
    }

    void addClientToLog(int temporaryId) throws IOException {
        String code;
        int oldId;
        while (true) {
            //new Message(Protocol.RECONNECT, "", Arrays.asList(NEW_GAME, RECONNECT))
            String answer = sendMessageAndWaitForAnswer(temporaryId, new ReconnectionMessage());
            if (answer.equals("ERR"))
                break;
            else if (answer.equals(RECONNECT)) {
                code = sendMessageAndWaitForAnswer(temporaryId, new OldGameID());
                if (code.equals("ERR"))
                    break;
                if (checkIfDisconnected(code)) {
                    oldId = Integer.parseInt(code);
                    if (!switchClientId(oldId, temporaryId))
                        break;
                    disconnectedPlayers.remove((Object) oldId);
                    //new Message(Protocol.WELCOME_BACK, nicknames.get(oldId), null)
                    sendMessageAndWaitForAnswer(oldId, new FirstPlayerMessage());
                    activeMatch.reconnect(nicknames.get(oldId));
                    break;
                }else{
                    sendMessageAndWaitForAnswer(temporaryId, new OldIdNotValid());
                }
            } else {
                addClientToLobby(temporaryId);
                break;
            }
        }
    }

    private boolean switchClientId(int oldId, int temporaryId) {
        if (socketClients.containsKey(temporaryId)) {
            socketClients.put(oldId, socketClients.get(temporaryId));
            socketClients.remove(temporaryId);
            return true;
        }
        if (rmiClients.containsKey(temporaryId)) {
            rmiClients.put(oldId, rmiClients.get(temporaryId));
            rmiClients.remove(temporaryId);
            return true;
        }
        return false;
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
    private boolean isUsernameTaken(String nickname, int idClient){
        for(String s: nicknames.values()){
            if(nickname.equals(s)){
                return true;
            }
        }
        nicknames.put(idClient, nickname);
        return false;
    }

    private void startGame() throws IOException {
//        activeMatch = checkMemoryDisk();

        activeMatch = new Controller(numberOfPlayers);
        System.out.println("A new game has been created");
        for (Integer j : this.lobby.keySet()) {
            activeMatch.setUsernamePlayer(lobby.get(j));
        }
        activeMatch.setFirstPlayer();

        boolean win = false;

        while (!win) {

            //saveGame();
            System.out.println("Game has been saved");

            String activeUsername = activeMatch.getActivePlayerUsername();

            //Sending graphical info on the game's status
            for (Integer i : this.lobby.keySet()) {
                sendMessageAndWaitForAnswer(i, new GraphicalGameInfo(activeMatch.getBoard(), activeMatch.getCommonCardsDesigns(), activeMatch.getPlayerBookshelf(this.lobby.get(i)), activeMatch.getPlayerPersonalCard(this.lobby.get(i)), activeUsername));
            }

            //Sending to the active player a move request and handling the answer
            int x = getNumberByUsernameFromLobby(activeUsername);

            /*for (Integer i : this.lobby.keySet()) {
                if (i != getNumberByUsernameFromLobby(activeUsername)) {
                    new Thread(() -> {
                        while (true) {
                            String answerChat = sendMessageAndWaitForAnswer(i, new ChatBeginsMessage());
                            Message m = Converter.convertFromJSON(answerChat);

                            System.out.println(((ChatMessage) m).getMessage());
                            /*for (Integer j : this.lobby.keySet()){
                                if (j != getNumberByUsernameFromLobby(activeUsername) && !j.equals(i)) {
                                    sendMessageAndWaitForAnswer(j, new ChatMessage(((ChatMessage) m).getMessage(), "Server"));
                                }
                            }
                        }
                    }).start();
                }
            }*/
            String answer = sendMessageAndWaitForAnswer(x, new MoveMessage(activeUsername));

            Message m = Converter.convertFromJSON(answer);
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
                m = Converter.convertFromJSON(sendMessageAndWaitForAnswer(number, new EmptyPositionError()));
            } else if (done == 2) {
                m = Converter.convertFromJSON(sendMessageAndWaitForAnswer(number, new NotEnoughSpaceBookshelfError()));
            } else if (done == 3) {
                m = Converter.convertFromJSON(sendMessageAndWaitForAnswer(number, new NoFreeSideError()));
            } else if (done == 4) {
                m = Converter.convertFromJSON(sendMessageAndWaitForAnswer(number, new NotAdjacTiles()));
            } else if (done == 5) {
                m = Converter.convertFromJSON(sendMessageAndWaitForAnswer(number, new NotEnoughSpaceColumnError()));
            }
            while (activeMatch.dummyInput(((MoveAnswer) m).getS())) {
                m = Converter.convertFromJSON(sendMessageAndWaitForAnswer(number, new NotValidMoveError()));
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

    private boolean checkIfDisconnected(String code) {
        int oldId;
        try {
            oldId = Integer.parseInt(code);
        } catch (NumberFormatException e) {
            return false;
        }
        if (isAwayFromKeyboard(oldId))
            return true;
        if (!answerReady.getOrDefault(oldId, false))
            return false;

        //TODO: this is a temporary message, create new message for connection
        sendMessageAndWaitForAnswer(oldId, new ChooseUsernameMessage());
        return isAwayFromKeyboard(oldId);
    }

    /**
     * @author Donato Fiore
     * @param code the id of the player
     * @return true if the player is AFK
     * */
    public boolean isAwayFromKeyboard(int code) {
        return afkPlayers.contains(code);
    }

    /**
     * @author Donato Fiore
     * @param code the id of the player
     * @return true if the player was disconnected
     * */
    public boolean isDisconnected(int code){
        return disconnectedPlayers.contains(code);
    }

    @Override
    public void run() {
        socketServer = new SocketServer(this, Constant.PORT_SOCKET_GAME);
        try {
            rmiServer = new RmiServer(this, Constant.PORT_RMI_GAME);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        new Thread(socketServer).start();
        new Thread(rmiServer).start();
    }

    /**
     * @author Alessandro Fornara
     * This method saves the game
     * @throws IOException
     */
    private void saveGame() throws IOException {
        String save = this.activeMatch.getModelSave();

        String filePath = "C:\\Users\\alefo\\Desktop\\ing-sw-2023-Gennaretti-Fiore-Fornara-Galli\\MyShelfie\\save.txt";
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(save);
        fileWriter.close();
    }

    /**
     * This method loads a game that has been saved in file
     * @return game {@link Model}
     * @throws IOException
     */
    private Model loadGame() throws IOException {
        File file = new File("C:\\Users\\alefo\\Desktop\\ing-sw-2023-Gennaretti-Fiore-Fornara-Galli\\MyShelfie\\save.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();

        if(file.exists()) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String content = stringBuilder.toString();

            return Converter.convertModelFromJSON(content);
        }
        return null;
    }

    private Controller checkMemoryDisk() throws IOException {
        Model m = loadGame();

        int i = 0;
        boolean samePlayers = true;

        if(m!=null) {
            m.getBoard().setCommonCards();
            Player[] players = m.getPlayers();
            ArrayList<String> oldUsernames = new ArrayList<>();

            for(int k=0; k<players.length; k++){
                oldUsernames.add(players[k].getUsername());
            }

            if (oldUsernames.size() == this.numberOfPlayers) {

                Utils.mergeSort(oldUsernames, 0, oldUsernames.size() - 1);
                List<String> lobbyList = new ArrayList<>(lobby.values());
                Utils.mergeSort(lobbyList, 0, lobbyList.size() - 1);

                for(int k = 0; k<oldUsernames.size() && samePlayers; k++)
                    if(!lobbyList.get(k).equals(oldUsernames.get(k))){
                        samePlayers = false;
                    }

                if (samePlayers) {
                    activeMatch = new Controller(m);
                    System.out.println("A game save has been restored");
                    activeMatch.setActivePlayer(activeMatch.getIdActivePlayer());
                }
                else{
                    activeMatch = new Controller(numberOfPlayers);
                    System.out.println("A new game has been created");
                    for (Integer j : this.lobby.keySet()) {
                        activeMatch.setUsernamePlayer(lobby.get(j));
                    }
                    activeMatch.setFirstPlayer();
                }
            }else{
                activeMatch = new Controller(numberOfPlayers);
                System.out.println("A new game has been created");
                for (Integer j : this.lobby.keySet()) {
                    activeMatch.setUsernamePlayer(lobby.get(j));
                }
                activeMatch.setFirstPlayer();
            }
        }
        else {
            activeMatch = new Controller(numberOfPlayers);
            System.out.println("A new game has been created");
            for (Integer j : this.lobby.keySet()) {
                activeMatch.setUsernamePlayer(lobby.get(j));
            }
            activeMatch.setFirstPlayer();
        }

        return activeMatch;
    }
}