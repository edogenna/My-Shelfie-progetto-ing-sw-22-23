package it.polimi.ingsw;

public final class Costant {

    static final String square = "██";
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

}

