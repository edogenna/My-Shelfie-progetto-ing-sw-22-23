package it.polimi.ingsw;
import java.util.Random;

/**
 * This class represents a tile of the game used in board, bookshelves and personal cards
 * @author Edoardo Gennaretti
 */
public enum ItemEnum {
    GREEN ("\u001B[38;5;040m"),
    WHITE ("\u001B[38;5;255m"),
    YELLOW ("\u001B[38;5;221m"),
    BLUE ("\u001B[38;5;018m"),
    AZURE ("\u001B[38;5;050m"),
    PURPLE ("\u001B[38;5;135m"),
    BLANK ("\u001B[38;5;235m");

    //public static final char HEADER = '\u001B';
    public static final String RESET = "\u001B[00000000m";

    public static final int LEN_LABEL = 11;
    public static final int LEN_RESET = 11;

    public static final int NUM_ITEMENUM = 6; //blank is excluded

    public final String label;
    private ItemEnum(String label) { this.label = label; }

    @Deprecated
    public static ItemEnum generateRandomItemEnum(){
        Random rand = new Random();
        int n = rand.nextInt(NUM_ITEMENUM); //blank is excluded

        return switch (n) {
            case 0 -> ItemEnum.GREEN;
            case 1 -> ItemEnum.WHITE;
            case 2 -> ItemEnum.YELLOW;
            case 3 -> ItemEnum.BLUE;
            case 4 -> ItemEnum.AZURE;
            case 5 -> ItemEnum.PURPLE;
            default -> ItemEnum.BLANK; //covers also for case 6
        };
    }
    @Deprecated
    public static void printMatrix(ItemEnum[][] m, int r, int c){
        for(int i = 0; i < c; i++){
            System.out.print(" "+i+" ");
        }
        System.out.print("\n");

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                System.out.print(m[i][j].label + Constant.square + " " + ItemEnum.RESET);
            }
            System.out.print(" "+ Constant.toChar(i) + "\n");
        }
    }

    public static CharMatrix generateCharMatrix(ItemEnum mEnum[][], int r, int c) {
        CharMatrix cm = new CharMatrix();
        String s = "";

        for (int i = 0; i < r; i++) {
            s = "";
            for (int j = 0; j < c; j++) {
                s = s.concat(mEnum[i][j].label)
                        .concat(Constant.square)
                        .concat(ItemEnum.RESET)
                        .concat(" ");
            }

            cm.appendAtBottom(s);
        }

        return cm;
    }

}
