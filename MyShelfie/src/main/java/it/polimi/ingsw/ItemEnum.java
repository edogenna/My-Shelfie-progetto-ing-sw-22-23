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

    public static final int NUM_ITEMENUM = 6; //blank is excluded
    public static final int NUM_TYPE_PER_ITEMENUM = 3;

    public final String label;
    private ItemEnum(String label) { this.label = label; }


    /**
     * @param m the tile
     * @param r the row of the tile
     * @param c the column of the tile
     */
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

    /**
     * Generates a CharMatrix from a matrix of ItemEnum
     * @param mEnum matrix
     * @param r rows
     * @param c column
     * @return CharMatrix object
     */
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

    public boolean equals(ItemEnum tile1){
        return getStandardItemEnum(tile1) == getStandardItemEnum(this);
    }

    /**
     * @param i Color
     * @return standard color (example: GREEN2 -> returns GREEN1)
     */
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

    /**
     * @return standard color (example: GREEN2 -> returns GREEN1)
     */
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

    public String getPath(){
        String s = "/graphics/itemtiles/";
        switch (this) {
            case GREEN1 -> s = s.concat("Gatti1.1.png");
            case GREEN2 -> s = s.concat("Gatti1.2.png");
            case GREEN3 -> s = s.concat("Gatti1.3.png");
            case WHITE1 -> s = s.concat("Libri1.1.png");
            case WHITE2 -> s = s.concat("Libri1.2.png");
            case WHITE3 -> s = s.concat("Libri1.3.png");
            case YELLOW1 -> s = s.concat("Giochi1.1.png");
            case YELLOW2 -> s = s.concat("Giochi1.2.png");
            case YELLOW3 -> s = s.concat("Giochi1.3.png");
            case BLUE1 -> s = s.concat("Cornici1.1.png");
            case BLUE2 -> s = s.concat("Cornici1.2.png");
            case BLUE3 -> s = s.concat("Cornici1.3.png");
            case AZURE1 -> s = s.concat("Trofei1.1.png");
            case AZURE2 -> s = s.concat("Trofei1.2.png");
            case AZURE3 -> s = s.concat("Trofei1.3.png");
            case PURPLE1 -> s = s.concat("Piante1.1.png");
            case PURPLE2 -> s = s.concat("Piante1.2.png");
            case PURPLE3 -> s = s.concat("Piante1.3.png");
            default -> s = s.concat("NULL");
        };

        return s;
    }

}
