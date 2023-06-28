package it.polimi.ingsw;

import it.polimi.ingsw.Network.client.RmiClient;
import it.polimi.ingsw.Network.client.SocketClient;
import it.polimi.ingsw.Network.server.Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class to start server or client
 */
public class MyShelfie {

    /**
     * Main method of the project
     * It allows the user to choose if he wants to start a server or a client.
     * It allows the user to choose if he wants to start a cli or a gui client.
     * It allows the user to choose the hostName of the server.
     * It allows the user to choose the port of the server.
     * It allows the user to choose the type of connection.
     * It allows the user to choose the type of game.
     * It allows the user to choose the number of players.
     * It starts the server or the client.
     * It starts the cli or the gui client.
     * It starts the socket or the rmi connection.
     * @param args the arguments of the main method.
     * @throws IOException if there are problems with the connection.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        boolean chooseServerClient = true;
        boolean chooseCliGui = true;
        String hostName;

        System.out.print(Constants.MY_SHELFIE_TITLE);

        System.out.println("Insert 0 for SERVER and any other number for CLIENT");
        if (stdIn.readLine().equals("0"))
            chooseServerClient = false;

        if(chooseServerClient) {

            System.out.println("Insert the hostName or just press enter to get your own device: ");
//            hostName = stdIn.readLine();
//            if (hostName.equals("\n"))
                hostName = "127.0.0.1";
            System.out.println("socket port: " + Constants.PORT_SOCKET_GAME);
            System.out.println("rmi port: " + Constants.PORT_RMI_GAME);

            System.out.println("Insert 0 for CLI and any other number for GUI");
            if (stdIn.readLine().equals("0"))
                chooseCliGui = false;

            System.out.println("Insert 0 for Socket and any other number for RMI");
            if (stdIn.readLine().equals("0"))
                new SocketClient().startSocketClient(hostName, chooseCliGui, args);
            else
                new RmiClient().startRMIClient(hostName, chooseCliGui, args);

        }else{
            new Server().startServer();
        }
    }
}
