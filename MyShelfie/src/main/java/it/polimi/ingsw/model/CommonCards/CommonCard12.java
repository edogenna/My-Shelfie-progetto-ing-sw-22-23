package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;
/**
 * Contains the algorithms for the twelfth common card:
 * Five columns of increasing or decreasing height. Starting from the first column on the left or on the right, each next column must be made of exactly one more tile. Tiles can be of any type.
 * @author Alessandro Fornara
 */
public class CommonCard12 implements CommonCardStrategy{
    private final String constant12=
            "Card number 12  Description:\n"+
            "|=|             Five columns of increasing o decreasing height.\n"+
            "|=|=|           Starting from the first column on the left or \n"+
            "|=|=|=|         on the right, each next column must be made of\n"+
            "|=|=|=|=|       exactly one more tile. Tiles can be of any type.\n"+
            "|=|=|=|=|=|\n";
    @Override
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

    @Override
    public void printCommonCard() {
        System.out.println(constant12);
    }
    @Override
    public CharMatrix printCommonCardMatrix(){

        return  new CharMatrix()
                .addNewLine("Card number 12  Description:")
                .addNewLine(" |=|            Five columns of increasing o decreasing height.")
                .addNewLine(" |=|=|          Starting from the first column on the left or")
                .addNewLine(" |=|=|=|        on the right, each next column must be made of")
                .addNewLine(" |=|=|=|=|      exactly one more tile. Tiles can be of any type.")
                .addNewLine(" |=|=|=|=|=| ");
    }
}

