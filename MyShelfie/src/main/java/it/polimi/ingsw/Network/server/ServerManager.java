package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.client.RmiClientInterface;

import java.net.Socket;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ServerManager implements Runnable{

    public static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;
    private static final int DEFAULT_BOARD = 1;
    private final int socketPort;
    private final int rmiPort;
    private final Map<Integer, Socket> socketClients = new HashMap<>();
    private final Map<Integer, RmiGame> rmiClients = new HashMap<>();
    private final Map<Integer, String> answers = new HashMap<>();
    private final Map<Integer, Boolean> answerReady = new HashMap<>();
    private final Map<Integer, String> lobby = new HashMap<>();
    private final Map<Integer, String> nicknames = new HashMap<>();
    //    private final Map<Integer, Controller> activeMatches = new HashMap<>();
    private SocketServer socketServer;
    private RmiServer rmiServer;
    private int idClient = 0;
    private String[] usernames;
    //    private int chosenBoard = 0;

    public ServerManager(int socketPort, int rmiPort) {
        this.socketPort = socketPort;
        this.rmiPort = rmiPort;
        this.usernames = new String[4];
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