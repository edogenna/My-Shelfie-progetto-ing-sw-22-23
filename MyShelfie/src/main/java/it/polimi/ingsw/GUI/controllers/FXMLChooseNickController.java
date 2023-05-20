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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void start(){
        String username = nickname.getText();
    }
}
