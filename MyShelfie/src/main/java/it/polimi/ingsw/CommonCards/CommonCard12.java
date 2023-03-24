package it.polimi.ingsw.CommonCards;

import it.polimi.ingsw.ItemEnum;

public class CommonCard12 implements CommonCardStrategy{
    public boolean checkBookshelf(ItemEnum[][] b) {
        final int numberOfRows=b.length;
        final int numberOfColumns=b[0].length;
        int i, j;

        if(!b[1][0].equals(ItemEnum.BLANK) || !b[1][numberOfColumns-1].equals(ItemEnum.BLANK)) {

            //controllo diagonale da [0][0] e [4][4]
            i = 0;
            j = 0;
            while (i < numberOfRows - 1 && j < numberOfColumns
                    && b[i][j].equals(ItemEnum.BLANK)
                    && !b[i + 1][j].equals(ItemEnum.BLANK)) {
                i++;
                j++;
                if (i == numberOfRows - 1 && j == numberOfColumns) return true;
            }

            //controllo diagonale da [4][0] e [0][4]
            i = numberOfRows - 2;
            j = 0;
            while (i >= 0 && j < numberOfColumns
                    && b[i][j].equals(ItemEnum.BLANK)
                    && !b[i + 1][j].equals(ItemEnum.BLANK)) {
                i--;
                j++;
                if (i == -1 && j == numberOfColumns) return true;
            }

            //controllo che la diagonale da [0][1] a [3][4] sia blank e la diagonale sotto di una cella diversa da blank
            i=0; j=1;
            while(i<numberOfRows-2 && j<numberOfColumns
                    && b[i][j].equals(ItemEnum.BLANK)
                    && !b[i+1][j].equals(ItemEnum.BLANK)) {
                i++;
                j++;
                if(i==numberOfRows-2 && j==numberOfColumns) return true;
            }

            //controllo che la diagonale da [3][0] a [0][3]
            i=3; j=0;
            while(i>=0 && j<numberOfColumns-1
                    && b[i][j].equals(ItemEnum.BLANK)
                    && !b[i+1][j].equals(ItemEnum.BLANK)) {
                i--;
                j++;
                if (i == -1  && j == numberOfColumns-1) return true;
            }
        }
        return false;
    }

}

