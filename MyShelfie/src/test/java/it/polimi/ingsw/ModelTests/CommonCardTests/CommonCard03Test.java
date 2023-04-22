package it.polimi.ingsw.ModelTests.CommonCardTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;
import it.polimi.ingsw.model.CommonCards.CommonCard03;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the third common card
 * @author Samuele Pietro Galli, Alessandro Fornara
 */
public class CommonCard03Test {
    private CommonCardStrategy c;
    private ItemEnum[][] matrix;
    public CommonCard03Test() {
        c = new CommonCard03();
        matrix = new ItemEnum[6][5];
    }
    /**
     * This method inserts in a column of an ItemEnum matrix some colors
     * @author Alessandro Fornara, Samuele Pietro Galli
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

        //CORRECT CASE
        insertColumn(0, ItemEnum.BLANK, ItemEnum.GREEN, ItemEnum.WHITE, ItemEnum.WHITE, ItemEnum.WHITE, ItemEnum.WHITE);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.GREEN, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.GREEN, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.YELLOW);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.YELLOW);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //CORRECT BUT DIFFERENT CASE(2 SQUARES (YELLOW AND BLUE), 2 COLUMNS (BLUE AND GREEN))
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.WHITE, ItemEnum.WHITE, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(1, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.WHITE, ItemEnum.WHITE, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.WHITE, ItemEnum.YELLOW, ItemEnum.YELLOW);
        insertColumn(4, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.YELLOW, ItemEnum.YELLOW);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insertColumn(0, ItemEnum.BLANK, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.WHITE, ItemEnum.WHITE);
        insertColumn(1, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.WHITE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.WHITE, ItemEnum.BLUE);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.WHITE, ItemEnum.YELLOW, ItemEnum.YELLOW);
        insertColumn(4, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.YELLOW, ItemEnum.YELLOW);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.WHITE, ItemEnum.WHITE);
        insertColumn(1, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.WHITE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.WHITE, ItemEnum.BLUE);
        insertColumn(3, ItemEnum.GREEN, ItemEnum.WHITE, ItemEnum.BLUE, ItemEnum.WHITE, ItemEnum.YELLOW, ItemEnum.YELLOW);
        insertColumn(4, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.YELLOW, ItemEnum.YELLOW);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertColumn(0, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.WHITE, ItemEnum.WHITE);
        insertColumn(1, ItemEnum.YELLOW, ItemEnum.YELLOW, ItemEnum.WHITE, ItemEnum.WHITE, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(2, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.WHITE, ItemEnum.WHITE);
        insertColumn(3, ItemEnum.AZURE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.YELLOW);
        insertColumn(4, ItemEnum.AZURE, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.YELLOW);
        Assert.assertFalse(c.checkBookshelf(matrix));
    }

}
