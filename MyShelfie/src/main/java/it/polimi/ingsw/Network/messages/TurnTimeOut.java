package it.polimi.ingsw.Network.messages;

public final class TurnTimeOut extends Message{
    public TurnTimeOut() {
        super("TurnTimeOut");
        this.s = "Time's up. You missed your turn, next time you will be disconnected.";
    }
    private final String s;

    public String getS() {
        return s;
    }
}
