package it.polimi.ingsw.ModelTests.CommonCardTests;

import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class getActivePlayerNameTest {
    private Player p = new Player("pippo");
    private Player p1 = new Player("S9so");

    private Player p2 = new Player("pluto");

    private Player p3 = new Player("puppo");



    @Test
    public void Test(){
        Assert.assertEquals("pippo", p.getUsername());
        Assert.assertEquals("S9so", p1.getUsername());
        Assert.assertEquals("pluto", p2.getUsername());
        Assert.assertEquals("puppo", p3.getUsername());

    }

}
