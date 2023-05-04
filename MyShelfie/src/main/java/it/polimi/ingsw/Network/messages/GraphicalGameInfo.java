package it.polimi.ingsw.Network.messages;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.model.Card;

public final class GraphicalGameInfo extends Message {

    private final ItemEnum[][] board;

    private final String[] commonCards;

    public GraphicalGameInfo(ItemEnum[][] board,  String[] commonCards) {
        super("GraphicalGameInformation");
        this.board = board;
        this.commonCards = commonCards;

    }

    public ItemEnum[][] getBoard() {
        return this.board;
    }

    public String[] getCommonCards(){
        return this.commonCards;
    }
}
