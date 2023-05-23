package it.polimi.ingsw;

/**
 * This class contains constants
 */
public final class Constant {
    public static final int PORT_SOCKET_GAME = 50010;
    public static final int PORT_RMI_GAME = 50011;

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

