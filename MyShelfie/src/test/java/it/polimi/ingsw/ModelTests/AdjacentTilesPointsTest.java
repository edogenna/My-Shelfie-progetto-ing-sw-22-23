package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.PersonalCards;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class AdjacentTilesPointsTest {
    Player player;
    CharMatrix printMatrix;
    @Test
    public void Test(){
        ItemEnum[][] matrix = new ItemEnum[6][5];
        player = new Player("luca");
        printMatrix = new CharMatrix();
        PersonalCards personalCards = new PersonalCards();

        for(int i=0; i<6; i++)
            for(int j=0; j<5; j++)
                matrix[i][j] = ItemEnum.BLANK;

        matrix[0][0] = matrix[0][4] = matrix[2][4] = matrix[4][3] = ItemEnum.AZURE2;
        matrix[1][0] = matrix[2][3] = matrix[5][4] = ItemEnum.BLUE1;
        matrix[2][0] = matrix[3][1] = matrix[4][2] = matrix[5][2] = matrix[5][3] = matrix[0][3]= ItemEnum.GREEN1;
        matrix[3][0] = matrix[3][3] = ItemEnum.YELLOW1;
        matrix[4][0] = matrix[2][1] = matrix[1][3] = ItemEnum.PURPLE3;
        matrix[1][1] = matrix[1][3] = ItemEnum.PURPLE2;
        matrix[5][0] = matrix[5][1] = matrix[4][1] = matrix[3][4] = matrix[4][4] = ItemEnum.WHITE1;

        player.setBookshelf(matrix);
        printMatrix = ItemEnum.generateCharMatrix(matrix, 6, 5).addColumnNumbering(5);
        printMatrix.printMatrix();
        player.setPersonalCard(personalCards.getCard(11));

        Assert.assertEquals(13, player.calculatePoints());

    }
}
