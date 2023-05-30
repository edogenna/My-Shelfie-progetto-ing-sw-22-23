package it.polimi.ingsw.GUI.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class FXMLChooseNickController  implements Initializable {
    @FXML
    private Pane basePane;

    @FXML
    private Label loginErrorLabel;

    @FXML
    private Button loginButton;

    @FXML
    private TextField nickname;
    @FXML
    private Label wrongUsername;
    @FXML
    private Label firstPlayerMessage;
    @FXML
    private TextField numPlayers;
    @FXML
    private Label wrongNumPlayers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wrongUsername.setVisible(false);
        firstPlayerMessage.setVisible(false);
        wrongNumPlayers.setVisible(false);
    }
    public String getUsernameFromTextfield(){
        return nickname.getText();
    }

    public void setWrongUsername(String s) {
        wrongUsername.setText(s);
        wrongUsername.setVisible(true);
    }
    //TODO: CHANGE METHOD LOCATION
    public void setWrongNumPlayers(String s){
        wrongNumPlayers.setText(s);
        wrongNumPlayers.setVisible(true);
    }

    //ATTIVO LA LABEL PER NOTIFICARE IL PRIMO GIOCATORE
    public void setFirstPlayerMessage(String s){
        firstPlayerMessage.setText(s);
        firstPlayerMessage.setVisible(true);
    }

    public String getNumPlayers() {
        return numPlayers.getText();
    }

    public void loginButtonPressed(){
        loginButton.setOnAction(event -> {

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("C:/Users/User/Desktop/Prog SOFTWARE/MyShelfie/src/main/resources/fxml/FirstPlayerScene.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scena = new Scene(root);

            Stage stage = (Stage) loginButton.getScene().getWindow();

            stage.setScene(scena);
            stage.show();
        });

    }

}
