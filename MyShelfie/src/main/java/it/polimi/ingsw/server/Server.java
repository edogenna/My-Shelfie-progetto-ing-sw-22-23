package it.polimi.ingsw.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int portNumber;

    public Server(int port){
        this.portNumber=port;
    }

    public void startServer() {
        ExecutorService executor= Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        PrintWriter out=null;

        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server started..");
        }catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Server ready");
        while(true) {
            try {
                Socket clientSocket;
                clientSocket = serverSocket.accept();
                System.out.println("User connected");
                executor.submit(new ClientHandler(clientSocket));
            } catch (IOException e) {
                break;
            }
        }
    }

}
