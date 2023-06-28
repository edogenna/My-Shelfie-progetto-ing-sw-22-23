package it.polimi.ingsw.view;
import it.polimi.ingsw.Network.messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User interface for the client
 */
public interface UI {
    /**
     * set the input and output of the UI towards the server
     * @param out output
     * @param in input
     */
    public void setInAndOut(PrintWriter out, BufferedReader in);

    /**
     * This method decides how to handle a certain message received by the client
     * @param m message
     * @return json string
     */
    String actionHandler(Message m) throws IOException;
}
