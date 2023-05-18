package it.polimi.ingsw.Network.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

class Server {
    public static void main(String[] args) {
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
}