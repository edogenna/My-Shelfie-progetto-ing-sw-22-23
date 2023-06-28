package it.polimi.ingsw.ModelTests.CommonCardTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.CommonCards.CommonCard09;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the ninth common card
 * @author Alessandro Fornara
 */
public class CommonCard09Test {

    private CommonCardStrategy c;
    private ItemEnum[][] matrix;
    public CommonCard09Test(){
        c=new CommonCard09();
        matrix= new ItemEnum[6][5];
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

    /**
     * This method tests if the method checkBookshelf is working correctly
     * */
    @Test
    public void Test(){

        //Blank matrix --> false
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //5 correct columns --> true
        insertColumn(0, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(1, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(2, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(3, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(4, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.YELLOW1, ItemEnum.BLUE1);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //2 correct columns --> false
        insertColumn(0, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(1, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(2, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(3, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(4, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //1 correct column --> false
        insertColumn(0, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.WHITE1);
        insertColumn(1, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.WHITE1);
        insertColumn(2, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(3, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.WHITE1);
        insertColumn(4, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.WHITE1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //3 correct columns and 2 incorrect columns --> true
        insertColumn(0, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.GREEN1);
        insertColumn(1, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.GREEN1);
        insertColumn(2, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.WHITE1);
        Assert.assertTrue(c.checkBookshelf(matrix));

        //5 not full columns --> false
        insertColumn(0, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.GREEN1);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.GREEN1);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.WHITE1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        Assert.assertTrue(c.getNumber() == 9);

        Assert.assertTrue(c.getCommonCardDesign().equals("| ■ | number 9  Description:\n"+
                "| ■ |           Three column each formed by 6 tiles\n"+
                "| ■ |  MAX 3 ≠  of maximum three different types. One\n"+
                "| ■ |    x3     column can show the same or a different\n"+
                "| ■ |           combination of another column.\n"+
                "| ■ |\n"));

        Assert.assertEquals(c.getPath(), "/graphics/commongoalcards/9.png");

    }

}
