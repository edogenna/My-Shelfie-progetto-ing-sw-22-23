package it.polimi.ingsw.Network.messages;

public class NotValidUsernameError extends Message{

    private final String s = "Select another username, this has been already selected.";
    public NotValidUsernameError() {
        super("NotValidUsername");
    }

    public String getS() {
        return s;
    }
}
