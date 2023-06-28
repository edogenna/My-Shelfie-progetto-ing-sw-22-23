package it.polimi.ingsw.Network.messages;

/**
 * Message notify tells the players who won the game
 */
public final class WinMessage extends Message{
    private final String s;
    public WinMessage(String s, int points){
        super("Win");
        this.s = "The winner is " + s + " who has scored " + points + " points";
    }
    public String getS() {
        return s;
    }
}
