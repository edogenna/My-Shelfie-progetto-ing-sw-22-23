package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.client.RmiClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This class is the interface of a rmi server
 * @author Donato Fiore
 */
public interface RmiServerInterface extends Remote {
    /**
     * This method is used to register a client to the server
     * @param client the client to be registered
     * @throws RemoteException if there are problems with the connection
     */
    void registry(RmiClientInterface client) throws RemoteException;


    /**
     * This method allows to test the connection with the server
     * @param x a number
     * @return true if the server is alive, false otherwise
     * @throws RemoteException if there are problems with the connection
     */
    boolean testServerConnection(int x) throws RemoteException;
}