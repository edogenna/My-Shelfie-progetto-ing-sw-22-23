package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that is sent by the server to a client when the chosen position of a move doesn't have a tile (is blank)
 */
public final class EmptyPositionError extends Message {
    public EmptyPositionError(){
        super("EmptyPosition");
        this.s = "You have selected an empty position.";
    }
    private final String s;

    public String getS() {
        return s;
    }
}
