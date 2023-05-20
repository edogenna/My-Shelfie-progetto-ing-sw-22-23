package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Constant;
import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.model.Model;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Main server class that starts a socket server.
 * @author Alessandro Fornara
 */
public class SocketServer implements Runnable{
    private ServerManager serverManager;
    private int portNumber;
    private final Map<Socket, Scanner> fromClient = new HashMap<>();
    private final Map<Socket, PrintWriter> toClient = new HashMap<>();

    public SocketServer(ServerManager serverManager, int port){
        this.serverManager = serverManager;
        this.portNumber = port;
    }

    private void registry(Socket client) throws IOException {
        serverManager.addClient(client);
        fromClient.put(client, new Scanner(client.getInputStream()));
        toClient.put(client, new PrintWriter(client.getOutputStream(), true));
        int number = serverManager.getNumber(client);
        System.out.println("User " + number + " connected on the SocketServer");
        new Thread(new ClientManager(serverManager, number)).start();
    }

    public String sendMessageAndGetAnswer(Socket socket, String message) {
        toClient.get(socket).println(message);
        try {
            return fromClient.get(socket).nextLine();
        } catch (NoSuchElementException e) {
            //remove socket from server manager;
            return "Unable to reach the client." + e.getMessage();
        }
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(this.portNumber)) {
            System.out.println("SocketServer has started on port: "+ Constant.PORT_SOCKET_GAME);
            while (true) {
                Socket client = serverSocket.accept();
                registry(client);
            }
        } catch (IOException e) {
            System.out.println("SocketServer is closed.");
        }
    }
}