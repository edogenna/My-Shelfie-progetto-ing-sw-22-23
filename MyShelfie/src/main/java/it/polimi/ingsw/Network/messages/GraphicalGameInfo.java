package it.polimi.ingsw.Network.messages;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Card;

public final class GraphicalGameInfo extends Message {

    private final ItemEnum[][] board;

    private final String[] commonCards;
    private final ItemEnum[][] shelf;
    private final Card personalCard;
    private String activePlayerUsername;
    private final String s;

    public GraphicalGameInfo(ItemEnum[][] board,  String[] commonCards, ItemEnum[][] shelf, Card personalCard, String activePlayerUsername) {
        super("GraphicalGameInfo");
        this.board = board;
        this.commonCards = commonCards;
        this.shelf = shelf;
        this.personalCard = personalCard;
        this.activePlayerUsername = activePlayerUsername;
        this.s = "it's " + this.activePlayerUsername + "'s turn";
    }

    public ItemEnum[][] getBoard() {
        return this.board;
    }

    public String[] getCommonCards(){
        return this.commonCards;
    }
    public ItemEnum[][] getShelf() {
        return this.shelf;
    }

    public Card getPersonalCard(){
        return this.personalCard;
    }

    public String getS() {
        return s;
    }
}
