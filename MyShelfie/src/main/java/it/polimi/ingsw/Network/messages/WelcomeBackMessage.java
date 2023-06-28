package it.polimi.ingsw.Network.messages;

/**
 * Message to welcome back a user
 */
public final class WelcomeBackMessage extends Message{

    private final String string;
    public WelcomeBackMessage(String username){
        super("WelcomeBack");
        this.string = "Welcome back " + username;
    }

    public String getString() {
        return string;
    }
}
