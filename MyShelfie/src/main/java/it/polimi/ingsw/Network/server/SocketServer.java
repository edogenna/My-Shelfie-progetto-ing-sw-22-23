package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Constants;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * This class is an implementation of a server using socket technology
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

    /**
     * This method adds a socket client to a map on the {@link ServerManager}
     * @param client client
     */
    private void registry(Socket client) throws IOException {
        serverManager.addClient(client);
        fromClient.put(client, new Scanner(client.getInputStream()));
        toClient.put(client, new PrintWriter(client.getOutputStream(), true));
        int number = serverManager.getNumber(client);
        System.out.println("User " + number + " connected on the SocketServer");
        new Thread(new ClientManager(serverManager, number)).start();
    }

    /**
     * This method removes a socket client from the map on the {@link ServerManager}
     * @param client the client to remove
     */
    public void unregister(Socket client){
        fromClient.remove(client);
        toClient.remove(client);
        serverManager.removeClient(client);
    }

    /**
     * This method sends a message to a client and waits for an answer
     * @param socket the socket of the client
     * @param message the message to send
     * @return the answer of the client
     */
    public String sendMessageAndGetAnswer(Socket socket, String message) {
        toClient.get(socket).println(message);
        try {
            return fromClient.get(socket).nextLine();
        } catch (NoSuchElementException e) {
            unregister(socket);
            return "Disconnect";
        }
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(this.portNumber)) {
            System.out.println("SocketServer has started on port: "+ Constants.PORT_SOCKET_GAME);
            while (true) {
                Socket client = serverSocket.accept();
                registry(client);
            }
        } catch (IOException e) {
            System.out.println("SocketServer is closed.");
            System.exit(0);
        }
    }
}