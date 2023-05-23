package it.polimi.ingsw.GUI.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenucontroller {
    @FXML
    private ImageView title;

    @FXML
    private Button setupGame;

    @FXML
    private Button quitGame;

    @FXML
    private ImageView roxleyIcon;

    @FXML
    private Label credits;

    @FXML
    public void setupGameClicked() throws IOException {
        // esempio: Apertura di una nuova finestra di gioco
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("C:/Users/User/Desktop/Prog SOFTWARE/MyShelfie/src/main/resources/fxml/ConnectionScene.fxml"));
        Parent root = loader.load();
        ConnectionController connectionController = loader.getController();
        Scene scene = new Scene(root);
        //imposta la scena appena caricata
        stage.setScene(scene);
        //mostra la finestra
        stage.show();
    }

    @FXML
    public void quit() {
        System.out.println("Thanks for playing! See you next time!");
        System.exit(0);
    }
}
