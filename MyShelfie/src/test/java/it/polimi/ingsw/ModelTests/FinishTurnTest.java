package it.polimi.ingsw.ModelTests;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is a test for the following method: finishTurn
 */
public class FinishTurnTest {
    Model model;
    Controller controller;
    Player player1;
    @Test
    public void Test(){
        int x;
        ItemEnum[][] matrix = new ItemEnum[6][5];
        ItemEnum[][] empty = new ItemEnum[9][9];

        for(int i=0; i<9; i++)
            for(int j=0; j<9; j++)
                empty[i][j] = ItemEnum.BLANK;

        for(int i=0; i<6; i++)
            for(int j=0; j<5; j++)
                matrix[i][j] = ItemEnum.BLUE1;

        model = new Model(2);
        model.setUsernamePlayer("aldo");
        model.setUsernamePlayer("giovanni");
        model.setFirstPlayer();
        controller = new Controller(model);
        x = model.getIdActivePlayer();
        Assert.assertFalse(controller.finishTurn());
        x++;
        x = x%2;
        Assert.assertEquals(x, model.getIdActivePlayer());
        controller.setActivePlayer(0);
        player1 = model.getPlayers()[0];
        player1.setBookshelf(matrix);
        controller.disconnect("giovanni");
        Assert.assertTrue(controller.finishTurn());


        //First Player = 1
        model = new Model(3);
        model.setUsernamePlayer("aldo");
        model.setUsernamePlayer("giovanni");
        model.setUsernamePlayer("giacomo");
        controller = new Controller(model);

        controller.setFirstPlayer();
        int z = controller.getIdFirstPlayer();
        while(z!=1){
            controller.setFirstPlayer();
            z = controller.getIdFirstPlayer();
        }

        x = model.getIdActivePlayer();
        Assert.assertFalse(controller.finishTurn());
        x++;
        x = x%3;
        Assert.assertEquals(x, model.getIdActivePlayer());

        controller.setActivePlayer(0);
        Assert.assertEquals(0, controller.getIdActivePlayer());
        player1 = model.getPlayers()[0];

        player1.setBookshelf(matrix);

        controller.disconnect("giovanni");
        Assert.assertTrue(controller.isDisconnected("giovanni"));
        controller.setActivePlayer(0);
        Assert.assertTrue(controller.finishTurn());


        //First Player = 0
        model = new Model(3);
        model.setUsernamePlayer("aldo");
        model.setUsernamePlayer("giovanni");
        model.setUsernamePlayer("giacomo");
        controller = new Controller(model);

        controller.setFirstPlayer();
        z = controller.getIdFirstPlayer();
        while(z!=0){
            controller.setFirstPlayer();
            z = controller.getIdFirstPlayer();
        }

        x = model.getIdActivePlayer();
        Assert.assertFalse(controller.finishTurn());
        x++;
        x = x % 3;
        Assert.assertEquals(x, model.getIdActivePlayer());

        controller.setActivePlayer(0);
        Assert.assertEquals(0, controller.getIdActivePlayer());
        player1 = model.getPlayers()[0];
        player1.setBookshelf(matrix);

        controller.disconnect("giovanni");
        Assert.assertTrue(controller.isDisconnected("giovanni"));
        controller.setActivePlayer(0);
        Assert.assertFalse(controller.finishTurn());
        Assert.assertEquals(2, controller.getIdActivePlayer());
        Assert.assertTrue(controller.finishTurn());

        //First Player = 2
        model = new Model(3);
        model.setUsernamePlayer("aldo");
        model.setUsernamePlayer("giovanni");
        model.setUsernamePlayer("giacomo");
        controller = new Controller(model);

        controller.setFirstPlayer();
        z = controller.getIdFirstPlayer();
        while(z!=2){
            controller.setFirstPlayer();
            z = controller.getIdFirstPlayer();
        }

        x = model.getIdActivePlayer();
        Assert.assertFalse(controller.finishTurn());
        x++;
        x = x%3;
        Assert.assertEquals(x, model.getIdActivePlayer());

        controller.setActivePlayer(0);
        Assert.assertEquals(0, controller.getIdActivePlayer());
        player1 = model.getPlayers()[0];
        player1.setBookshelf(matrix);

        controller.disconnect("giovanni");
        Assert.assertTrue(controller.isDisconnected("giovanni"));
        controller.setActivePlayer(0);
        Assert.assertTrue(controller.finishTurn());


        //First Player = 3
        model = new Model(4);
        model.setUsernamePlayer("hearts");
        model.setUsernamePlayer("diamonds");
        model.setUsernamePlayer("clubs");
        model.setUsernamePlayer("spades");
        controller = new Controller(model);

        controller.setFirstPlayer();
        z = controller.getIdFirstPlayer();
        while(z!=2){
            controller.setFirstPlayer();
            z = controller.getIdFirstPlayer();
        }

        x = model.getIdActivePlayer();
        Assert.assertFalse(controller.finishTurn());
        x++;
        x = x % 4;
        Assert.assertEquals(x, model.getIdActivePlayer());

        controller.setActivePlayer(0);
        Assert.assertEquals(0, controller.getIdActivePlayer());
        player1 = model.getPlayers()[0];
        player1.setBookshelf(matrix);

        controller.disconnect("clubs");
        Assert.assertTrue(controller.isDisconnected("clubs"));
        controller.setActivePlayer(0);
        Assert.assertFalse(controller.finishTurn());
        Assert.assertEquals(1, controller.getIdActivePlayer());
        model.getBoard().setMatrix(empty);
        Assert.assertTrue(controller.finishTurn());


        //First Player = 4
        model = new Model(4);
        model.setUsernamePlayer("hearts");
        model.setUsernamePlayer("diamonds");
        model.setUsernamePlayer("clubs");
        model.setUsernamePlayer("spades");
        controller = new Controller(model);

        controller.setFirstPlayer();
        z = controller.getIdFirstPlayer();
        while(z!=3){
            controller.setFirstPlayer();
            z = controller.getIdFirstPlayer();
        }

        x = model.getIdActivePlayer();
        Assert.assertFalse(controller.finishTurn());
        x++;
        x = x % 4;
        Assert.assertEquals(x, model.getIdActivePlayer());

        controller.setActivePlayer(0);
        Assert.assertEquals(0, controller.getIdActivePlayer());
        player1 = model.getPlayers()[0];
        player1.setBookshelf(matrix);

        controller.disconnect("clubs");
        Assert.assertTrue(controller.isDisconnected("clubs"));
        controller.setActivePlayer(0);
        Assert.assertFalse(controller.finishTurn());
        Assert.assertEquals(1, controller.getIdActivePlayer());
        model.getBoard().setMatrix(empty);
        Assert.assertTrue(controller.finishTurn());
    }
}