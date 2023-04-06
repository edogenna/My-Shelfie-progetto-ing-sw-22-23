package it.polimi.ingsw;

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
     * @author Alessandro Fornara
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

    /**
     * This method tests if the method checkBookshelf is working correctly
     * @author Alessandro Fornara
     * */
    @Test
    public void Test(){
        insert(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insert(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insert(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insert(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insert(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert(0, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(1, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.YELLOW, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(2, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(3, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(4, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.YELLOW, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insert(0, ItemEnum.WHITE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(1, ItemEnum.WHITE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(2, ItemEnum.WHITE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(3, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(4, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert(0, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.WHITE);
        insert(1, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.WHITE);
        insert(2, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(3, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(4, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.WHITE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert(0, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.GREEN);
        insert(1, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.GREEN);
        insert(2, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE);
        insert(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.WHITE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insert(0, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.GREEN);
        insert(1, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.GREEN);
        insert(2, ItemEnum.BLANK, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.GREEN, ItemEnum.BLUE, ItemEnum.BLUE);
        insert(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE);
        insert(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE, ItemEnum.WHITE);
        Assert.assertFalse(c.checkBookshelf(matrix));
    }

}
