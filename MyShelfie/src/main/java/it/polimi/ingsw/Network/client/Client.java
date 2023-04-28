package it.polimi.ingsw.Network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public static void main(String[] args) throws IOException {

        String hostName = "127.0.0.1";
        int portNumber = 1234;

        /*if (args.length != 2) {
            System.err.println(
                "Usage: java Client <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        */
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Would you like to use SOCKET (0) or RMI (1) ? ");
        switch (Integer.parseInt(stdIn.readLine())){
            case 0 -> new SocketClient().startSocketClient();
        }
    }
}
