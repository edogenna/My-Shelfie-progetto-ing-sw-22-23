package it.polimi.ingsw.Network.messages;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Card;

/**
 * Message that contains the information needed to update the graphical interface of the client.
 * it contains the board, the common cards, the shelves, the personal card and the active player.
 * it is sent to all the players.
 * It is sent by the server to the client.
 */
public final class GraphicalGameInfo extends Message {

    private final ItemEnum[][] board;

    private final String[] commonCards;
    private final ItemEnum[][][] shelves;
    private String[] usernames;
    private final Card personalCard;
    private String activePlayerUsername;
    private final String s;
    private final int yourId;

    public GraphicalGameInfo(ItemEnum[][] board,  String[] commonCards, String[] usernames, ItemEnum[][][] shelves, Card personalCard, String activePlayerUsername, int yourId) {
        super("GraphicalGameInfo");
        this.board = board;
        this.commonCards = commonCards;
        this.usernames = usernames;
        this.shelves = shelves;
        this.personalCard = personalCard;
        this.activePlayerUsername = activePlayerUsername;
        this.yourId = yourId;
        this.s = "it's " + this.activePlayerUsername + "'s turn";
    }

    public ItemEnum[][] getBoard() {
        return this.board;
    }

    public String[] getCommonCards(){
        return this.commonCards;
    }
    public ItemEnum[][][] getShelves() {
        return this.shelves;
    }

    public String[] getUsernames(){
        return this.usernames;
    }

    public Card getPersonalCard(){
        return this.personalCard;
    }

    public String getS() {
        return s;
    }

    public int getPersonalCardId(){
        return personalCard.getId();
    }

    public int getYourId() {
        return yourId;
    }
}
