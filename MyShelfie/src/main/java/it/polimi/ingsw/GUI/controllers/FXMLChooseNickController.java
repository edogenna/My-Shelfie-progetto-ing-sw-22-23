package it.polimi.ingsw.GUI.controllers;

import it.polimi.ingsw.view.GuiView;
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
    @FXML
    private TextField loginTextBox;

    private String username;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginErrorLabel.setVisible(false);
        loginButton.setOnAction(event -> {
            username = loginTextBox.getText();
        });
    }

    public String getUsername() {
        return username;
    }

    public void setWrongUsername(String s) {
        loginErrorLabel.setText(s);
        loginErrorLabel.setVisible(true);
    }

}
