package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.client.ClientInformation;
import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.Observer.Observable;
import it.polimi.ingsw.controller.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Server {
    private ServerSocket serverSocket = null;
    private ExecutorService executor;
    private int portNumber;
    private ArrayList<ClientInformation> connectedClients;
    private ArrayList<String> usernames;
    private Observable observable;
    private int numberOfPlayers;
    private String str = "";
    public static Semaphore numOfPlayersLock;
    public static Semaphore usernameChosen;

    public Server(int port){
        this.portNumber=port;
        this.observable = new Observable();
        this.connectedClients = new ArrayList<>();
        this.usernames = new ArrayList<>();
        this.executor = Executors.newCachedThreadPool();
        this.numberOfPlayers = 0;
        this.numOfPlayersLock = new Semaphore(1);
        this.usernameChosen = new Semaphore(1);;
    }

    public void startServer() throws IOException, InterruptedException {
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server started..");
        }catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Server ready");

        LobbyPhase();
    }

    private void sendMessageToObservers(Message message) {
        observable.notifyObservers(message);
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
    public void setStr(String s) {this.str = s;}

    private ArrayList<ClientInformation> acceptConnection(ServerSocket serverSocket) throws IOException {
        Socket clientSocket;
        clientSocket = serverSocket.accept();
        ClientInformation inf = new ClientInformation(clientSocket, new PrintWriter(clientSocket.getOutputStream(), true), new Scanner(clientSocket.getInputStream()), connectedClients.size()-1);
        connectedClients.add(inf);
        return connectedClients;
    }

    public boolean isUsernameTaken(String usrn){
        for(String s: usernames){
            if(usrn.equals(s)){
                return false;
            }
        }
        usernames.add(usrn);
        connectedClients.get(connectedClients.size()-1).setUsername(usrn);
        return true;
    }

    private void LobbyPhase() throws IOException, InterruptedException {

        while (true) {
            Server.numOfPlayersLock.acquire();
            Server.usernameChosen.acquire();
            System.out.println("Ready to accept new connection");
            connectedClients = acceptConnection(serverSocket);
            executor.submit(new ClientHandler(this, connectedClients.get(connectedClients.size() - 1), observable));
            System.out.println("User connected");

            while (!Server.usernameChosen.tryAcquire());
            Server.usernameChosen.release();

            if(connectedClients.size() > 1)
                Server.numOfPlayersLock.release();

            if (connectedClients.size() == 1) {

                sendMessageToObservers(new FirstPlayerMessage());

                while (!Server.numOfPlayersLock.tryAcquire()) ;

                sendMessageToObservers(new LobbyMessage(1, numberOfPlayers));
                sendMessageToObservers(new WaitingMessage());
                Server.numOfPlayersLock.release();

            } else {
                sendMessageToObservers(new LobbyMessage(connectedClients.size(), numberOfPlayers));

                if (connectedClients.size() < numberOfPlayers)
                    sendMessageToObservers(new WaitingMessage());
            }

            if (connectedClients.size() == numberOfPlayers) {
                sendMessageToObservers(new StartingGameMessage());
                System.out.println("Starting Game...");
                break;
            }
        }
    }

    private void GamePhase(){
        Controller controller = new Controller(numberOfPlayers);
        //TODO: implementation coming soon

    }
}
