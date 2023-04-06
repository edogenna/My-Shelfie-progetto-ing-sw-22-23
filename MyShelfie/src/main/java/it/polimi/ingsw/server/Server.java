package it.polimi.ingsw.server;

import java.util.*;

//TODO: check everything

public class Server {
    private final ServerSocket socketServer;

    //metodo che stacca le connessioni dal server quando legge quit
    public void quitter() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.next().equalsIgnoreCase("QUIT")) {
                getSocketServer().setActive(false);
                System.exit(0);
                break;
            }
        }
    }


    public Server() {
        //TODO: change port utilization
        int port = 0;
        socketServer = new ServerSocket(port, this);
        Thread thread = new Thread(this::quitter);
        thread.start();
    }

    public synchronized ServerSocket getSocketServer() {
        return socketServer;
    }
}
