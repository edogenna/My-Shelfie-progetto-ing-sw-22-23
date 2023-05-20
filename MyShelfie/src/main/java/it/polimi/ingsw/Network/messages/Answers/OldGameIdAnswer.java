package it.polimi.ingsw.Network.messages.Answers;

import it.polimi.ingsw.Network.messages.Message;

public final class OldGameIdAnswer extends Message {
    private int id;
    private final String s = "Game id is: " + id;

    public OldGameIdAnswer(int id) {
        super("OldGameIdAnswer");
        this.id = id;
    }

    public String getS() {
        return s;
    }
}
