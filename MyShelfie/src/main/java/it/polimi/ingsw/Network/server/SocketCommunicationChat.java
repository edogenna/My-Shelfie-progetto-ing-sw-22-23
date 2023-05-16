package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.messages.Converter;
import it.polimi.ingsw.Network.messages.Message;

import java.net.Socket;

public class SocketCommunicationChat extends Communication{

    private final Socket client;
    private final SocketServerChat socketServer;

    SocketCommunicationChat(String message, Socket client, SocketServerChat socketServer, int number, ServerManager serverManager) {
        super(number, serverManager, message);
        this.client = client;
        this.socketServer = socketServer;
    }

    @Override
    public void run() {
        String answer = socketServer.sendMessageAndGetAnswer(this.client, this.message);
        showAndSetAnswer(answer);
    }
}
