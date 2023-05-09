package it.polimi.ingsw.Network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.polimi.ingsw.Network.messages.Converter;
import it.polimi.ingsw.Network.messages.Message;
import it.polimi.ingsw.Network.messages.WaitingMessage;
import it.polimi.ingsw.Network.server.RmiGame;
import it.polimi.ingsw.view.CliView;

public class RmiClient {

    public void startRMIClient() throws IOException, NotBoundException {
        int x;

        Registry registry = LocateRegistry.getRegistry();
        System.out.println("rmi registry bindings");
        String[] e = registry.list();
        for (int i=0; i < e.length; i++){
            System.out.println(e[i]);
        }
        String remoteObjectName = "MyShelfie";
        RmiGame remoteObject = (RmiGame) registry.lookup(remoteObjectName);

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        CliView cliViewRmi = new CliView(null, null, stdIn, remoteObject);
        Converter c = new Converter();

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
}