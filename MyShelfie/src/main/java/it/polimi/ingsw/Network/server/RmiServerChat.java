package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Constant;
import it.polimi.ingsw.Network.client.RmiClientInterface;
import it.polimi.ingsw.Network.messages.ChooseUsernameMessage;
import it.polimi.ingsw.Network.messages.Converter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServerChat implements RmiServerInterface, Runnable{
    private int rmiPort;
    private ServerManager serverManager;

    public RmiServerChat(ServerManager serverManager, int port) throws RemoteException {
        this.serverManager = serverManager;
        this.rmiPort = port;
    }

    @Override
    public void registry(RmiClientInterface client) throws RemoteException {
        System.out.println("registry method!");
        serverManager.addClient(client);
        int number = serverManager.getNumber(client);
        System.out.println("User " + number + " connected on the RmiServer.");
        new Thread(new ClientManager(serverManager, number)).start();
    }

    public synchronized boolean testAliveness() {
        return true;
    }

    String sendMessageAndGetAnswer(RmiClientInterface rmiClient, String message){
        try {
            return rmiClient.sendMessageAndGetAnswer(message);
        } catch (RemoteException e) {
            return "Impossible connection with the rmi client" + e.getMessage();
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
            System.out.println("RmiServer started on port: " + Constant.PORT_RMI_CHAT);
        } catch (UnknownHostException | RemoteException | AlreadyBoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
