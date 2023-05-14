package it.polimi.ingsw.Network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.Network.messages.Converter;
import it.polimi.ingsw.Network.messages.Message;
import it.polimi.ingsw.Network.messages.WaitingMessage;
import it.polimi.ingsw.Network.server.RmiServerInterface;
import it.polimi.ingsw.view.CliView;

public class RmiClient extends Client implements RmiClientInterface, Runnable{

    private BufferedReader stdIn;
    private CliView cliViewRmi;
    private Converter c;
    private RmiServerInterface rmiServerInterface;

    public RmiClient(){
        this.stdIn = null;
        this.cliViewRmi = null;
        this.c = null;
    }


    public void startRMIClient() throws IOException, NotBoundException {
        int x;

        Registry registry = LocateRegistry.getRegistry();
        String remoteObjectName = "MyShelfie";
        RmiServerInterface remoteObject = (RmiServerInterface) registry.lookup(remoteObjectName);

        this.stdIn = new BufferedReader(new InputStreamReader(System.in));
        this.cliViewRmi = new CliView(null, null, stdIn, remoteObject);
        this.c = new Converter();

        String stringMessage = remoteObject.notifyMyConnection();
        Message mex = c.convertFromJSON(stringMessage);
        cliViewRmi.actionHandler(mex);

        x = remoteObject.getNumberOfActivePlayers();
        if(x < remoteObject.getNumberOfPlayers())
            cliViewRmi.actionHandler(new WaitingMessage());

        while (x != remoteObject.getNumberOfPlayers()){
            while(x == remoteObject.getNumberOfActivePlayers());
            stringMessage = remoteObject.notifyOtherConnections();
            mex = c.convertFromJSON(stringMessage);
            cliViewRmi.actionHandler(mex);
            if(x < remoteObject.getNumberOfPlayers())
                cliViewRmi.actionHandler(new WaitingMessage());
            x = remoteObject.getNumberOfActivePlayers();
        }
    }

    @Override
    public String sendMessageAndGetAnswer(String message) throws IOException {
        return manageMessage(message);
    }

    synchronized String manageMessage(String messageJsonCoded) throws IOException {
        Converter conv = new Converter();
        Message fromServer = conv.convertFromJSON(messageJsonCoded);
        CliView view = new CliView(null, null, stdIn, rmiServerInterface);
        view.actionHandler(fromServer);
        return null;
    }

    @Override
    public boolean testAliveness() {
        return true;
    }

    @Override
    public void run() {
        while (true) {
            try {
                rmiServerInterface = (RmiServerInterface) LocateRegistry.getRegistry(hostName, Integer.parseInt(portNumber)).lookup("MyShelfie");
                rmiServerInterface.registry((RmiClientInterface) UnicastRemoteObject.exportObject(this, 0));
                break;
            } catch (RemoteException | NotBoundException e) {
            }
        }
    }
}