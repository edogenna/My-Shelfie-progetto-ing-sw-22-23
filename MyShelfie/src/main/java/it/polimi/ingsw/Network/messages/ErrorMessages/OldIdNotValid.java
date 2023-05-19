package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

public class OldIdNotValid extends Message {
    public OldIdNotValid() {
        super("OldIdNotValid");
    }
    private final String s = "this ID is not present! The match could be finished.";

    public String getS() {
        return s;
    }
}
