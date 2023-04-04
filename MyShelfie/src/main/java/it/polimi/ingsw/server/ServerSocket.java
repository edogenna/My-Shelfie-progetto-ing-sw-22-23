package it.polimi.ingsw.server;

import java.util.concurrent.ExecutorService;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.logging.Level;


public class SocketServer implements Runnable {
    private final int port;
    private final ExecutorService executorService;
    private final Server server;
    private volatile boolean active;
    //private final Logger logger = Logger.getLogger(getClass().getName());


    public SocketServer(int port, Server server) {
        this.server = server;
        this.port = port;
        executorService = Executors.newCachedThreadPool();
        active = true;
    }


    public void setActive(boolean value) {
        active = value;
    }