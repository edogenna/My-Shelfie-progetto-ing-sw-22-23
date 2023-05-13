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
import it.polimi.ingsw.Network.server.RmiGame;
import it.polimi.ingsw.view.CliView;

public class RmiClient implements RmiClientInterface, Runnable{

    private BufferedReader stdIn;
    private CliView cliViewRmi;
    private Converter c;
    private RmiGame rmiGame;

    public RmiClient(){
        this.stdIn = null;
        this.cliViewRmi = null;
        this.c = null;
    }


    public void startRMIClient() throws IOException, NotBoundException {
        int x;

        Registry registry = LocateRegistry.getRegistry();
        String remoteObjectName = "MyShelfie";
        RmiGame remoteObject = (RmiGame) registry.lookup(remoteObjectName);

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
    public void printMessage(String message) throws IOException {
        Message mex = c.convertFromJSON(message);
        cliViewRmi.actionHandler(mex);
    }

    @Override
    public void run() {
 /*       while (true) {
            try {
                rmiGame = (RmiGame) LocateRegistry.getRegistry(ip, rmiPort).lookup("MyShelfie");
                rmiGame.registry((RmiClientInterface) UnicastRemoteObject.exportObject(this, 0));
                break;
            } catch (RemoteException | NotBoundException e) {
            }
        }*/
    }
}