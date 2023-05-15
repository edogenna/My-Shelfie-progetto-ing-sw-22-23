package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Model;
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
        s=m.getActivePlayerShelf();
        Assert.assertEquals(m.getBoardMatrix()[x1][y1], ItemEnum.BLANK);
        Assert.assertEquals(tile, s[5][0]);
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public ItemEnum getTile() {
        return tile;
    }

    public void setTile(ItemEnum tile) {
        this.tile = tile;
    }

    public Model getM() {
        return m;
    }

    public void setM(Model m) {
        this.m = m;
    }
}
