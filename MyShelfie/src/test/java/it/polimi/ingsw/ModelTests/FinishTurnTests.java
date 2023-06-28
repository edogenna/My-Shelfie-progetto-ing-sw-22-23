package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Model;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the following method: finishTurn
 */
public class FinishTurnTests {
    Model model;
    Controller controller;
    @Test
    public void Test(){
        int x;
        model = new Model(2);
        model.setUsernamePlayer("aldo");
        model.setUsernamePlayer("giovanni");
        model.setFirstPlayer();
        controller = new Controller(model);
        x = model.getIdActivePlayer();
        Assert.assertFalse(controller.finishTurn());
        x++;
        x = x%2;
        Assert.assertEquals(x, model.getIdActivePlayer());
//        controller.disconnect("aldo");
    }
}
