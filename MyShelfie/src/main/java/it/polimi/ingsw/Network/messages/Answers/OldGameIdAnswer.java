package it.polimi.ingsw.Network.messages.Answers;

import it.polimi.ingsw.Network.messages.Message;

public final class OldGameIdAnswer extends Message {
    private int id;
    private final String s = "Game id is: " + id;

    public OldGameIdAnswer(String id) {
        super("OldGameIdAnswer");
        this.id = Integer.parseInt(id);
    }

    public String getS() {
        return s;
    }
}
