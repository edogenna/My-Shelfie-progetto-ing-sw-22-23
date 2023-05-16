package it.polimi.ingsw.Network.client;

import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.view.CliView;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class represents a socket client implementation
 * @author Alessandro Fornara
 */
public class SocketClientChat extends Client{

    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    BufferedReader stdIn = null;
    public void startSocketClientChat(String hostName, int portNumber) throws IOException {
        try {
            this.socket = new Socket(hostName, portNumber);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e){
            System.out.println("Invalid parameters: " + e.getMessage());
            System.exit(0);
        }
        System.out.println("you are connected with socket");
        Converter c = new Converter();
        CliView cliView = new CliView(out, in, stdIn, null);

        while(true){
            String message = in.readLine();
            Message m = c.convertFromJSON(message);
            cliView.actionHandler(m);
        }

    }

    @Override
    public void stop() throws IOException {
        out.close();
        in.close();
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("An error occured while trying to close socket: " + e.getMessage());
        }
    }

}


class ReceivedMessagesHandler implements Runnable {
    private BufferedReader in;
    private CliView cliView;
    private String tmp;

    public ReceivedMessagesHandler(PrintWriter out, BufferedReader in) {
        this.in = in;
        this.cliView = new CliView(out, in, null, null);
    }

    public void run() {
        while (true) {
            try {
                tmp = in.readLine();
                if (tmp != null) {
                    cliView.actionHandler(new Converter().convertFromJSON(tmp));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}



