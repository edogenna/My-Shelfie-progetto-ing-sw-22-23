package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.messages.Converter;
import it.polimi.ingsw.Network.messages.Message;
import it.polimi.ingsw.Network.messages.TimeoutMessage;

import java.net.Socket;

/**
 * Implementation of the communication class for socket
 * @author Alessandro Fornara
 */
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
        System.out.println("Socket Communication run");
        String answer = socketServer.sendMessageAndGetAnswer(this.client, this.message);
        showAndSetAnswer(answer);
        if (this.timeExceeded) {
//            answer = rmiServer.sendMessageAndGetAnswer(client, new Parser().serialize(new Message(Protocol.TIME_EXCEEDED, "", null)));
            //TODO: maybe this is wrong;
            answer = socketServer.sendMessageAndGetAnswer(this.client, new TimeoutMessage().getS());
            showAndSetAnswer(answer);
            socketServer.unregister(client);
        }
    }
}
