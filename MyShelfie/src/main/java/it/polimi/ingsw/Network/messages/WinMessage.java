package it.polimi.ingsw.Network.messages;

import java.util.Map;

/**
 * Message notify tells the players who won the game
 */
public final class WinMessage extends Message{
    private final String s;
    public WinMessage(String s, int max, Map<Integer, String> usernames, int[] points){
        super("Win");
        StringBuilder s1;
        s1 = new StringBuilder("The winner is " + s + " who has scored " + max + " points\n" +
                "The final scores are:\n");
        for(int i=0; i<usernames.size(); i++){
            s1.append(usernames.get(i)).append(": ").append(points[i]).append("\n");
        }
        this.s = s1.toString();
    }
    public String getS() {
        return s;
    }
}
