package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.ItemEnum;
/**
 * Contains the algorithms for the seventh common card:
 * Two groups each containing 4 tiles of the same type in a 2x2 square. The tiles of one square can be different from those of the other square.
 * @author Alessandro Fornara
 * @author Samuele Galli
 */
public class CommonCard07 implements CommonCardStrategy {
    public static final String constant7=
            "Card number 7   Description\n"+
            "|=| |=|         Two groups each containing 4 tiles of\n" +
            "|=| |=|         the same type in a 2x2 square. The tiles\n" +
            "   x2           of one square can be different from\n" +
            "                those of the other square.\n";

    public final int number = 7;

    @Override
    public String getPath(){
        return "/graphics/commongoalcards/7.png";
    }
    @Override
    public boolean checkBookshelf(ItemEnum[][] b) {
        int count=0;
        boolean[][] matrix = new boolean[6][5];
        for (int i=0; i<5; i++){ //scorro la matrice fino alla penultima riga e penultima colonna dati i controlli
            for (int j=0; j<4; j++){
                //if che controlla a destra, poi sotto, poi in diagonale
                if((!matrix[i][j]) &&(!matrix[i + 1][j]) && (!matrix[i][j + 1]) && (!matrix[i + 1][j + 1]) && (b[i][j].equals(b[i][j+1]) && (!b[i][j].equals(ItemEnum.BLANK)) && (b[i][j].equals(b[i+1][j])) && (b[i][j].equals(b[i+1][j+1])))){
                    count++;
                    matrix[i][j] = true;
                    matrix[i][j+1]=true;
                    matrix[i+1][j]=true;
                    matrix[i+1][j+1]=true;
                }
                if(count == 2)
                    return true;
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
        return constant7;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
