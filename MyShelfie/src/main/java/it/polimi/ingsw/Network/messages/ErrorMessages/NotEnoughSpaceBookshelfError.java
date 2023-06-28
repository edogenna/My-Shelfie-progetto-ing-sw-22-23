package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that is sent by the server to a client when the chosen tiles of a move are not adjacent
 * @author Alessandro Fornara
 */
public final class NotEnoughSpaceBookshelfError extends Message {

    private final String s;
    public NotEnoughSpaceBookshelfError(){
        super("NotEnoughSpaceBookshelf");
        s = "You don't have enough space in the bookshelf for that many tiles";
    }

    public String getS() {
        return s;
    }
}
