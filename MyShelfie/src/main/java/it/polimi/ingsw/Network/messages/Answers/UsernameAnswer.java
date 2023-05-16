package it.polimi.ingsw.Network.messages.Answers;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message to send to the server containing the username chosen by the user
 * @author Alessandro Fornara
 */
public final class UsernameAnswer extends Message {
    private final String s;
    public UsernameAnswer(String usrn){
        super("UsernameAnswer");
        this.s = usrn;
    }
    public String getString() {
        return s;
    }
}
