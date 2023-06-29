package it.polimi.ingsw;

/**
 * This class contains constants
 */
public final class Constants {
    /**
     * The port used for the socket connection
     */
    public static final int PORT_SOCKET_GAME = 50010;
    /**
     * The port used for the RMI connection
     */
    public static final int PORT_RMI_GAME = 50011;
    /**
     * The minimum number of players
     */
    public static final int MIN_PLAYERS = 2;
    /**
     * The maximum number of players
     */
    public static final int MAX_PLAYERS = 4;
    /**
     * The number of rows of the board
     */
    public static final int DEFAULT_BOARD = 1;
    /**
     * The milliseconds to wait for a check on client-server connection
     */
    public static final int MILLIS_TO_WAIT = 10;
    /**
     * The milliseconds in a second
     */
    public static final int MILLIS_IN_SECOND = 1000;
    /**
     * The number of seconds to wait for a turn
     */
    public static final int secondsDuringTurn = 60;
    /**
     * string to reconnect
     */
    public static final String RECONNECT = "Reconnect";
    /**
     * string to disconnect
     */
    public static final String DISCONNECT = "Disconnect";
    /**
     * strinf to signal a generic error
     */
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


    /**
     * This method converts an integer to a char
     * @param n the integer to convert
     * @return the char
     */
    static char toChar(int n){
        return (char) ((n%26) + 'a');
    }

    /**
     * This method converts an integer to a string
     * @param n the integer to convert
     * @return the string
     */
    static String toString(int n){
        return String.valueOf(Constants.toChar(n));
    }

}

