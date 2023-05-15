package it.polimi.ingsw.Network.messages;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Card;

/**
 * Message to send generic information about the game to the user and ask for a player's move
 * @author Alessandro Fornara
 */
public final class MoveMessage extends Message {

    private final String activePlayerUsername;

    private final String s = "it's your turn." +
                             "Please insert which tiles you would like to remove from the board and the column of your bookshelf you want to put your tiles in\n" +
                             "the first one will go to the first position available on the bottom of the column and the others will pile up\n" +
                             "Example: x1,y1,x2,y2,x3,y3,column\n" +
                             "Example: a,3,a,4,a,5,column";

    public MoveMessage(String username) {
        super("MoveMessage");
        this.activePlayerUsername = username;
    }

    public String getActivePlayerUsername() {
        return activePlayerUsername;
    }

    public String getS() {
        return s;
    }
}
