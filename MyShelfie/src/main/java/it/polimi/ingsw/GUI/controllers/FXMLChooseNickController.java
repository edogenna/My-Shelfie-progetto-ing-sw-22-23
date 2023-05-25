package it.polimi.ingsw.GUI.controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


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
    public void setWrongNumPlayers(String s){
        wrongNumPlayers.setText(s);
        wrongNumPlayers.setVisible(true);
    }

    public void setFirstPlayerMessage(String s){
        firstPlayerMessage.setText(s);
        firstPlayerMessage.setVisible(true);
    }

    public String getNumPlayers() {
        return numPlayers.getText();
    }
}
