package it.polimi.ingsw.view;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.Network.messages.Answers.*;
import it.polimi.ingsw.Network.messages.ErrorMessages.*;
import it.polimi.ingsw.Network.server.RmiServerInterface;
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
    private String myUsername;
    private String userInput;
    private RmiServerInterface remoteObject;
    private String messageToServer;

    public CliView(PrintWriter out, BufferedReader in, BufferedReader stdIn, RmiServerInterface remoteObject){
        this.out = out;
        this.in = in;
        this.stdIn = stdIn;
        this.outputStream = new PrintStream(System.out);
        this.personalCard = null;
        this.CommonCards = null;
        this.board = null;
        this.shelf = null;
        this.myUsername = null;
        this.userInput = null;
        this.remoteObject = remoteObject;
    }

    /**
     * This method decides how to handle a certain message received by the client
     * @param m message
     * @throws IOException
     */
    public String actionHandler(Message m) throws IOException {

        switch (m.getType()) {
            case "MoveMessage" -> handleMoveMessage(m);
            case "FirstPlayer" -> {handleFirstPlayerMessage(m);}
            case "Lobby" -> {handleLobbyMessage(m);}
            case "CommonCard" -> {outputStream.println(((CommonCardMessage) m).getS());
                if(out != null)
                    sendMessageToSocketServer(new ACKMessage());
                else sendMessageToRmiServer(new ACKMessage());}
            case "ChatBegins" -> {handleChatBeginsMessage(m);}
            case "StartingGame" -> {handleStartingGameMessage(m);}
            case "ChooseUsername" -> {handleChooseUsernameMessage(m);}
            case "NotValidUsername" -> {handleNotValidUsernameError(m);}
            case "GraphicalGameInfo" -> {handleGraphicalInfoMessage(m);}
            case "Waiting" -> {handleWaitingMessage(m);}
            case "NotValidMove" -> {dummyInputPrint(m); handleNotValidMove();}
            case "Winner" -> handleWinMessage(m);
            case "NotEnoughSpaceColumn" -> {outputStream.println(((NotEnoughSpaceColumnError) m).getS()); handleNotValidMove();}
            case "InvalidColumn" -> {outputStream.println(((InvalidColumnError) m).getS()); handleNotValidMove();}
            case "EmptyPosition" -> {outputStream.println(((EmptyPositionError) m).getS()); handleNotValidMove();}
            case "NotAdjTiles" -> {outputStream.println(((NotAdjacTiles) m).getS()); handleNotValidMove();}
            case "NotEnoughSpaceBookshelf" -> {outputStream.println(((NotEnoughSpaceBookshelfError) m).getS()); handleNotValidMove();}
            case "NoFreeSide" -> {outputStream.println(((NoFreeSideError) m).getS()); handleNotValidMove();}
            case "ChatMessage" -> {handleChatMessage(m);}
            case "Reconnect" -> {handleReconnectionMessage(m);}
            case "OldGameId" -> {handleOldGameIdMessage(m);}
            default -> throw new IllegalStateException("Unexpected value: " + m.getType());
        }

        return messageToServer;
    }

    /**
     * This method handles the {@link MoveMessage}
     * @param m message
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
     * @param m message
     */
    private void handleLobbyMessage(Message m) throws IOException {
        outputStream.println(((LobbyMessage) m).getS());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());
    }

    /**
     * This method handles the {@link StartingGameMessage}
     * @param m message
     */
    private void handleStartingGameMessage(Message m) throws IOException {
        outputStream.println(((StartingGameMessage) m).getS());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());
    }

    /**
     * This method handles the {@link WaitingMessage}
     * @param m message
     */
    private void handleWaitingMessage(Message m) throws IOException {
        outputStream.println(((WaitingMessage) m).getS());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());
    }
    /**
     * This method handles the {@link FirstPlayerMessage}
     * @param m message
     * @throws IOException
     */
    private void handleFirstPlayerMessage(Message m) throws IOException {
        outputStream.println(((FirstPlayerMessage) m).getS());
        userInput = stdIn.readLine();
        try {
            while (Integer.parseInt(userInput) < 2 || Integer.parseInt(userInput) > 4) {
                outputStream.println(new NotValidNumberofPlayersMessage().getS());
                userInput = stdIn.readLine();
            }
        }catch (NumberFormatException e){
            outputStream.println(new NotValidNumberofPlayersMessage().getS());
            userInput = stdIn.readLine();
        }
        if(out != null)
            sendMessageToSocketServer(new NumberOfPlayersAnswer(Integer.parseInt(userInput)));
        else sendMessageToRmiServer(new NumberOfPlayersAnswer(Integer.parseInt(userInput)));
    }

    /**
     * This method handles the {@link ChooseUsernameMessage}
     * @param m message
     * @throws IOException
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
     * @param m message
     * @throws IOException
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
     * @param m message
     * @throws IOException
     */
    private void handleGraphicalInfoMessage(Message m) throws IOException {
        GraphicalGameInfo graphicalGameInfo = (GraphicalGameInfo) m;
        this.board = graphicalGameInfo.getBoard();
        this.CommonCards = graphicalGameInfo.getCommonCards();
        this.personalCard = graphicalGameInfo.getPersonalCard();
        this.shelf = graphicalGameInfo.getShelf();
        printGame();
        printBookshelfAndPersonal();
        outputStream.println(graphicalGameInfo.getS());

        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());
    }

    /**
     * This method handles the {@link NotValidMoveError}
     * @throws IOException
     */
    private void handleNotValidMove() throws IOException {
        userInput = stdIn.readLine();
        if(out != null)
            sendMessageToSocketServer(new MoveAnswer(userInput));
        else sendMessageToRmiServer(new MoveAnswer(userInput));
    }

    /**
     * This method handles the {@link WinMessage}
     * @param m message
     */
    private void handleWinMessage(Message m) throws IOException {
        outputStream.println(((WinMessage) m).getS());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());
    }

    /**
     * This method prints the board and common goal cards
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
     */
    private void printBookshelfAndPersonal(){
        outputStream.println("\n" + "Your Bookshelf:    Your Personal Card:");
        ItemEnum.generateCharMatrix(shelf, 6, 5)
                .addColumnNumbering(5)
                .appendToAllRows("   ")
                .alignColumn()
                .addOnRight(ItemEnum.generateCharMatrix(personalCard.getMatrix(), 6, 5).addColumnNumbering(5))
                .printMatrix();
    }

    /**
     * This method prints a {@link NotValidMoveError}
     * @param m
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
    private void sendMessageToRmiServer(Message m) throws IOException {
        this.messageToServer = Converter.convertToJSON(m);
    }

    /**
     * This method handles the {@link ChatMessage}
     * @param m message
     */
    private void handleChatMessage(Message m) throws IOException {
        outputStream.print(((ChatMessage) m).getSender() + ": ");
        outputStream.println(((ChatMessage) m).getMessage());

        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());

    }

    /**
     * This method handles the {@link ChatBeginsMessage}
     * @param m message
     */
    private void handleChatBeginsMessage(Message m) throws IOException {
        outputStream.println(((ChatBeginsMessage) m).getS());
        userInput = stdIn.readLine();

        if(out != null)
            sendMessageToSocketServer(new ChatMessage(userInput, "1"));
        else sendMessageToRmiServer(new ChatMessage(userInput, "1"));
    }

    /**
     * This method handles the {@link ReconnectionMessage}
     * @param m message
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
     * @param m message
     */
    private void handleOldGameIdMessage(Message m) throws IOException {
        outputStream.println(((OldGameId) m).getS());
        this.userInput = stdIn.readLine();
        if(this.out != null)
            sendMessageToSocketServer(new OldGameIdAnswer(this.userInput));
        else
            sendMessageToRmiServer(new OldGameIdAnswer(this.userInput));
    }

}