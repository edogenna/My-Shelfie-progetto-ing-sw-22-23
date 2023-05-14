package it.polimi.ingsw.Network.server;

/**
 * This class allows to manage parallel client enrollments.
 */

public class ClientManager implements Runnable {

    private final ServerManager serverManager;
    private final int number;

    ClientManager(ServerManager serverManager, int number) {
        this.serverManager = serverManager;
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("ClientManager run");
        serverManager.clientLogin(this.number);
    }
}