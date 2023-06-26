package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class ControlCommonCardsTest {
    Model model = new Model(2);

    @Test
    public void Test(){
        model.setUsernamePlayer("luca");
        model.setUsernamePlayer("paolo");
        model.setFirstPlayer();

        Assert.assertFalse(model.controlCommonCards(0));
        Assert.assertFalse(model.controlCommonCards(1));

        int x = model.getIdActivePlayer();
        Player[] player = model.getPlayers();
        model.getCommonCards();
        //TODO: prepare a bookshelf that claim the common Card
        //Assert.assertTrue(model.controlCommonCards(0));
        player[x].updateCommonPoints(4,0);
        Assert.assertEquals(4, player[x].calculatePoints());
    }
}
