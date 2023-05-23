package it.polimi.ingsw.Network.messages;

public final class UnknownCodeMessage extends Message{

    private String s;
    public UnknownCodeMessage(){
        super("UnknownCode");
        s = "Unknown code. The game could be over already";
    }

    public String getS() {
        return s;
    }
}
