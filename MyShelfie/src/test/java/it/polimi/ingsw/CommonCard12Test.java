package it.polimi.ingsw;



import it.polimi.ingsw.model.CommonCards.CommonCard02;
import it.polimi.ingsw.model.CommonCards.CommonCard05;
import it.polimi.ingsw.model.CommonCards.CommonCard12;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the second common card
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
     * This method inserts an ItemEnum some colors in specified positions
     * @author Samuele Pietro Galli
     * @param x1 color
     * @param x2 color
     * @param x3 color
     * @param x4 color
     * @param x5 color
     * @param x6 color
     * @param x7 color
     * @param x8 color
     * @param x9 color
     * @param x10 color
     * @param x11 color
     * @param x12 color
     * @param x13 color
     * @param x14 color
     * @param x15 color
     */
    public void insert1(ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, ItemEnum x5,
                        ItemEnum x6, ItemEnum x7, ItemEnum x8, ItemEnum x9, ItemEnum x10,
                        ItemEnum x11, ItemEnum x12, ItemEnum x13, ItemEnum x14, ItemEnum x15){
        matrix[0][0]=x1;
        matrix[1][0]=x2;
        matrix[1][1]=x3;
        matrix[2][0]=x4;
        matrix[2][1]=x5;
        matrix[2][2]=x6;
        matrix[3][0]=x7;
        matrix[3][1]=x8;
        matrix[3][2]=x9;
        matrix[3][3]=x10;
        matrix[4][0]=x11;
        matrix[4][1]=x12;
        matrix[4][2]=x13;
        matrix[4][3]=x14;
        matrix[4][4]=x15;
    }
    /**
     * This method inserts an ItemEnum some colors in specified positions
     * @author Samuele Pietro Galli
     * @param x1 color
     * @param x2 color
     * @param x3 color
     * @param x4 color
     * @param x5 color
     * @param x6 color
     * @param x7 color
     * @param x8 color
     * @param x9 color
     * @param x10 color
     * @param x11 color
     * @param x12 color
     * @param x13 color
     * @param x14 color
     * @param x15 color
     */
    public void insert2(ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, ItemEnum x5,
                        ItemEnum x6, ItemEnum x7, ItemEnum x8, ItemEnum x9, ItemEnum x10,
                        ItemEnum x11, ItemEnum x12, ItemEnum x13, ItemEnum x14, ItemEnum x15){
        matrix[1][0]=x1;
        matrix[2][0]=x2;
        matrix[2][1]=x3;
        matrix[3][0]=x4;
        matrix[3][1]=x5;
        matrix[3][2]=x6;
        matrix[4][0]=x7;
        matrix[4][1]=x8;
        matrix[4][2]=x9;
        matrix[4][3]=x10;
        matrix[5][0]=x11;
        matrix[5][1]=x12;
        matrix[5][2]=x13;
        matrix[5][3]=x14;
        matrix[5][4]=x15;
    }
    /**
     * This method inserts an ItemEnum some colors in specified positions
     * @author Samuele Pietro Galli
     * @param x1 color
     * @param x2 color
     * @param x3 color
     * @param x4 color
     * @param x5 color
     * @param x6 color
     * @param x7 color
     * @param x8 color
     * @param x9 color
     * @param x10 color
     * @param x11 color
     * @param x12 color
     * @param x13 color
     * @param x14 color
     * @param x15 color
     */
    public void insert3(ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, ItemEnum x5,
                        ItemEnum x6, ItemEnum x7, ItemEnum x8, ItemEnum x9, ItemEnum x10,
                        ItemEnum x11, ItemEnum x12, ItemEnum x13, ItemEnum x14, ItemEnum x15){
        matrix[0][4]=x1;
        matrix[1][3]=x2;
        matrix[1][4]=x3;
        matrix[2][2]=x4;
        matrix[2][3]=x5;
        matrix[2][4]=x6;
        matrix[3][1]=x7;
        matrix[3][2]=x8;
        matrix[3][3]=x9;
        matrix[3][4]=x10;
        matrix[4][0]=x11;
        matrix[4][1]=x12;
        matrix[4][2]=x13;
        matrix[4][3]=x14;
        matrix[4][4]=x15;
    }

    /**
     * This method inserts an ItemEnum some colors in specified positions
     * @author Samuele Pietro Galli
     * @param x1 color
     * @param x2 color
     * @param x3 color
     * @param x4 color
     * @param x5 color
     * @param x6 color
     * @param x7 color
     * @param x8 color
     * @param x9 color
     * @param x10 color
     * @param x11 color
     * @param x12 color
     * @param x13 color
     * @param x14 color
     * @param x15 color
     */
    public void insert4(ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, ItemEnum x5,
                        ItemEnum x6, ItemEnum x7, ItemEnum x8, ItemEnum x9, ItemEnum x10,
                        ItemEnum x11, ItemEnum x12, ItemEnum x13, ItemEnum x14, ItemEnum x15){
        matrix[1][4]=x1;
        matrix[2][3]=x2;
        matrix[2][4]=x3;
        matrix[3][2]=x4;
        matrix[3][3]=x5;
        matrix[3][4]=x6;
        matrix[4][1]=x7;
        matrix[4][2]=x8;
        matrix[4][3]=x9;
        matrix[4][4]=x10;
        matrix[5][0]=x11;
        matrix[5][1]=x12;
        matrix[5][2]=x13;
        matrix[5][3]=x14;
        matrix[5][4]=x15;
    }





    /**
     * This method tests if the method checkBookshelf is working correctly
     * @author Samuele Pietro Galli
     * */
    @Test
    public void Test(){

        insert1(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertTrue(c.checkBookshelf(matrix));

        /*
         * PARTE ASSERTFALSE
         * */

        insert1(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.YELLOW);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.YELLOW);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.YELLOW);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.YELLOW);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert1(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.YELLOW,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.YELLOW,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.YELLOW,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.YELLOW,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert1(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.YELLOW,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.YELLOW,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.YELLOW,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.YELLOW,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert1(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLANK,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLANK,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLANK,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLANK,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert1(ItemEnum.BLANK, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLANK, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLANK, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLANK, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert1(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.PURPLE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert1(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.PURPLE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.PURPLE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.PURPLE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.PURPLE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert1(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.AZURE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.AZURE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.AZURE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.AZURE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert1(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.WHITE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.WHITE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.WHITE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.WHITE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert1(ItemEnum.BLUE, ItemEnum.GREEN,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.GREEN,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.GREEN,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.GREEN,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert1(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.YELLOW,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert2(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.YELLOW,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert3(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.YELLOW,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));

        insert4(ItemEnum.BLUE, ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.YELLOW,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE,
                ItemEnum.BLUE,ItemEnum.BLUE,ItemEnum.BLUE);
        Assert.assertFalse(c.checkBookshelf(matrix));


    }

}
