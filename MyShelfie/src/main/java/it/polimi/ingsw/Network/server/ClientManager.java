package it.polimi.ingsw.Network.server;

import java.io.IOException;

/**
 * This class allows to manage parallel client enrollments.
 * @author Alessandro Fornara
 */

public class ClientManager implements Runnable {

    private final ServerManager serverManager;
    private final int number;

    /**
     * Constructor of the class
     * @param serverManager server manager
     * @param number number of the client
     */
    ClientManager(ServerManager serverManager, int number) {
        this.serverManager = serverManager;
        this.number = number;
    }

    /**
     * This method adds the client to the log of the server
     * @throws IOException if there are problems with the connection
     * @throws InterruptedException if the thread is interrupted
     * @throws RuntimeException if there are problems with the connection
     */
    @Override
    public void run() {
        try {
            serverManager.addClientToLog(this.number);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}