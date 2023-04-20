package it.polimi.ingsw.view.CLI;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Board;

import java.io.PrintStream;
import java.util.Scanner;

public class CliView implements Runnable {
    private Scanner scanner;
    private PrintStream outputStream;
    private boolean done;
    private Controller controllerCli;
    private ItemEnum[][] board;

//it is temporary; later I will create an interface similar to the observers;

    public CliView(int numPlayers){
        scanner = new Scanner(System.in);
        outputStream = new PrintStream(System.out);
        controllerCli = new Controller(numPlayers, this);
    }

    public void match(){
        int x, z, i, j, k;
        boolean done = false;
        String name, inputs;
        String[] tiles;
        x = controllerCli.getNumPlayers();
        for(i=0; i<x; i++){
            outputStream.println("Player" + i + ", insert your username");
            do{
                name = scanner.next();
                if(controllerCli.duplicatedUsername(name)){
                    outputStream.println("Select another username, this has been already selected.");
                }
            }while(controllerCli.duplicatedUsername(name));
            controllerCli.setUsernamePlayer(name);
        }
        controllerCli.setFirstPlayer();
        //TODO: print the board
        this.board = controllerCli.getBoard();

        while (true) {

            //TODO: FINIRE CONTROLLI SULLA MOSSA
            while(!done) {
                outputStream.println("Please insert the column of your bookshelf you want to put your tiles in (the first one will go to the first position available on the bottom of the column and the others will pile up) and which tiles you would like to remove from the board. Example: column,x1,y1,x2,y2,x3,y3");
                inputs = scanner.next();
                tiles = inputs.split(",");
                i = tiles.length;
                switch (i) {
                    case 3:
                        done = controllerCli.isFeasibleMove(Integer.parseInt(tiles[1]), Integer.parseInt(tiles[2]));
                        if (!done)
                            outputStream.println("one of tiles hasn't any free sides, please make a new move (column,x1,y1,x2,y2,x3,y3)");

                        z = Integer.parseInt(tiles[0]);
                        j = Integer.parseInt(tiles[1]);
                        k = Integer.parseInt(tiles[2]);

                        done = controllerCli.pickCard(j, k, z);
                        if (!done) {
                            outputStream.println("the column selected hasn't enough space, select another one, please make a new move (column,x1,y1,x2,y2,x3,y3)");
                        }
                        break;
                    case 5:

                        break;
                    case 7:

                        break;
                }
            }
            done = controllerCli.finishTurn();
            //TODO: now we have controlled the commonPoints and if someone has filled the bookshelf; finish the match
        }
    }

    public void commonPoints(String nickname, int points, int number){
        outputStream.println(nickname + "has completed the "+ number + " common goal card");
        outputStream.println(nickname + "scored " + points + " points");
    }
/*
    private void printGame(){
        ItemEnum.generateCharMatrix(board, Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addNumbering(Board.BOARD_SIZE).appendToAllRows("   ").alignColumn()
                .addOnRight(controllerCli.getCommonCards(0).printCommonCardMatrix()).appendToAllRows("   ").alignColumn()
                .addOnRight(controllerCli.getCommonCards(1).printCommonCardMatrix())
                .printMatrix();
    }*/

    @Override
    public void run() {
        while(!done){
            match();
        }
    }
}