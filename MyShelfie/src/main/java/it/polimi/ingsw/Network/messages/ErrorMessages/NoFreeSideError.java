package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

public final class NoFreeSideError extends Message {

    private final String s;

    public NoFreeSideError(){
        super("NoFreeSide");
        this.s = "One of the tiles you have selected doesn't have a free side";
    }

    public String getS() {
        return s;
    }
}
