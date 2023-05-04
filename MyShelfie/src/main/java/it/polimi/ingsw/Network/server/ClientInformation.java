package it.polimi.ingsw.Network.server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class contains the information of a client
 * @author Alessandro Fornara
 */
public class ClientInformation {
    private Socket socket;
    private PrintWriter out;
    private Scanner in;
    private int id;
    private String username;

    public ClientInformation(Socket socket, PrintWriter out, Scanner in, int id){
        this.socket = socket;
        this.out = out;
        this.in = in;
        this.id = id;
        this.username = null;
    }

    /**
     * @return this client's socket
     */
    public Socket getSocket() {
        return this.socket;
    }

    /**
     * @return the output stream to send this client a message
     */
    public PrintWriter getOut() {
        return this.out;
    }

    /**
     * @return the input stream to receive a message from this client
     */
    public Scanner getIn() {
        return this.in;
    }

    /**
     * @return this client's id
     */
    public int getId() {return this.id;}

    /**
     * Sets this client's username to a value
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return this client's username
     */
    public String getUsername() {
        return this.username;
    }
}
