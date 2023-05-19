package it.polimi.ingsw.Network.messages;

public final class UserIdMessage extends Message{
    public UserIdMessage(int id) {
        super("UserIdMessage");
        this.id = id;
    }
    private int id;
    private final String s = "This is your UserId." + id +"It will be required for reconnection if you get disconnected from the game.";

    public int getId() {
        return id;
    }

    public String getS() {
        return s;
    }
}
