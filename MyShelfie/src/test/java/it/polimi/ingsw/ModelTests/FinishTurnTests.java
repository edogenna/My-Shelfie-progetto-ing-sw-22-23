package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class FinishTurnTests {
    Model model;
    @Test
    public void Test(){
        int x;
        model = new Model(2);
        model.setUsernamePlayer("aldo");
        model.setUsernamePlayer("giovanni");
        model.setFirstPlayer();
        x = model.getIdActivePlayer();
        Assert.assertFalse(model.finishTurn());
        x++;
        x = x%2;
        Assert.assertEquals(x, model.getIdActivePlayer());
    }
}
