package it.polimi.ingsw.Network.messages;

public final class UsernameAnswer extends Message{
    private final String s;
    public UsernameAnswer(String usrn){
        super("UsernameAnswer");
        this.s = usrn;
    }
    public String getS() {
        return s;
    }
}
