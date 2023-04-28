package it.polimi.ingsw.Network.messages;

public final class LobbyMessage extends Message{
    private final int actualNum, totalNum;
    private final String s;
    public LobbyMessage(int actualNum, int totalNum){
        super("Lobby");
        this.totalNum = totalNum;
        this.actualNum = actualNum;
        s = actualNum + " / " + totalNum + " Clients Connected...";
    }
    public String getS() {
        return s;
    }
}
