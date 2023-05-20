package it.polimi.ingsw.Network.messages.Answers;

import it.polimi.ingsw.Network.messages.Message;

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