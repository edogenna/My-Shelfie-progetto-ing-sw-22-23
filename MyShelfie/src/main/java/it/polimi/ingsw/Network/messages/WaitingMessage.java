package it.polimi.ingsw.Network.messages;

public class WaitingMessage extends Message{

    String s = "Waiting for more players...";

    public WaitingMessage() {
        super("Waiting");
    }

    public String getS() {
        return s;
    }
}
