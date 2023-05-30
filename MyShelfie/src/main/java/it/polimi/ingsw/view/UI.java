package it.polimi.ingsw.view;
import it.polimi.ingsw.Network.messages.Message;
import java.io.IOException;

public interface UI {
    public void main() throws IOException;
    String actionHandler(Message m) throws IOException;
}
