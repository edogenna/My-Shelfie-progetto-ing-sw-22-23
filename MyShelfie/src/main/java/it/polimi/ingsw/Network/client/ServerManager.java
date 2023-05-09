package it.polimi.ingsw.Network.client;

import it.polimi.ingsw.Network.server.RmiServer;
import it.polimi.ingsw.Network.server.SocketServer;
import it.polimi.ingsw.controller.Controller;

import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerManager implements Runnable{

    public static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;
    private static final int DEFAULT_BOARD = 1;
    private final int socketPort;
    private final int rmiPort;
    private final Map<Integer, Socket> socketClients = new HashMap<>();
    private final Map<Integer, RmiClientInterface> rmiClients = new HashMap<>();
    private final Map<Integer, String> answers = new HashMap<>();
    private final Map<Integer, Boolean> answerReady = new HashMap<>();
    private final Map<Integer, String> lobby = new HashMap<>();
    private final List<Integer> awayFromKeyboardOrDisconnected = new ArrayList<>();
    private final Map<Integer, String> nicknames = new HashMap<>();
//    private final Map<Integer, Controller> activeMatches = new HashMap<>();
    private SocketServer socketServer;
    private RmiServer rmiServer;
    private int idClient = 12345;
    private int chosenBoard = 0;

    public ServerManager(int secondsAfterThirdConnection, int secondsDuringTurn, int socketPort, int rmiPort, int skulls) {
        this.socketPort = socketPort;
        this.rmiPort = rmiPort;
    }

    void addClient(Socket client) {
        socketClients.put(idClient, client);
        answerReady.put(idClient, true);
        idClient++;
    }


    public String getNickname(int playerId) {
        return nicknames.get(playerId);
    }


    @Override
    public void run() {
        socketServer = new SocketServer(this, socketPort);
        try {
            rmiServer = new RmiServer(this, rmiPort);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        new Thread(socketServer).start();
        new Thread(rmiServer).start();
    }
}
