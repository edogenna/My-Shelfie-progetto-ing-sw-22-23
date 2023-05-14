package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.client.RmiClientInterface;
import it.polimi.ingsw.Network.messages.ChooseUsernameMessage;
import it.polimi.ingsw.Network.messages.Converter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer implements RmiServerInterface, Runnable{
    private int rmiPort;
    private ServerManager serverManager;

    public RmiServer(ServerManager serverManager, int port) throws RemoteException {
        this.serverManager = serverManager;
        this.rmiPort = port;
    }

    public void startRMIServer() {
        System.out.println("Starting RMI");
        try {
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("Binding server implementation to registry");
        try {
            Naming.rebind("MyShelfie", this);
            System.out.println("Server RMI started");
            System.out.println();
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Donato Fiore
     * it prints in the server when a rmi client has connected
     * */
    @Override
    public String notifyMyConnection(){
        System.out.println("Rmi User connected");
        //ClientInformation inf = new ClientInformation(null, null, null, connectedClients.size()-1);
        //connectedClients.add(inf);
        //activePlayers = connectedClients.size();
//        sendMessageToObservers(new LobbyMessage(this.activePlayers, numberOfPlayers));
//        sendMessageToObservers(new WaitingMessage());
//        return new Converter().convertToJSON(new LobbyMessage(connectedClients.size(), numberOfPlayers));
        return new Converter().convertToJSON(new ChooseUsernameMessage());
    }

    @Override
    public String notifyOtherConnections(){
        System.out.println("user connected");
        //return new Converter().convertToJSON(new LobbyMessage(connectedClients.size(), numberOfPlayers));
        return null;
    }

    @Override
    public int getNumberOfActivePlayers(){
        //return this.activePlayers;
        return 0;
    }

    @Override
    public int getNumberOfPlayers(){
        //return this.numberOfPlayers;
        return 0;
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

    String sendMessageAndGetAnswer(RmiClientInterface addressee, String message){
        try {
            return addressee.sendMessageAndGetAnswer(message);
        } catch (RemoteException e) {
            return "Impossible connection with the rmi client" + e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        System.out.println("RmiServer run");
        try {
            System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());
            RmiServerInterface remoteObject = (RmiServerInterface) UnicastRemoteObject.exportObject(this, 0);
            LocateRegistry.createRegistry(this.rmiPort);
            LocateRegistry.getRegistry(this.rmiPort).bind("MyShelfie", remoteObject);
            System.out.println("RmiServer started.");
        } catch (UnknownHostException | RemoteException | AlreadyBoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
