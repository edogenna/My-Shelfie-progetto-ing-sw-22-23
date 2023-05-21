package it.polimi.ingsw.ModelTests.CommonCardTests;



import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.CommonCards.CommonCard02;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the second common card
 * @author Samuele Pietro Galli
 */
public class CommonCard02Test {

    private CommonCardStrategy c;
    private ItemEnum[][] matrix;
    public CommonCard02Test(){
        c = new CommonCard02();
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
        matrix[0][j] = x1;
        matrix[1][j] = x2;
        matrix[2][j] = x3;
        matrix[3][j] = x4;
        matrix[4][j] = x5;
        matrix[5][j] = x6;
    }




    /**
     * This method tests if the method checkBookshelf is working correctly
     * */
    @Test
    public void Test(){
        //ALL BLANK CASE
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //TRUE CASE
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.BLUE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(4, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.YELLOW, ItemEnum.PURPLE, ItemEnum.WHITE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //TRUE CASE
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.BLUE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(4, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.YELLOW, ItemEnum.PURPLE, ItemEnum.WHITE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //TRUE CASE
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //TRUE CASE
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.PURPLE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.WHITE, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //WRONG COLOUR IN DIAGONAL
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.PURPLE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.GREEN, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.WHITE, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //WRONG COLOUR IN DIAGONAL
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.GREEN);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.PURPLE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.WHITE, ItemEnum.BLUE, ItemEnum.PURPLE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //INCREASING DIAGONAL CASE
        insertColumn(0, ItemEnum.GREEN, ItemEnum.PURPLE, ItemEnum.AZURE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.PURPLE, ItemEnum.YELLOW, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.PURPLE, ItemEnum.AZURE, ItemEnum.WHITE);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.WHITE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.GREEN, ItemEnum.YELLOW, ItemEnum.PURPLE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //INCREASING DIAGONAL CASE
        insertColumn(0, ItemEnum.GREEN, ItemEnum.PURPLE, ItemEnum.AZURE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.PURPLE, ItemEnum.AZURE, ItemEnum.WHITE);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.WHITE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.GREEN, ItemEnum.YELLOW, ItemEnum.PURPLE);
        Assert.assertFalse(c.checkBookshelf(matrix));

    }

}
