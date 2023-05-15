package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.client.RmiClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiServerInterface extends Remote {
    public void registry(RmiClientInterface client) throws RemoteException;
}