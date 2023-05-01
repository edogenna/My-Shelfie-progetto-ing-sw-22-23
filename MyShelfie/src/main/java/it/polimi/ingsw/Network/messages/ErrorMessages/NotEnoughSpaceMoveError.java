package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

public class NotEnoughSpaceMoveError extends Message {
    private final String s = "You don’t have enough space in the column for that many tiles";
    public NotEnoughSpaceMoveError(){
        super("NotEnoughSpaceMove");
    }

    public String getS() {
        return s;
    }
}
