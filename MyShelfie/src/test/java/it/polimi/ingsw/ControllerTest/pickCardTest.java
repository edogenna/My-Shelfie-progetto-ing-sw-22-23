package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class pickCardTest {
    Model m;
    Controller c = new Controller(m);
    private int x, y, col;
    public ItemEnum[][] board = new ItemEnum[9][9];

    @Test
    public void test(){
        for (int i = 0; i<9; i++){
            for (int j=0; j<9;j++)
                board[i][j]= ItemEnum.BLUE;
        }
        x=0; y=0; col=2;
        m.getBoard().setItemEnum(x,y, ItemEnum.WHITE);
        m.getBoard().setItemEnum(x+1,y,ItemEnum.WHITE);

        Assert.assertEquals(c.pickCard(x, y, col),0 );
    }


//setter and getter methods

    public Controller getC() {
        return c;
    }

    public void setC(Controller c) {
        this.c = c;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
