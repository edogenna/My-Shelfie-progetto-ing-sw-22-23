package it.polimi.ingsw;

import it.polimi.ingsw.Network.client.Client;
import it.polimi.ingsw.Network.server.Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;

public class MyShelfie {
    public static void main(String[] args) throws IOException, NotBoundException {
        System.out.print(Constant.MY_SHELFIE_TITLE);

        System.out.println("Would you like to run SERVER(0) or CLIENT(1)");
        switch (Integer.parseInt((new BufferedReader(new InputStreamReader(System.in))).readLine())) {
            case 0 -> Server.main();
            case 1 -> Client.main();
        }
    }


}
