package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class adjacentTiles2Test {
    Model m = new Model(3);

    private int x1 = 1;
    private int y1 = 1;
    private int x2 = 1;
    private int y2 = 0;
    private int x3 = 4;
    private int y3 = 2;

    /**
     * this method tests the boolean method adjacentTiles with three parameters.
     * @author Samuele Pietro Galli
     */
    @Test
    public void Test(){
        //false first condition
        Assert.assertFalse(m.adjacentTiles(x1,y1,x2,y2,x3,y3));
        //correct case first condition
        x1=0;
        y1=0;
        x2=0;
        y2=1;
        x3=0;
        y3=2;
        Assert.assertTrue(m.adjacentTiles(x1,y1,x2,y2,x3,y3));

        x1=0;
        y1=0;
        x2=1;
        y2=0;
        x3=2;
        y3=0;
        Assert.assertTrue(m.adjacentTiles(x1,y1,x2,y2,x3,y3));

    }
}
