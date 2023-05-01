package it.polimi.ingsw.Network.messages;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Card;

public final class GameInformation extends Message {

    private final ItemEnum[][] board;
    private final ItemEnum[][] shelf;
    private final Card personalCard;
    private final String[] commonCards;
    private String activePlayerUsername;

    private final String s = "it's your turn." +
                             "Please insert which tiles you would like to remove from the board and the column of your bookshelf you want to put your tiles in\n" +
                             "the first one will go to the first position available on the bottom of the column and the others will pile up\n" +
                             "Example: x1,y1,x2,y2,x3,y3,column\n" +
                             "Example: a,3,a,4,a,5,column";

    public GameInformation(ItemEnum[][] board, ItemEnum[][] shelf, Card personalCard, String[] commonCards, String usrn) {
        super("GameInformation");
        this.board = board;
        this.shelf = shelf;
        this.personalCard = personalCard;
        this.activePlayerUsername = usrn;
        this.commonCards = commonCards;
    }

    public ItemEnum[][] getBoard() {
        return this.board;
    }

    public ItemEnum[][] getShelf() {
        return this.shelf;
    }

    public Card getPersonalCard(){
        return this.personalCard;
    }

    public String[] getCommonCards(){
        return this.commonCards;
    }

    public String getActivePlayerUsername() {
        return activePlayerUsername;
    }

    public String getS() {
        return s;
    }
}
