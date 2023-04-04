package it.polimi.ingsw.server;

import it.polimi.ingsw.server.ServerSocket;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Server {
    private final ServerSocket socketServer;
    private final int port = 0;
    public Server() {
        socketServer = new ServerSocket (port, this);
        //clientToConnection = new HashMap<>();
        //totalPlayers = -1;
        Thread thread = new Thread(this::quitter);
        thread.start();
    }
}
