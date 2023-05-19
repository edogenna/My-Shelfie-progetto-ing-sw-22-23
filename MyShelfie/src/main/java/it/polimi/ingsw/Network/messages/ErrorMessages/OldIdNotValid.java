package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

public class OldIdNotValid extends Message {
    public OldIdNotValid() {
        super("OldIdNotValid");
    }
    private final String s = "This ID isn't correct! The match could be finished.";

    public String getS() {
        return s;
    }
}
