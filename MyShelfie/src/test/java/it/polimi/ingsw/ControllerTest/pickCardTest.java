package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class pickCardTest {
    Model model = new Model(2);
    Controller controller = new Controller(model);
    private int x, y, col;
    public ItemEnum[][] board = new ItemEnum[9][9];

    @Test
    public void test(){
        for (int i = 0; i<9; i++){
            for (int j=0; j<9;j++)
                board[i][j]= ItemEnum.BLUE1;
        }
        model.setUsernamePlayer("giovanni");
        model.setUsernamePlayer("luca");
        model.setFirstPlayer();

        //created correct 0 case - done
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                model.getBoard().setItemEnum(i,j,ItemEnum.WHITE1);
            }
        }
        x=0; y=0; col=0;
        Assert.assertEquals("error case done", 0, controller.pickCard(x, y, col));
        x=1;
        Assert.assertEquals("error case done", 0, controller.pickCard(x, y, x+1, y, col));
        x=3;
        Assert.assertEquals("error case done", 0, controller.pickCard(x, y, x+1, y, x+2, y, col));

        //created error 1 case - blank tile
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                model.getBoard().setItemEnum(i,j,ItemEnum.BLANK);
            }
        }
        x=0; y=0; col=0;
        Assert.assertEquals("error 1 case", 1, controller.pickCard(x, y, col) );
        Assert.assertEquals("error 1 case", 1, controller.pickCard(x, y, x+1, y, col));
        Assert.assertEquals("error 1 case", 1, controller.pickCard(x, y, x+1, y, x+2, y, col));

        //created error 5 case - no free space in column
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                model.getBoard().setItemEnum(i,j,ItemEnum.BLUE1);
            }
        }
        for(x=0, y=0, col=1; x<6; x++){
            Assert.assertEquals("preparing the error 5 case, x = " + x, 0, controller.pickCard(x, y, col));
        }
        x=0; y=1; col=1;
        Assert.assertEquals("error 5 case", 5, controller.pickCard(x, y, col) );
        Assert.assertEquals("error 5 case", 5, controller.pickCard(x, y, x+1, y, col) );
        Assert.assertEquals("error 5 case", 5, controller.pickCard(x, y, x+1, y, x+2, y, col) );

        //created error 3 case - no free side
        x=5; y=5; col=2;
        Assert.assertEquals("error 3 case", 3, controller.pickCard(x, y, col) );
        Assert.assertEquals("error 3 case", 3, controller.pickCard(x, y, x+1, y, col) );
        Assert.assertEquals("error 3 case", 3, controller.pickCard(x, y, x+1, y, x+2, y, col) );

        //created error 4 case - adjacent tiles
        x=0; y=1; col=1;
        Assert.assertEquals("error 4 case", 4, controller.pickCard(x, y, x+2, y, col) );
        Assert.assertEquals("error 4 case", 4, controller.pickCard(x, y, x+2, y, x+3, y, col) );

        //created error 2 case - full bookshelf

    }


//setter and getter methods

    public Controller getC() {
        return controller;
    }

    public void setC(Controller controller) {
        this.controller = controller;
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
