package it.polimi.ingsw.ModelTests.CommonCardTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.CommonCards.CommonCard07;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the seventh common card
 * @author Samuele Pietro Galli
 */
public class CommonCard07Test {
    private CommonCardStrategy c;
    private ItemEnum[][] matrix;
    public CommonCard07Test() {
        c = new CommonCard07();
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
         //all blank case
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //correct case
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.YELLOW);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //upper correct case
        insertColumn(0, ItemEnum.PURPLE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN);
        insertColumn(1, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.GREEN);
        insertColumn(2, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.WHITE, ItemEnum.AZURE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.YELLOW, ItemEnum.YELLOW);
        insertColumn(4, ItemEnum.WHITE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.AZURE, ItemEnum.YELLOW, ItemEnum.YELLOW);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //wrong case
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.YELLOW);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.WHITE);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //upper wrong case
        insertColumn(0, ItemEnum.PURPLE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN);
        insertColumn(1, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.GREEN);
        insertColumn(2, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.WHITE, ItemEnum.AZURE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.YELLOW, ItemEnum.YELLOW);
        insertColumn(4, ItemEnum.WHITE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.AZURE, ItemEnum.YELLOW, ItemEnum.YELLOW);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //correct BOTTOM-corner case
        insertColumn(0, ItemEnum.PURPLE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.GREEN);
        insertColumn(1, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.PURPLE, ItemEnum.GREEN, ItemEnum.GREEN);
        insertColumn(2, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.WHITE, ItemEnum.AZURE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.YELLOW, ItemEnum.YELLOW);
        insertColumn(4, ItemEnum.WHITE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.AZURE, ItemEnum.YELLOW, ItemEnum.YELLOW);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //correct UPPER-corner case
        insertColumn(0, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.GREEN);
        insertColumn(1, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.PURPLE, ItemEnum.YELLOW, ItemEnum.GREEN);
        insertColumn(2, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.WHITE, ItemEnum.AZURE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.YELLOW, ItemEnum.YELLOW);
        insertColumn(4, ItemEnum.WHITE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.AZURE, ItemEnum.YELLOW, ItemEnum.YELLOW);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //wrong UPPER-corner case
        insertColumn(0, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.GREEN);
        insertColumn(1, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.PURPLE, ItemEnum.YELLOW, ItemEnum.GREEN);
        insertColumn(2, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.WHITE, ItemEnum.AZURE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.YELLOW, ItemEnum.YELLOW);
        insertColumn(4, ItemEnum.YELLOW, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.AZURE, ItemEnum.GREEN, ItemEnum.YELLOW);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //wrong adjacent case
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //CORRECT adjacent case
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

    }
}
