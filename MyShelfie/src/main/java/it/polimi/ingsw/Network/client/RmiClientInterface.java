package it.polimi.ingsw.Network.client;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiClientInterface extends Remote {

    String sendMessageAndGetAnswer(String message) throws IOException;

    boolean testAliveness() throws RemoteException;
}
