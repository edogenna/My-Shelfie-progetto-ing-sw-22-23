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
        s=m.getActivePlayerShelf();
        //insert da rivedere
        m.insert(x1,y1,x2,y2,col);
        Assert.assertEquals(m.getBoardMatrix()[x1][y1], ItemEnum.BLANK);
        Assert.assertEquals(tile1, s[5][0]);
        Assert.assertEquals(tile2, s[4][0]);
    }
}
