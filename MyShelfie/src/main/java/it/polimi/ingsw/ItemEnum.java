package it.polimi.ingsw;

public enum ItemEnum {
    GREEN ("\u001B[32m"),
    WHITE ("\u001b[37m"),
    YELLOW ("\u001B[33m"),
    BLUE ("\u001B[34m"),
    AZURE ("\u001b[36m"),
    PURPLE ("\u001B[35m"),
    BLANK ("\u001b[30m");

    public static final String RESET = "\u001B[0m";

    private String c;
    ItemEnum(String c) {
        this.c = c;
    }


}
