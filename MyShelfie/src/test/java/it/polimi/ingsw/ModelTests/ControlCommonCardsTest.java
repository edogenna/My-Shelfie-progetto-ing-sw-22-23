package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ControlCommonCardsTest {
    Model model = new Model(2);
    ArrayList<ItemEnum[][]> commonCards = new ArrayList<>();

    @Test
    public void Test(){
        createCommonCards();
        model.setUsernamePlayer("luca");
        model.setUsernamePlayer("paolo");
        model.setFirstPlayer();

        Assert.assertFalse(model.controlCommonCards(0));
        Assert.assertFalse(model.controlCommonCards(1));

        int x = model.getIdActivePlayer();
        Player[] player = model.getPlayers();
        int y = model.getCommonCards()[0].getNumber();
        player[x].setBookshelf(commonCards.get(y-1));

        Assert.assertTrue(model.controlCommonCards(0));
        player[x].updateCommonPoints(4,0);
        player[x].updateCommonPoints(6,1);
        int z = player[x].calculatePoints();
        Assert.assertEquals(10, z - player[x].getMyCommonPoints());
    }

    private void insertColumn(int j, ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, ItemEnum x5, ItemEnum x6, int i){
        commonCards.get(i)[0][j]=x1;
        commonCards.get(i)[1][j]=x2;
        commonCards.get(i)[2][j]=x3;
        commonCards.get(i)[3][j]=x4;
        commonCards.get(i)[4][j]=x5;
        commonCards.get(i)[5][j]=x6;
    }

    private void insertCorners(ItemEnum x1, ItemEnum x2, ItemEnum x3, ItemEnum x4, int i){
        commonCards.get(i)[0][0]=x1;
        commonCards.get(i)[0][4]=x2;
        commonCards.get(i)[5][0]=x3;
        commonCards.get(i)[5][4]=x4;
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
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.WHITE1, 0);
        insertColumn(1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 0);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.BLUE1, 0);
        insertColumn(3, ItemEnum.GREEN1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 0);
        insertColumn(4, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 0);

        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, 1);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.PURPLE1, 1);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.YELLOW1, 1);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.BLUE1, 1);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.PURPLE1, ItemEnum.BLUE1, 1);

        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 2);
        insertColumn(1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 2);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 2);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 2);
        insertColumn(4, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 2);

        insertColumn(0, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.PURPLE1, ItemEnum.YELLOW1, ItemEnum.GREEN1, ItemEnum.BLUE1, 3);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.PURPLE1, ItemEnum.BLUE1, 3);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.WHITE1, ItemEnum.BLUE1, 3);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.WHITE1, ItemEnum.BLUE1, ItemEnum.PURPLE1, ItemEnum.BLUE1, 3);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, 3);

        insertCorners(ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 4);

        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 5);
        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 5);
        insertColumn(2, ItemEnum.YELLOW1, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.AZURE1, ItemEnum.PURPLE1, ItemEnum.BLUE1, 5);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 5);
        insertColumn(4, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.WHITE1, ItemEnum.GREEN1, ItemEnum.AZURE1, ItemEnum.PURPLE1, 5);

        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 6);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 6);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.YELLOW1, 6);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, 6);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, 6);

        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.GREEN1, 7);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.PURPLE1, ItemEnum.PURPLE1, 7);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.AZURE1, ItemEnum.YELLOW1, 7);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.WHITE1, ItemEnum.AZURE1, 7);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.YELLOW1, ItemEnum.BLUE1, 7);

        insertColumn(0, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1, 8);
        insertColumn(1, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1, 8);
        insertColumn(2, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1, 8);
        insertColumn(3, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.BLUE1, 8);
        insertColumn(4, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.BLUE1, ItemEnum.GREEN1, ItemEnum.YELLOW1, ItemEnum.BLUE1, 8);

        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 9);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, 9);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 9);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.PURPLE1, 9);
        insertColumn(4, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.BLUE1, 9);

        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, 10);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.PURPLE1, ItemEnum.BLUE1, 10);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.PURPLE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 10);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 10);
        insertColumn(4, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.YELLOW1, ItemEnum.YELLOW1, ItemEnum.PURPLE1, ItemEnum.WHITE1, 10);

        insertColumn(0, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, 11);
        insertColumn(1, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 11);
        insertColumn(2, ItemEnum.BLANK, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 11);
        insertColumn(3, ItemEnum.BLANK, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 11);
        insertColumn(4, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, ItemEnum.BLUE1, 11);
    }
}