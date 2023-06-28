package it.polimi.ingsw.Network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.Constants;
import it.polimi.ingsw.Network.messages.Converter;
import it.polimi.ingsw.Network.messages.Message;
import it.polimi.ingsw.Network.server.RmiServerInterface;
import it.polimi.ingsw.view.CliView;
import it.polimi.ingsw.view.GuiView;
import it.polimi.ingsw.view.UI;

import static java.lang.Thread.sleep;

/**
 * This class represents a rmi client implementation
 * @author Donato Fiore
 * @author Alessandro Fornara
 */
public class RmiClient implements RmiClientInterface{

    private BufferedReader stdIn = null;
    private UI ui = null;
    private RmiServerInterface rmiServerInterface = null;
    private boolean switchOff;
    private int id;

    /**
     * Starts the rmi client
     * @param hostName The host name
     * @param chooseCliGui True if the user wants to play with the cli, false if he wants to play with the gui
     * @param args The arguments
     */
    public void startRMIClient(String hostName, boolean chooseCliGui, String[] args) {
        this.stdIn = new BufferedReader(new InputStreamReader(System.in));

        if (chooseCliGui) {
            ui = GuiView.getInstance();
            ui.setInAndOut(null, null);
            ((GuiView) ui).main(args, stdIn);
        }else
            ui = new CliView(null, null, stdIn);

        try {
            rmiServerInterface = (RmiServerInterface) LocateRegistry.getRegistry(hostName, Constants.PORT_RMI_GAME).lookup("MyShelfie");
            rmiServerInterface.registry((RmiClientInterface) UnicastRemoteObject.exportObject(this, 0));;
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Invalid parameters: " + e.getMessage());
            System.exit(0);
        }

        while(true){
            try {
                switchOff = rmiServerInterface.testServerConnection(this.id);
                if(switchOff){
                    System.out.println("Connection to the server lost");
                    System.out.println("Client will close now");
                    System.exit(0);
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } catch (RemoteException e) {
                System.out.println("Connection to the server has been lost");
                System.out.println("Client will close now");
                System.exit(0);
            }
        }
    }

    /**
     * Manages the message received from the server
     * @param messageJsonCoded The message received from the server
     * @return The answer to the server
     * @throws IOException
     * @throws InterruptedException
     */
    synchronized String manageMessage(String messageJsonCoded) throws IOException {
        Message fromServer = Converter.convertFromJSON(messageJsonCoded);
        return ui.actionHandler(fromServer);
    }

    /**
     * Sends a message to the server and waits for the answer
     * @param message The message to send
     * @return The answer to the server
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public String sendMessageAndGetAnswer(String message) throws IOException {
        return manageMessage(message);
    }

    /**
     * Tests the connection with the server
     * @return True if the connection is still alive, false otherwise
     */
    @Override
    public boolean testAliveness() {
        return true;
    }

    /**
     * Closes the client
     */
    @Override
    public void stopClient(){
        System.out.println("Client will close now");
        System.exit(0);
    }

    /**
     * Sets the id of the client
     * @param number The id of the client
     */
    @Override
    public void setId(int number) {
        this.id = number;
    }

}