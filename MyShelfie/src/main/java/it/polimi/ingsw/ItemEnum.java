package it.polimi.ingsw;
import java.util.Random;

public enum ItemEnum {
    GREEN ("\u001B[38;5;040m"),
    WHITE ("\u001B[38;5;255m"),
    YELLOW ("\u001B[38;5;221m"),
    BLUE ("\u001B[38;5;018m"),
    AZURE ("\u001B[38;5;050m"),
    PURPLE ("\u001B[38;5;135m"),
    BLANK ("\u001b[38;5;235m");

    public static final String RESET = "\u001B[0m";

    public final String label;
    private ItemEnum(String label) { this.label = label; }

    public static ItemEnum generateRandomItemEnum(){
        Random rand = new Random();
        int n = rand.nextInt(6); //blank is excluded

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
