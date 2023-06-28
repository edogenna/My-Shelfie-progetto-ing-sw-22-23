package it.polimi.ingsw.Network.messages;

/**
 * Message the server sends to clients when the game is about start
 */
public final class StartingGameMessage extends Message {

    private final String s = "Starting game...";
    public StartingGameMessage(){
        super("StartingGame");
    }
    public String getS() {
        return s;
    }
}