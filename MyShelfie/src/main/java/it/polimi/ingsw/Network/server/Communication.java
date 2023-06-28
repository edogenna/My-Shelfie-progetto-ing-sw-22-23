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
    //boolean timeExceeded;

    /**
     * Constructor of the class
     * @param num number of the client
     * @param serverManager server manager
     * @param message message to be sent
     */
    Communication(int num, ServerManager serverManager, String message) {
        this.num = num;
        this.serverManager = serverManager;
        this.message = message;
        //this.timeExceeded = false;
    }

    /**
     * This method shows the answer of the server and sets it
     * @param answer answer of the server
     */
    void showAndSetAnswer(String answer) {
        serverManager.setAnswer(num, answer);
        System.out.println("User " + num + ": " + answer);
    }

    /*void setTimeExceeded() {
        this.timeExceeded = true;
    }*/

    @Override
    public void run() {
        //empty, because we want so
    }
}
