package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that is sent by the server to a client when the chosen position of a move doesn't have a tile (is blank)
 * @author Samuele Pietro Galli
 */
public final class EmptyPositionError extends Message {
    public EmptyPositionError(){
        super("EmptyPosition");
    }
    private final String s="You have selected an empty position.";

    public String getS() {
        return s;
    }
}
