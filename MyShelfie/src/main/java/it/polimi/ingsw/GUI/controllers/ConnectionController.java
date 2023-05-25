package it.polimi.ingsw.GUI.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ConnectionController {
    @FXML
    private Pane basePane;

    @FXML
    private Label loginErrorLabel;

    @FXML
    private TextField hostnameTextField;

    @FXML
    private TextField typeTextField;

    @FXML
    private Label connectedLabel;

    @FXML
    private Button connectButton;

    private String takenHostname;
    //private Client client;
    private String type;


    /*public void setClient(Client client) {
        this.client = client;
    }*/
    public void initialize(){
        connectedLabel.setText("You are connected! ");
        connectedLabel.setVisible(false);
    }

    /*public void hostnameTextFieldTaken(){
        takenHostname = hostnameTextField.getText();
        client.hostName = this.takenHostname;
    }*/
    public void TypeTextFieldTaken(){
        type = typeTextField.getText();

    }

}
