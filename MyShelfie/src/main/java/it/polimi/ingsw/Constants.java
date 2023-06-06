package it.polimi.ingsw;

/**
 * This class contains constants
 */
public final class Constants {
    public static final int PORT_SOCKET_GAME = 50010;
    public static final int PORT_RMI_GAME = 50011;
    public static final int MIN_PLAYERS = 2;
    public static final int MAX_PLAYERS = 4;
    public static final int DEFAULT_BOARD = 1;
    public static final int MILLIS_TO_WAIT = 10;
    public static final int MILLIS_IN_SECOND = 1000;
    public static final int secondsDuringTurn = 60;
    public static final String RECONNECT = "Reconnect";
    public static final String DISCONNECT = "Disconnect";
    public static final String GENERIC_ERROR = "Error";
    static final String square = "██";
    static final char squareChar = '█';
    static final int LEN_SQUARE = 2;
    static final String MY_SHELFIE_TITLE =
        "███╗   ███╗██╗   ██╗         ██████╗██╗  ██╗███████╗██╗     ███████╗██╗███████╗\n" +
        "████╗ ████║╚██╗ ██╔╝        ██╔════╝██║  ██║██╔════╝██║     ██╔════╝██║██╔════╝\n" +
        "██╔████╔██║ ╚████╔╝         ╚█████╗ ███████║█████╗  ██║     █████╗  ██║█████╗  \n" +
        "██║╚██╔╝██║  ╚██╔╝           ╚═══██╗██╔══██║██╔══╝  ██║     ██╔══╝  ██║██╔══╝  \n" +
        "██║ ╚═╝ ██║   ██║           ██████╔╝██║  ██║███████╗███████╗██║     ██║███████╗\n" +
        "╚═╝     ╚═╝   ╚═╝           ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝\n";


    static char toChar(int n){
        return (char) ((n%26) + 'a');
    }
    static String toString(int n){
        return String.valueOf(Constants.toChar(n));
    }

}

