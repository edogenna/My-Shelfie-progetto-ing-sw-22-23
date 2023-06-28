package it.polimi.ingsw.Network.messages.Answers;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that contains a move that the user wants to make, it is sent to the server
 */
public final class MoveAnswer extends Message {
    private final String s;
    public MoveAnswer(String s) {
        super("Move");
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
