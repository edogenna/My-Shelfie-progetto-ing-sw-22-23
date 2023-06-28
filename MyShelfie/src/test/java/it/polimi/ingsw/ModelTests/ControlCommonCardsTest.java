package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * This class is a test for the following method: controlCommonCards
 */
public class ControlCommonCardsTest {
    Model model;
    ArrayList<ItemEnum[][]> commonCards = new ArrayList<>();

    @Test
    public void Test(){
        createCommonCards();
        int x, y, z, w, p, t;

        for(z=2; z<5; z++){
            for(p=0; p<2; p++){
                model = new Model(z);
                for(w=0; w<z; w++){
                    model.setUsernamePlayer("luca" + w);
                }
                model.setFirstPlayer();
                Assert.assertFalse(model.controlCommonCards(0));
                Assert.assertFalse(model.controlCommonCards(1));

                x = model.getIdActivePlayer();
                Player[] player = model.getPlayers();
                y = model.getCommonCards()[p].getNumber();
                player[x].setBookshelf(commonCards.get(y-1));

                if(z==2)
                    t = 4;
                else
                    t = 2;
                Assert.assertTrue("y = " + y, model.controlCommonCards(p));
                Assert.assertEquals(8, player[x].getMyCommonPoints());
                Assert.assertEquals(8-t, model.getCommonCardsPoints(p));
            }
        }
        Assert.assertEquals(0, model.getCommonCardsPoints(-1));
    }

    private void insertColumn(ItemEnum[][] matr, int j, ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, ItemEnum x5, ItemEnum x6, int i){
        matr[0][j]=x1;
        matr[1][j]=x2;
        matr[2][j]=x3;
        matr[3][j]=x4;
        matr[4][j]=x5;
        matr[5][j]=x6;
    }

    private void insertCorners(ItemEnum[][] matrix, ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, int i){
        for(int k=0; k<6; k++)
            for(int p=0; p<5; p++)
                matrix[k][p] = ItemEnum.BLANK;
        matrix[0][0]=x1;
        matrix[0][4]=x2;
        matrix[5][0]=x3;
        matrix[5][4]=x4;
    }

    private void initializeMatrix(){
        int i, j, z;
        ItemEnum[][] matrix = new ItemEnum[6][5];
        for(i=0; i<6; i++){
            for(j=0; j<5; j++){
                matrix[i][j] = ItemEnum.BLANK;
            }
        }
        for(z=0; z<12; z++){
            commonCards.add(matrix);
        }
    }

    private void createCommonCards(){
        initializeMatrix();
        ItemEnum[][] prova = new ItemEnum[6][5];

        insertColumn(prova, 0, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.WHITE1, 0);
        insertColumn(prova, 1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 0);
        insertColumn(prova, 2, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.BLUE1, 0);
        insertColumn(prova, 3, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 0);
        insertColumn(prova, 4, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 0);
        commonCards.set(0, prova);

        prova = new ItemEnum[6][5];
        insertColumn(prova, 0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, 1);
        insertColumn(prova, 1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.PURPLE1, 1);
        insertColumn(prova, 2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.YELLOW1, 1);
        insertColumn(prova, 3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.BLUE1, 1);
        insertColumn(prova, 4, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.PURPLE1, ItemEnum.BLUE1, 1);
        commonCards.set(1, prova);

        prova = new ItemEnum[6][5];
        insertColumn(prova, 0, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 2);
        insertColumn(prova, 1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 2);
        insertColumn(prova, 2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 2);
        insertColumn(prova, 3, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 2);
        insertColumn(prova, 4, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 2);
        commonCards.set(2, prova);

        prova = new ItemEnum[6][5];
        insertColumn(prova, 0, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.PURPLE1, ItemEnum.YELLOW1, ItemEnum.GREEN1, ItemEnum.BLUE1, 3);
        insertColumn(prova, 1, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.PURPLE1, ItemEnum.BLUE1, 3);
        insertColumn(prova, 2, ItemEnum.BLANK, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.BLUE1, 3);
        insertColumn(prova, 3, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.PURPLE1, ItemEnum.BLUE1, 3);
        insertColumn(prova, 4, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, 3);
        commonCards.set(3, prova);

        prova = new ItemEnum[6][5];
        insertCorners(prova, ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 4);
        commonCards.set(4, prova);

        prova = new ItemEnum[6][5];
        insertColumn(prova,0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 5);
        insertColumn(prova,1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 5);
        insertColumn(prova,2, ItemEnum.YELLOW1, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.AZURE1, ItemEnum.PURPLE1, ItemEnum.BLUE1, 5);
        insertColumn(prova,3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 5);
        insertColumn(prova,4, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.AZURE1, ItemEnum.PURPLE1, 5);
        commonCards.set(5, prova);

        prova = new ItemEnum[6][5];
        insertColumn(prova, 0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 6);
        insertColumn(prova, 1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 6);
        insertColumn(prova, 2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 6);
        insertColumn(prova, 3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, 6);
        insertColumn(prova, 4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, 6);
        commonCards.set(6, prova);

        prova = new ItemEnum[6][5];
        insertColumn(prova, 0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.GREEN1, 7);
        insertColumn(prova, 1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE1, ItemEnum.PURPLE1, 7);
        insertColumn(prova, 2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.AZURE1, ItemEnum.YELLOW1, 7);
        insertColumn(prova, 3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.WHITE1, ItemEnum.AZURE1, 7);
        insertColumn(prova, 4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.BLUE1, 7);
        commonCards.set(7, prova);

        prova = new ItemEnum[6][5];
        insertColumn(prova, 0, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1, 8);
        insertColumn(prova, 1, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1, 8);
        insertColumn(prova, 2, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1, 8);
        insertColumn(prova, 3, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1, 8);
        insertColumn(prova, 4, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.YELLOW1, ItemEnum.BLUE1, 8);
        commonCards.set(8, prova);

        prova = new ItemEnum[6][5];
        insertColumn(prova, 0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 9);
        insertColumn(prova, 1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 9);
        insertColumn(prova, 2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 9);
        insertColumn(prova, 3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.PURPLE1, 9);
        insertColumn(prova, 4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, 9);
        commonCards.set(9, prova);

        prova = new ItemEnum[6][5];
        insertColumn(prova, 0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, 10);
        insertColumn(prova, 1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.PURPLE1, ItemEnum.BLUE1, 10);
        insertColumn(prova, 2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.PURPLE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 10);
        insertColumn(prova, 3, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 10);
        insertColumn(prova, 4, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.PURPLE1, ItemEnum.WHITE1, 10);
        commonCards.set(10, prova);

        prova = new ItemEnum[6][5];
        insertColumn(prova, 0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, 11);
        insertColumn(prova, 1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 11);
        insertColumn(prova, 2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 11);
        insertColumn(prova, 3, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 11);
        insertColumn(prova, 4, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 11);
        commonCards.set(11, prova);
    }
}