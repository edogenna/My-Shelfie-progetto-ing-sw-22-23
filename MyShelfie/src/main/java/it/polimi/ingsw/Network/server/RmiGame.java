package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.messages.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiGame extends Remote {
    public String notifyMyConnection() throws RemoteException;
    public String notifyConnection() throws RemoteException;
    public int getNumberOfActivePlayers() throws RemoteException;
    public int getNumberOfPlayers() throws RemoteException;
}