package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.model.Model;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the following method: getNumPlayers
 */
public class GetnumPlayersTest {
    public Model getM() {
        return m;
    }

    public void setM(Model m) {
        this.m = m;
    }

    public int getTre() {
        return tre;
    }

    public void setTre(int tre) {
        this.tre = tre;
    }

    Model m = new Model(3);
    private int tre=3;

    @Test
    public void Test(){
        Assert.assertEquals(tre, m.getNumPlayers());
    }
}
