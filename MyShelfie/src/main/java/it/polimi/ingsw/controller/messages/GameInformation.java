package it.polimi.ingsw.controller.messages;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Card;

public final class GameInformation extends Message {

    private final ItemEnum[][] board;
    private final ItemEnum[][] shelf;
    private final Card personalCard;
    //private final String[] commonCards;

    public GameInformation(ItemEnum[][] board, ItemEnum[][] shelf, Card personalCard/*, String[] commonCards*/) {
        super("GameInformation");
        this.board = board;
        this.shelf = shelf;
        this.personalCard = personalCard;
        //this.commonCards = commonCards;
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

    /*public String[] getCommonCards(){
        return this.commonCards;
    }*/
}
