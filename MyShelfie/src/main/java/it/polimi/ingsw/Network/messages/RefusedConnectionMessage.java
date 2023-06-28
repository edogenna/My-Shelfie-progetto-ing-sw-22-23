package it.polimi.ingsw.Network.messages;

/**
 * Message to inform the user that the connection has been refused because a game has already started
 */
public final class RefusedConnectionMessage extends Message{
    public RefusedConnectionMessage() {
        super("RefusedConnection");
        this.s = "A game has already started, client will close";
    }
    private final String s;

    public String getS() {
        return s;
    }
}
