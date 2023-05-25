package it.polimi.ingsw.view;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.Network.messages.ErrorMessages.NotValidUsernameError;
import it.polimi.ingsw.Network.messages.Message;
import it.polimi.ingsw.Network.server.RmiServerInterface;
import it.polimi.ingsw.model.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public interface UI {

    String actionHandler(Message m) throws IOException;
}
