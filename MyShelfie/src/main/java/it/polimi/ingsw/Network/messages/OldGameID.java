package it.polimi.ingsw.Network.messages;

public final class OldGameID extends Message{
    public OldGameID() {
        super("OldGameId");
    }
    private final String s = "Please insert your previous game id: ";
    public String getS() {
        return s;
    }
}
