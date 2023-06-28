package it.polimi.ingsw.Network.messages;

import java.io.Serial;
import java.io.Serializable;

/**
 * This class represents a generic message
 * @author Alessandro Fornara
 */
public abstract class Message implements Serializable {

    /**
     * Serial Version UID
     * @see Serializable
     */
    @Serial
    private static final long serialVersionUID = 1L;

    private String type;

    public Message(String type) {
        this.type = type;
    }

    /**
     * @author Alessandro Fornara
     * @return returns a message's type
     */
    public String getType(){
        return this.type;
    }
}
