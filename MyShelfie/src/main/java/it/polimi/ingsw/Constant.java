package it.polimi.ingsw;

/**
 * This class contains constants
 */
public final class Constant {

    public static final int PORT_SOCKET_CHAT = 2255;
    public static final int PORT_RMI_CHAT = 2266;
    public static final int PORT_SOCKET_GAME = 2277;
    public static final int PORT_RMI_GAME = 2288;

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
        return String.valueOf(Constant.toChar(n));
    }

}

