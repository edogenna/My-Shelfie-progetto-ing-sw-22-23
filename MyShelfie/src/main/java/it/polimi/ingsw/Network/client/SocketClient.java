package it.polimi.ingsw.Network.client;

import it.polimi.ingsw.Constant;
import it.polimi.ingsw.Network.messages.*;
;
import it.polimi.ingsw.view.CliView;
import it.polimi.ingsw.view.GuiView;
import it.polimi.ingsw.view.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * This class represents a socket client implementation
 * @author Alessandro Fornara
 */
public class SocketClient{

    //TODO: salvare l'username nel client quando questo si connette al server
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private BufferedReader stdIn = null;
    private UI ui = null;

    public void startSocketClient(String hostName, boolean chooseCliGui) throws IOException {
        try {

            try {
                this.socket = new Socket(hostName, Constant.PORT_SOCKET_GAME);
                this.out = new PrintWriter(socket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.stdIn = new BufferedReader(new InputStreamReader(System.in));
            } catch (IOException e) {
                System.out.println("Invalid parameters: " + e.getMessage());
                System.out.println("Client will close now");
                System.exit(0);
            }

            System.out.println("you are connected with socket");

            if (chooseCliGui)
                ui = new GuiView(out, in);
            else ui = new CliView(out, in, stdIn);

            while (true) {
                String message = in.readLine();
                Message m = Converter.convertFromJSON(message);
                ui.actionHandler(m);
            }

        }catch (SocketException e){
            System.out.println("Connection to the server has been lost");
            System.out.println("Client will close now");
            socket.close();
            stdIn.close();
            System.exit(0);
        }
    }
}