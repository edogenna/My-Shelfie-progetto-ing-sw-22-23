package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class insert2Test {
    Model m = new Model(2);

    Player p1;
    Player p2;


    private ItemEnum[][] s;
    private int x2, x1;
    private int y2, y1;
    private int col;
    private ItemEnum tile1, tile2;
    @Test
    public void Test(){
        m.setUsernamePlayer("pollo");
        m.setUsernamePlayer("pollo1");
        m.setFirstPlayer();
        x1=4;
        y1=6;
        x2=4;
        y2=7;
        col=0;
        tile1 = m.getBoardMatrix()[x1][y1];
        tile2 = m.getBoardMatrix()[x2][y2];
        s=m.getShelf();
        //insert da rivedere
        m.insert(x1,y1,x2,y2,col);
        Assert.assertEquals(m.getBoardMatrix()[x1][y1], ItemEnum.BLANK);
        Assert.assertEquals(tile1, s[5][0]);
        Assert.assertEquals(tile2, s[4][0]);
    }
}
