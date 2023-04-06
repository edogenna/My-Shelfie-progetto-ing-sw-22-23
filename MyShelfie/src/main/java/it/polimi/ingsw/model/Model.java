package it.polimi.ingsw.model;

import java.util.Observable;

public class Model extends Observable {
    private Board board = new Board(2);

    public boolean isFeasiblePickMove(int x, int y){
        return this.board.tileFreeSide(x, y);
    }

    public void performMove(int x, int y, Player bob){
        int z = 1;
        bob.insert(z, board.deleteItemEnum(x,y));
    }

    public boolean checkFullShelf(Player bob){
        for(int i=0; i<5; i++){
            if(bob.getHeights(i) != 6){
                return false;
            }
        }
        return true;
    }
}
