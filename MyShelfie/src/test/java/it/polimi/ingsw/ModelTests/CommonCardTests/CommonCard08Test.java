package it.polimi.ingsw.ModelTests.CommonCardTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.CommonCards.CommonCard08;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the eighth common card
 * @author Samuele Pietro Galli
 */
public class CommonCard08Test {
    private CommonCardStrategy c;
    private ItemEnum[][] matrix;
    public CommonCard08Test() {
        c = new CommonCard08();
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
     public void insertColumn(int j, ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, ItemEnum x5, ItemEnum x6){
        matrix[0][j]=x1;
        matrix[1][j]=x2;
        matrix[2][j]=x3;
        matrix[3][j]=x4;
        matrix[4][j]=x5;
        matrix[5][j]=x6;
    }

    @Test
    public void Test(){
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //CORRECT CASE
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.GREEN);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.AZURE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.WHITE, ItemEnum.AZURE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));


        //CORRECT CASE
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.GREEN);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.WHITE, ItemEnum.AZURE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.WHITE, ItemEnum.AZURE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.AZURE, ItemEnum.YELLOW, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //WRONG CASE
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.GREEN);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.WHITE, ItemEnum.AZURE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.BLUE, ItemEnum.AZURE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.AZURE, ItemEnum.YELLOW, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //CORRECT CASE
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.GREEN);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.AZURE, ItemEnum.BLANK, ItemEnum.WHITE, ItemEnum.AZURE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.WHITE, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.WHITE, ItemEnum.AZURE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.BLANK, ItemEnum.AZURE, ItemEnum.YELLOW, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //CORRECT CASE
        insertColumn(0, ItemEnum.PURPLE, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN);
        insertColumn(1, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.WHITE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.GREEN);
        insertColumn(2, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.WHITE, ItemEnum.AZURE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.YELLOW, ItemEnum.AZURE);
        insertColumn(4, ItemEnum.WHITE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.AZURE, ItemEnum.YELLOW, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //CORRECT CASE
        insertColumn(0, ItemEnum.PURPLE, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.GREEN);
        insertColumn(1, ItemEnum.PURPLE, ItemEnum.BLUE, ItemEnum.WHITE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.GREEN);
        insertColumn(2, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.WHITE, ItemEnum.AZURE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.YELLOW, ItemEnum.AZURE);
        insertColumn(4, ItemEnum.WHITE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.AZURE, ItemEnum.YELLOW, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));
    }
}
