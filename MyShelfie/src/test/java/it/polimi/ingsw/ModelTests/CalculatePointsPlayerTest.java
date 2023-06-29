package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.PersonalCards;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class CalculatePointsPlayerTest {
    Player player;
    PersonalCards personalCards = new PersonalCards();
    @Test
    public void Test(){
        ItemEnum[][] matrix = new ItemEnum[6][5];
        for(int i=0; i<6; i++)
            for(int j=0;j<5; j++)
                matrix[i][j] = ItemEnum.BLUE1;

        player = new Player("antonio");
        player.setPersonalCard(personalCards.getCard(0));
        player.setBookshelf(matrix);
        player.updateCommonPoints(8,0);
        Assert.assertEquals(17, player.calculatePoints());

        player = new Player("antonio");
        player.setPersonalCard(personalCards.getCard(0));
        matrix[2][0] = ItemEnum.AZURE1;
        matrix[4][1] = ItemEnum.WHITE1;
        player.setBookshelf(matrix);
        player.updateCommonPoints(8,0);
        Assert.assertEquals(20, player.calculatePoints());

        player = new Player("antonio");
        player.setPersonalCard(personalCards.getCard(0));
        matrix[4][2] = ItemEnum.GREEN1;
        player.setBookshelf(matrix);
        player.updateCommonPoints(8,0);
        Assert.assertEquals(22, player.calculatePoints());

        player = new Player("antonio");
        player.setPersonalCard(personalCards.getCard(0));
        matrix[3][3] = ItemEnum.PURPLE1;
        player.setBookshelf(matrix);
        player.updateCommonPoints(8,0);
        player.updateCommonPoints(4,1);
        Assert.assertEquals(29, player.calculatePoints());

        player = new Player("antonio");
        player.setPersonalCard(personalCards.getCard(0));
        matrix[0][4] = ItemEnum.YELLOW1;
        player.setBookshelf(matrix);
        player.updateCommonPoints(8,0);
        Assert.assertEquals(28, player.calculatePoints());
    }
}