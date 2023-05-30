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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class FXMLFirstPlayerController  implements Initializable {
    @FXML
    private Pane basePane;

    @FXML
    private Label numberErroreLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField numberOfPlayersTextField;

    @FXML
    private Button goButton;
    private int numberOfPlayers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageLabel.setVisible(false);
        numberErroreLabel.setVisible(false);
        goButton.setOnAction(event -> {
            numberOfPlayers = numberOfPlayersTextField.getText().charAt(0) - '0';
        });
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setMessageLabel(String s) {
        messageLabel.setText(s);
        messageLabel.setVisible(true);
    }

    public void setNumberErrorLabel(String s) {
        numberErroreLabel.setText(s);
        numberErroreLabel.setVisible(true);
    }
}
