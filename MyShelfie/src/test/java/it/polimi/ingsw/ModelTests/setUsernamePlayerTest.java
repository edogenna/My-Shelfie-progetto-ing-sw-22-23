package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import it.polimi.ingsw.model.Model;
import org.junit.Test;

/**
 * Test method to check if setUsernamePlayer works
 * @author Samuele Pietro Galli
 */

public class setUsernamePlayerTest {
    Model m = new Model(3);
    private String x = "pippo";
    private String x1 = "pippo1";
    private String x2 = "pippo2";

    private final String[] arr = new String[] {"pippo", "pippo1", "pippo2"};

    @Test
    public void Test(){
        m.setUsernamePlayer(x);
        m.setUsernamePlayer(x1);
        m.setUsernamePlayer(x2);
        for (int i = 0; i < m.getPlayers().length; i++) {
            Assert.assertEquals(m.getPlayers()[i].getUsername(), arr[i]);
        }
    }

}
