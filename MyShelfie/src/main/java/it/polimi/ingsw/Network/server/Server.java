package it.polimi.ingsw.Network.server;

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
        serverManager = new ServerManager();
        serverManager.run();
    }

    @Deprecated
    private static int manageIntInput(Scanner stdin, int forbiddenValue) {
        int output;
        try {
            output = stdin.nextInt();
        } catch (InputMismatchException e) {
            output = Server.MIN_PORT - 1;
            stdin.nextLine();
        }
        while (output > Server.MAX_PORT || output < Server.MIN_PORT || output == forbiddenValue) {
            System.out.println("the value must be between " + Server.MIN_PORT + " and " + Server.MAX_PORT + ". Try again:");
            try {
                output = stdin.nextInt();
            } catch (InputMismatchException e) {
                output = Server.MIN_PORT - 1;
                stdin.nextLine();
            }
        }
        return output;
    }
}