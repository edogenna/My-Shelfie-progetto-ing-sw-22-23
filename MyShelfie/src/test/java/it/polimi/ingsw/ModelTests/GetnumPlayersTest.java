package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class GetnumPlayersTest {
    Model m = new Model(3);
    private int tre=3;

    @Test
    public void Test(){
        Assert.assertEquals(tre, m.getNumPlayers());
    }
}
