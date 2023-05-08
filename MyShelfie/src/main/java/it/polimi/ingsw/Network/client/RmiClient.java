package it.polimi.ingsw.Network.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import it.polimi.ingsw.Network.server.RmiGame;

public class RmiClient {
    public static void main(String[] args)throws RemoteException, NotBoundException {
        Scanner input = new Scanner(System.in);

        Registry registry = LocateRegistry.getRegistry();
        System.out.println("rmi registry bindings");
        String[] e = registry.list();
        for (int i=0; i < e.length; i++){
            System.out.println(e[i]);
        }
        String remoteObjectName = "MyShelfie";
        RmiGame remoteObject = (RmiGame) registry.lookup(remoteObjectName);

    }
}