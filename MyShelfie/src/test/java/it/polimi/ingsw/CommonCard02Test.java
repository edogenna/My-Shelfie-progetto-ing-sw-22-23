package it.polimi.ingsw;



import it.polimi.ingsw.model.CommonCards.CommonCard02;
import it.polimi.ingsw.model.CommonCards.CommonCard05;
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
        c=new CommonCard02();
        matrix= new ItemEnum[6][5];
    }

    /**
     * This method inserts an ItemEnum some colors in specified positions
     * @author Samuele Pietro Galli
     * @param x1 First color
     * @param x2 Second color
     * @param x3 Third color
     * @param x4 Fourth color
     * @param x5 Fifth color
     */
    public void insert1(ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, ItemEnum x5){
        matrix[0][0]=x1;
        matrix[1][1]=x2;
        matrix[2][2]=x3;
        matrix[3][3]=x4;
        matrix[4][4]=x5;
    }

    /**
     * This method inserts an ItemEnum some colors in specified positions
     * @author Samuele Pietro Galli
     * @param x1 First color
     * @param x2 Second color
     * @param x3 Third color
     * @param x4 Fourth color
     * @param x5 Fifth color
     */
    public void insert2(ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, ItemEnum x5){
        matrix[1][0]=x1;
        matrix[2][1]=x2;
        matrix[3][2]=x3;
        matrix[4][3]=x4;
        matrix[5][4]=x5;
    }

    /**
     * This method inserts an ItemEnum some colors in specified positions
     * @author Samuele Pietro Galli
     * @param x1 First color
     * @param x2 Second color
     * @param x3 Third color
     * @param x4 Fourth color
     * @param x5 Fifth color
     */
    public void insert3(ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, ItemEnum x5){
        matrix[0][4]=x1;
        matrix[1][3]=x2;
        matrix[2][2]=x3;
        matrix[3][1]=x4;
        matrix[4][0]=x5;
    }

    /**
     * This method inserts an ItemEnum some colors in specified positions
     * @author Samuele Pietro Galli
     * @param x1 First color
     * @param x2 Second color
     * @param x3 Third color
     * @param x4 Fourth color
     * @param x5 Fifth color
     */
    public void insert4(ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, ItemEnum x5){
        matrix[1][4]=x1;
        matrix[2][3]=x2;
        matrix[3][2]=x3;
        matrix[4][1]=x4;
        matrix[5][0]=x5;
    }

    /**
     * This method tests if the method checkBookshelf is working correctly
     * @author Samuele Pietro Galli
     * */
    @Test
    public void Test(){
        insert1(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        /*INIZIO FASE ASSERTFALSE*/

        insert1(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        /*
        *
        *
        *
        *
        *
        * */

        insert1(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert1(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert1(ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert1(ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.YELLOW, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE, ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

    }

}
