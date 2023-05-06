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

    public Model getM() {
        return m;
    }

    public void setM(Model m) {
        this.m = m;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }
}
