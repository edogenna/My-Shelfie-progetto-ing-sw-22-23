package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that is sent by the server to a client when the chosen column of a move does not exist
 * @author Samuele Pietro Galli
 */
public final class InvalidColumnError extends Message {
    private final String s;

    public InvalidColumnError(){
        super("InvalidColumn");
        this.s = "You chose a column that does not exist";
    }

    public String getS() {
        return s;
    }
}
