package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;


/**
 * test for the function used in @Link{finishTurn()} in Model class
 * @author Samuele Pietro Galli
 */
public class checkIfFullTest {
    Player player = new Player("pippo");
    @Test
    public void fullShelf(){
         for (int j = 0; j<5; j++){
                player.insert(j, ItemEnum.WHITE);
                player.insert(j, ItemEnum.WHITE);
                player.insert(j, ItemEnum.WHITE);
                player.insert(j, ItemEnum.WHITE);
                player.insert(j, ItemEnum.WHITE);
                player.insert(j, ItemEnum.WHITE);
            }
        Assert.assertTrue(player.checkIfFull());
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
