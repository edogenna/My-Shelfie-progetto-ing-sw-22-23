package it.polimi.ingsw.Network.messages;

import java.io.Serial;
import java.io.Serializable;

public abstract class Message implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String type;

    public Message(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
