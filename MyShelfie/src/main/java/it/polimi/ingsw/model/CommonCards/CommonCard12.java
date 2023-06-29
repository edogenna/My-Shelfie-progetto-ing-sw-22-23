package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.ItemEnum;
/**
 * Contains the algorithms for the twelfth common card:
 * Five columns of increasing or decreasing height. Starting from the first column on the left or on the right, each next column must be made of exactly one more tile. Tiles can be of any type.
 * @author Alessandro Fornara
 */
public class CommonCard12 implements CommonCardStrategy{
    public static final String constant12=
            "Card number 12  Description:\n"+
            "|•|             Five columns of increasing o decreasing height.\n"+
            "|•|•|           Starting from the first column on the left or \n"+
            "|•|•|•|         on the right, each next column must be made of\n"+
            "|•|•|•|•|       exactly one more tile. Tiles can be of any type.\n"+
            "|•|•|•|•|•|\n";

    public final int number = 12;

    @Override
    public boolean checkBookshelf(ItemEnum[][] b) {
        final int numberOfRows=b.length;
        final int numberOfColumns=b[0].length;
        int i, j;

        if(!b[1][0].equals(ItemEnum.BLANK) || !b[1][numberOfColumns-1].equals(ItemEnum.BLANK)) {

            i = 0;
            j = 0;
            while (i < numberOfRows - 1 && j < numberOfColumns
                    && !b[i + 1][j].equals(ItemEnum.BLANK)) {
                i++;
                j++;
                if (i == numberOfRows - 1 && j == numberOfColumns) return true;
            }

            i = numberOfRows - 2;
            j = 0;
            while (i >= 0 && j < numberOfColumns
                    && !b[i + 1][j].equals(ItemEnum.BLANK)) {
                i--;
                j++;
                if (i == -1 && j == numberOfColumns) return true;
            }

            i=0; j=1;
            while(i<numberOfRows-2 && j<numberOfColumns
                    && !b[i+1][j].equals(ItemEnum.BLANK)) {
                i++;
                j++;
                if(i==numberOfRows-2 && j==numberOfColumns) return true;
            }

            i=3; j=0;
            while(i>=0 && j<numberOfColumns-1
                    && !b[i+1][j].equals(ItemEnum.BLANK)) {
                i--;
                j++;
                if (i == -1  && j == numberOfColumns-1) return true;
            }
        }
        return false;
    }

    /**
     * Returns the string containing the design of the card.
     * @return the string containing the design of the card.
     */
    @Override
    public String getCommonCardDesign() {
        return constant12;
    }

    @Override
    public int getNumber() {
        return number;
    }
}

