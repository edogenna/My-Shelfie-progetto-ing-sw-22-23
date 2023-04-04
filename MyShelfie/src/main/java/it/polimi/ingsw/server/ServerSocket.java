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

    /**
     * Constructor SocketServer creates a new SocketServer instance.
     *
     * @param port of type int - the port on which server will listen.
     * @param server of type Server - the main server object.
     */
    public SocketServer(int port, Server server) {
        this.server = server;
        this.port = port;
        executorService = Executors.newCachedThreadPool();
        active = true;
    }

    /**
     * Method setActive sets the active connection field of this SocketServer object.
     *
     * @param value the active connection value of the socket.
     */
    public void setActive(boolean value) {
        active = value;
    }