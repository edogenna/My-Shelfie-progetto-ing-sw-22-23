package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

public final class NotValidMoveError extends Message {

    private final String s = "Your move can't be done. Please choose not less than 1 tile and not more than 3!";

    public NotValidMoveError() {
        super("NotValidMove");
    }

    public String getS() {
        return s;
    }
}
