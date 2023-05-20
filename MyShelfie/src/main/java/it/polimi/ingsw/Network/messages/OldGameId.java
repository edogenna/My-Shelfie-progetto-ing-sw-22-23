package it.polimi.ingsw.Network.messages;

public final class OldGameId extends Message{
    public OldGameId() {
        super("OldGameId");
    }
    private final String s = "Please insert your previous game ID: ";
    public String getS() {
        return s;
    }
}
