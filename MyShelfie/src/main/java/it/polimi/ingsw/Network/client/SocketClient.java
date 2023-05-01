package it.polimi.ingsw.Network.client;

import it.polimi.ingsw.Network.messages.*;
;
import it.polimi.ingsw.view.CliView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    public void startSocketClient() throws IOException {

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
        try (
                Socket Socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(Socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            Converter c = new Converter();
            CliView cliView = new CliView(out, in, stdIn);

            while (true) {
                String message = in.readLine();
                Message m = c.convertFromJSON(message);
                cliView.actionHandler(m);
            }
        }
    }
}