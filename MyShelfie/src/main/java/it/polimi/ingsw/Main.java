package it.polimi.ingsw;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.view.CLI.CliView;

public class Main {
    public static void main( String[] args ){
        /*Match m1;

        m1=new Match(2);
        m1.begin();
        */

        Server s=new Server(1234);
        s.startServer();


        /*CliView cliView = new CliView(2);
        Thread thread = new Thread(cliView);
        thread.start();
        */
    }
}