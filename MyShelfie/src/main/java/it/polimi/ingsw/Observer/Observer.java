package it.polimi.ingsw.Observer;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Observer interface. It supports a generic method of update.
 * @author Alessandro Fornara
 */
public interface Observer {
    void update(Message message);
}