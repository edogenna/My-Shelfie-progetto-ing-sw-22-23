package it.polimi.ingsw.Network.messages;

/**
 * Message to inform the user that a client has disconnected
 */
public class UserDisconnection extends Message{
    private final String s;

    public UserDisconnection(String username){
        super("Disconnection");
        s = "'" + username + "' user logged out";
    }
    public String getS() {
        return s;
    }
}
