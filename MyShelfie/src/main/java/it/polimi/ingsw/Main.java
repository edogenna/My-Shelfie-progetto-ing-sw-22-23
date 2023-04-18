package it.polimi.ingsw;

import it.polimi.ingsw.server.Server;

public class Main {
    public static void main( String[] args ){
        /*Match m1;

        m1=new Match(2);
        m1.begin();
        */

        Server s=new Server(1234);
        s.startServer();

    }
}