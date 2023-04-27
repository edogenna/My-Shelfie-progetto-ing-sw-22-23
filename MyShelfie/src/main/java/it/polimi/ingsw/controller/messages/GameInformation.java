package it.polimi.ingsw.controller.messages;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.CommonCards.CommonCardStrategy;

public final class GameInformation extends Message {

    private final ItemEnum[][] board;
    public ItemEnum[][] shelf;
    public Card personalCard;
    public CommonCardStrategy[] commonCards;

    public GameInformation(ItemEnum[][] board, ItemEnum[][] shelf, Card personalCard, CommonCardStrategy[] commonCard) {
        super("GameInformation");
        this.board = board;
        this.shelf = shelf;
        this.personalCard = personalCard;
        this.commonCards = commonCard;
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

    public CommonCardStrategy[] getCommonCards(){
        return this.commonCards;
    }
}
