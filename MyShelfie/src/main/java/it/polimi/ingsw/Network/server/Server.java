package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.client.ServerManager;

import java.io.IOException;
import java.net.Socket;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

class Server {

    private static final int MIN_PORT = 1000;
    private static final int MAX_PORT = 50000;

    public static void main(String[] args) {
        int socketPort;
        int rmiPort;
        ServerManager serverManager;
        Scanner stdin = new Scanner(System.in);
        System.out.println("server starting...");
        try {
            System.out.println("local ip: " + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("local ip: unknown");
        }
        System.out.println("Specify the port number for the SocketServer:");
        socketPort = manageIntInput(stdin, MIN_PORT, MAX_PORT, 0);
        System.out.println("Specify the port number for the RmiServer:");
        rmiPort = manageIntInput(stdin, MIN_PORT, MAX_PORT, socketPort);
//        serverManager = new ServerManager(secondsAfterThirdConnection, secondsDuringTurn, socketPort, rmiPort, skulls);
//        serverManager.run();
    }

    private static int manageIntInput(Scanner stdin, int minValue, int maxValue, int forbiddenValue) {
        int output;
        try {
            output = stdin.nextInt();
        } catch (InputMismatchException e) {
            output = minValue - 1;
            stdin.nextLine();
        }
        while (output > maxValue || output < minValue || output == forbiddenValue) {
            System.out.println("Il valore deve essere compreso fra " + minValue + " and " + maxValue + ". Try again:");
            try {
                output = stdin.nextInt();
            } catch (InputMismatchException e) {
                output = minValue - 1;
                stdin.nextLine();
            }
        }
        return output;
    }
}

/*
public class Server {

    public static void main( String[] args ) throws IOException, InterruptedException {
        SocketServer socketServer = new SocketServer(1234);
        RmiServer rmiServer = new RmiServer();
        rmiServer.startRMIServer();
        socketServer.startServer();
    }
}
*/