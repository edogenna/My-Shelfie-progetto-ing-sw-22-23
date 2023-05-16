package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.client.RmiClient;
import it.polimi.ingsw.Network.client.RmiClientInterface;

public class RmiCommunicationChat extends Communication{
    private final RmiClientInterface client;
    private final RmiServerChat rmiServer;

    RmiCommunicationChat(String message, RmiClientInterface client, RmiServerChat rmiServer, int number, ServerManager serverManager){
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
