package it.polimi.ingsw.Network.client;

import it.polimi.ingsw.Constants;
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

import static java.lang.Thread.sleep;

/**
 * This class represents a socket client implementation
 * @author Alessandro Fornara
 */
public class SocketClient{

    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private BufferedReader stdIn = null;
    private UI ui = null;
    private Thread timer;

    /**
     * Starts the socket client
     * @param hostName The host name of the server to connect to
     * @param chooseCliGui True if the user wants to play with the cli, false if he wants to play with the gui
     * @param args The arguments of the main
     * @throws IOException If there is an error with the socket
     */
    public void startSocketClient(String hostName, boolean chooseCliGui, String[] args) throws IOException {
        try {
            try {
                this.socket = new Socket(hostName, Constants.PORT_SOCKET_GAME);
                this.out = new PrintWriter(socket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.stdIn = new BufferedReader(new InputStreamReader(System.in));
            } catch (IOException e) {
                System.out.println("Invalid parameters: " + e.getMessage());
                System.out.println("Client will close now");
                System.exit(0);
            }

            System.out.println("you are connected with socket");

            if (chooseCliGui) {
                ui = GuiView.getInstance();
                ui.setInAndOut(out, in);
                ((GuiView) ui).main(args, stdIn);
            }else
                ui = new CliView(out, in, stdIn);

            while (true) {
                String message = in.readLine();
                startTimer();
                Message m = Converter.convertFromJSON(message);
                ui.actionHandler(m);
                stopTimer();
            }

        }catch (SocketException e){
            System.out.println("Connection to the server has been lost");
            System.out.println("Client will close now");
            socket.close();
            stdIn.close();
            System.exit(0);
        }
    }

    /**
     * Starts the timer
     * The timer is used to check if the client is still connected to the server
     */
    private void startTimer(){
        this.timer = new Thread(()->{
            int counter = 0;

            while(true) {
                try {
                    sleep(Constants.MILLIS_IN_SECOND);
                    counter++;
                    //System.out.println(counter);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                if (counter >= Constants.secondsDuringTurn) {
                    System.out.println("Time out, client will close now");
                    System.exit(0);
                }
            }
        });
        this.timer.start();
    }

    /**
     * Stops the timer
     */
    private void stopTimer(){
        this.timer.interrupt();
    }
}