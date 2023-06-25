package it.polimi.ingsw.ModelTests.CommonCardTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.CommonCards.CommonCard06;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the sixth common card
 * @author Samuele Pietro Galli
 * @author Alessandro Fornara
 */
public class CommonCard06Test {
    private CommonCardStrategy c;
    private ItemEnum[][] matrix;
    public CommonCard06Test() {
        c = new CommonCard06();
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
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(2, ItemEnum.YELLOW1, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.AZURE1, ItemEnum.PURPLE1, ItemEnum.BLUE1);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(4, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.AZURE1, ItemEnum.PURPLE1);
        Assert.assertTrue(c.checkBookshelf(matrix));

         //wrong case
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(2, ItemEnum.WHITE1, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.AZURE1, ItemEnum.PURPLE1, ItemEnum.BLUE1);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(4, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.AZURE1, ItemEnum.PURPLE1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        //correct case
        insertColumn(0, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.AZURE1, ItemEnum.PURPLE1, ItemEnum.GREEN1);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(2, ItemEnum.YELLOW1, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.AZURE1, ItemEnum.PURPLE1, ItemEnum.BLUE1);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK);
        insertColumn(4, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.AZURE1, ItemEnum.PURPLE1);
        Assert.assertTrue(c.checkBookshelf(matrix));

         //wrong case
        insertColumn(0, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.AZURE1, ItemEnum.AZURE1, ItemEnum.GREEN1);
        insertColumn(1, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.WHITE1, ItemEnum.AZURE1, ItemEnum.AZURE1, ItemEnum.AZURE1);
        insertColumn(2, ItemEnum.YELLOW1, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.AZURE1, ItemEnum.PURPLE1, ItemEnum.BLUE1);
        insertColumn(3, ItemEnum.PURPLE1, ItemEnum.PURPLE1, ItemEnum.PURPLE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.PURPLE1);
        insertColumn(4, ItemEnum.BLUE1, ItemEnum.PURPLE1, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.AZURE1, ItemEnum.PURPLE1);
        Assert.assertFalse(c.checkBookshelf(matrix));

        Assert.assertTrue(c.getNumber() == 6);

        Assert.assertTrue(c.getCommonCardDesign().equals("| ≠ | number 6  Description:\n"+
                "| ≠ |           Two columns each formed\n"+
                "| ≠ |           by 6 different\n"+
                "| ≠ |    x2     types of tiles.\n"+
                "| ≠ |\n"+
                "| ≠ |\n"));
    }
}
