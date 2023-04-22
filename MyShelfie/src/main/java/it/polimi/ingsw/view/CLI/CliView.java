package it.polimi.ingsw.view.CLI;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.controller.Controller;

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
        done = false;
    }

    public void match(){
        int num, col, i, x1, y1;
        boolean done = false;
        boolean win = false;
        String name, inputs;
        String[] tiles;
        num = controllerCli.getNumPlayers();
        for(i=0; i<num; i++){
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

        while (!win) {
            //TODO: change the input format: x1,y1,...,column
            while(!done) {
                outputStream.println("Please insert the column of your bookshelf you want to put your tiles in and which tiles you would like to remove from the board.");
                outputStream.println("the first one will go to the first position available on the bottom of the column and the others will pile up");
                outputStream.println("Example: column,x1,y1,x2,y2,x3,y3");
                inputs = scanner.next();
                tiles = inputs.split(",");
                i = tiles.length;
                //i = number of tiles * 2 + 1;
                switch (i) {
                    case 3:
                        //we have taken 1 tile;
//                        done = controllerCli.isFeasibleMove(Integer.parseInt(tiles[1]), Integer.parseInt(tiles[2]));
                        col = Integer.parseInt(tiles[0]);
                        x1 = Integer.parseInt(tiles[1]);
                        y1 = Integer.parseInt(tiles[2]);
                        done = controllerCli.pickCard(x1, y1, col);
                        break;
                    case 5:
                        //we have taken 2 tiles;
                        done = controllerCli.pickCard(Integer.parseInt(tiles[1]), Integer.parseInt(tiles[2]), Integer.parseInt(tiles[3]), Integer.parseInt(tiles[4]), Integer.parseInt(tiles[0]));
                        break;
                    case 7:
                        //we have taken 3 tiles;
                        done = controllerCli.pickCard(Integer.parseInt(tiles[1]), Integer.parseInt(tiles[2]), Integer.parseInt(tiles[3]), Integer.parseInt(tiles[4]), Integer.parseInt(tiles[5]), Integer.parseInt(tiles[6]), Integer.parseInt(tiles[0]));
                        break;
                }
            }
            win = controllerCli.finishTurn();
        }
        //TODO: controller calculate the winner; declare the winner
        controllerCli.declareWinner();
        this.done = true;
    }

    public void commonPoints(String nickname, int points, int number){
        outputStream.println(nickname + "has completed the "+ number + " common goal card");
        outputStream.println(nickname + "scored " + points + " points");
    }

/*    private void printGame(){
        ItemEnum.generateCharMatrix(board, Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addNumbering(Board.BOARD_SIZE).appendToAllRows("   ").alignColumn()
                .addOnRight(controllerCli.getCommonCards(0).printCommonCardMatrix()).appendToAllRows("   ").alignColumn()
                .addOnRight(controllerCli.getCommonCards(1).printCommonCardMatrix())
                .printMatrix();
    }*/

    public void notEnoughSpaceBookshelfPrint(){
        outputStream.println("your bookshelf hasn't enough space, please make a new move taking less tiles");
    }

    public void noFreeSidesPrint(int x, int y){
        String column;
        column = String.valueOf(y + 'a');
        //TODO: control the string
        outputStream.println("the tile " + x +","+ column + " hasn't any free sides, please make a new move");
    }

    public void notAdjacentTilesPrint(){
        outputStream.println("the selected tiles aren't adjacent");
    }
    
    public void notEnoughSpaceBookshelfColPrint(int y){
        outputStream.println("the column "+y+ " hasn't enough space, please make a new move");
    }

    public void winnerPrint(String username, int points){
        outputStream.println("the match is finished");
        outputStream.println("the winner is...");
        outputStream.println(username);
        outputStream.println("the player " + username + "has done " + points + " points");
    }

    @Override
    public void run() {
        while(!this.done){
            match();
        }
    }
}