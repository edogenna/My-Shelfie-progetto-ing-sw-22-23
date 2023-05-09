package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.messages.ChooseUsernameMessage;
import it.polimi.ingsw.Network.messages.Converter;
import it.polimi.ingsw.Network.messages.LobbyMessage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer extends UnicastRemoteObject implements RmiGame{

    public RmiServer() throws RemoteException {
        super();
    }
    private void startRMIServer() {
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
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Donato Fiore
     * it prints in the server when an rmi client has connected
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
}
