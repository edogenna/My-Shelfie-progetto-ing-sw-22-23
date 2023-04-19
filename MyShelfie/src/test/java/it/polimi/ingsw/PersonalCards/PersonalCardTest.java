package it.polimi.ingsw.PersonalCards;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.PersonalCard;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the following methods:
 * getCard, PersonalCard constructor, Card contructor, addTriplet
 * @author Alessandro Fornara
 */
public class PersonalCardTest {

    PersonalCard p = new PersonalCard();
    Card c = new Card(0);

    /**
     * This method tests if the cards created with the PersonalCard constructor are as they should be
     */
    @Test
    public void Test(){

        c = p.getCard(0);
        Assert.assertEquals(ItemEnum.AZURE, c.getMatrix()[2][0]);
        Assert.assertEquals(ItemEnum.WHITE, c.getMatrix()[4][1]);
        Assert.assertEquals(ItemEnum.BLUE, c.getMatrix()[2][2]);
        Assert.assertEquals(ItemEnum.GREEN, c.getMatrix()[4][2]);
        Assert.assertEquals(ItemEnum.PURPLE, c.getMatrix()[3][3]);
        Assert.assertEquals(ItemEnum.YELLOW, c.getMatrix()[0][4]);

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 5; j++)
                if(!(i==2 && j==0) && !(i==4 && j==1) && !(i==2 && j==2)
                        && !(i==4 && j==2) && !(i==3 && j==3) && !(i==0 && j==4))
                    Assert.assertEquals(ItemEnum.BLANK, c.getMatrix()[i][j]);

        c = p.getCard(1);
        Assert.assertEquals(ItemEnum.AZURE, c.getMatrix()[5][3]);
        Assert.assertEquals(ItemEnum.WHITE, c.getMatrix()[1][1]);
        Assert.assertEquals(ItemEnum.BLUE, c.getMatrix()[3][2]);
        Assert.assertEquals(ItemEnum.GREEN, c.getMatrix()[4][4]);
        Assert.assertEquals(ItemEnum.PURPLE, c.getMatrix()[0][2]);
        Assert.assertEquals(ItemEnum.YELLOW, c.getMatrix()[2][0]);

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 5; j++)
                if(!(i==5 && j==3) && !(i==1 && j==1) && !(i==3 && j==2)
                        && !(i==4 && j==4) && !(i==0 && j==2) && !(i==2 && j==0))
                    Assert.assertEquals(ItemEnum.BLANK, c.getMatrix()[i][j]);

        c = p.getCard(2);
        Assert.assertEquals(ItemEnum.AZURE, c.getMatrix()[5][2]);
        Assert.assertEquals(ItemEnum.WHITE, c.getMatrix()[2][3]);
        Assert.assertEquals(ItemEnum.BLUE, c.getMatrix()[0][2]);
        Assert.assertEquals(ItemEnum.GREEN, c.getMatrix()[1][4]);
        Assert.assertEquals(ItemEnum.PURPLE, c.getMatrix()[0][0]);
        Assert.assertEquals(ItemEnum.YELLOW, c.getMatrix()[3][1]);

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 5; j++)
                if(!(i==5 && j==2) && !(i==2 && j==3) && !(i==0 && j==2)
                        && !(i==1 && j==4) && !(i==0 && j==0) && !(i==3 && j==1))
                    Assert.assertEquals(ItemEnum.BLANK, c.getMatrix()[i][j]);

        c = p.getCard(3);
        Assert.assertEquals(ItemEnum.AZURE, c.getMatrix()[3][4]);
        Assert.assertEquals(ItemEnum.WHITE, c.getMatrix()[5][0]);
        Assert.assertEquals(ItemEnum.BLUE, c.getMatrix()[1][0]);
        Assert.assertEquals(ItemEnum.GREEN, c.getMatrix()[3][1]);
        Assert.assertEquals(ItemEnum.PURPLE, c.getMatrix()[2][2]);
        Assert.assertEquals(ItemEnum.YELLOW, c.getMatrix()[1][3]);

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 5; j++)
                if(!(i==3 && j==4) && !(i==5 && j==0) && !(i==1 && j==0)
                        && !(i==3 && j==1) && !(i==2 && j==2) && !(i==1 && j==3))
                    Assert.assertEquals(ItemEnum.BLANK, c.getMatrix()[i][j]);

        c = p.getCard(4);
        Assert.assertEquals(ItemEnum.AZURE, c.getMatrix()[1][1]);
        Assert.assertEquals(ItemEnum.WHITE, c.getMatrix()[3][2]);
        Assert.assertEquals(ItemEnum.BLUE, c.getMatrix()[3][1]);
        Assert.assertEquals(ItemEnum.GREEN, c.getMatrix()[5][3]);
        Assert.assertEquals(ItemEnum.PURPLE, c.getMatrix()[4][4]);
        Assert.assertEquals(ItemEnum.YELLOW, c.getMatrix()[5][0]);

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 5; j++)
                if(!(i==1 && j==1) && !(i==3 && j==2) && !(i==3 && j==1)
                        && !(i==5 && j==3) && !(i==4 && j==4) && !(i==5 && j==0))
                    Assert.assertEquals(ItemEnum.BLANK, c.getMatrix()[i][j]);

        c = p.getCard(5);
        Assert.assertEquals(ItemEnum.AZURE, c.getMatrix()[4][1]);
        Assert.assertEquals(ItemEnum.WHITE, c.getMatrix()[3][4]);
        Assert.assertEquals(ItemEnum.BLUE, c.getMatrix()[5][0]);
        Assert.assertEquals(ItemEnum.GREEN, c.getMatrix()[2][2]);
        Assert.assertEquals(ItemEnum.PURPLE, c.getMatrix()[4][4]);
        Assert.assertEquals(ItemEnum.YELLOW, c.getMatrix()[0][2]);

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 5; j++)
                if(!(i==4 && j==1) && !(i==3 && j==4) && !(i==5 && j==0)
                        && !(i==2 && j==2) && !(i==4 && j==4) && !(i==0 && j==2))
                    Assert.assertEquals(ItemEnum.BLANK, c.getMatrix()[i][j]);

        c = p.getCard(6);
        Assert.assertEquals(ItemEnum.AZURE, c.getMatrix()[0][4]);
        Assert.assertEquals(ItemEnum.WHITE, c.getMatrix()[2][0]);
        Assert.assertEquals(ItemEnum.BLUE, c.getMatrix()[4][1]);
        Assert.assertEquals(ItemEnum.GREEN, c.getMatrix()[3][3]);
        Assert.assertEquals(ItemEnum.PURPLE, c.getMatrix()[5][3]);
        Assert.assertEquals(ItemEnum.YELLOW, c.getMatrix()[1][1]);

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 5; j++)
                if(!(i==0 && j==4) && !(i==2 && j==0) && !(i==4 && j==1)
                        && !(i==3 && j==3) && !(i==5 && j==3) && !(i==1 && j==1))
                    Assert.assertEquals(ItemEnum.BLANK, c.getMatrix()[i][j]);

        c = p.getCard(7);
        Assert.assertEquals(ItemEnum.AZURE, c.getMatrix()[3][3]);
        Assert.assertEquals(ItemEnum.WHITE, c.getMatrix()[0][2]);
        Assert.assertEquals(ItemEnum.BLUE, c.getMatrix()[2][2]);
        Assert.assertEquals(ItemEnum.GREEN, c.getMatrix()[5][0]);
        Assert.assertEquals(ItemEnum.PURPLE, c.getMatrix()[1][1]);
        Assert.assertEquals(ItemEnum.YELLOW, c.getMatrix()[4][4]);

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 5; j++)
                if(!(i==3 && j==3) && !(i==0 && j==2) && !(i==2 && j==2)
                        && !(i==5 && j==0) && !(i==1 && j==1) && !(i==4 && j==4))
                    Assert.assertEquals(ItemEnum.BLANK, c.getMatrix()[i][j]);

        c = p.getCard(8);
        Assert.assertEquals(ItemEnum.AZURE, c.getMatrix()[3][0]);
        Assert.assertEquals(ItemEnum.WHITE, c.getMatrix()[5][2]);
        Assert.assertEquals(ItemEnum.BLUE, c.getMatrix()[1][3]);
        Assert.assertEquals(ItemEnum.GREEN, c.getMatrix()[0][0]);
        Assert.assertEquals(ItemEnum.PURPLE, c.getMatrix()[2][1]);
        Assert.assertEquals(ItemEnum.YELLOW, c.getMatrix()[4][4]);

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 5; j++)
                if(!(i==3 && j==0) && !(i==5 && j==2) && !(i==1 && j==3)
                        && !(i==0 && j==0) && !(i==2 && j==1) && !(i==4 && j==4))
                    Assert.assertEquals(ItemEnum.BLANK, c.getMatrix()[i][j]);

        c = p.getCard(9);
        Assert.assertEquals(ItemEnum.BLUE, c.getMatrix()[0][4]);
        Assert.assertEquals(ItemEnum.AZURE, c.getMatrix()[2][2]);
        Assert.assertEquals(ItemEnum.YELLOW, c.getMatrix()[5][3]);
        Assert.assertEquals(ItemEnum.GREEN, c.getMatrix()[1][1]);
        Assert.assertEquals(ItemEnum.WHITE, c.getMatrix()[4][3]);
        Assert.assertEquals(ItemEnum.PURPLE, c.getMatrix()[3][0]);

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 5; j++)
                if(!(i==0 && j==4) && !(i==2 && j==2) && !(i==5 && j==3)
                        && !(i==1 && j==1) && !(i==4 && j==3) && !(i==3 && j==0))
                    Assert.assertEquals(ItemEnum.BLANK, c.getMatrix()[i][j]);

        c = p.getCard(10);
        Assert.assertEquals(ItemEnum.BLUE, c.getMatrix()[4][3]);
        Assert.assertEquals(ItemEnum.AZURE, c.getMatrix()[0][2]);
        Assert.assertEquals(ItemEnum.YELLOW, c.getMatrix()[4][1]);
        Assert.assertEquals(ItemEnum.GREEN, c.getMatrix()[0][4]);
        Assert.assertEquals(ItemEnum.WHITE, c.getMatrix()[2][3]);
        Assert.assertEquals(ItemEnum.PURPLE, c.getMatrix()[5][0]);

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 5; j++)
                if(!(i==4 && j==3) && !(i==0 && j==2) && !(i==4 && j==1)
                        && !(i==0 && j==4) && !(i==2 && j==3) && !(i==5 && j==0))
                    Assert.assertEquals(ItemEnum.BLANK, c.getMatrix()[i][j]);

        c = p.getCard(11);
        Assert.assertEquals(ItemEnum.BLUE, c.getMatrix()[5][4]);
        Assert.assertEquals(ItemEnum.AZURE, c.getMatrix()[4][3]);
        Assert.assertEquals(ItemEnum.YELLOW, c.getMatrix()[2][2]);
        Assert.assertEquals(ItemEnum.GREEN, c.getMatrix()[2][0]);
        Assert.assertEquals(ItemEnum.WHITE, c.getMatrix()[3][4]);
        Assert.assertEquals(ItemEnum.PURPLE, c.getMatrix()[1][1]);

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 5; j++)
                if(!(i==5 && j==4) && !(i==4 && j==3) && !(i==2 && j==2)
                        && !(i==2 && j==0) && !(i==3 && j==4) && !(i==1 && j==1))
                    Assert.assertEquals(ItemEnum.BLANK, c.getMatrix()[i][j]);
    }
}