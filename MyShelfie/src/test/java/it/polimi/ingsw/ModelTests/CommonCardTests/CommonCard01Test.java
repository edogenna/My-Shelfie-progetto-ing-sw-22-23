package it.polimi.ingsw.ModelTests.CommonCardTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.CommonCards.CommonCard01;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the first common card
 * @author Samuele Pietro Galli
 */
public class CommonCard01Test {
    private CommonCardStrategy c;
    private ItemEnum[][] matrix;
    public CommonCard01Test() {
        c = new CommonCard01();
        matrix = new ItemEnum[6][5];
    }
    /**
     * This method inserts in a column of an ItemEnum matrix some colors
     * @param j Column
     * @param x1 First color
     * @param x2 Second color
     * @param x3 Third color
     * @param x4 Fourth color
     * @param x5 Fifth color
     * @param x6 Sixth color
     */
    private void insertColumn(int j, ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, ItemEnum x5, ItemEnum x6){
        matrix[0][j]=x1;
        matrix[1][j]=x2;
        matrix[2][j]=x3;
        matrix[3][j]=x4;
        matrix[4][j]=x5;
        matrix[5][j]=x6;
    }

    @Test
    public void Test(){
        //ALL BLANK CASE
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertColumn(0, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.WHITE1, ItemEnum.WHITE1, ItemEnum.WHITE1);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //CORRECT BUT DIFFERENT CASE(2 SQUARES (YELLOW AND BLUE), 2 COLUMNS (BLUE AND GREEN))
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        insertColumn(4, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertColumn(0, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.WHITE1);
        insertColumn(1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.BLUE1);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        insertColumn(4, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.WHITE1);
        insertColumn(1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.BLUE1);
        insertColumn(3, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        insertColumn(4, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insertColumn(0, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.WHITE1);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.BLUE1);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        insertColumn(4, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insertColumn(0, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.WHITE1);
        insertColumn(1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.WHITE1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(2, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.PURPLE1, ItemEnum.PURPLE1, ItemEnum.WHITE1, ItemEnum.WHITE1);
        insertColumn(3, ItemEnum.AZURE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.YELLOW1);
        insertColumn(4, ItemEnum.AZURE1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.PURPLE1, ItemEnum.PURPLE1, ItemEnum.YELLOW1);
        Assert.assertTrue(c.checkBookshelf(matrix));

        Assert.assertTrue(c.getNumber() == 1);

        Assert.assertTrue(c.getCommonCardDesign().equals("Card number 1   Description:\n"+
                "   |=|          Six groups each containing at least\n"+
                "   |=|   x6     2 tiles of the same type (non necessarily\n"+
                "                in the depicted shape). Tiles can be\n"+
                "                different between different groups.\n"));
    }

}
