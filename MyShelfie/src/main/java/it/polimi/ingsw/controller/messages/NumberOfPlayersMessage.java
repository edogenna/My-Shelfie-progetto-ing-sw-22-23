package it.polimi.ingsw.controller.messages;
public final class NumberOfPlayersMessage extends Message{
    private final int num;

    public NumberOfPlayersMessage(int num){
        super("NumberOfPlayers");
        this.num = num;
    }
    public int getNum() {
        return num;
    }
}
