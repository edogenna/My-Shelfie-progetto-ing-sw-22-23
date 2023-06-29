package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class declareWinnerTest {
    Model model;
    Controller controller;
    Player player1;
    @Test
    public void Test(){
        ItemEnum[][] matrix = new ItemEnum[6][5];

        for(int i=0; i<6; i++)
            for(int j=0; j<5; j++)
                matrix[i][j] = ItemEnum.BLUE1;

        model = new Model(2);
        model.setUsernamePlayer("aldo");
        model.setUsernamePlayer("giovanni");
        model.setFirstPlayer();
        controller = new Controller(model);
        controller.setActivePlayer(0);
        for(int z=0; z<2; z++){
            player1 = model.getPlayers()[z];
            player1.setBookshelf(matrix);
        }
        player1 = model.getPlayers()[0];
        controller.disconnect("giovanni");
        Assert.assertTrue(controller.finishTurn());
        int y = player1.calculatePoints();
        Assert.assertEquals(y, controller.declareWinner()[controller.getIdActivePlayer()]);
        Assert.assertEquals(0, controller.getIdActivePlayer());


        model = new Model(2);
        model.setUsernamePlayer("aldo");
        model.setUsernamePlayer("giovanni");
        controller = new Controller(model);
        int z = controller.getIdFirstPlayer();
        while(z!=0){
            controller.setFirstPlayer();
            z = controller.getIdFirstPlayer();
        }
        controller.setActivePlayer(0);
        for(z=0; z<2; z++){
            player1 = model.getPlayers()[z];
            player1.setBookshelf(matrix);
        }
        Assert.assertFalse(controller.finishTurn());
        player1 = model.getPlayers()[1];
        Assert.assertTrue(controller.finishTurn());
        y = player1.calculatePoints();
        Assert.assertEquals(y, controller.declareWinner()[controller.getIdActivePlayer()]);
        Assert.assertEquals(1, controller.getIdActivePlayer());

        model = new Model(2);
        model.setUsernamePlayer("aldo");
        model.setUsernamePlayer("giovanni");
        model.setFirstPlayer();
        controller = new Controller(model);
        controller.setActivePlayer(0);
        controller.disconnect("giovanni");
        Assert.assertTrue(controller.finishTurn());
        Assert.assertEquals(0, controller.declareWinner()[controller.getIdActivePlayer()]);
        Assert.assertEquals(0, controller.getIdActivePlayer());


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
        Assert.assertEquals(2, controller.getIdFirstPlayer());
        Assert.assertEquals(2, z);
        for(int j=0; j<4; j++){
            player1 = model.getPlayers()[j];
            player1.setBookshelf(matrix);
        }
        player1 = model.getPlayers()[0];
        controller.setActivePlayer(2);
        controller.disconnect("diamonds");
        Assert.assertFalse(controller.finishTurn());
        Assert.assertFalse(controller.finishTurn());
        Assert.assertTrue(controller.finishTurn());
        y = player1.calculatePoints();
        Assert.assertEquals(y, controller.declareWinner()[controller.getIdActivePlayer()]);
        Assert.assertEquals(0, controller.getIdActivePlayer());
    }
}
