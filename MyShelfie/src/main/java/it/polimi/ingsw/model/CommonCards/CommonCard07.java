package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;
/**
 * Contains the algorithms for the seventh common card:
 * Two groups each containing 4 tiles of the same type in a 2x2 square. The tiles of one square can be different from those of the other square.
 * @author Alessandro Fornara
 * @author Samuele Galli
 */
public class CommonCard07 implements CommonCardStrategy {
    private final String constant7=
            "Card number 7   Description\n"+
            "|=| |=|         Two groups each containing 4 tiles of\n" +
            "|=| |=|         the same type in a 2x2 square. The tiles\n" +
            "   x2           of one square can be different from\n" +
            "                those of the other square.\n";
    @Override
    public boolean checkBookshelf(ItemEnum[][] b) {
        int count=0;
        for (int i=0; i<5; i++){ //scorro la matrice fino alla penultima riga e penultima colonna dati i controlli
            for (int j=0; j<4; j++){
                //if che controlla a destra, poi sotto, poi in diagonale
                if((b[i][j].equals(b[i][j+1]) && (b[i][j].equals(b[i+1][j])) && (b[i][j].equals(b[i+1][j+1])))){
                    count++;
                }
                if(count == 2)
                    return true;
            }
        }
        return false;
    }
    @Override
    public CharMatrix printCommonCardMatrix(){

        return  new CharMatrix()
                .appendAtBottom("Card number 7   Description:")
                .appendAtBottom(" |=| |=|        Two groups each containing 4 tiles of")
                .appendAtBottom(" |=| |=|        the same type in a 2x2 square. The tiles")
                .appendAtBottom("   x2           of one square can be different from")
                .appendAtBottom("                those of the other square.");
    }
    @Override
    public void printCommonCard() {
        System.out.println(constant7);
    }
}
