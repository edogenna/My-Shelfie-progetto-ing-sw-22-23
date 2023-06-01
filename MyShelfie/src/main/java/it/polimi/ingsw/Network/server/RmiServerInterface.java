package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.client.RmiClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This class is the interface of a rmi server
 * @author Donato Fiore
 */
public interface RmiServerInterface extends Remote {
    void registry(RmiClientInterface client) throws RemoteException;

    boolean testServerConnection(int x) throws RemoteException;
}