package it.polimi.ingsw.Network.messages;

public final class MoveMessage extends Message{
    private final String s;
    public MoveMessage(String s) {
        super("Move");
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
