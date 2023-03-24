package it.polimi.ingsw.CommonCards;

import it.polimi.ingsw.ItemEnum;

public class CommonCard2 implements CommonCardStrategy {
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
}