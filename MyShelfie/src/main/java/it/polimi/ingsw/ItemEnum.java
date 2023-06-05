package it.polimi.ingsw;

/**
 * This class represents a tile of the game used in board, bookshelves and personal cards
 * @author Edoardo Gennaretti
 */
public enum ItemEnum {

    GREEN1 ("\u001B[38;5;040m"),
    GREEN2 ("\u001B[38;5;040m"),
    GREEN3 ("\u001B[38;5;040m"),
    WHITE1 ("\u001B[38;5;255m"),
    WHITE2 ("\u001B[38;5;255m"),
    WHITE3 ("\u001B[38;5;255m"),
    YELLOW1 ("\u001B[38;5;221m"),
    YELLOW2 ("\u001B[38;5;221m"),
    YELLOW3 ("\u001B[38;5;221m"),
    BLUE1 ("\u001B[38;5;018m"),
    BLUE2 ("\u001B[38;5;018m"),
    BLUE3 ("\u001B[38;5;018m"),
    AZURE1 ("\u001B[38;5;050m"),
    AZURE2 ("\u001B[38;5;050m"),
    AZURE3 ("\u001B[38;5;050m"),
    PURPLE1 ("\u001B[38;5;135m"),
    PURPLE2 ("\u001B[38;5;135m"),
    PURPLE3 ("\u001B[38;5;135m"),
    BLANK ("\u001B[38;5;235m");


    public static final String RESET = "\u001B[00000000m";

    public static final String colorGreen = "\u001B[38;5;040m";
    public static final String colorWhite = "\u001B[38;5;255m";
    public static final String colorYellow = "\u001B[38;5;221m";
    public static final String colorBlue = "\u001B[38;5;018m";
    public static final String colorAzure = "\u001B[38;5;050m";
    public static final String colorPurple = "\u001B[38;5;135m";


    public static final int NUM_ITEMENUM = 6; //blank is excluded
    public static final int NUM_TYPE_PER_ITEMENUM = 3;

    public final String label;
    private ItemEnum(String label) { this.label = label; }


    @Deprecated
    public static void printMatrix(ItemEnum[][] m, int r, int c){
        for(int i = 0; i < c; i++){
            System.out.print(" "+i+" ");
        }
        System.out.print("\n");

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                System.out.print(m[i][j].label + Constants.square + " " + ItemEnum.RESET);
            }
            System.out.print(" "+ Constants.toChar(i) + "\n");
        }
    }

    public static CharMatrix generateCharMatrix(ItemEnum mEnum[][], int r, int c) {
        CharMatrix cm = new CharMatrix();
        String s = "";

        for (int i = 0; i < r; i++) {
            s = "";
            for (int j = 0; j < c; j++) {
                s = s.concat(mEnum[i][j].label)
                        .concat(Constants.square)
                        .concat(ItemEnum.RESET)
                        .concat(" ");
            }

            cm.newLineAtBottom(s);
        }

        return cm;
    }

    public static boolean equals(ItemEnum tile1, ItemEnum tile2){
        return getStandardItemEnum(tile1) == getStandardItemEnum(tile2);
    }

    public boolean equals(ItemEnum tile1){
        return getStandardItemEnum(tile1) == getStandardItemEnum(this);
    }

    public static ItemEnum getStandardItemEnum(ItemEnum i){
        return switch (i) {
            case GREEN1, GREEN2, GREEN3 -> GREEN1;
            case WHITE1, WHITE2, WHITE3 -> WHITE1;
            case YELLOW1, YELLOW2, YELLOW3 -> YELLOW1;
            case BLUE1, BLUE2, BLUE3 -> BLUE1;
            case AZURE1, AZURE2, AZURE3 -> AZURE1;
            case PURPLE1, PURPLE2, PURPLE3 -> PURPLE1;
            default -> BLANK;
        };
    }

    public ItemEnum getStandardItemEnum(){
        return switch (this) {
            case GREEN1, GREEN2, GREEN3 -> GREEN1;
            case WHITE1, WHITE2, WHITE3 -> WHITE1;
            case YELLOW1, YELLOW2, YELLOW3 -> YELLOW1;
            case BLUE1, BLUE2, BLUE3 -> BLUE1;
            case AZURE1, AZURE2, AZURE3 -> AZURE1;
            case PURPLE1, PURPLE2, PURPLE3 -> PURPLE1;
            default -> BLANK;
        };
    }

}
