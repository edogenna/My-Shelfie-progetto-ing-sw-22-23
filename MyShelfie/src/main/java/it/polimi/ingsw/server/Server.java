package it.polimi.ingsw.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int portNumber;
    ArrayList<Socket> connectedClients = new ArrayList<>();

    public Server(int port){
        this.portNumber=port;
    }

    public void startServer() {
        //ExecutorService executor= Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        PrintWriter out=null;
        int numberOfPlayers=0;

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
                System.out.println("User connected" + clientSocket);
                connectedClients.add(clientSocket);

                for(Socket s: connectedClients) {
                    if(connectedClients.size() == 1) {
                        out = new PrintWriter(s.getOutputStream(), true);
                        out.println("You are the first player to connect, please submit the number of players for the next game:");
                        Scanner in = new Scanner(s.getInputStream());
                        numberOfPlayers = in.nextInt();
                        out.println("1 / " + numberOfPlayers + " Clients Connected...");
                        out.println("Waiting for more players...");
                    }
                    else {
                        out = new PrintWriter(s.getOutputStream(), true);
                        out.println(connectedClients.size() + " / " + numberOfPlayers + " Clients Connected...");

                        if(connectedClients.size() < numberOfPlayers)
                            out.println("Waiting for more players...");
                    }
                }

                if(connectedClients.size() == numberOfPlayers) {
                    for(Socket s: connectedClients){
                        out = new PrintWriter(s.getOutputStream(), true);
                        out.println("Starting game...");
                    }
                }
                //executor.submit(new ClientHandler(clientSocket));
            } catch (IOException e) {
                break;
            }
        }
    }

}
