package it.polimi.ingsw.Network.messages;

/**
 * Message to notify clients that the server is waiting for more players
 */
public class WaitingMessage extends Message{

    String s = "Waiting for more players...";

    public WaitingMessage() {
        super("Waiting");
    }

    public String getS() {
        return s;
    }
}
