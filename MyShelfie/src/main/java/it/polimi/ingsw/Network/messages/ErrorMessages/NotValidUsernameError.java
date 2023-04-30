package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

public final class NotValidUsernameError extends Message {

    private final String s = "Select another username, this has been already selected.";
    public NotValidUsernameError() {
        super("NotValidUsername");
    }

    public String getS() {
        return s;
    }
}
