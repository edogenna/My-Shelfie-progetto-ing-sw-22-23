package it.polimi.ingsw.Network.messages.Answers;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message that is used to let the server know the client has received a previous message
 * @author Alessandro Fornara
 */
public final class ACKMessage extends Message {
    public ACKMessage() {
        super("ACK");
    }
}
