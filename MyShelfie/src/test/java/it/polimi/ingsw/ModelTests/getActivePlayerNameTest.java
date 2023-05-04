package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class getActivePlayerNameTest {
    private Player p = new Player("pippo");
    private Player p1 = new Player("S9so");

    private Player p2 = new Player("pluto");

    private Player p3 = new Player("puppo");

    Model m = new Model(2);


    @Test
    public void Test(){
        m.setUsernamePlayer("pippo");
        m.setFirstPlayer();
        m.setUsernamePlayer("S9so");
        Assert.assertEquals(p.getUsername(), m.getPlayers()[0].getUsername());
        Assert.assertEquals(p1.getUsername(), m.getPlayers()[1].getUsername());

    }

}
