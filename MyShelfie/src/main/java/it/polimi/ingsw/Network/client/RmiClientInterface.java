package it.polimi.ingsw.Network.client;

import it.polimi.ingsw.Network.messages.Message;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiClientInterface extends Remote {

    String sendMessageAndGetAnswer(String message) throws RemoteException;
    public void printMessage(String message) throws IOException, RemoteException;
}
