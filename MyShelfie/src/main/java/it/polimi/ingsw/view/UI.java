package it.polimi.ingsw.view;

import it.polimi.ingsw.Network.messages.Message;

import java.io.IOException;

public abstract class UI {

    public abstract String actionHandler(Message m) throws IOException;
}
