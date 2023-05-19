package it.polimi.ingsw.Network.messages;

public final class TurnTimeOut extends Message{
    public TurnTimeOut() {
        super("TurnTimeOut");
    }
    private final String s = "Time's up. You missed your turn, next time you will be disconnected.";

    public String getS() {
        return s;
    }
}
