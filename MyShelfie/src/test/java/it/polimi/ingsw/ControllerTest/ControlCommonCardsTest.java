package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.controller.Controller;
import org.junit.Assert;
import org.junit.Test;

/**
 * ControlCommonCardsTest tests the methods of the class Controller that manage the common cards
 */
public class ControlCommonCardsTest {
    Controller controller;

    @Test
    public void Test(){
        controller = new Controller(2);
        controller.setUsernamePlayer("pio");
        controller.setUsernamePlayer("amedeo");
        controller.setFirstPlayer();
        Assert.assertEquals(2, controller.getNumPlayers());
        for(int i=0; i<2; i++)
            Assert.assertEquals("id card = " + i, 0, controller.controlCommonCards(i));
    }
}
