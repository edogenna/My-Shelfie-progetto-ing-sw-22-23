package it.polimi.ingsw.Client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientInformation {
    private Socket socket;
    private PrintWriter out;
    private Scanner in;

    public ClientInformation(Socket socket, PrintWriter out, Scanner in){
        this.socket = socket;
        this.out = out;
        this.in = in;
    }

    public Socket getSocket() {
        return socket;
    }

    public PrintWriter getOut() {
        return out;
    }

    public Scanner getIn() {
        return in;
    }
}
