package it.polimi.ingsw.Network.messages;

/**
 * Message to inform the user of his UserId
 * @author Samuele Pietro Galli
 */
public final class UserIdMessage extends Message{
    private final int id;
    private final String s;

    public UserIdMessage(int id) {
        super("UserIdMessage");
        this.id = id;
        this.s = "This is your UserId: " + id +"\nIt will be required for reconnection if you get disconnected from the game.\n";
    }

    public int getId() {
        return id;
    }

    public String getS() {
        return s;
    }
}
