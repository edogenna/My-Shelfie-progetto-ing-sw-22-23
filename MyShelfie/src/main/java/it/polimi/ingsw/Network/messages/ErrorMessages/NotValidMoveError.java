package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that is sent by the server to a client when the move the player wants to make is not valid
 */
public final class NotValidMoveError extends Message {

    private final String s;

    public NotValidMoveError() {
        super("NotValidMove");
        this.s = "Your move can't be done. Please write the input with the correct format";
    }

    public String getS() {
        return s;
    }
}
