package it.polimi.ingsw.view;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.view.GuiView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalSceneController implements Initializable {

    private GuiView guiView;
    public ItemEnum[][] board;
    public ItemEnum[][] shelf;
    public String[] common;
    public String personal;




    @FXML
    private ImageView personalCard;

    @FXML
    private ImageView commonGoalSX;
    @FXML
    private ImageView commonGoalDX;


    @FXML
    private ImageView b03;
    @FXML
    private ImageView b04;
    @FXML
    private ImageView b13;
    @FXML
    private ImageView b14;
    @FXML
    private ImageView b15;
    @FXML
    private ImageView b34;
    @FXML
    private ImageView b33;
    @FXML
    private ImageView b23;
    @FXML
    private ImageView b22;
    @FXML
    private ImageView b32;
    @FXML
    private ImageView b31;
    @FXML
    private ImageView b26;
    @FXML
    private ImageView b25;
    @FXML
    private ImageView b24;
    @FXML
    private ImageView b53;
    @FXML
    private ImageView b52;
    @FXML
    private ImageView b51;
    @FXML
    private ImageView b50;
    @FXML
    private ImageView b48;
    @FXML
    private ImageView b38;
    @FXML
    private ImageView b37;
    @FXML
    private ImageView b36;
    @FXML
    private ImageView b35;
    @FXML
    private ImageView b47;
    @FXML
    private ImageView b46;
    @FXML
    private ImageView b45;
    @FXML
    private ImageView b44;
    @FXML
    private ImageView b43;
    @FXML
    private ImageView b42;
    @FXML
    private ImageView b41;
    @FXML
    private ImageView b40;
    @FXML
    private ImageView b54;
    @FXML
    private ImageView b55;
    @FXML
    private ImageView b56;
    @FXML
    private ImageView b57;
    @FXML
    private ImageView b62;
    @FXML
    private ImageView b85;
    @FXML
    private ImageView b84;
    @FXML
    private ImageView b66;
    @FXML
    private ImageView b65;
    @FXML
    private ImageView b64;
    @FXML
    private ImageView b63;
    @FXML
    private ImageView b73;
    @FXML
    private ImageView b75;
    @FXML
    private ImageView b74;



    @FXML
    private ImageView s51;
    @FXML
    private ImageView s02;
    @FXML
    private ImageView s03;
    @FXML
    private ImageView s04;
    @FXML
    private ImageView s10;
    @FXML
    private ImageView s20;
    @FXML
    private ImageView s14;
    @FXML
    private ImageView s13;
    @FXML
    private ImageView s12;
    @FXML
    private ImageView s11;
    @FXML
    private ImageView s33;
    @FXML
    private ImageView s32;
    @FXML
    private ImageView s31;
    @FXML
    private ImageView s30;
    @FXML
    private ImageView s24;
    @FXML
    private ImageView s23;
    @FXML
    private ImageView s22;
    @FXML
    private ImageView s21;
    @FXML
    private ImageView s50;
    @FXML
    private ImageView s44;
    @FXML
    private ImageView s43;
    @FXML
    private ImageView s42;
    @FXML
    private ImageView s41;
    @FXML
    private ImageView s40;
    @FXML
    private ImageView s34;
    @FXML
    private ImageView s00;
    @FXML
    private ImageView s01;
    @FXML
    private ImageView s54;
    @FXML
    private ImageView s53;
    @FXML
    private ImageView s52;

    @FXML
    private Button buttonGO;
    @FXML
    private TextField textField;
    @FXML
    private Label labelText;


    private ImageView[][] boardIV;
    private ImageView[][] shelfIV;

    /**
     * This method is used to update the board
     */
    private void updateBoard(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(boardIV[i][j] != null){
                    if(board[i][j].equals(ItemEnum.BLANK))
                        boardIV[i][j].setOpacity(0);
                    else {
                        boardIV[i][j].setOpacity(1);
                        boardIV[i][j].setImage(new Image(board[i][j].getPath()));
                    }
                }
            }
        }
    }

    /**
     * This method is used to update the shelf
     */
    private void updateShelf(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                if(shelf[i][j].equals(ItemEnum.BLANK))
                    shelfIV[i][j].setOpacity(0);
                else {
                    shelfIV[i][j].setOpacity(1);
                    shelfIV[i][j].setImage(new Image(shelf[i][j].getPath()));
                }
            }
       }
    }


    /**
     * This method is used to update the view of the game
     */
    public void updateView(){
        updateBoard();
        updateShelf();
        if(common != null) {
            commonGoalDX.setImage(new Image("/graphics/commongoalcards/" + common[0] + ".jpg"));
            commonGoalSX.setImage(new Image("/graphics/commongoalcards/" + common[1] + ".jpg"));
        }

        if(personal != null){
            personalCard.setImage(new Image("/graphics/personalgoalcards/" + personal + ".png"));
        }
    }

    /**
     * This method is used to set the text of the label
     * @param text is the text to set
     */
    public void setLabelText(String text){
        Platform.runLater(() -> {
            labelText.setText(text);
            labelText.setOpacity(1);
        });
    }

    public void setCommon(String common1, String common2){
        commonGoalDX.setImage(new Image("/graphics/commongoalcards/" + common1 + ".jpg"));
        commonGoalSX.setImage(new Image("/graphics/commongoalcards/" + common2 + ".jpg"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boardIV = new ImageView[][]
                        { { null, null , null,  b03,  b04, null, null, null, null},
                        { null, null , null,  b13,  b14,  b15, null, null, null},
                        { null, null ,  b22,  b23,  b24,  b25,  b26, null, null},
                        { null,  b31 ,  b32,  b33,  b34,  b35,  b36,  b37,  b38},
                        {  b40,  b41 ,  b42,  b43,  b44,  b45,  b46,  b47,  b48},
                        {  b50,  b51 ,  b52,  b53,  b54,  b55,  b56,  b57, null},
                        { null, null ,  b62,  b63,  b64,  b65,  b66, null, null},
                        { null, null , null,  b73,  b74,  b75, null, null, null},
                        { null, null , null, null,  b84,  b85, null, null, null},};

        shelfIV = new ImageView[][]
                        {{s00,s01,s02,s03,s04},
                        {s10,s11,s12,s13,s14},
                        {s20,s21,s22,s23,s24},
                        {s30,s31,s32,s33,s34},
                        {s40,s41,s42,s43,s44},
                        {s50,s51,s52,s53,s54},};

        guiView = GuiView.getInstance();
        guiView.setController(this);


        buttonGO.setOnAction(actionEvent -> {
            guiView.move = textField.getText();
            labelText.setText("");
        });

    }
}
