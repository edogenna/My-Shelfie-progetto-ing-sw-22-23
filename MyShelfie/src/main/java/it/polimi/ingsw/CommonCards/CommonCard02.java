package it.polimi.ingsw.CommonCards;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;

/**
 * Contains the algorithms for the second common card:
 * Five tiles of the same type forming a diagonal.
 * @author Alessandro Fornara
 */
public class CommonCard02 implements CommonCardStrategy {
    private final String constant2=
            "Card number 2   Description:\n"+
            "|=|             Five tiles of the same type forming\n"+
            "  |=|           a diagonal.\n"+
            "    |=|\n"+
            "      |=|\n"+
            "        |=|\n";
    @Override
    public boolean checkBookshelf(ItemEnum[][] b) {
        ItemEnum color;

        //controllo diagonale da [5][4] a [1][0]
        int i=b.length-1, j=b[0].length-1;
        if (!b[i][j].equals(ItemEnum.BLANK)) {
            color = b[i][j];
            do{
                i--;
                j--;
                if (i == 0 && j == -1) return true;
            }
            while (b[i][j].equals(color));

        }
        //controllo diagonale da [4][4] a [0][0]
        i=b.length-2;
        j=b[0].length-1;
        if (!b[i][j].equals(ItemEnum.BLANK)) {
            color = b[i][j];
            do{
                i--;
                j--;
                if (i == -1 && j == -1) return true;
            }
            while (b[i][j].equals(color));

        }

        //controllo diagonale da [5][0] a [1][4]
        i=b.length-1;
        j=0;
        if (!b[i][j].equals(ItemEnum.BLANK)) {
            color = b[i][j];
            do{
                i--;
                j++;
                if (i == 0 && j == 5) return true;
            }
            while (b[i][j].equals(color));

        }

        //controllo diagonale da [4][0] a [0][4]
        i=b.length-2;
        j=0;
        if (!b[i][j].equals(ItemEnum.BLANK)) {
            color = b[i][j];
            do{
                i--;
                j++;
                if (i == -1 && j == 5) return true;
            }
            while (b[i][j].equals(color));

        }
        return false;
    }
    @Override
    public CharMatrix printCommonCardMatrix() {

        return new CharMatrix()
                .appendAtBottom("Card number 2   Description:")
                .appendAtBottom("|=|             Five tiles of the same type forming")
                .appendAtBottom("  |=|           a diagonal.")
                .appendAtBottom("    |=|")
                .appendAtBottom("      |=|")
                .appendAtBottom("        |=|");
    }
    @Override
    public void printCommonCard() {
        System.out.println(constant2);
    }
}