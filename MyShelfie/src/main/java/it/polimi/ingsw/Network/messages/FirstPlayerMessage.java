package it.polimi.ingsw.Network.messages;

public final class FirstPlayerMessage extends Message {
    private final String s = "You are the first player to connect, please submit the number of players for the next game:";
    public FirstPlayerMessage() {
        super("FirstPlayer");
    }

    public String getS() {
        return s;
    }
}
