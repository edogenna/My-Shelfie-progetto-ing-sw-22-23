package it.polimi.ingsw.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseReconnectionScene implements Initializable {
    @FXML
    private Pane basePane;

    @FXML
    private Button reconnectionButton;
    @FXML
    private Button newGameButton;

    //0 if new game, 1 if reconnection
    private int isReconnection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isReconnection = -1;
        reconnectionButton.setOnAction(e -> {
            if(isReconnection == -1)
                isReconnection = 1;
        });
        newGameButton.setOnAction(e -> {
            if(isReconnection == -1)
                isReconnection = 0;
        });
    }

    public int getIsReconnection() {
        return isReconnection;
    }
}
