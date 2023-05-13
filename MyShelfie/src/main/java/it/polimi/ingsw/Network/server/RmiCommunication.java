package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.client.RmiClient;
import it.polimi.ingsw.Network.client.RmiClientInterface;

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
        String answer = rmiServer.sendMessageAndGetAnswer(client, message);
        showAndSetAnswer(answer);
    }
}
