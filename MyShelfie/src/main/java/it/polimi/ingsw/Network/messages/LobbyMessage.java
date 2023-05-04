package it.polimi.ingsw.Network.messages;

/**
 * Message to send generic information about the connected clients to the user
 * @author Alessandro Fornara
 */
public final class LobbyMessage extends Message{
    private final int actualNum, totalNum;
    private final String s;
    public LobbyMessage(int actualNum, int totalNum){
        super("Lobby");
        this.totalNum = totalNum;
        this.actualNum = actualNum;
        this.s = actualNum + " / " + totalNum + " Clients Connected...";
    }
    public String getS() {
        return s;
    }
}
