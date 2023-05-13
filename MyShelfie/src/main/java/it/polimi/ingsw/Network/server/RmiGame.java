package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.client.RmiClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiGame extends Remote {
    public String notifyMyConnection() throws RemoteException;
    public String notifyOtherConnections() throws RemoteException;
    //public boolean isUsernameTaken(String nickname) throws RemoteException;
    public int getNumberOfActivePlayers() throws RemoteException;
    public int getNumberOfPlayers() throws RemoteException;
    public void registry(RmiClientInterface client) throws RemoteException;
}