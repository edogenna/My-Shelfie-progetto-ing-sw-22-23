package it.polimi.ingsw.Network.messages;

public final class ReconnectionMessage extends Message{
    private final String s = "Would you like to start a new game, or re-join your last game?";

    public ReconnectionMessage(){
        super("Reconnect");
    }
    public String getS() {
        return s;
    }
}
