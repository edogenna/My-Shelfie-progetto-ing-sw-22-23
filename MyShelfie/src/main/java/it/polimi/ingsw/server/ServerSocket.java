package it.polimi.ingsw.server;

import it.polimi.ingsw.server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.*;
import java.util.logging.Logger;

public class ServerSocket {
    public class SocketServer implements Runnable {
        private final int port;
        private final ExecutorService executorService;
        private final Server server;
        private boolean active;
    }
    public SocketServer(int , Server server) {
        this.server = server;
        //TODO: rethink this port
        //this.port = port;
        //executorService = Executors.newCachedThreadPool();
        //active = true;
    }

    public void setActive(boolean value) {
        //active = value;
    }
}
