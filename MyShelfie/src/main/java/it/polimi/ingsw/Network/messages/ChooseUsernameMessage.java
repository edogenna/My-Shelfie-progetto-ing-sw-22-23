package it.polimi.ingsw.Network.messages;

public final class ChooseUsernameMessage extends Message{

    private final String s = "Choose your username";

    public ChooseUsernameMessage(){
        super("ChooseUsername");
    }
    public String getS() {
        return s;
    }
}
