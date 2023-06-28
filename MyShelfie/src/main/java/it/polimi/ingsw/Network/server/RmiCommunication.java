package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.client.RmiClient;
import it.polimi.ingsw.Network.client.RmiClientInterface;
import it.polimi.ingsw.Network.messages.TimeoutMessage;

import java.sql.Time;

/**
 * Implementation of the communication class for rmi
 * @author Donato Fiore
 */
public class RmiCommunication extends Communication{
    private final RmiClientInterface client;
    private final RmiServer rmiServer;

    /**
     * Constructor of the class RmiCommunication
     * @param message the message to be sent
     * @param client the client to which the message is sent
     * @param rmiServer the rmi server
     * @param number the number of the client
     * @param serverManager the server manager
     */
    RmiCommunication(String message, RmiClientInterface client, RmiServer rmiServer, int number, ServerManager serverManager){
        super(number, serverManager, message);
        this.client = client;
        this.rmiServer = rmiServer;
    }

    @Override
    public void run(){
        String answer = rmiServer.sendMessageAndGetAnswer(this.client, this.message);
        showAndSetAnswer(answer);
    }
}
