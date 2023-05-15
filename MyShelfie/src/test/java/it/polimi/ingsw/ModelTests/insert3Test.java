package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class insert3Test {
    Model m = new Model(2);

    Player p1;
    Player p2;


    private ItemEnum[][] s;
    private int x3, x2, x1;
    private int y3, y2, y1;
    private int col;
    private ItemEnum tile1, tile2, tile3;

    //seleziono tre tessere e le metto dentro
    @Test
    public void Test(){
        m.setUsernamePlayer("pollo");
        m.setUsernamePlayer("pollo1");
        m.setFirstPlayer();
        x1=4;
        y1=6;
        x2=5;
        y2=7;
        x3=6;
        y3=8;
        col=0;
        tile1 = m.getBoardMatrix()[x1][y1];
        tile2 = m.getBoardMatrix()[x2][y2];
        tile3 = m.getBoardMatrix()[x3][y3];
        s=m.getActivePlayerShelf();
        //TODO: riguardare le coordinate delle tre tessere
        m.insert(x1,y1,col);
        Assert.assertEquals(m.getBoardMatrix()[x1][y1], ItemEnum.BLANK);
        Assert.assertEquals(tile1, s[5][0]);
        Assert.assertEquals(tile2, s[4][0]);
        Assert.assertEquals(tile3, s[3][0]);
    }

    public Model getM() {
        return m;
    }

    public void setM(Model m) {
        this.m = m;
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public ItemEnum[][] getS() {
        return s;
    }

    public void setS(ItemEnum[][] s) {
        this.s = s;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public ItemEnum getTile1() {
        return tile1;
    }

    public void setTile1(ItemEnum tile1) {
        this.tile1 = tile1;
    }

    public ItemEnum getTile2() {
        return tile2;
    }

    public void setTile2(ItemEnum tile2) {
        this.tile2 = tile2;
    }

    public ItemEnum getTile3() {
        return tile3;
    }

    public void setTile3(ItemEnum tile3) {
        this.tile3 = tile3;
    }
}
