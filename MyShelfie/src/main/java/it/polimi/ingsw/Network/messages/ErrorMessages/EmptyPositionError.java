package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

public final class EmptyPositionError extends Message {
    public EmptyPositionError(){
        super("EmptyPosition");
    }
    private final String s="You have selected an empty position.";

    public String getS() {
        return s;
    }
}
