package it.polimi.ingsw.Network.client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientInformation {
    private Socket socket;
    private PrintWriter out;
    private Scanner in;
    private int ID;

    public ClientInformation(Socket socket, PrintWriter out, Scanner in, int id){
        this.socket = socket;
        this.out = out;
        this.in = in;
        this.ID = id;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public PrintWriter getOut() {
        return this.out;
    }

    public Scanner getIn() {
        return this.in;
    }

    public int getID() {return this.ID;}
}
