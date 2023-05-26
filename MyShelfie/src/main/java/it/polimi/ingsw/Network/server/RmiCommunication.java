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

    RmiCommunication(String message, RmiClientInterface client, RmiServer rmiServer, int number, ServerManager serverManager){
        super(number, serverManager, message);
        this.client = client;
        this.rmiServer = rmiServer;
    }

    @Override
    public void run(){
        String answer = rmiServer.sendMessageAndGetAnswer(this.client, this.message);
        showAndSetAnswer(answer);
        if (this.timeExceeded) {
//            answer = rmiServer.sendMessageAndGetAnswer(client, new Parser().serialize(new Message(Protocol.TIME_EXCEEDED, "", null)));
            //TODO: maybe this is wrong;
            answer = rmiServer.sendMessageAndGetAnswer(this.client, new TimeoutMessage().getS());
            System.out.println("answer in communication: " + answer);
            showAndSetAnswer(answer);
            rmiServer.unregister(client);
        }
    }
}
