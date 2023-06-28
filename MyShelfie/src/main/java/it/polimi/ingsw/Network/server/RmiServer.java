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

    /**
     * This method allows to register a client to the server
     * @param client the client to be registered
     * @throws RemoteException if there are problems with the connection
     */
    @Override
    public void registry(RmiClientInterface client) throws RemoteException {
        serverManager.addClient(client);
        int number = serverManager.getNumber(client);
        client.setId(number);
        System.out.println("User " + number + " connected on the RmiServer.");
        new Thread(new ClientManager(serverManager, number)).start();
    }

    /**
     * This method allows to unregister a client from the server
     * @param client the client to be unregistered
     */
    public void unregister(RmiClientInterface client) {
        serverManager.removeClient(client);
    }

    /**
     * This method allows to test the aliveness of the server
     * @return true if the server is alive, false otherwise
     */
    public synchronized boolean testAliveness() {
        return true;
    }

    /**
     * This method allows to send a message to a client and get an answer
     * @param rmiClient the client to which the message is sent
     * @param message the message to be sent
     * @return the answer of the client
     */
    String sendMessageAndGetAnswer(RmiClientInterface rmiClient, String message){
        try {
            try {
                return rmiClient.sendMessageAndGetAnswer(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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

    /**
     * This method allows to test the connection with the server
     * @param x a number to test the connection
     * @return true if the connection is active, false otherwise
     */
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
