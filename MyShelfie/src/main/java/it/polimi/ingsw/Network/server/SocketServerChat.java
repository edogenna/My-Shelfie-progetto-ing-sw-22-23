package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Constant;
import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.model.Model;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class SocketServerChat implements Runnable{
    private ServerManager serverManager;
    private int portNumber;
    private final Map<Socket, Scanner> fromClient = new HashMap<>();
    private final Map<Socket, PrintWriter> toClient = new HashMap<>();

    public Scanner getScannerFromSocket(Socket socket) {
        return fromClient.get(socket);
    }

    public PrintWriter getPrintWriterFromSocket(Socket socket) {
        return toClient.get(socket);
    }

    public SocketServerChat(ServerManager serverManager, int port){
        this.serverManager = serverManager;
        this.portNumber = port;


    }

    private void registry(Socket client) throws IOException {
        System.out.println("socket registry method!");
        serverManager.addClientChat(client);
        fromClient.put(client, new Scanner(client.getInputStream()));
        toClient.put(client, new PrintWriter(client.getOutputStream(), true));
        new Thread(new UserHandler(client, this, serverManager)).start();
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
            System.out.println("SocketServerChat has started on port: " + Constant.PORT_SOCKET_CHAT);
            while (true) {
                Socket client = serverSocket.accept();
                registry(client);
            }
        } catch (IOException e) {
            System.out.println("SocketServerChat is closed.");
        }
    }

    public void broadcastAllUsers(Message m) {
        Converter converter = new Converter();
        String message = converter.convertToJSON(m);

        for (PrintWriter client : this.toClient.values()) {
            client.println(message);
        }
    }
}



class UserHandler implements Runnable {
    Socket s;
    SocketServerChat server;
    ServerManager serverManager;

    UserHandler(Socket s, SocketServerChat server, ServerManager serverManager){
        this.s = s;
        this.server = server;
        this.serverManager = serverManager;
    }

    @Override
    public void run() {
        String message;

        while (server.getScannerFromSocket(s).hasNextLine()) {
            message = server.getScannerFromSocket(s).nextLine();
            serverManager.messagesQueue.add(message);
        }
    }
}