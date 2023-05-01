package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

public class InvalidColumnError extends Message {
    private String s= "You chose a column that does not exist";

    public InvalidColumnError(){
        super("InvalidColumn");
    }

    public String getS() {
        return s;
    }
}
