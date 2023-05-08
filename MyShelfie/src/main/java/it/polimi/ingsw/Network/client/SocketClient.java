package it.polimi.ingsw.Network.client;

import it.polimi.ingsw.Network.messages.*;
;
import it.polimi.ingsw.view.CliView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class represents a socket client implementation
 * @author Alessandro Fornara
 */
public class SocketClient {
    public void startSocketClient(String hostName, int portNumber) throws IOException {

        try(
                Socket Socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(Socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ){
            System.out.println("Connected");
            Converter c = new Converter();
            CliView cliView = new CliView(out, in, stdIn, null);

            while(true){
                String message = in.readLine();
                Message m = c.convertFromJSON(message);
                cliView.actionHandler(m);
            }
        }
    }
}