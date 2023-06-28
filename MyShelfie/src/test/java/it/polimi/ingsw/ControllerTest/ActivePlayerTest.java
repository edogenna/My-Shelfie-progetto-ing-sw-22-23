package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.controller.Controller;
import org.junit.Assert;
import org.junit.Test;

/**
 * ActivePlayerTest tests the methods of the class Controller that manage the active player
 */
public class ActivePlayerTest {
    Controller controller;
    @Test
    public void Test(){
        controller = new Controller(2);
        controller.setUsernamePlayer("luca");
        controller.setUsernamePlayer("paolo");
        controller.setFirstPlayer();
        controller.setActivePlayer(1);
        Assert.assertEquals(1, controller.getIdActivePlayer());
        controller.setActivePlayer(0);
        Assert.assertEquals(0, controller.getIdActivePlayer());
        controller.setActivePlayer(0);
        Assert.assertNotEquals(1, controller.getIdActivePlayer());
    }
}
