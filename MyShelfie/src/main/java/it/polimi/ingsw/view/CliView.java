package it.polimi.ingsw.view;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.Network.messages.Answers.*;
import it.polimi.ingsw.Network.messages.ErrorMessages.*;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Card;

import java.io.*;

/**
 * This class offers a User Interface to the user via terminal
 * @author Alessandro Fornara
 * @author Donato Fiore
 */
public class CliView implements UI{
    private PrintStream outputStream;
    private ItemEnum[][] board;
    private String[] CommonCards;
    private Card personalCard;
    private ItemEnum[][][] shelves;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader stdIn;
    private String myUsername;
    private String userInput;
    private String messageToServer;
    int id;
    String[] usernames;

    public CliView(PrintWriter out, BufferedReader in, BufferedReader stdIn){
        this.out = out;
        this.in = in;
        this.stdIn = stdIn;
        this.outputStream = new PrintStream(System.out);
        this.personalCard = null;
        this.CommonCards = null;
        this.board = null;
        this.shelves = null;
        this.myUsername = null;
        this.userInput = null;
        this.messageToServer = null;
        this.id = 0;
        this.usernames = null;
    }

    @Override
    public void setInAndOut(PrintWriter out, BufferedReader in){//Do nothing
    }

    @Override
    public String actionHandler(Message m) throws IOException {

        if(m == null){
            return null;
        }
        switch (m.getType()) {
            case "MoveMessage" -> handleMoveMessage(m);
            case "FirstPlayer" -> {handleFirstPlayerMessage(m);}
            case "Lobby" -> {handleLobbyMessage(m);}
            case "CommonCard" -> handleCommonCardMessage(m);
            case "StartingGame" -> {handleStartingGameMessage(m);}
            case "ChooseUsername" -> {handleChooseUsernameMessage(m);}
            case "NotValidUsername" -> {handleNotValidUsernameError(m);}
            case "GraphicalGameInfo" -> {handleGraphicalInfoMessage(m);}
            case "Waiting" -> {handleWaitingMessage(m);}
            case "NotValidMove" -> {dummyInputPrint(m); handleNotValidMove();}
            case "Win" -> handleWinMessage(m);
            case "NotEnoughSpaceColumn" -> {outputStream.println(((NotEnoughSpaceColumnError) m).getS()); handleNotValidMove();}
            case "InvalidColumn" -> {outputStream.println(((InvalidColumnError) m).getS()); handleNotValidMove();}
            case "EmptyPosition" -> {outputStream.println(((EmptyPositionError) m).getS()); handleNotValidMove();}
            case "NotAdjTiles" -> {outputStream.println(((NotAdjacTiles) m).getS()); handleNotValidMove();}
            case "NotEnoughSpaceBookshelf" -> {outputStream.println(((NotEnoughSpaceBookshelfError) m).getS()); handleNotValidMove();}
            case "NoFreeSide" -> {outputStream.println(((NoFreeSideError) m).getS()); handleNotValidMove();}
            case "Reconnect" -> {handleReconnectionMessage(m);}
            case "OldGameId" -> {handleOldGameIdMessage(m);}
            case "OldIdNotValid" ->{handleOldIdNotValidMessage(m);}
            case "Disconnection" -> {handleDisconnectionMessage(m);}
            case "WelcomeBack" -> {handleWelcomeBackMessage(m);}
            case "RefusedConnection" -> {
                handleRefusedConnectionMessage(m);}
            case "TimeOut" -> {handleTimeOut(m);}
            case "UserIdMessage" -> {handleUserId(m);}
            default -> throw new IllegalStateException("Unexpected value: " + m.getType());
        }
        return messageToServer;
    }

    /**
     * This method handles the {@link UserIdMessage} message
     * @param m message
     * @throws IOException if an I/O error occurs
     */

    private void handleUserId(Message m) throws IOException {
        outputStream.println(((UserIdMessage)m).getS());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else
            sendMessageToRmiServer(new ACKMessage());
    }

    /**
     * This method handles the {@link TimeoutMessage} message
     * @param m message
     * @throws IOException if an I/O error occurs
     */
    private void handleTimeOut(Message m) throws IOException {
        outputStream.println(((TimeoutMessage)m).getS());
        if(out != null)
            sendMessageToSocketServer(new TimeOutAnswer());
        else
            sendMessageToRmiServer(new TimeOutAnswer());
    }

    /**
     * This method handles the {@link RefusedConnectionMessage} message
     * @throws IOException if an I/O error occurs
     */
    private void handleRefusedConnectionMessage(Message m) throws IOException {
        outputStream.println(((RefusedConnectionMessage) m).getS());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else
            sendMessageToRmiServer(new ACKMessage());
        System.exit(0);
    }
    /**
     * This method handles the {@link WelcomeBackMessage}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleWelcomeBackMessage(Message m) throws IOException {
        outputStream.println(((WelcomeBackMessage) m).getString());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else
            sendMessageToRmiServer(new ACKMessage());
    }

    /**
     * This method handles the {@link OldIdNotValid}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleOldIdNotValidMessage(Message m) throws IOException {
        outputStream.println(((OldIdNotValid) m).getS());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else
            sendMessageToRmiServer(new ACKMessage());
    }

    /**
     * This method handles the {@link CommonCardMessage}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleCommonCardMessage(Message m) throws IOException {
        outputStream.println(((CommonCardMessage) m).getS());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());
    }

    //function that changes scene


    /**
     * This method handles the {@link MoveMessage}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleMoveMessage(Message m) throws IOException{
        outputStream.println(((MoveMessage) m).getS());
        userInput = stdIn.readLine();
        if(out != null)
            sendMessageToSocketServer(new MoveAnswer(userInput));
        else sendMessageToRmiServer(new MoveAnswer(userInput));
    }
    /**
     * This method handles the {@link LobbyMessage}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleLobbyMessage(Message m) throws IOException {
        outputStream.println(((LobbyMessage) m).getS());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());
    }

    /**
     * This method handles the {@link StartingGameMessage}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleStartingGameMessage(Message m) throws IOException {
        outputStream.println(((StartingGameMessage) m).getS());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());
    }

    /**
     * This method handles the {@link WaitingMessage}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleWaitingMessage(Message m) throws IOException {
        outputStream.println(((WaitingMessage) m).getS());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());
    }
    /**
     * This method handles the {@link FirstPlayerMessage}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleFirstPlayerMessage(Message m) throws IOException {
        outputStream.println(((FirstPlayerMessage) m).getS());
        userInput = stdIn.readLine();
        while (userInput.length()!=1 || (userInput.charAt(0) < '2' || userInput.charAt(0) > '4')) {
            outputStream.println(new NotValidNumberofPlayersMessage().getS());
            userInput = stdIn.readLine();
        }
        if(out != null)
            sendMessageToSocketServer(new NumberOfPlayersAnswer(Integer.parseInt(userInput)));
        else sendMessageToRmiServer(new NumberOfPlayersAnswer(Integer.parseInt(userInput)));
    }

    /**
     * This method handles the {@link ChooseUsernameMessage}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleChooseUsernameMessage(Message m) throws IOException {
        outputStream.println(((ChooseUsernameMessage) m).getS());
        userInput = stdIn.readLine();
        this.myUsername = userInput;
        if(out != null)
            sendMessageToSocketServer(new UsernameAnswer(userInput));
        else
            sendMessageToRmiServer(new UsernameAnswer(userInput));
    }

    /**
     * This method handles the {@link NotValidUsernameError}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleNotValidUsernameError(Message m) throws IOException {
        outputStream.println(((NotValidUsernameError) m).getS());
        userInput = stdIn.readLine();
        this.myUsername = userInput;
        if(out != null)
            sendMessageToSocketServer(new UsernameAnswer(userInput));
        else sendMessageToRmiServer(new UsernameAnswer(userInput));
    }

    /**
     * This method handles the {@link GraphicalGameInfo}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleGraphicalInfoMessage(Message m) throws IOException {
        GraphicalGameInfo graphicalGameInfo = (GraphicalGameInfo) m;
        this.board = graphicalGameInfo.getBoard();
        this.CommonCards = graphicalGameInfo.getCommonCards();
        this.personalCard = graphicalGameInfo.getPersonalCard();
        this.shelves = graphicalGameInfo.getShelves();
        this.id = graphicalGameInfo.getYourId();
        this.usernames = graphicalGameInfo.getUsernames();
        printGame();
        outputStream.println(graphicalGameInfo.getS());

        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());
    }

    /**
     * This method handles the {@link NotValidMoveError}
     * @throws IOException if an I/O error occurs
     */
    private void handleNotValidMove() throws IOException {
        userInput = stdIn.readLine();
        if(out != null)
            sendMessageToSocketServer(new MoveAnswer(userInput));
        else sendMessageToRmiServer(new MoveAnswer(userInput));
    }

    /**
     * This method handles the {@link WinMessage}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleWinMessage(Message m) throws IOException {
        outputStream.println(((WinMessage) m).getS());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());
    }

    /**
     * This method prints the board, the player's bookshelf and his personal card and common goal cards
     */
    private void printGame(){
        CharMatrix shelf = null;
        int i = 0;
        boolean iniz = false;
        for(; i<4 && !iniz; i++){
            if(i != id){
                shelf = ItemEnum.generateCharMatrix(shelves[i], 6, 5)
                        .addColumnNumbering(5)
                        .newLineAtTop(usernames[i]);
                iniz = true;
            }
        }

        for(; i<4; i++){
            if(i != id && shelves[i] != null){
                shelf = shelf.alignColumn().appendToAllRows("   ").addOnRight(
                        ItemEnum.generateCharMatrix(shelves[i], 6, 5)
                        .addColumnNumbering(5)
                        .newLineAtTop(usernames[i]));
            }
        }


        CharMatrix boardCM = ItemEnum.generateCharMatrix(board, Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addNumbering(Board.BOARD_SIZE)
                .newLineAtTop("Board:")
                .alignColumn()
                .appendToAllRows("       ");

        CharMatrix bookshelfCM = ItemEnum.generateCharMatrix(shelves[id], 6, 5)
                .addColumnNumbering(5)
                .newLineAtTop("Your Bookshelf:")
                .alignColumn()
                .appendToAllRows("     ");
        CharMatrix personalCM = ItemEnum.generateCharMatrix(personalCard.getMatrix(), 6, 5)
                .addColumnNumbering(5)
                .newLineAtTop("Your Personal Card:");

        outputStream.println("\n\n");
        shelf.printMatrix();
        boardCM.addOnRight(bookshelfCM).addOnRight(personalCM).printMatrix();
        
        outputStream.println(CommonCards[0]);
        outputStream.println(CommonCards[1]);
    }

    /**
     * This method prints a {@link NotValidMoveError}
     * @param m {@link Message}
     */
    public void dummyInputPrint(Message m){
        outputStream.println(((NotValidMoveError) m).getS());
    }

    /**
     * This method sends a message to the socket server
     * @param m {@link Message}
     */
    private void sendMessageToSocketServer(Message m){
        String jsonString = Converter.convertToJSON(m);
        out.println(jsonString);
    }

    /**
     * This method sends a message to the rmi server
     * @param m {@link Message}
     */
    private void sendMessageToRmiServer(Message m) {
        this.messageToServer = Converter.convertToJSON(m);
    }



    /**
     * This method handles the {@link ReconnectionMessage}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleReconnectionMessage(Message m) throws IOException {
        outputStream.println(((ReconnectionMessage) m).getS());
        this.userInput = stdIn.readLine();
        if(this.out != null)
            sendMessageToSocketServer(new ReconnectionAnswer(this.userInput));
        else
            sendMessageToRmiServer(new ReconnectionAnswer(this.userInput));
    }

    /**
     * This method handles the {@link OldGameId}
     * @param m {@link Message}
     * @throws IOException if an I/O error occurs
     */
    private void handleOldGameIdMessage(Message m) throws IOException {
        outputStream.println(((OldGameId) m).getS());
        this.userInput = stdIn.readLine();
        while (userInput.length()!=1 || (userInput.charAt(0) < '0' || userInput.charAt(0) > '9')) {
            outputStream.println("Insert a number");
            userInput = stdIn.readLine();
        }
        if(this.out != null)
            sendMessageToSocketServer(new OldGameIdAnswer(this.userInput));
        else
            sendMessageToRmiServer(new OldGameIdAnswer(this.userInput));
    }

    /**
     * This method handles the {@link UserDisconnection}
     * @param m {@link Message}
     */
    private void handleDisconnectionMessage(Message m) {
        outputStream.println(((UserDisconnection) m).getS());
        if(this.out != null)
            sendMessageToSocketServer(new ACKMessage());
        else
            sendMessageToRmiServer(new ACKMessage());
    }
}