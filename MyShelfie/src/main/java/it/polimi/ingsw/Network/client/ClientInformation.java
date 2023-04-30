package it.polimi.ingsw.Network.client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientInformation {
    private Socket socket;
    private PrintWriter out;
    private Scanner in;
    private int ID;
    private String username;

    public ClientInformation(Socket socket, PrintWriter out, Scanner in, int id){
        this.socket = socket;
        this.out = out;
        this.in = in;
        this.ID = id;
        this.username = null;
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

    public void setUsername(String username) {
        this.username = username;
    }
}
