package it.polimi.ingsw.Network.client;

import it.polimi.ingsw.Network.messages.Converter;
import it.polimi.ingsw.Network.messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;

/**
 * Main client class that starts a socket client or an RMI client.
 */
public class Client implements Runnable{
    static String hostName;
    static String portNumber;
    public static void main(String[] args) throws IOException, NotBoundException {

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Insert the hostName or just press enter to get 127.0.0.1 (your own device): ");
        hostName = stdIn.readLine();
        if(hostName.equals("\n"))
            hostName = "127.0.0.1";
        System.out.println("Insert the portNumber: ");
        portNumber = stdIn.readLine();
        System.out.println("Would you like to use SOCKET (0) or RMI (1) ? ");
        switch (Integer.parseInt(stdIn.readLine())){
            case 0 -> new SocketClient().startSocketClient(hostName, Integer.parseInt(portNumber));
            case 1 -> new RmiClient().startRMIClient();
        }
    }

    @Override
    public void run() {

    }
}