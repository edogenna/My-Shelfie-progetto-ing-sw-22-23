package it.polimi.ingsw.Network.client;

import it.polimi.ingsw.Network.messages.Message;

import java.io.IOException;

public interface RmiClientInterface {

    public void printMessage(String message) throws IOException;
}
