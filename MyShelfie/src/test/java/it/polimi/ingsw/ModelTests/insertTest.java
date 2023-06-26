package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class insertTest {
    Model model = new Model(2);

    /**
     * this method tests the insertion and deletion of the tiles, respectively, in the bookshelf and the board
     * @author Donato Fiore
     */
    @Test
    public void Test(){
        model.setUsernamePlayer("luca");
        model.setUsernamePlayer("paolo");
        model.setFirstPlayer();
        int col, i, j;
        int x1, y1, x2, y2, x3, y3;
        ItemEnum tile1, tile2, tile3;
        ItemEnum[][] shelf;
                
        col = 0;
        x1 = 5;
        y1 = 5;
        tile1 = model.getBoardMatrix()[x1][y1];
        model.insert(x1, y1, col);
        shelf = model.getActivePlayerBookshelf();
        Assert.assertEquals(ItemEnum.BLANK, model.getBoardMatrix()[x1][y1]);
        Assert.assertEquals(tile1, shelf[5][0]);

        for (i=0; i<9; i++){
            for (j=0; j<9; j++){
                model.getBoard().setItemEnum(i,j,ItemEnum.WHITE1);
            }
        }
        
        x1 = y1 = 0;
        x2 = y2 = 1;
        col = 1;
        tile1 = model.getBoardMatrix()[x1][y1];
        tile2 = model.getBoardMatrix()[x2][y2];
        model.insert(x1, y1, x2, y2, col);
        shelf = model.getActivePlayerBookshelf();
        Assert.assertEquals(ItemEnum.BLANK, model.getBoardMatrix()[x1][y1]);
        Assert.assertEquals(ItemEnum.BLANK, model.getBoardMatrix()[x2][y2]);
        Assert.assertEquals(tile1, shelf[5][1]);
        Assert.assertEquals(tile2, shelf[4][1]);

        x1 = y1 = 2;
        x2 = y2 = 3;
        x3 = y3 = 4;
        col = 2;
        tile1 = model.getBoardMatrix()[x1][y1];
        tile2 = model.getBoardMatrix()[x2][y2];
        tile3 = model.getBoardMatrix()[x3][y3];
        model.insert(x1, y1, x2, y2, x3, y3, col);
        shelf = model.getActivePlayerBookshelf();
        Assert.assertEquals(ItemEnum.BLANK, model.getBoardMatrix()[x1][y1]);
        Assert.assertEquals(ItemEnum.BLANK, model.getBoardMatrix()[x2][y2]);
        Assert.assertEquals(ItemEnum.BLANK, model.getBoardMatrix()[x3][y3]);
        Assert.assertEquals(tile1, shelf[5][2]);
        Assert.assertEquals(tile2, shelf[4][2]);
        Assert.assertEquals(tile3, shelf[3][2]);
    }
}
