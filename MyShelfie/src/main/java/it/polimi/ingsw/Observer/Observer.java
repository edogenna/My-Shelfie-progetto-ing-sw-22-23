package it.polimi.ingsw.Observer;

import it.polimi.ingsw.controller.messages.Message;

public interface Observer {
    void update(Message message);
}