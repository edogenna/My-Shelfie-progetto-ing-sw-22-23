package it.polimi.ingsw.controller.messages;

public final class LobbyMessage extends Message{
    private final int actualNum, totalNum;
    private final String s;
    public LobbyMessage(int actualNum, int totalNum){
        super("Lobby");
        this.totalNum = totalNum;
        this.actualNum = actualNum;
        s = actualNum + " / " + totalNum + " Clients Connected...\nWaiting for more players...";
    }
    public String getS() {
        return s;
    }
}
