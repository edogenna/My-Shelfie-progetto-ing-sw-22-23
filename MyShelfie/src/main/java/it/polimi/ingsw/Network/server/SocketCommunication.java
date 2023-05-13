package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.messages.Converter;
import it.polimi.ingsw.Network.messages.Message;

import java.net.Socket;

public class SocketCommunication extends Communication{

    private final Socket client;
    private final SocketServer socketServer;

    SocketCommunication(String message, Socket client, SocketServer socketServer, int number, ServerManager serverManager) {
        super(number, serverManager, message);
        this.client = client;
        this.socketServer = socketServer;
    }

    @Override
    public void run() {
        String answer = socketServer.sendMessageAndGetAnswer(client, message);
        showAndSetAnswer(answer);
    }
}
