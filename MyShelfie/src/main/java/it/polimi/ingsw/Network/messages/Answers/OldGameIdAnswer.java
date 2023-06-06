package it.polimi.ingsw.Network.messages.Answers;

import it.polimi.ingsw.Network.messages.Message;

public final class OldGameIdAnswer extends Message {
    private int id;
    public OldGameIdAnswer(String id) {
        super("OldGameIdAnswer");
        this.id = Integer.parseInt(id);
    }

    public int getId() {
        return id;
    }
}
