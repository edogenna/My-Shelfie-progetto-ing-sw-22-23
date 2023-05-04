package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that is sent by the server to a client when the chosen column of a move is not valid
 * @author Samuele Pietro Galli
 */
public final class NotEnoughSpaceColumnError extends Message {
    private final String s = "You don’t have enough space in the column for that many tiles";
    public NotEnoughSpaceColumnError(){
        super("NotEnoughSpaceColumn");
    }

    public String getS() {
        return s;
    }
}
