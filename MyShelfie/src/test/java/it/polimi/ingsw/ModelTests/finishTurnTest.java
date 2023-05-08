package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class finishTurnTest {
    Player player = new Player("ale");
    Model model = new Model(2);
    public ItemEnum tile = ItemEnum.GREEN;
    @Test
    public void testFullfield(){
        model.setUsernamePlayer(player.getUsername());
        model.setFirstPlayer();
        for (int i = 0; i < 5; i++){
            for (int j = 0; j<6; j++){
                player.insert(i,tile);
            }
        }
        Assert.assertTrue(model.finishTurn());



    }
}
