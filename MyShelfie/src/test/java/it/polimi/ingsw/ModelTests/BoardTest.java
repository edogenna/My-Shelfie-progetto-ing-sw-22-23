package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.CommonCards.CommonCard01;
import it.polimi.ingsw.model.CommonCards.CommonCard02;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class tests methods of the class Board
 */
public class BoardTest {
    private Board b;
    private CommonCardStrategy[] commonCardStrategies = new CommonCardStrategy[2];
    private CommonCard01 commonCard01 = new CommonCard01();
    private CommonCard02 commonCard02 = new CommonCard02();
    private Board board = new Board(2);
    int first, second;
    @Test
    public void Test(){
        b = new Board(4);
        positionAlwaysEmpty(b);
        ItemEnum.generateCharMatrix(b.getMatrix(),Board.BOARD_SIZE,Board.BOARD_SIZE).addNumbering(Board.BOARD_SIZE).printMatrix();
        System.out.println("\n\n");

        b = new Board(3);
        positionAlwaysEmpty(b);
        positionEmptyFor3or2Players(b);
        ItemEnum.generateCharMatrix(b.getMatrix(),Board.BOARD_SIZE,Board.BOARD_SIZE).addNumbering(Board.BOARD_SIZE).printMatrix();
        System.out.println("\n\n");

        b = new Board(2);
        positionAlwaysEmpty(b);
        positionEmptyFor3or2Players(b);
        positionEmptyFor2Players(b);
        ItemEnum.generateCharMatrix(b.getMatrix(),Board.BOARD_SIZE,Board.BOARD_SIZE).addNumbering(Board.BOARD_SIZE).printMatrix();
        System.out.println("\n\n");

        commonCardStrategies = board.getCommonCards();
        first = commonCardStrategies[0].getNumber();
        second = commonCardStrategies[1].getNumber();
        board.setCommonCards();

        Assert.assertEquals(board.getCommonCards()[0].getNumber(), first);
        Assert.assertEquals(board.getCommonCards()[1].getNumber(), second);


    }

    private void positionAlwaysEmpty(Board b){
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 3; j++)
                Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[i][j]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[2][0]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[2][1]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[3][0]);

        for(int i = 6; i < Board.BOARD_SIZE; i++)
            for(int j = 0; j < 2; j++)
                Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[i][j]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[7][2]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[8][2]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[8][3]);

        for(int i = 0; i < 3; i++)
            for(int j = 7; j < Board.BOARD_SIZE; j++)
                Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[i][j]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[0][5]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[0][6]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[1][6]);

        for(int i = 6; i < Board.BOARD_SIZE; i++)
            for(int j = 7; j < Board.BOARD_SIZE; j++)
                Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[i][j]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[5][8]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[6][7]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[7][7]);
    }
    private void positionEmptyFor3or2Players(Board b){
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[4][0]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[3][1]);

        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[0][4]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[1][5]);

        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[4][8]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[5][7]);

        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[8][4]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[7][3]);
    }
    private void positionEmptyFor2Players(Board b){
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[3][0]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[2][2]);

        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[5][0]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[6][2]);

        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[3][8]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[2][6]);

        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[5][8]);
        Assert.assertEquals(ItemEnum.BLANK, b.getMatrix()[6][6]);
    }
}
