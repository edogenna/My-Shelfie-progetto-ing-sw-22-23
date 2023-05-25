package it.polimi.ingsw.view;
import it.polimi.ingsw.Network.messages.Message;
import java.io.IOException;

public interface UI {


    String actionHandler(Message m) throws IOException;
}
