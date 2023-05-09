package it.polimi.ingsw.Network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import it.polimi.ingsw.Network.messages.Converter;
import it.polimi.ingsw.Network.messages.LobbyMessage;
import it.polimi.ingsw.Network.messages.Message;
import it.polimi.ingsw.Network.server.RmiGame;
import it.polimi.ingsw.view.CliView;

public class RmiClient {

    public void startRMIClient() throws IOException, NotBoundException {
        Scanner input = new Scanner(System.in);
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
        System.out.println(((LobbyMessage) mex).getS());

        x = remoteObject.getNumberOfActivePlayers();
        while (x != remoteObject.getNumberOfPlayers()){
            while(x == remoteObject.getNumberOfActivePlayers());
            stringMessage = remoteObject.notifyConnection();
            mex = c.convertFromJSON(stringMessage);
            System.out.println(((LobbyMessage) mex).getS());
            x = remoteObject.getNumberOfActivePlayers();
        }

        while(true){
            String message = input.nextLine();
            Message m = c.convertFromJSON(message);
            cliViewRmi.actionHandler(m);
        }
    }
}