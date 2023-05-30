package it.polimi.ingsw.view;
import it.polimi.ingsw.Network.messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface UI {
    public void main(String[] args) throws IOException;
    public void setInAndOut(PrintWriter out, BufferedReader in);
    String actionHandler(Message m) throws IOException;
}
