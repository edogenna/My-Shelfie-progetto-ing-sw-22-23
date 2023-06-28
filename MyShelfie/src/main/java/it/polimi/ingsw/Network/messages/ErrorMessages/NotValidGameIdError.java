package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that is sent by the server to a client when the chosen GameId is not valid
 */
public final class NotValidGameIdError extends Message {

    private final String s = "Please select another GameId.";
    public NotValidGameIdError() {
        super("NotValidId");
    }

    public String getS() {
        return s;
    }
}
