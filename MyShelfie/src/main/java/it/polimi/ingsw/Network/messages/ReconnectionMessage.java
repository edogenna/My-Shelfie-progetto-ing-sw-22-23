package it.polimi.ingsw.Network.messages;

/**
 * Message to ask if a plater wants to reconnect to a previous game or start a new one
 * @author Samuele Pietro Galli
 */
public final class ReconnectionMessage extends Message{
    private final String s = "Would you like to start a new game, or re-join your last game? If you want to re-join write: 'Reconnect', enter otherwise";

    public ReconnectionMessage(){
        super("Reconnect");
    }
    public String getS() {
        return s;
    }
}
