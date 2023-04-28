package it.polimi.ingsw.controller.messages;
public final class NumberOfPlayersAnswer extends Message{
    private final int num;

    public NumberOfPlayersAnswer(int num){
        super("NumberOfPlayers");
        this.num = num;
    }
    public int getNum() {
        return num;
    }
}
