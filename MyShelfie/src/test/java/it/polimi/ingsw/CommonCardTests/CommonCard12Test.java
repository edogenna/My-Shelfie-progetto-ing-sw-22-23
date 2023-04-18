package it.polimi.ingsw.CommonCardTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.CommonCards.CommonCard12;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the twelfth common card
 * @author Samuele Pietro Galli
 */
public class CommonCard12Test {

    private CommonCardStrategy c;
    private ItemEnum[][] matrix;
    public CommonCard12Test(){
        c=new CommonCard12();
        matrix= new ItemEnum[6][5];
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
     public void insert(int j, ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, ItemEnum x5, ItemEnum x6){
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
        insert(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insert(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insert(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insert(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insert(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(3, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(4, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insert(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE);
        insert(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(4, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insert(0, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(1, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insert(0, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insert(0, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.YELLOW, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //NOT INCREASING SCALE
        insert(0, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.AZURE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.YELLOW, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.AZURE, ItemEnum.WHITE);
        insert(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.PURPLE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //NOT DECREASING SCALE
        insert(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.AZURE, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(3, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(4, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //NOT INCREASING SCALE
        insert(0, ItemEnum.GREEN, ItemEnum.PURPLE, ItemEnum.AZURE, ItemEnum.PURPLE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(1, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.PURPLE, ItemEnum.YELLOW, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.PURPLE, ItemEnum.AZURE, ItemEnum.WHITE);
        insert(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.WHITE, ItemEnum.PURPLE, ItemEnum.PURPLE);
        insert(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.GREEN, ItemEnum.YELLOW, ItemEnum.PURPLE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //NOT DECREASING SCALE
        insert(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.AZURE, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(4, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

    }

}
