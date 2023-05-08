package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class pickCardTest {
    Model m;
    Controller c = new Controller(m);
    private int x, y, col;
    public pickCardTest(){
        x=2; y=3; col=2;
        m=c.getModel();
        m.getBoardMatrix();
        c.pickCard(x, y, col);
//        Assert.assertEquals();
    }
//setter and getter methods
    public Model getM() {
        return m;
    }

    public void setM(Model m) {
        this.m = m;
    }

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
