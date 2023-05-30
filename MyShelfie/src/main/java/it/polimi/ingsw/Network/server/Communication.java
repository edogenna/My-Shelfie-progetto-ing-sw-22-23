package it.polimi.ingsw.Network.server;

/**
 * This class contains methods used by the server to communicate with clients
 * @author Alessandro Fornara
 * @author Donato Fiore
 */
public class Communication implements Runnable{
    final String message;
    private final int num;
    private final ServerManager serverManager;
    boolean timeExceeded;

    Communication(int num, ServerManager serverManager, String message) {
        this.num = num;
        this.serverManager = serverManager;
        this.message = message;
        this.timeExceeded = false;
    }

    void showAndSetAnswer(String answer) {
        serverManager.setAnswer(num, answer);
        System.out.println("User " + num + ": " + answer);
    }

    void setTimeExceeded() {
        this.timeExceeded = true;
    }

    @Override
    public void run() {
        //empty, because we want so
    }
}
