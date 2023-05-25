package it.polimi.ingsw.view;


import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.Network.messages.Message;
import it.polimi.ingsw.model.Card;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Objects;

public class GuiView extends Application implements UI {

    private ItemEnum[][] board;
    private String[] CommonCards;
    private Card personalCard;
    private ItemEnum[][] shelf;
    private PrintWriter out;
    private BufferedReader in;
    private String myUsername;
    private String userInput;
    private String messageToServer;

    public GuiView(PrintWriter out, BufferedReader in){
        this.out = out;
        this.in = in;
        this.personalCard = null;
        this.CommonCards = null;
        this.board = null;
        this.shelf = null;
        this.myUsername = null;
        this.userInput = null;
        this.messageToServer = null;
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root, 300, 275));
        stage.setTitle("MyShelfie");

//        stage.getIcons().add(new Image("/resources/graphics/icon.png"));
        stage.show();
    }

    @Override
    public String actionHandler(Message m) throws IOException{
        /*
        replicating the action handler in cliview
        switch (m.getType()) {
            case "MoveMessage" -> handleMoveMessage(m);
            case "FirstPlayer" -> {handleFirstPlayerMessage(m);}
            case "Lobby" -> {handleLobbyMessage(m);}
            case "CommonCard" -> handleCommonCardMessage(m);
            case "ChatBegins" -> {handleChatBeginsMessage(m);}
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
            case "ChatMessage" -> {handleChatMessage(m);}
            case "Reconnect" -> {handleReconnectionMessage(m);}
            case "OldGameId" -> {handleOldGameIdMessage(m);}
            case "OldIdNotValid" ->{handleOldIdNotValidMessage(m);}
            case "Disconnection" -> {handleDisconnectionMessage(m);}
            case "WelcomeBack" -> {handleWelcomeBackMessage(m);}
            case "TurnTimeOut" -> {handleTurnTimeOut(m);}
            default -> throw new IllegalStateException("Unexpected value: " + m.getType());
        }
        */
        return messageToServer;
    }
}
