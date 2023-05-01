package it.polimi.ingsw.view.CLI;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.Network.messages.ErrorMessages.NotValidMove;
import it.polimi.ingsw.Network.messages.ErrorMessages.NotValidNumberofPlayersMessage;
import it.polimi.ingsw.Network.messages.ErrorMessages.NotValidUsernameError;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Card;

import java.io.*;

public class CliView{
    private PrintStream outputStream;
    private ItemEnum[][] board;
    private String[] CommonCards;
    private Card personalCard;
    private ItemEnum[][] shelf;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader stdIn;
    private Converter c;
    private String myUsername;
    private String userInput;

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

    public void actionHandler(Message m) throws IOException {

        switch (m.getType()) {
            case "FirstPlayer" -> {handleFirstPlayerMessage(m);}
            case "Lobby" -> outputStream.println(((LobbyMessage) m).getS());
            case "StartingGame" -> {outputStream.println(((StartingGameMessage) m).getS());}
            case "ChooseUsername" -> {handleChooseUsernameMessage(m);}
            case "NotValidUsername" -> {handleNotValidUsernameMessage(m);}
            case "GameInformation" -> {handleGameInformationMessage(m);}
            case "Waiting" -> outputStream.println(((WaitingMessage) m).getS());
            case "NotValidMove" -> handleNotValidMove(m);
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
        this.CommonCards = gameInformation.getCommonCards();
        printGame();
        if(gameInformation.getActivePlayerUsername().equals(this.myUsername)){
            this.personalCard = gameInformation.getPersonalCard();
            this.shelf = gameInformation.getShelf();
            printBookshelfAndPersonal();
            outputStream.println(gameInformation.getS());
            userInput = stdIn.readLine();
            out.println(c.convertToJSON(new MoveMessage(userInput)));
        } else {
            outputStream.println(gameInformation.getActivePlayerUsername() + " is making his move...");
        }
    }

    private void handleNotValidMove(Message m) throws IOException {
        outputStream.println(((NotValidMove) m).getS());
        userInput = stdIn.readLine();
        out.println(c.convertToJSON(new MoveMessage(userInput)));
    }

    /**
     * This method prints the board and common goal cards
     * @author Alessandro Fornara
     */
    private void printGame(){
        ItemEnum.generateCharMatrix(board, Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addNumbering(Board.BOARD_SIZE).appendToAllRows("   ").alignColumn()
                .printMatrix();
        System.out.println(CommonCards[0]);
        System.out.println(CommonCards[1]);
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

    @Deprecated
    public void dummyInputPrint(){
        outputStream.println("please write the input with the correct format");
    }

    @Deprecated
    public void blankTilesSelected(int x, int y){
        outputStream.println("the tile " + x + ","+ y + " is a blank tile, please make a new move");
    }

    @Deprecated
    public void notEnoughSpaceBookshelfPrint(){
        outputStream.println("your bookshelf hasn't enough space, please make a new move taking less tiles");
    }

    @Deprecated
    public void noFreeSidesPrint(int x, int y){
        String column;
        column = String.valueOf(y + 'a');
        //TODO: control the string
        outputStream.println("the tile " + x +","+ column + " hasn't any free sides, please make a new move");
    }

    @Deprecated
    public void notAdjacentTilesPrint(){
        outputStream.println("the selected tiles aren't adjacent");
    }

    @Deprecated
    public void notEnoughSpaceBookshelfColPrint(int y){
        outputStream.println("the column "+y+ " hasn't enough space, please make a new move");
        outputStream.println();
    }

    @Deprecated
    public void winnerPrint(String username, int points){
        outputStream.println("the match is finished");
        outputStream.println("the winner is...");
        outputStream.println(username);
        outputStream.println("the player " + username + "has done " + points + " points");
    }

    @Deprecated
    public void commonPoints(String nickname, int points, int number){
        outputStream.println(nickname + "has completed the "+ number + " common goal card");
        outputStream.println(nickname + "scored " + points + " points");
    }

}