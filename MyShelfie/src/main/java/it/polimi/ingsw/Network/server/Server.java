package it.polimi.ingsw.Network.server;

import com.google.gson.Gson;
import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.Observer.Observable;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

/**
 * Main server class that starts a socket server.
 * @author Alessandro Fornara
 */
public class Server extends UnicastRemoteObject implements RmiGame{
    private ServerSocket serverSocket = null;
    private ExecutorService executor;
    private int portNumber;
    private ArrayList<ClientInformation> connectedClients;
    private ArrayList<String> usernames;
    private Observable observable;
    private int numberOfPlayers;
    public static Semaphore Lock1;
    public static Semaphore Lock2;
    protected Controller controller;
    protected boolean win;

    public Server(int port) throws RemoteException{
        super();
        this.portNumber = port;
        this.observable = new Observable();
        this.connectedClients = new ArrayList<>();
        this.usernames = new ArrayList<>();
        this.executor = Executors.newCachedThreadPool();
        this.numberOfPlayers = 0;
        this.Lock1 = new Semaphore(1);
        this.Lock2 = new Semaphore(1);
        this.controller = null;
        this.win = false;
    }

    //TODO: correct the documentation
    /**
     * This method starts the server
     * @author Alessandro Fornara
     * @throws IOException
     * @throws InterruptedException
     */
    public void startServer() throws IOException, InterruptedException {
        try {
            System.out.println("Starting Socket");
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Socket started");
/*
            System.out.println("Starting RMI");
            GameRemote gameRemote = new GameRemote();
            System.out.println("Binding server implementation to registry");
            Registry registry = LocateRegistry.createRegistry(portNumber + 1);
            registry.rebind("MyShelfie", gameRemote);
*/
            System.out.println("Server started..");
        }catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Server ready");

        LobbyPhase();
        GamePhase();
    }

    /**
     * This method sends a message to all observers
     * @author Alessandro Fornara
     * @param message
     */
    protected void sendMessageToObservers(Message message) {
        observable.notifyObservers(message);
    }

    /**
     * This method sets the number of players for the game
     * @author Alessandro Fornara
     * @param numberOfPlayers
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * This method accepts a socket connection and adds the connected client to a list of {@link ClientInformation}
     * @author Alessandro Fornara
     * @param serverSocket server
     * @return list of connected clients
     * @throws IOException
     */
    private ArrayList<ClientInformation> acceptConnection(ServerSocket serverSocket) throws IOException {
        Socket clientSocket;
        clientSocket = serverSocket.accept();
        ClientInformation inf = new ClientInformation(clientSocket, new PrintWriter(clientSocket.getOutputStream(), true), new Scanner(clientSocket.getInputStream()), connectedClients.size()-1);
        connectedClients.add(inf);
        return connectedClients;
    }

    /**
     * This method check if a username has already been taken by another client, if not it is added to a list of usernames and to the client's information
     * @author Alessandro Fornara
     * @param nickname username
     * @return false if the username has already been taken, true otherwise
     */
    public boolean isUsernameTaken(String nickname){
        for(String s: usernames){
            if(nickname.equals(s)){
                return false;
            }
        }
        usernames.add(nickname);
        connectedClients.get(connectedClients.size()-1).setUsername(nickname);
        return true;
    }

    /**
     * This method is used for the lobby phase (pre-game)
     * @author Alessandro Fornara
     * @throws IOException
     * @throws InterruptedException
     */
    private void LobbyPhase() throws IOException, InterruptedException {

        while (true) {
            Server.Lock1.acquire();
            Server.Lock2.acquire();
            System.out.println("Ready to accept new connection");
            connectedClients = acceptConnection(serverSocket);
            executor.submit(new ClientHandlerSocket(this, connectedClients.get(connectedClients.size() - 1), observable));
            System.out.println("User connected");

            while (!Server.Lock2.tryAcquire());
            Server.Lock2.release();

            if(connectedClients.size() > 1)
                Server.Lock1.release();

            if (connectedClients.size() == 1) {

                sendMessageToObservers(new FirstPlayerMessage());

                while(!Server.Lock1.tryAcquire());

                sendMessageToObservers(new LobbyMessage(1, numberOfPlayers));
                sendMessageToObservers(new WaitingMessage());
                Server.Lock1.release();

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

    /**
     * This method is used for the game phase (in-game)
     * @author Alessandro Fornara
     * @throws InterruptedException
     */
    private void GamePhase() throws InterruptedException, IOException {

        //TODO: checks if there is a game saved on disk and creates the controller using the other constructor if true
        controller = new Controller(numberOfPlayers);
        for(ClientInformation s: connectedClients){
            controller.setUsernamePlayer(s.getUsername());
        }
        controller.setFirstPlayer();

        Server.Lock1.acquire();
        while (!win) {

            saveGame();
            System.out.println("Game has been saved");

            sendMessageToObservers(new GameInformationMessage(controller.getBoard(), controller.getActivePlayershelf(), controller.getActivePlayerPersonalCard(), controller.getCommonCardsDesigns(), controller.getActivePlayerUsername()));
            while (!Server.Lock1.tryAcquire());
            if(win){
                int points = controller.declareWinner();
                sendMessageToObservers(new WinMessage(controller.getActivePlayerUsername(), points));
            }
        }
    }

    /**
     * This method saves the game
     * @author Alessandro Fornara
     * @throws IOException
     */
    private void saveGame() throws IOException {
        String save = controller.getModelSave();

        String filePath = "C:\\Users\\alefo\\Desktop\\ing-sw-2023-Gennaretti-Fiore-Fornara-Galli\\MyShelfie\\save.txt";
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(save);
        fileWriter.close();
    }

    /**
     * This method loads a game that has been saved in file
     * @return game {@link Model}
     * @throws IOException
     */
    private Model loadGame() throws IOException {
        File file = new File("C:\\Users\\alefo\\Desktop\\ing-sw-2023-Gennaretti-Fiore-Fornara-Galli\\MyShelfie\\save.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        Converter c = new Converter();

        if(file.exists()) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String content = stringBuilder.toString();

            return c.convertModelFromJSON(content);
        }
        return null;
    }

    //TODO: I have to find a way to serialize the commonCards, I could use Strings like in the message
    //DO NOT USE THIS METHOD FOR NOW
    private Controller checkMemoryDisk() throws IOException {
        Model m = loadGame();
        int i = 0;
        boolean samePlayers = true;

        if(m!=null) {
            Player[] players = m.getPlayers();
            if (players.length == connectedClients.size()) {
                for (ClientInformation s : connectedClients) {
                    if (!s.getUsername().equals(players[i].getUsername())) {
                        samePlayers = false;
                        break;
                    }
                    i++;
                }
                if (samePlayers)
                    controller = new Controller(m);
            }
        }
        else {
            controller = new Controller(numberOfPlayers);
            for (ClientInformation s : connectedClients) {
                controller.setUsernamePlayer(s.getUsername());
            }
            controller.setFirstPlayer();
        }
        return controller;
    }
}
