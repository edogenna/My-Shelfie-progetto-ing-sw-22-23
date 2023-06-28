package it.polimi.ingsw.Network.messages.Answers;

import it.polimi.ingsw.Network.messages.Message;

public class ACKOffline extends Message {
    /**
     * This message is sent to the server when the client is offline
     */
    public ACKOffline() {
        super("ACKOffline");
    }
}
