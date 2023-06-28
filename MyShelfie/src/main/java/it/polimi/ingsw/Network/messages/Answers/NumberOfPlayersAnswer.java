package it.polimi.ingsw.Network.messages.Answers;

import it.polimi.ingsw.Network.messages.Message;

/**
 * Message to send the number of players for the game chosen by the user to the server
 * @author Alessandro Fornara
 */
public final class NumberOfPlayersAnswer extends Message {
    private final int num;

    /**
     * Constructor of the class
     * @param num The number of players
     */
    public NumberOfPlayersAnswer(int num){
        super("NumberOfPlayers");
        this.num = num;
    }
    public int getNum() {
        return num;
    }
}
