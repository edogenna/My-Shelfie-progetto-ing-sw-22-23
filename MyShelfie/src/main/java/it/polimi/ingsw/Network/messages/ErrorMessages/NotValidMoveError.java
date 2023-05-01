package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that is sent by the server to a client when the move the player wants to make is not valid
 * @author Samuele Pietro Galli
 */
public final class NotValidMoveError extends Message {

    private final String s = "Your move can't be done. Please choose not less than 1 tile and not more than 3!";

    public NotValidMoveError() {
        super("NotValidMove");
    }

    public String getS() {
        return s;
    }
}
