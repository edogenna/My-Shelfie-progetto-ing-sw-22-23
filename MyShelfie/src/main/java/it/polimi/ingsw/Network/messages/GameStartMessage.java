package it.polimi.ingsw.Network.messages;

public final class GameStartMessage extends Message {

    private final String s = "Starting game...";
    public GameStartMessage(){
        super("GameStartMessage");
    }
    public String getS() {
        return s;
    }
}
