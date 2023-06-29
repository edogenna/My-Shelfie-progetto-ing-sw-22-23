package it.polimi.ingsw.view;

import it.polimi.ingsw.ItemEnum;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * This class is the controller of the principal scene of the GUI.
 * @author Edoardo Gennaretti
 */
public class PrincipalSceneController implements Initializable {
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
    private ImageView sa51;
    @FXML
    private ImageView sa02;
    @FXML
    private ImageView sa03;
    @FXML
    private ImageView sa04;
    @FXML
    private ImageView sa10;
    @FXML
    private ImageView sa20;
    @FXML
    private ImageView sa14;
    @FXML
    private ImageView sa13;
    @FXML
    private ImageView sa12;
    @FXML
    private ImageView sa11;
    @FXML
    private ImageView sa33;
    @FXML
    private ImageView sa32;
    @FXML
    private ImageView sa31;
    @FXML
    private ImageView sa30;
    @FXML
    private ImageView sa24;
    @FXML
    private ImageView sa23;
    @FXML
    private ImageView sa22;
    @FXML
    private ImageView sa21;
    @FXML
    private ImageView sa50;
    @FXML
    private ImageView sa44;
    @FXML
    private ImageView sa43;
    @FXML
    private ImageView sa42;
    @FXML
    private ImageView sa41;
    @FXML
    private ImageView sa40;
    @FXML
    private ImageView sa34;
    @FXML
    private ImageView sa00;
    @FXML
    private ImageView sa01;
    @FXML
    private ImageView sa54;
    @FXML
    private ImageView sa53;
    @FXML
    private ImageView sa52;
    
    
    @FXML
    private ImageView sb51;
    @FXML
    private ImageView sb02;
    @FXML
    private ImageView sb03;
    @FXML
    private ImageView sb04;
    @FXML
    private ImageView sb10;
    @FXML
    private ImageView sb20;
    @FXML
    private ImageView sb14;
    @FXML
    private ImageView sb13;
    @FXML
    private ImageView sb12;
    @FXML
    private ImageView sb11;
    @FXML
    private ImageView sb33;
    @FXML
    private ImageView sb32;
    @FXML
    private ImageView sb31;
    @FXML
    private ImageView sb30;
    @FXML
    private ImageView sb24;
    @FXML
    private ImageView sb23;
    @FXML
    private ImageView sb22;
    @FXML
    private ImageView sb21;
    @FXML
    private ImageView sb50;
    @FXML
    private ImageView sb44;
    @FXML
    private ImageView sb43;
    @FXML
    private ImageView sb42;
    @FXML
    private ImageView sb41;
    @FXML
    private ImageView sb40;
    @FXML
    private ImageView sb34;
    @FXML
    private ImageView sb00;
    @FXML
    private ImageView sb01;
    @FXML
    private ImageView sb54;
    @FXML
    private ImageView sb53;
    @FXML
    private ImageView sb52;
    
    
    @FXML
    private ImageView sc51;
    @FXML
    private ImageView sc02;
    @FXML
    private ImageView sc03;
    @FXML
    private ImageView sc04;
    @FXML
    private ImageView sc10;
    @FXML
    private ImageView sc20;
    @FXML
    private ImageView sc14;
    @FXML
    private ImageView sc13;
    @FXML
    private ImageView sc12;
    @FXML
    private ImageView sc11;
    @FXML
    private ImageView sc33;
    @FXML
    private ImageView sc32;
    @FXML
    private ImageView sc31;
    @FXML
    private ImageView sc30;
    @FXML
    private ImageView sc24;
    @FXML
    private ImageView sc23;
    @FXML
    private ImageView sc22;
    @FXML
    private ImageView sc21;
    @FXML
    private ImageView sc50;
    @FXML
    private ImageView sc44;
    @FXML
    private ImageView sc43;
    @FXML
    private ImageView sc42;
    @FXML
    private ImageView sc41;
    @FXML
    private ImageView sc40;
    @FXML
    private ImageView sc34;
    @FXML
    private ImageView sc00;
    @FXML
    private ImageView sc01;
    @FXML
    private ImageView sc54;
    @FXML
    private ImageView sc53;
    @FXML
    private ImageView sc52;


    
    @FXML
    private ImageView personalCard;
    @FXML
    private ImageView commonGoalSX;
    @FXML
    private ImageView commonGoalDX;
    @FXML
    private Button buttonGO;
    @FXML
    private TextField textField;
    @FXML
    private Label labelText;

    @FXML
    private Label labelA;
    @FXML
    private Label labelB;
    @FXML
    private Label labelC;
    
    @FXML
    private ImageView imgA;
    @FXML
    private ImageView imgB;
    @FXML
    private ImageView imgC;



    private GuiView guiView;
    public ItemEnum[][] board;
    public ItemEnum[][] shelf;
    public ItemEnum[][] shelfA;
    public ItemEnum[][] shelfB;
    public ItemEnum[][] shelfC;
    public String usernameA;
    public String usernameB;
    public String usernameC;
    public String[] common;
    public String personal;



    private ImageView[][] boardIV;
    private ImageView[][] shelfIV;
    private ImageView[][] shelfIVA;
    private ImageView[][] shelfIVB;
    private ImageView[][] shelfIVC;

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
     * This method is used to update the shelf A
     */
    private void updateShelfA(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                if(shelfA == null || shelfA[i][j].equals(ItemEnum.BLANK))
                    shelfIVA[i][j].setOpacity(0);
                else {
                    shelfIVA[i][j].setOpacity(1);
                    shelfIVA[i][j].setImage(new Image(shelfA[i][j].getPath()));
                }
            }
        }
    }

    /**
     * This method is used to update the shelf B
     */
    private void updateShelfB(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                if(shelfB == null || shelfB[i][j].equals(ItemEnum.BLANK))
                    shelfIVB[i][j].setOpacity(0);
                else {
                    shelfIVB[i][j].setOpacity(1);
                    shelfIVB[i][j].setImage(new Image(shelfB[i][j].getPath()));
                }
            }
        }
    }

    /**
     * This method is used to update the shelf C
     */
    private void updateShelfC(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                if(shelfC == null|| shelfC[i][j].equals(ItemEnum.BLANK))
                    shelfIVC[i][j].setOpacity(0);
                else {
                    shelfIVC[i][j].setOpacity(1);
                    shelfIVC[i][j].setImage(new Image(shelfC[i][j].getPath()));
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
        
        if(shelfA != null){
            imgA.setOpacity(1);
        }else{
            imgA.setOpacity(0);
        }
        updateShelfA();
        
        if(shelfB != null){
            imgB.setOpacity(1);
        }else{
            imgB.setOpacity(0);
        }
        updateShelfB();
        
        if(shelfC != null){
            imgC.setOpacity(1);
        }else{
            imgC.setOpacity(0);
        }
        updateShelfC();
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

    public void setLabelA(String text){
        Platform.runLater(() -> {
            labelA.setText(text);
            labelA.setOpacity(1);
        });
    }

    public void setLabelB(String text){
        Platform.runLater(() -> {
            labelB.setText(text);
            labelB.setOpacity(1);
        });
    }

    public void setLabelC(String text){
        Platform.runLater(() -> {
            labelC.setText(text);
            labelC.setOpacity(1);
        });
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

        shelfIVA = new ImageView[][]
                {{sa00,sa01,sa02,sa03,sa04},
                        {sa10,sa11,sa12,sa13,sa14},
                        {sa20,sa21,sa22,sa23,sa24},
                        {sa30,sa31,sa32,sa33,sa34},
                        {sa40,sa41,sa42,sa43,sa44},
                        {sa50,sa51,sa52,sa53,sa54},};

        shelfIVB = new ImageView[][]
                {{sb00,sb01,sb02,sb03,sb04},
                        {sb10,sb11,sb12,sb13,sb14},
                        {sb20,sb21,sb22,sb23,sb24},
                        {sb30,sb31,sb32,sb33,sb34},
                        {sb40,sb41,sb42,sb43,sb44},
                        {sb50,sb51,sb52,sb53,sb54},};

        shelfIVC = new ImageView[][]
                {{sc00,sc01,sc02,sc03,sc04},
                        {sc10,sc11,sc12,sc13,sc14},
                        {sc20,sc21,sc22,sc23,sc24},
                        {sc30,sc31,sc32,sc33,sc34},
                        {sc40,sc41,sc42,sc43,sc44},
                        {sc50,sc51,sc52,sc53,sc54},};

        guiView = GuiView.getInstance();
        guiView.setController(this);


        buttonGO.setOnAction(actionEvent -> {
            guiView.move = textField.getText();
            labelText.setText("");
        });
    }
}
