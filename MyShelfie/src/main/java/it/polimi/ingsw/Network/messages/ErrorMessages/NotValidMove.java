package it.polimi.ingsw.Network.messages.ErrorMessages;

import it.polimi.ingsw.Network.messages.Message;

public class NotValidMove extends Message {

    private final String s = "Your move can't be done. Please choose another move!";

    public NotValidMove() {
        super("NotValidMove");
    }

}
