package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class insertTest {
    Model m = new Model(4);
    Board b = new Board(4);

    Player p = new Player("pollo");

    private ItemEnum[][] s = m.getShelf();
    private int x, x1;
    private int y, y1;
    private int col;
    private ItemEnum tile = b.getItemEnum(x1, y1);
    @Test
    public void Test(){
        x=0;
        y=0;
        col=0;
        x1=2;
        y1=2;
        m.insert(x,y,col);
        Assert.assertEquals(b.getItemEnum(x1,y1), ItemEnum.BLANK);
        Assert.assertEquals(tile, m.getShelf()[0][0]);
    }

}
