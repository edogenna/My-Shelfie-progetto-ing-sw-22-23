package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that is sent by the server to a client when the chosen number of players is not valid
 * @author Alessandro Fornara
 */
public final class NotValidNumberofPlayersMessage extends Message {

    private final String s = "Select another number, it has to be between 2 and 4.";
    public NotValidNumberofPlayersMessage() {
        super("NotValidNumber");
    }

    public String getS() {
        return s;
    }
}
