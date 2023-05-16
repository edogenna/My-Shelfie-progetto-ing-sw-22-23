package it.polimi.ingsw.Network.messages;

public final class ChatMessage extends Message{
    private final String message;
    private final String sender;

    public ChatMessage(String message, String sender){
        super("ChatMessage");
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }
}