package it.polimi.ingsw.view.CLI;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.Network.messages.ErrorMessages.NotValidNumberofPlayersMessage;
import it.polimi.ingsw.Network.messages.ErrorMessages.NotValidUsernameError;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;

import java.io.*;
import java.util.Scanner;

public class CliView{
    private Scanner scanner;
    private PrintStream outputStream;
    private boolean done;
    private Controller controllerCli;
    private ItemEnum[][] board;
    private CommonCardStrategy[] CommonCards;
    private Card personalCard;
    private ItemEnum[][] shelf;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader stdIn;
    private Converter c;
    private String myUsername;
    private String userInput;

    @Deprecated
    public CliView(int numPlayers){
        scanner = new Scanner(System.in);
        outputStream = new PrintStream(System.out);
        controllerCli = new Controller(numPlayers, this);
        done = false;
    }

    public CliView(PrintWriter out, BufferedReader in, BufferedReader stdIn){
        this.out = out;
        this.in = in;
        this.stdIn = stdIn;
        this.outputStream = new PrintStream(System.out);
        this.personalCard = null;
        this.CommonCards = null;
        this.board = null;
        this.shelf = null;
        this.c = new Converter();
        this.myUsername = null;
        this.userInput = null;
    }

    @Deprecated
    public void match(){
        int num, i, x;
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

        while (!win) {
            this.board = controllerCli.getBoard();
            CommonCards = controllerCli.getCommonCards();
            printGame();
            while(!done) {
                personalCard = controllerCli.getActivePlayerPersonalCard();
                shelf = controllerCli.getActivePlayershelf();
                printBookshelfAndPersonal();
                outputStream.println("\n" + controllerCli.getActivePlayerUsername() + " it's your turn.");
                outputStream.println("Please insert which tiles you would like to remove from the board and the column of your bookshelf you want to put your tiles in");
                outputStream.println("the first one will go to the first position available on the bottom of the column and the others will pile up");
                outputStream.println("Example: x1,y1,x2,y2,x3,y3,column");
                outputStream.println("Example: a,3,a,4,a,5,column");
                inputs = scanner.next();
                while(controllerCli.dummyInput(inputs)){
                    inputs = scanner.next();
                }
                tiles = inputs.split(",");
                i = tiles.length;
                //i = number of tiles * 2 + 1;
                switch (i) {
                    case 3:
                        //we have taken 1 tile;
                        done = controllerCli.pickCard(tiles[0].charAt(0)-'a', Integer.parseInt(tiles[1]), Integer.parseInt(tiles[2]));
                        break;
                    case 5:
                        //we have taken 2 tiles;
                        done = controllerCli.pickCard(tiles[0].charAt(0)-'a', Integer.parseInt(tiles[1]), tiles[2].charAt(0)-'a', Integer.parseInt(tiles[3]), Integer.parseInt(tiles[4]));
                        break;
                    case 7:
                        //we have taken 3 tiles;
                        done = controllerCli.pickCard(tiles[0].charAt(0)-'a', Integer.parseInt(tiles[1]), tiles[2].charAt(0)-'a', Integer.parseInt(tiles[3]), tiles[4].charAt(0)-'a', Integer.parseInt(tiles[5]), Integer.parseInt(tiles[6]));
                        break;
                }
            }
            win = controllerCli.finishTurn();
            done = false;
        }
        //TODO: controller calculate the winner; declare the winner
        controllerCli.declareWinner();
        this.done = true;
    }

    public void actionHandler(Message m) throws IOException {

        switch (m.getType()) {
            case "FirstPlayer" -> {handleFirstPlayerMessage(m);}
            case "Lobby" -> outputStream.println(((LobbyMessage) m).getS());
            case "StartingGame" -> {outputStream.println(((StartingGameMessage) m).getS());}
            case "ChooseUsername" -> {handleChooseUsernameMessage(m);}
            case "NotValidUsername" -> {handleNotValidUsernameMessage(m);}
            case "GameInformation" -> {handleGameInformationMessage(m);}
            case "Waiting" -> outputStream.println(((WaitingMessage) m).getS());
        }
    }

    private void handleFirstPlayerMessage(Message m) throws IOException {
        outputStream.println(((FirstPlayerMessage) m).getS());
        userInput = stdIn.readLine();
        while(Integer.parseInt(userInput)<2 || Integer.parseInt(userInput)>4){
            outputStream.println(new NotValidNumberofPlayersMessage().getS());
            userInput = stdIn.readLine();
        }
        m = new NumberOfPlayersAnswer(Integer.parseInt(userInput));
        out.println(c.convertToJSON(m));
    }

    private void handleChooseUsernameMessage(Message m) throws IOException {
        outputStream.println(((ChooseUsernameMessage) m).getS());
        userInput = stdIn.readLine();
        this.myUsername = userInput;
        out.println(c.convertToJSON(new UsernameAnswer(userInput)));
    }

    private void handleNotValidUsernameMessage(Message m) throws IOException {
        outputStream.println(((NotValidUsernameError) m).getS());
        userInput = stdIn.readLine();
        this.myUsername = userInput;
        out.println(c.convertToJSON(new UsernameAnswer(userInput)));
    }

    private void handleGameInformationMessage(Message m) throws IOException {
        GameInformation gameInformation = (GameInformation) m;
        this.board = gameInformation.getBoard();
        //TODO: CommonCards
        printGame();
        if(gameInformation.getActivePlayerUsername().equals(this.myUsername)){
            this.personalCard = gameInformation.getPersonalCard();
            this.shelf = gameInformation.getShelf();
            printBookshelfAndPersonal();
            outputStream.println(gameInformation.getS());
            userInput = in.readLine();
            out.println(new MoveMessage(userInput));
        } else {
            outputStream.println(gameInformation.getActivePlayerUsername() + " is making his move...");
        }
    }

    public void commonPoints(String nickname, int points, int number){
        outputStream.println(nickname + "has completed the "+ number + " common goal card");
        outputStream.println(nickname + "scored " + points + " points");
    }

    /**
     * This method prints the board and common goal cards
     * @author Alessandro Fornara
     */
    private void printGame(){
        ItemEnum.generateCharMatrix(board, Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addNumbering(Board.BOARD_SIZE).appendToAllRows("   ").alignColumn()
                /*.addOnRight(CommonCards[0].printCommonCardMatrix()).appendToAllRows("   ").alignColumn()
                .addOnRight(CommonCards[1].printCommonCardMatrix())*/
                .printMatrix();
    }

    /**
     * This method prints a player's bookshelf and his personal card
     * @author Alessandro Fornara
     */
    private void printBookshelfAndPersonal(){
        outputStream.println("\n" + "Your Bookshelf:   Your Personal Card:");
        ItemEnum.generateCharMatrix(shelf, 6, 5).appendToAllRows("   ")
                .addOnRight(ItemEnum.generateCharMatrix(personalCard.getMatrix(), 6, 5)).printMatrix();
    }

    public void dummyInputPrint(){
        outputStream.println("please write the input with the correct format");
    }

    public void blankTilesSelected(int x, int y){
        outputStream.println("the tile " + x + ","+ y + " is a blank tile, please make a new move");
    }

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
        outputStream.println();
    }

    public void winnerPrint(String username, int points){
        outputStream.println("the match is finished");
        outputStream.println("the winner is...");
        outputStream.println(username);
        outputStream.println("the player " + username + "has done " + points + " points");
    }
}