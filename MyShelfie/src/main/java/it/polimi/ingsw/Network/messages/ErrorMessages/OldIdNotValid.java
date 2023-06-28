package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that is sent by the server to a client when the chosen GameId is not valid
 */
public class OldIdNotValid extends Message {
    public OldIdNotValid() {
        super("OldIdNotValid");
    }
    private final String s = "this ID is not present! The match could be finished.";

    public String getS() {
        return s;
    }
}
