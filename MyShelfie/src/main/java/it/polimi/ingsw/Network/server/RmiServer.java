package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Constants;
import it.polimi.ingsw.Network.client.RmiClientInterface;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * This class is an implementation of a server using rmi technology
 * @author Donato Fiore
 */
public class RmiServer implements RmiServerInterface, Runnable{
    private int rmiPort;
    private ServerManager serverManager;

    public RmiServer(ServerManager serverManager, int port) throws RemoteException {
        this.serverManager = serverManager;
        this.rmiPort = port;
    }

    @Override
    public void registry(RmiClientInterface client) throws RemoteException {
        serverManager.addClient(client);
        int number = serverManager.getNumber(client);
        client.setId(number);
        System.out.println("User " + number + " connected on the RmiServer.");
        new Thread(new ClientManager(serverManager, number)).start();
    }

    public void unregister(RmiClientInterface client) {
        serverManager.removeClient(client);
    }

    public synchronized boolean testAliveness() {
        return true;
    }

    String sendMessageAndGetAnswer(RmiClientInterface rmiClient, String message){
        try {
            return rmiClient.sendMessageAndGetAnswer(message);
        } catch (RemoteException e) {
            return "Disconnect";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());
            RmiServerInterface remoteObject = (RmiServerInterface) UnicastRemoteObject.exportObject(this, 0);
            LocateRegistry.createRegistry(this.rmiPort);
            LocateRegistry.getRegistry(this.rmiPort).bind("MyShelfie", remoteObject);
            System.out.println("RmiServer started on port: " + Constants.PORT_RMI_GAME);
        } catch (UnknownHostException | RemoteException | AlreadyBoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public synchronized boolean testServerConnection(int x){
        boolean stop;

        if(!serverManager.isDisconnected(x))
            return false;

        stop = serverManager.stopRmiClient();
        serverManager.setTimeExceededPt2();
        return stop;
    }
}
