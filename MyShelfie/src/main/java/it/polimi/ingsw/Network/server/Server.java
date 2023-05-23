package it.polimi.ingsw.Network.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Main server class that starts a socket server and an RMI server.
 */
public class Server {
    public static void main() {
        ServerManager serverManager;
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