package it.polimi.ingsw.controller.messages;

public class WaitingMessage extends Message{

    String s = "Waiting for more players...";

    public WaitingMessage() {
        super("Waiting");
    }

    public String getS() {
        return s;
    }
}
