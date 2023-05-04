package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that is sent by the server to a client when the chosen tiles of a move are not adjacent
 * @author Samuele Pietro Galli
 */
public final class NotAdjacTiles extends Message {
    public NotAdjacTiles(){
        super("NotAdjTiles");
    }
    private final String s = "The tiles aren’t adjacent.";

    public String getS() {
        return s;
    }
}
