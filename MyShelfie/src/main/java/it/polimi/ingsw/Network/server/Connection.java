package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Network.messages.Message;

public interface Connection {
    void sendMessage(Message m);

}
