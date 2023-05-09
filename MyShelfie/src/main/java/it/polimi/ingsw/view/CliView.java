package it.polimi.ingsw.view;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.Network.messages.Answers.MoveAnswer;
import it.polimi.ingsw.Network.messages.Answers.NumberOfPlayersAnswer;
import it.polimi.ingsw.Network.messages.Answers.UsernameAnswer;
import it.polimi.ingsw.Network.messages.ErrorMessages.*;
import it.polimi.ingsw.Network.server.RmiGame;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Card;

import java.io.*;

/**
 * This class offers a User Interface to the user via terminal
 * @author Alessandro Fornara
 * @author Donato Fiore
 */
public class CliView{
    private final PrintStream outputStream;
    private ItemEnum[][] board;
    private String[] CommonCards;
    private Card personalCard;
    private ItemEnum[][] shelf;
    private final PrintWriter out;
    private final BufferedReader in;
    private final BufferedReader stdIn;
    private final Converter c;
    private String myUsername;
    private String userInput;
    private RmiGame remoteObject;

    public CliView(PrintWriter out, BufferedReader in, BufferedReader stdIn, RmiGame remoteObject){
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
        this.remoteObject = remoteObject;
    }

    /**
     * This method decides how to handle a certain message received by the client
     * @author Alessandro Fornara
     * @param m message
     * @throws IOException
     */
    public void actionHandler(Message m) throws IOException {

        switch (m.getType()) {
            case "FirstPlayer" -> {handleFirstPlayerMessage(m);}
            case "Lobby" -> outputStream.println(((LobbyMessage) m).getS());
            case "StartingGame" -> {outputStream.println(((StartingGameMessage) m).getS());}
            case "ChooseUsername" -> {handleChooseUsernameMessage(m);}
            case "NotValidUsername" -> {handleNotValidUsernameError(m);}
            case "GameInformation" -> {handleGameInformationMessage(m);}
            case "Waiting" -> outputStream.println(((WaitingMessage) m).getS());
            case "NotValidMove" -> {dummyInputPrint(m);
                handleNotValidMove();}
            case "Winner" -> handleWinMessage(m);
            case "NotEnoughSpaceColumn" -> {outputStream.println(((NotEnoughSpaceColumnError) m).getS());
                handleNotValidMove();}
            case "InvalidColumn" -> {outputStream.println(((InvalidColumnError) m).getS());
                handleNotValidMove();}
            case "EmptyPosition" -> {outputStream.println(((EmptyPositionError) m).getS());
                handleNotValidMove();}
            case "NotAdjTiles" -> {outputStream.println(((NotAdjacTiles) m).getS());
                handleNotValidMove();}
            case "NotEnoughSpaceBookshelf" -> {outputStream.println(((NotEnoughSpaceBookshelfError) m).getS());
                handleNotValidMove();}
            case "NoFreeSide" -> {outputStream.println(((NoFreeSideError) m).getS());
                handleNotValidMove();}
        }
    }

    /**
     * This method handles the {@link FirstPlayerMessage}
     * @author Alessandro Fornara
     * @param m message
     * @throws IOException
     */
    private void handleFirstPlayerMessage(Message m) throws IOException {
        outputStream.println(((FirstPlayerMessage) m).getS());
        userInput = stdIn.readLine();
        while(Integer.parseInt(userInput)<2 || Integer.parseInt(userInput)>4){
            outputStream.println(new NotValidNumberofPlayersMessage().getS());
            userInput = stdIn.readLine();
        }
        if(out != null)
            sendMessageToServer(new NumberOfPlayersAnswer(Integer.parseInt(userInput)));
        else sendMessageToServer(new NumberOfPlayersAnswer(Integer.parseInt(userInput)), 2);
    }

    /**
     * This method handles the {@link ChooseUsernameMessage}
     * @author Alessandro Fornara
     * @param m message
     * @throws IOException
     */
    private void handleChooseUsernameMessage(Message m) throws IOException {
        outputStream.println(((ChooseUsernameMessage) m).getS());
        userInput = stdIn.readLine();
        this.myUsername = userInput;
        if(out != null)
            sendMessageToServer(new UsernameAnswer(userInput));
        else
            sendMessageToServer(new UsernameAnswer(userInput), 1);
    }

    /**
     * This method handles the {@link NotValidUsernameError}
     * @author Alessandro Fornara
     * @param m message
     * @throws IOException
     */
    private void handleNotValidUsernameError(Message m) throws IOException {
        outputStream.println(((NotValidUsernameError) m).getS());
        userInput = stdIn.readLine();
        this.myUsername = userInput;
        if(out != null)
            sendMessageToServer(new UsernameAnswer(userInput));
        else sendMessageToServer(new UsernameAnswer(userInput), 1);
    }

    /**
     * This method handles the {@link GameInformationMessage}
     * @author Alessandro Fornara
     * @param m message
     * @throws IOException
     */
    private void handleGameInformationMessage(Message m) throws IOException {
        GameInformationMessage gameInformation = (GameInformationMessage) m;
        this.board = gameInformation.getBoard();
        this.CommonCards = gameInformation.getCommonCards();
        printGame();
        if(gameInformation.getActivePlayerUsername().equals(this.myUsername)){
            this.personalCard = gameInformation.getPersonalCard();
            this.shelf = gameInformation.getShelf();
            printBookshelfAndPersonal();
            outputStream.println(gameInformation.getS());
            userInput = stdIn.readLine();
            if(out != null)
                sendMessageToServer(new MoveAnswer(userInput));
            else sendMessageToServer(new MoveAnswer(userInput), 3);
        } else {
            outputStream.println(gameInformation.getActivePlayerUsername() + " is making his move...");
        }
    }

    /**
     * This method handles the {@link NotValidMoveError}
     * @author Alessandro Fornara
     * @throws IOException
     */
    private void handleNotValidMove() throws IOException {
        userInput = stdIn.readLine();
        if(out != null)
            sendMessageToServer(new MoveAnswer(userInput));
        else sendMessageToServer(new MoveAnswer(userInput), 3);
    }

    /**
     * This method handles the {@link WinMessage}
     * @param m message
     */
    private void handleWinMessage(Message m){
        outputStream.println(((WinMessage) m).getS());
    }

    /**
     * This method prints the board and common goal cards
     * @author Alessandro Fornara
     */
    private void printGame(){
        ItemEnum.generateCharMatrix(board, Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addNumbering(Board.BOARD_SIZE).appendToAllRows("   ").alignColumn()
                .printMatrix();
        outputStream.println(CommonCards[0]);
        outputStream.println(CommonCards[1]);
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

    public void dummyInputPrint(Message m){
        outputStream.println(((NotValidMoveError) m).getS());
    }

    /**
     * This method sends a message to the server
     * @author Alessandro Fornara
     * @param m {@link Message}
     */
    private void sendMessageToServer(Message m){
        String jsonString = c.convertToJSON(m);
        out.println(jsonString);
    }

    private void sendMessageToServer(Message m, int x){
        String jsonString = c.convertToJSON(m);
        switch (x){
            case 1: //Username function;
            case 2: //Number of players;
            case 3: //Move function;
        }
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