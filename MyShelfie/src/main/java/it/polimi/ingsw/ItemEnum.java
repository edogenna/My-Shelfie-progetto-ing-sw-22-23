package it.polimi.ingsw;
import java.util.Random;

public enum ItemEnum {
    GREEN ("\u001B[32m"),
    WHITE ("\u001B[38;5;15m"),
    YELLOW ("\u001B[33m"),
    BLUE ("\u001B[38;5;18m"),
    AZURE ("\u001b[36m"),
    PURPLE ("\u001B[35m"),
    BLANK ("\u001b[38;5;235m");

    public static final String RESET = "\u001B[0m";

    public final String label;
    private ItemEnum(String label) { this.label = label; }

    public static ItemEnum generateRandomItemEnum(){
        Random rand = new Random();
        int n = rand.nextInt(7);

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

}
