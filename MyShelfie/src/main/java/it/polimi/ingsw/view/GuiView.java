package it.polimi.ingsw.view;


import it.polimi.ingsw.GUI.controllers.ChooseReconnectionScene;
import it.polimi.ingsw.GUI.controllers.FXMLChooseNickController;
import it.polimi.ingsw.GUI.controllers.FXMLFirstPlayerController;
import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.Network.messages.*;

import it.polimi.ingsw.Network.messages.Answers.*;
import it.polimi.ingsw.Network.messages.ErrorMessages.NotValidNumberofPlayersMessage;
import it.polimi.ingsw.Network.messages.ErrorMessages.NotValidUsernameError;
import it.polimi.ingsw.model.Card;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;

import static java.lang.Thread.sleep;

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
    private FXMLChooseNickController fxmlChooseNickController;
    private FXMLFirstPlayerController fxmlFirstPlayerController;
    private ChooseReconnectionScene chooseReconnectionScene;
    private static GuiView instance = null;


    public GuiView(){
        this.out = null;
        this.in = null;
        this.personalCard = null;
        this.CommonCards = null;
        this.board = null;
        this.shelf = null;
        this.userInput = null;
        this.messageToServer = null;
        this.fxmlChooseNickController = new FXMLChooseNickController();
        this.fxmlFirstPlayerController = new FXMLFirstPlayerController();
        this.chooseReconnectionScene = new ChooseReconnectionScene();
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

    public void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GuiView guiView = GuiView.getInstance();
        Parent root = (new FXMLLoader(getClass().getResource("/fxml/ChooseReconnectionScene.fxml"))).load();
        basicScene = new Scene(root);
        basicScene.setCursor(new ImageCursor(new Image("/graphics/cursor.png")));

        this.stage = stage;
        stage.setScene(basicScene);
        stage.getIcons().add(new Image("/graphics/icon.png"));
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setResizable(true);

        stage.setTitle("MyShelfie");

        stage.show();
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

    public void handleChooseUsernameMessage(Message m) throws IOException {
        while((userInput = fxmlChooseNickController.getUsername()) == null){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(out != null)
            sendMessageToSocketServer(new UsernameAnswer(userInput));
        else
            sendMessageToRmiServer(new UsernameAnswer(userInput));
    }
    private void handleNotValidUsernameError(Message m) throws IOException {
        fxmlChooseNickController.setWrongUsername(((NotValidUsernameError) m).getS());

         while(fxmlChooseNickController.getUsername().equals(userInput)){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        userInput = fxmlChooseNickController.getUsername();
        if(out != null)
            sendMessageToSocketServer(new UsernameAnswer(userInput));
        else
            sendMessageToRmiServer(new UsernameAnswer(userInput));
    }
    private void handleFirstPlayerMessage(Message m) throws IOException {
        Parent root = (new FXMLLoader(getClass().getResource("/fxml/ChooseNumPlayers.fxml"))).load();

        stage.setScene(new Scene(root, 300, 275));
        int numPlayers;
        fxmlFirstPlayerController.setMessageLabel(((FirstPlayerMessage) m).getS());
        numPlayers = fxmlFirstPlayerController.getNumberOfPlayers();

        while (numPlayers < 2 || numPlayers > 4) {
            fxmlFirstPlayerController.setNumberErrorLabel((new NotValidNumberofPlayersMessage()).getS());
            numPlayers = fxmlFirstPlayerController.getNumberOfPlayers();
        }
        if(out != null)
            sendMessageToSocketServer(new NumberOfPlayersAnswer(numPlayers));
        else
            sendMessageToRmiServer(new NumberOfPlayersAnswer(numPlayers));
    }

    @Override
    public String actionHandler(Message m) throws IOException {
        switch (m.getType()){
            case "ChooseUsername" -> {handleChooseUsernameMessage(m);}
            case "NotValidUsername" -> {handleNotValidUsernameError(m);}
            case "FirstPlayer" -> {handleFirstPlayerMessage(m);}
            case "Reconnect" -> {handleReconnectionMessage(m);}
        }
        /*
        replicating the action handler in cliview
        switch (m.getType()) {
            case "MoveMessage" -> handleMoveMessage(m);

            case "Lobby" -> {handleLobbyMessage(m);}
            case "CommonCard" -> handleCommonCardMessage(m);
            case "ChatBegins" -> {handleChatBeginsMessage(m);}
            case "StartingGame" -> {handleStartingGameMessage(m);}


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

    private void handleReconnectionMessage(Message m) throws IOException {
        int r;
        String s;
        System.out.println("cose a caso");
        System.out.println("PwerfgqwerfgqwergUTO" + chooseReconnectionScene.getIsReconnection());
        r = chooseReconnectionScene.getIsReconnection();



        while(chooseReconnectionScene.getIsReconnection() == -1){
            try {
                sleep(100);
                //System.out.println(this.chooseReconnectionScene.gggggg);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //r = chooseReconnectionScene.gggggg;
        }

        System.out.println("PORCODIO ANDAVANTI FROCIction PREMUTO");

        if(chooseReconnectionScene.getIsReconnection() == 0)
            s = "newgame";
        else
            s = "Reconnection";


        if(out != null)
            sendMessageToSocketServer(new UsernameAnswer(s));
        else
            sendMessageToRmiServer(new UsernameAnswer(s));

        System.out.println("PMESAGIO INVBIATO TO");

        //changeScenes("/fxml/ChooseNick.fxml");
    }
    private void changeScenes(String sceneName){
        Platform.runLater(() -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(sceneName));
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
