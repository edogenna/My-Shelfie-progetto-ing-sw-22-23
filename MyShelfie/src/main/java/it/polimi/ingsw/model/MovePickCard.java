package it.polimi.ingsw.model;

public class MovePickCard {
    private int row, col, num;

    public MovePickCard(int x, int y){
        this.row = x;
        this.col = y;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol(){
        return this.col;
    }
}
