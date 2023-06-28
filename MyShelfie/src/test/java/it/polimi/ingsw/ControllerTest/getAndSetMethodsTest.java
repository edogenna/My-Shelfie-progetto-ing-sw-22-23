package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.controller.Controller;
import org.junit.Assert;
import org.junit.Test;

public class getAndSetMethodsTest {

    Controller controller = new Controller(2);
    @Test
    public void Test() {

        controller.setUsernamePlayer("player1");
        controller.setUsernamePlayer("player2");
        controller.setFirstPlayer();
        controller.setActivePlayer(1);
        Assert.assertEquals(1, controller.getIdActivePlayer());
        controller.setStopMatch();
        Assert.assertFalse(controller.getStopMatch());
        Assert.assertNotNull(controller.getBoard());
        Assert.assertNotNull(controller.getCommonCardsDesigns());
        Assert.assertNotNull(controller.getModelSave());
        Assert.assertNotNull(controller.getPlayerBookshelf("player1"));
        Assert.assertNotNull(controller.getPlayerBookshelf("player2"));
        Assert.assertTrue(controller.getActivePlayerUsername().equals("player1") || controller.getActivePlayerUsername().equals("player2"));
        Assert.assertNotNull(controller.getPlayerPersonalCard("player1"));
        Assert.assertNotNull(controller.getPlayerPersonalCard("player2"));
    }
}
