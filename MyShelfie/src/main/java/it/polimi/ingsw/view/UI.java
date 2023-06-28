package it.polimi.ingsw.view;
import it.polimi.ingsw.Network.messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface UI {
    /**
     * set the input and output of the UI
     * @param out output
     * @param in input
     */
    public void setInAndOut(PrintWriter out, BufferedReader in);
    String actionHandler(Message m) throws IOException;
}
