package it.polimi.ingsw.ModelTests.CommonCardTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.CommonCards.CommonCard05;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the fifth common card
 * @author Alessandro Fornara
 */
public class CommonCard05Test {

    private CommonCardStrategy c;
    private ItemEnum[][] matrix;
    public CommonCard05Test(){
        c=new CommonCard05();
        matrix= new ItemEnum[6][5];
    }

    /**
     * This method inserts in the corners of an ItemEnum matrix some colors
     * @param x1 First color
     * @param x2 Second color
     * @param x3 Third color
     * @param x4 Fourth color
     */
    private void insertCorners(ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4){
        matrix[0][0]=x1;
        matrix[0][4]=x2;
        matrix[5][0]=x3;
        matrix[5][4]=x4;
    }

    /**
     * This method tests if the method checkBookshelf is working correctly
     * */
    @Test
    public void Test(){
        insertCorners(ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.YELLOW1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.YELLOW1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.BLUE1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.YELLOW1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.YELLOW1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.BLUE1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insertCorners(ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.YELLOW1);
        Assert.assertTrue(c.checkBookshelf(matrix));

        Assert.assertTrue(c.getNumber() == 5);

        Assert.assertTrue(c.getCommonCardDesign().equals("Card number 5     Description:\n"+
                "|=|       |=|     Four tiles of the same type in the four\n"+
                "                  corners of the bookshelf.\n"+
                "\n"+
                "|=|       |=|\n"));

        Assert.assertEquals(c.getPath(), "/graphics/commongoalcards/5.png");
    }

}
