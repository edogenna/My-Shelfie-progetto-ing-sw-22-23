package it.polimi.ingsw.GUI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;


import java.util.ArrayList;
import java.util.List;

public class FXMLLobbyController {

    private List<String> connectedPlayers;

    private List<Label> labelList;

    private List<ImageView> imageViewList;

    @FXML
    private ImageView secondImage;

    @FXML
    private ImageView thirdImage;

    @FXML
    private Label firstNicknameLabel;

    @FXML
    private Label secondNicknameLabel;

    @FXML
    private Label thirdNicknameLabel;

     @FXML
    private Label fourthNicknameLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Button closeButton;



    public void initialize() {

        connectedPlayers = new ArrayList<>();
        labelList = new ArrayList<>();
        imageViewList = new ArrayList<>();

        closeButton.setDisable(true);
        closeButton.setVisible(false);

        closeButton.setFont(Font.loadFont(getClass().getResource("/Font/Teko-Bold.ttf").toExternalForm(),
                15
        ));

        titleLabel.setFont(Font.loadFont(getClass().getResource("/Font/Teko-Bold.ttf").toExternalForm(),
                15
        ));


    }


    /**
     * initializes labels and set not visible because it changes a run time when players connect in lobby
     */
    private void initializeLabels() {

        labelList.add(firstNicknameLabel);
        labelList.add(secondNicknameLabel);
        labelList.add(thirdNicknameLabel);
        labelList.add(fourthNicknameLabel);

        for (Label label : labelList) {

            label.setText("");
            label.setVisible(false);

            label.setFont( Font.loadFont(getClass().getResource("/Font/Teko-Bold.ttf").toExternalForm(),
                    15
            ));


        }

    }


    public void initController(List<String> connectedPlayers) {
        fillNicknames(connectedPlayers);
    }


    /**
     * when lobby is closed, the scene modifies to show closing lobby and to show the players connected
     * @param playerNickname the final nicknames in game
     */
    public void close(List<String> playerNickname) {

        titleLabel.setText("The Lobby is Closed!");

        fillNicknames(playerNickname);
    }

    /**
     * this method fill the lobby with the nicknames in lobby
     * @param connectedPlayers the list of nickname to fill labels in lobby FXML
     */
    public void fillNicknames(List<String> connectedPlayers) {

        this.connectedPlayers = connectedPlayers;

        firstNicknameLabel.setText(connectedPlayers.get(0));
        firstNicknameLabel.setVisible(true);

        for(int i = 1; i < connectedPlayers.size(); i++ ) {
            labelList.get(i).setText(connectedPlayers.get(i));
            labelList.get(i).setVisible(true);
            imageViewList.get(i-1).setVisible(true);
        }



    }



}