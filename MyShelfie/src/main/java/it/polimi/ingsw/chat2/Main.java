package it.polimi.ingsw.chat2;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("0) Server\n1) Client");
        int s = (new Scanner(System.in)).nextInt();

        if(s == 0)
            Server.main();
        else if(s == 1)
            Client.main();
        else
            System.out.println("scelta non possibile\nClosing...");
    }
}
