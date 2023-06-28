package it.polimi.ingsw.Network.messages;

/**
 * Message to inform a player that he has been disconnected from the game because of a timeout
 */
public final class TimeoutMessage extends Message{
    public TimeoutMessage() {
        super("Timeout");
    }
    private final String s = "There is no time left. You are now disconnecting from the game.\n" +
                                "You can re-join this game by reconnecting to the server.";

    public String getS() {
        return s;
    }
}