package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.ItemEnum;

/**
 * Contains the algorithms for the second common card:
 * Five tiles of the same type forming a diagonal.
 * @author Alessandro Fornara
 */
public class CommonCard02 implements CommonCardStrategy {
    public static final String constant2=
            "Card number 2   Description:\n"+
            "|=|             Five tiles of the same type forming\n"+
            "  |=|           a diagonal.\n"+
            "    |=|\n"+
            "      |=|\n"+
            "        |=|\n";

    public final int number = 2;
    @Override
    public String getPath(){
        return "/graphics/commongoalcards/2.png";
    }

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

    /**
     * @return the description of the card
     */
    @Override
    public String getCommonCardDesign() {
        return constant2;
    }

    /**
     * @return the number of the card
     */
    @Override
    public int getNumber() {
        return number;
    }
}