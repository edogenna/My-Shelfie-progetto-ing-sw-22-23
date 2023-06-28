package it.polimi.ingsw.GUI.controllers;

import it.polimi.ingsw.view.GuiView;
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
    private GuiView guiView;

    @FXML
    private Button reconnectionButton;
    @FXML
    private Button newGameButton;

    boolean done = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        guiView = GuiView.getInstance();

        reconnectionButton.setOnAction(e -> {
            if(!done) {
                done = true;
                guiView.setIsReconnection(1);
            }
        });
        newGameButton.setOnAction(e -> {
           if(!done) {
                done = true;
               guiView.setIsReconnection(0);
           }
        });
    }
}
