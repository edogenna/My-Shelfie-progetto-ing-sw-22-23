package it.polimi.ingsw.Observer;

import it.polimi.ingsw.Network.messages.Message;

public interface Observer {
    void update(Message message);
}