package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that is sent by the server to a client when the chosen tiles of a move are not adjacent
 */
public final class NotAdjacTiles extends Message {
    public NotAdjacTiles(){
        super("NotAdjTiles");
        this.s = "The tiles aren’t adjacent.";
    }
    private final String s;

    public String getS() {
        return s;
    }
}
