package it.polimi.ingsw.Network.messages.Answers;

import it.polimi.ingsw.Network.messages.Message;

/**
 * This message is sent to the server when the client is offline
 */
public class ACKOffline extends Message {

    public ACKOffline() {
        super("ACKOffline");
    }
}
