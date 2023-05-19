package it.polimi.ingsw.Network.client;

import it.polimi.ingsw.Constant;
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
public class SocketClient extends Client{

    //TODO: salvare l'username nel client quando questo si connette al server
    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    BufferedReader stdIn = null;
    Thread inputReader;
    public void startSocketClient() throws IOException {
        try {
            this.socket = new Socket(hostName, Constant.PORT_SOCKET_GAME);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e){
            System.out.println("Invalid parameters: " + e.getMessage());
            System.exit(0);
        }

        System.out.println("you are connected with socket");
        CliView cliView = new CliView(out, in, stdIn, null);
        inputReader = createInputReader();
        inputReader.start();

        while(true){
            String message = in.readLine();
            Message m = Converter.convertFromJSON(message);
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

    private Thread createInputReader(){
        return new Thread(() -> {
            while(true){
                try {
                    String message = stdIn.readLine();
                    out.println(Converter.convertToJSON(new ChatMessage(message, "IlMioUserName")));;
                } catch (IOException e) {
                    System.out.println("An error occured while trying to read from socket: " + e.getMessage());
                }
            }
        });
    }
}