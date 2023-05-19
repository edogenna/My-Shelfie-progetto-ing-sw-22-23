package it.polimi.ingsw.Network.messages;

public final class ChatBeginsMessage extends Message{

    private final String s;

    public ChatBeginsMessage(){
        super("ChatBegins");
        s = "Chat begins here";
    }

    public String getS() {
        return s;
    }
}
