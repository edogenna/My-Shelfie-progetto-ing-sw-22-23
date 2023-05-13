package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.messages.Message;

public class Communication implements Runnable{

    final String message;
    private final int num;
    private final ServerManager serverManager;

    Communication(int num, ServerManager serverManager, String message) {
        this.num = num;
        this.serverManager = serverManager;
        this.message = message;
    }

    void showAndSetAnswer(String answer) {
        serverManager.setAnswer(num, answer);
        System.out.println("User " + num + ": " + answer);
    }

    @Override
    public void run() {

    }
}
