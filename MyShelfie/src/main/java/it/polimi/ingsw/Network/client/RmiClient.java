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

public class RmiClient extends Client implements RmiClientInterface{

    private BufferedReader stdIn;
    private CliView cliViewRmi;
    private Converter c;
    private RmiServerInterface rmiServerInterface;

    public RmiClient(){
        this.stdIn = null;
        this.cliViewRmi = null;
        this.c = null;
    }


    public void startRMIClient() throws IOException {

        this.stdIn = new BufferedReader(new InputStreamReader(System.in));
        this.cliViewRmi = new CliView(null, null, stdIn, rmiServerInterface);
        this.c = new Converter();

        try {
            rmiServerInterface = (RmiServerInterface) LocateRegistry.getRegistry(hostName, Integer.parseInt(portNumber)).lookup("MyShelfie");
            rmiServerInterface.registry((RmiClientInterface) UnicastRemoteObject.exportObject(this, 0));;
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Invalid parameters: " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public String sendMessageAndGetAnswer(String message) throws IOException {
        return manageMessage(message);
    }

    synchronized String manageMessage(String messageJsonCoded) throws IOException {
        Converter conv = new Converter();
        Message fromServer = conv.convertFromJSON(messageJsonCoded);
        return cliViewRmi.actionHandler(fromServer);
    }

    @Override
    public boolean testAliveness() {
        return true;
    }

}