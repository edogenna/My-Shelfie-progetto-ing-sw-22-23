package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

public class EmptyPositionError extends Message {
    public EmptyPositionError(){
        super("EmptyPosition");
    }
    private String s="You have selected an empty position.";

    public String getS() {
        return s;
    }
}
