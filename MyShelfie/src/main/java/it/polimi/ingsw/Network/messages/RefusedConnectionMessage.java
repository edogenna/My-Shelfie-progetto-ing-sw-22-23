package it.polimi.ingsw.Network.messages;

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
