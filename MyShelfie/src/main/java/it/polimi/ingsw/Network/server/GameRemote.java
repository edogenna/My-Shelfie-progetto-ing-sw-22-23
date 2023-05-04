package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.messages.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameRemote extends UnicastRemoteObject implements RmiGame {

    protected GameRemote() throws RemoteException {

    }

    public void update(Message message) {

    }
    public void sendMessage(Message message){

    }
}
