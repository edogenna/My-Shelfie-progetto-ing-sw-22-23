package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class insertTest {
    Model m = new Model(2);

    Player p1;
    Player p2;


    private ItemEnum[][] s;
    private int x, x1;
    private int y, y1;
    private int col;
    private ItemEnum tile;

    /**
     * this method tests the insertion and deletion of a tile, respectively, in the bookshelf and the board
     * @author Samuele Pietro Galli
     */
    @Test
    public void Test(){
        m.setUsernamePlayer("pollo");
        m.setUsernamePlayer("pollo1");
        m.setFirstPlayer();
        x1=4;
        y1=6;
        col=0;
        tile = m.getBoardMatrix()[x1][y1];

        //insert da rivedere
        m.insert(x1,y1,col);
        s=m.getShelf();
        Assert.assertEquals(m.getBoardMatrix()[x1][y1], ItemEnum.BLANK);
        Assert.assertEquals(tile, s[5][0]);
    }

}
