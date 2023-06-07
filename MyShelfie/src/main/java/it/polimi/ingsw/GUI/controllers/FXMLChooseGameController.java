package it.polimi.ingsw.GUI.controllers;

import it.polimi.ingsw.view.GuiView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class FXMLChooseGameController  implements Initializable {
    @FXML
    private Pane basePane;
    @FXML
    private Button reconnectButton;
    @FXML
    private Button joinButton;
    @FXML
    private Button newGameButton;
    private String username;
    private String reconnect;
    private String idGame;
    private GuiView guiView;
    private Dialog dialog;


    public FXMLChooseGameController(GuiView guiView) {
        this.guiView = guiView;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reconnectButton.setOnAction(event -> {
            reconnect = "Reconnect";

            dialog = new TextInputDialog("Reconnect");
            dialog.setTitle("Enter old ID game");
            dialog.setHeaderText("Enter ID game");

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                idGame = result.get();
            }
        });

        joinButton.setOnAction(event -> {
            reconnect = "join";

            dialog = new TextInputDialog("Username");
            dialog.setTitle("Enter username");
            dialog.setHeaderText("Enter your username");

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                username = result.get();
            }
        });

        newGameButton.setOnAction(event -> {
            //non ancora implementato
        });
    }


    public String getReconnect() {
        return reconnect;
    }

    public String getUsername() {
        return username;
    }

    public String getIdGame() {
        return idGame;
    }

    public void wrongUsername(String error){
        username = null;

        dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle("Error");
        dialog.setHeaderText("Wrong username");
        dialog.setContentText(error);
        dialog.showAndWait();

        dialog = new TextInputDialog("Username");
        dialog.setTitle("Enter username");
        dialog.setHeaderText("Enter your username");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            username = result.get();
        }
    }




}
