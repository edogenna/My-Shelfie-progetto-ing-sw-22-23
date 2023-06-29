package it.polimi.ingsw.view;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.Network.messages.*;
import it.polimi.ingsw.Network.messages.Answers.*;
import it.polimi.ingsw.Network.messages.ErrorMessages.*;
import it.polimi.ingsw.model.Card;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import it.polimi.ingsw.model.CommonCards.*;


/**
 * This class offers a Graphical User Interface
 * @author Edoardo Gennaretti
 * @author Samuele Pietro Galli
 */
public class GuiView extends Application implements UI {
    private ItemEnum[][] board;
    private String[] CommonCards;
    private Card personalCard;
    private ItemEnum[][] shelf;
    //canali verso e dal server validi solo per socket
    private PrintWriter out;
    private BufferedReader in;
    private String userInput;
    private String messageToServer;
    private Scene basicScene;
    private Stage stage;
    private PrintStream outputStream;
    private BufferedReader stdIn;
    private static GuiView instance = null;
    private String[] args;
    private PrincipalSceneController controller;
    public String move;



    public GuiView(){
        this.out = null;
        this.in = null;
        this.personalCard = null;
        this.CommonCards = null;
        this.board = null;
        this.shelf = null;
        this.userInput = null;
        this.messageToServer = null;
        this.outputStream = new PrintStream(System.out);
    }
    public static GuiView getInstance() {
        if (instance == null) {
            instance = new GuiView();
        }
        return instance;
    }

    @Override
    public void setInAndOut(PrintWriter out, BufferedReader in){
        this.out = out;
        this.in = in;
    }

    public void main(String[] args, BufferedReader stdIn) {
         this.args = args;
         this.stdIn = stdIn;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PrincipalScene.fxml"));
            try {
                basicScene = new Scene(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            basicScene.setCursor(new ImageCursor(new Image("/graphics/cursor.png")));
            this.stage.setScene(basicScene);
            this.stage.getIcons().add(new Image("/graphics/icon.png"));
            this.stage.setResizable(false);
            this.stage.setTitle("MyShelfie");

            this.stage.show();
        });

        this.stage.setOnCloseRequest(e -> {
            this.stage.close();
            Platform.exit();
            System.exit(0);
        });

    }

    /**
     * This method is used to get the board
     * @return the board
     */
    public ItemEnum[][] getBoard() {
        return board;
    }

    /**
     * This method is used to get the common cards
     * @return the common cards
     */
    public String[] getCommonCards() {
        String[] a = new String[2];

        a[0] = switch (CommonCards[0]){
            case CommonCard01.constant1 -> "1";
            case CommonCard02.constant2 -> "2";
            case CommonCard03.constant3 -> "3";
            case CommonCard04.constant4 -> "4";
            case CommonCard05.constant5 -> "5";
            case CommonCard06.constant6 -> "6";
            case CommonCard07.constant7 -> "7";
            case CommonCard08.constant8 -> "8";
            case CommonCard09.constant9 -> "9";
            case CommonCard10.constant10 -> "10";
            case CommonCard11.constant11 -> "11";
            case CommonCard12.constant12 -> "12";
            default -> null;
        };

        a[1] = switch (CommonCards[1]){
            case CommonCard01.constant1 -> "1";
            case CommonCard02.constant2 -> "2";
            case CommonCard03.constant3 -> "3";
            case CommonCard04.constant4 -> "4";
            case CommonCard05.constant5 -> "5";
            case CommonCard06.constant6 -> "6";
            case CommonCard07.constant7 -> "7";
            case CommonCard08.constant8 -> "8";
            case CommonCard09.constant9 -> "9";
            case CommonCard10.constant10 -> "10";
            case CommonCard11.constant11 -> "11";
            case CommonCard12.constant12 -> "12";
            default -> null;
        };


        return a;
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
     * This method sets the controller of the principal scene
     * @param controller {@link PrincipalSceneController}
     */
    public void setController(PrincipalSceneController controller) {
        this.controller = controller;
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
     * @param m message
     * @throws IOException
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
     * @param m message
     * @throws IOException
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
     * @param m message
     * @throws IOException
     */
    private void handleCommonCardMessage(Message m) throws IOException {
        outputStream.println(((CommonCardMessage) m).getS());
        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());
    }


    /**
     * This method handles the {@link MoveMessage}
     * @param m message
     */
    private void handleMoveMessage(Message m) throws IOException{
        outputStream.println(((MoveMessage) m).getS());
        if(controller != null)
            controller.setLabelText(((MoveMessage) m).getS());

        while(move == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(out != null)
            sendMessageToSocketServer(new MoveAnswer(move));
        else sendMessageToRmiServer(new MoveAnswer(move));

        move = null;
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
        new Thread( () -> {
            Application.launch(args);
        }).start();

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
        if(controller != null)
            controller.setLabelText(((WaitingMessage) m).getS());

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
     * @param m message
     * @throws IOException
     */
    private void handleChooseUsernameMessage(Message m) throws IOException {
        outputStream.println(((ChooseUsernameMessage) m).getS());
        userInput = stdIn.readLine();

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

        while(controller == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        controller.board = board;
        controller.shelf = shelf;
        controller.personal = Integer.toString(graphicalGameInfo.getPersonalCardId());
        controller.common = getCommonCards();
        controller.updateView();
        move = null;

        outputStream.println(graphicalGameInfo.getS());

        if(controller != null)
            controller.setLabelText(graphicalGameInfo.getS());

        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else
            sendMessageToRmiServer(new ACKMessage());
    }

    /**
     * This method handles the {@link ReconnectionMessage}
     * @param m message
     */
    private void handleReconnectionMessage(Message m) throws IOException {
        outputStream.println(((ReconnectionMessage) m).getS());
        if(controller != null)
            controller.setLabelText(((ReconnectionMessage) m).getS());

        this.userInput = stdIn.readLine();
        if(this.out != null)
            sendMessageToSocketServer(new ReconnectionAnswer(this.userInput));
        else
            sendMessageToRmiServer(new ReconnectionAnswer(this.userInput));
    }

    /**
     * This method handles the {@link NotValidMoveError}
     * @throws IOException
     */
    private void handleNotValidMove() throws IOException {
        if(controller != null)
            controller.setLabelText("Move not valid, try again");

        while(move == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(out != null)
            sendMessageToSocketServer(new MoveAnswer(move));
        else sendMessageToRmiServer(new MoveAnswer(move));

        move = null;
    }

    /**
     * This method handles the {@link WinMessage}
     * @param m message
     */
    private void handleWinMessage(Message m) throws IOException {
        outputStream.println(((WinMessage) m).getS());
        if(controller != null)
            controller.setLabelText(((WinMessage) m).getS());

        if(out != null)
            sendMessageToSocketServer(new ACKMessage());
        else sendMessageToRmiServer(new ACKMessage());
    }


    /**
     * This method prints a {@link NotValidMoveError}
     * @param m
     */
    public void dummyInputPrint(Message m){
        outputStream.println(((NotValidMoveError) m).getS());
        if(controller != null)
            controller.setLabelText(((NotValidMoveError) m).getS());
    }

    /**
     * This method handles the {@link UserDisconnection}
     * @param m message
     */
    private void handleDisconnectionMessage(Message m) throws IOException {
        outputStream.println(((UserDisconnection) m).getS());

        if(controller != null)
            controller.setLabelText(((UserDisconnection) m).getS());

        if(this.out != null)
            sendMessageToSocketServer(new ACKMessage());
        else
            sendMessageToRmiServer(new ACKMessage());
    }


    /**
     * This method handles the {@link OldGameId}
     * @param m message
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
}
