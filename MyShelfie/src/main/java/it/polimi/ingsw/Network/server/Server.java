package it.polimi.ingsw.Network.server;

import java.io.IOException;
import java.net.Socket;

public class Server {

    public static void main( String[] args ) throws IOException, InterruptedException {
        SocketServer socketServer = new SocketServer(1234);
        RmiServer rmiServer = new RmiServer();
        rmiServer.startRMIServer();
        socketServer.startServer();
    }
}
