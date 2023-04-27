package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class adjacentTilesTest {

    Model m = new Model(2);
    private int x1 = 1;
    private int y1 = 1;
    private int x2 = 1;
    private int y2 = 0;

    private int x3 = 4;

    private int y3 = 2;

    /**
     * this method tests the boolean function adjacentTiles.
     * @author Samuele Pietro Galli
     */


    @Test
    public void Test(){
        Assert.assertTrue(m.adjacentTiles(x1, y1, x2, y2));
        Assert.assertFalse(m.adjacentTiles(x1, y1, x3, y3));
    }

}
