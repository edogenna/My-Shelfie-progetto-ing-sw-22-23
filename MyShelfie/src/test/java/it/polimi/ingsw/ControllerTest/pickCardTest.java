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
    private ItemEnum[][] board = new ItemEnum[9][9];

    @Test
    public void test(){
        int i, j, k;
        for (i = 0; i<9; i++){
            for (j=0; j<9;j++)
                board[i][j]= ItemEnum.BLUE1;
        }
        model.setUsernamePlayer("giovanni");
        model.setUsernamePlayer("luca");
        model.setFirstPlayer();

        //created correct 0 case - done
        for (i=0; i<9; i++){
            for (j=0; j<9; j++){
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
        for (i=0; i<9; i++){
            for (j=0; j<9; j++){
                model.getBoard().setItemEnum(i,j,ItemEnum.BLANK);
            }
        }
        x=0; y=0; col=0;
        Assert.assertEquals("error 1 case", 1, controller.pickCard(x, y, col) );
        Assert.assertEquals("error 1 case", 1, controller.pickCard(x, y, x+1, y, col));
        Assert.assertEquals("error 1 case", 1, controller.pickCard(x, y, x+1, y, x+2, y, col));

        //created error 5 case - no free space in column
        for (i=0; i<9; i++){
            for (j=0; j<9; j++){
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

        x=1; y=1; col=2;
        Assert.assertEquals("error 3 case", 3, controller.pickCard(x, y, x, y+1, col) );
        Assert.assertEquals("error 3 case", 3, controller.pickCard(x, y, x+1, y, x+2, y+2, col) );
        Assert.assertEquals("error 3 case", 3, controller.pickCard(x, y, x+2, y+2, x+1, y, col) );

        //created error 4 case - adjacent tiles
        x=0; y=1; col=1;
        Assert.assertEquals("error 4 case", 4, controller.pickCard(x, y, x+2, y, col) );
        Assert.assertEquals("error 4 case", 4, controller.pickCard(x, y, x+2, y, x+3, y, col) );

        //created error 2 case - full bookshelf
        col = 2;
        k = 0;
        for(x=0; x<9 && col<5; x++){
            for(y=1; y<9 && col<5; y++){
                Assert.assertEquals("preparing the error 2 case, x = " + x + ", y = " + y + ", col = " + col,
                                    0, controller.pickCard(x, y, col));
                k++;
                if(k==6){
                    col++;
                    k = 0;
                }
            }
        }
        Assert.assertEquals("error 2 case", 2, controller.pickCard(x, y, col) );
        Assert.assertEquals("error 2 case", 2, controller.pickCard(x, y, x+1, y, col) );
        Assert.assertEquals("error 2 case", 2, controller.pickCard(x, y, x+1, y, x+2, y, col) );
    }
}