package it.polimi.ingsw.Network.messages;

public final class StartingGameMessage extends Message {

    private final String s = "Starting game...";
    public StartingGameMessage(){
        super("StartingGame");
    }
    public String getS() {
        return s;
    }
}