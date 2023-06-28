package it.polimi.ingsw.Network.messages.Answers;

import it.polimi.ingsw.Network.messages.Message;

/**
 * This message is used by the client to tell the server if he wants to reconnect to the game or create a new one
 */
public final class ReconnectionAnswer extends Message {
    private final String s;
    public ReconnectionAnswer(String answer){
        super("ReconnectionAnswer");
        this.s = answer;
    }
    public String getString() {
        return s;
    }
}