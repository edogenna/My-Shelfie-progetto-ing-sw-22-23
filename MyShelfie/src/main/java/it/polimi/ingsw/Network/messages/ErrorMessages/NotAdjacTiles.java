package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

public class NotAdjacTiles extends Message {
    public NotAdjacTiles(){
        super("NotAdjTiles");
    }
    private String s = "The tiles aren’t adjacent.";

    public String getS() {
        return s;
    }
}
