package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.controller.Controller;
import org.junit.Assert;
import org.junit.Test;

public class disconnectTest {

    private Controller controller = new Controller(3);

    @Test
    public void Test() {
        controller.setUsernamePlayer("player1");
        controller.setUsernamePlayer("player2");
        controller.setUsernamePlayer("player3");

        controller.disconnect("player1");
        controller.disconnect("player2");

        Assert.assertTrue(controller.isDisconnected("player1"));
        Assert.assertTrue(controller.isDisconnected("player2"));
        controller.reconnect("player1");
        Assert.assertFalse(controller.isDisconnected("player1"));
        controller.reconnect("player2");
        Assert.assertFalse(controller.isDisconnected("player2"));

        controller.disconnect("player3");
        Assert.assertTrue(controller.isDisconnected("player3"));

        controller.reconnect("player3");
        Assert.assertFalse(controller.isDisconnected("player3"));
    }
}
