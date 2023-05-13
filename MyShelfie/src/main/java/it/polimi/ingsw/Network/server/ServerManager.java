package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.Network.messages.Answers.NumberOfPlayersAnswer;
import it.polimi.ingsw.Network.messages.Answers.UsernameAnswer;
import it.polimi.ingsw.Network.messages.ErrorMessages.NotValidUsernameError;
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
    private final int socketPort;
    private final int rmiPort;
    private final Map<Integer, Socket> socketClients = new HashMap<>();
    private final Map<Integer, RmiGame> rmiClients = new HashMap<>();
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
    private int idClient = 0;
    private boolean firstPlayer = true;
    int numberOfPlayers = 0;
    //private int chosenBoard = 0;

    public ServerManager(int socketPort, int rmiPort) {
        this.socketPort = socketPort;
        this.rmiPort = rmiPort;
    }

    void addClient(Socket client) {
        socketClients.put(idClient, client);
        answerReady.put(idClient, true);
        idClient++;
    }

    void addClient(RmiGame client) {
        rmiClients.put(idClient, client);
        answerReady.put(idClient, true);
        idClient++;
    }

    public String getNickname(int playerId) {
        return nicknames.get(playerId);
    }

    public int getNumber(Socket client){
        int x = -1;
        for (Map.Entry<Integer, Socket> entry : socketClients.entrySet())
            if (entry.getValue() == client)
                x = entry.getKey();
        return x;
    }

    public int getNumber(RmiGame client) {
        for (Map.Entry<Integer, RmiGame> entry : rmiClients.entrySet())
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
            //communication = new RmiCommunication(serializedMessage, rmiClients.get(number), rmiServer, number, this);
            //new Thread(communication).start();
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
                /*if (rmiClients.containsKey(number)) {
                    try {
                        //check if rmi client is still connected;
                    } catch (RemoteException e) {
                        System.out.println("Unable to reach client. " + e.getMessage());
                        //rmi client removed from rmi server;
                        return "ERROR";
                    }
                }*/
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

        while (isUsernameTaken(((UsernameAnswer) m).getS(), idClient)){
            answer = sendMessageAndWaitForAnswer(number, new NotValidUsernameError());
            m = converter.convertFromJSON(answer);
        }
        String usrn = ((UsernameAnswer) m).getS();
        return usrn;
    }

    protected void clientLogin(int number){
        String username = login(number);

        if(firstPlayer){
            firstPlayer = false;
            String answer = sendMessageAndWaitForAnswer(number, new FirstPlayerMessage());
            Message m = converter.convertFromJSON(answer);
            activeMatch = new Controller(((NumberOfPlayersAnswer) m).getNum());
            this.numberOfPlayers = ((NumberOfPlayersAnswer) m).getNum();
        }
        lobby.put(number, username);
        notifyNewConnection(this.numberOfPlayers);

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
     * @return true if the username has already been taken, flase otherwise
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

    @Override
    public void run() {
        socketServer = new SocketServer(this, this.socketPort);
        try {
            rmiServer = new RmiServer(this, this.rmiPort);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        new Thread(socketServer).start();
        new Thread(rmiServer).start();
    }
}