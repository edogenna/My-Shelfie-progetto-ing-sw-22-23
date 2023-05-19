package it.polimi.ingsw.Network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.Constant;
import it.polimi.ingsw.Network.messages.Converter;
import it.polimi.ingsw.Network.messages.Message;
import it.polimi.ingsw.Network.messages.WaitingMessage;
import it.polimi.ingsw.Network.server.RmiServerInterface;
import it.polimi.ingsw.view.CliView;

public class RmiClient extends Client implements RmiClientInterface{

    private BufferedReader stdIn;
    private CliView cliViewRmi;
    private RmiServerInterface rmiServerInterface;

    public RmiClient(){
        this.stdIn = null;
        this.cliViewRmi = null;
    }

    public void startRMIClient() throws IOException {
        this.stdIn = new BufferedReader(new InputStreamReader(System.in));
        this.cliViewRmi = new CliView(null, null, stdIn, rmiServerInterface);

        try {
            rmiServerInterface = (RmiServerInterface) LocateRegistry.getRegistry(hostName, Constant.PORT_RMI_GAME).lookup("MyShelfie");
            rmiServerInterface.registry((RmiClientInterface) UnicastRemoteObject.exportObject(this, 0));;
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Invalid parameters: " + e.getMessage());
            System.exit(0);
        }
    }

    synchronized String manageMessage(String messageJsonCoded) throws IOException {
        Message fromServer = Converter.convertFromJSON(messageJsonCoded);
        return cliViewRmi.actionHandler(fromServer);
    }

    @Override
    public String sendMessageAndGetAnswer(String message) throws IOException {
        return manageMessage(message);
    }

    @Override
    public boolean testAliveness() {
        return true;
    }

}