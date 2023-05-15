package it.polimi.ingsw.Network.server;

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

    //TODO: correct the documentation

    /**
     * This method saves the game
     * @author Alessandro Fornara
     * @throws IOException
     */
    /*private void saveGame() throws IOException {
        String save = controller.getModelSave();

        String filePath = "C:\\Users\\alefo\\Desktop\\ing-sw-2023-Gennaretti-Fiore-Fornara-Galli\\MyShelfie\\save.txt";
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(save);
        fileWriter.close();
    }*/

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
    /*private Controller checkMemoryDisk() throws IOException {
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
    }*/

    private void registry(Socket client) throws IOException {
        System.out.println("socket registry method!");
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
            System.out.println("SocketServer has started");
            while (true) {
                Socket client = serverSocket.accept();
                registry(client);
            }
        } catch (IOException e) {
            System.out.println("SocketServer is closed.");
        }
    }
}