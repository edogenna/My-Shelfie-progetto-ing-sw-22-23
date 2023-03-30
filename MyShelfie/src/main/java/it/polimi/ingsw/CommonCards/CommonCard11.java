package it.polimi.ingsw.CommonCards;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;
/**
 * Contains the algorithms for the eleventh common card:
 * Eight tiles of the same type. There's no restriction about the position of these tiles.
 * @author Alessandro Fornara
 * @author Samuele Galli
 */
public class CommonCard11 implements CommonCardStrategy {
    private final String constant11=
            "Card number 11  Description:\n"+
            "  |=| |=|       Eight tiles of the same type. There's\n"+
            "|=| |=| |=|     no restriction about the positions\n"+
            "|=| |=| |=|     of these tiles.\n";
    @Override
    public boolean checkBookshelf(ItemEnum[][] b) {
        int[] occurrences = new int[6];
        int pos=0;

        for (int i=0; i<6; i++) { //scorro la matrice
            for (int j=0; j<5; j++) {
                if(!b[i][j].equals(ItemEnum.BLANK)){
                    pos = enumToInt(b[i][j]);
                    occurrences[pos]++;
                    if(occurrences[pos] == 8)
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public void printCommonCard() {
        System.out.println(constant11);
    }

    private int enumToInt(ItemEnum b) { //hash per array occorrenze
        int value=0;

        if(b.equals(ItemEnum.GREEN))
            value=0;
        else if(b.equals(ItemEnum.WHITE))
            value=1;
        else if(b.equals(ItemEnum.YELLOW))
            value=2;
        else if(b.equals(ItemEnum.BLUE))
            value=3;
        else if(b.equals(ItemEnum.AZURE))
            value=4;
        else if(b.equals(ItemEnum.PURPLE))
            value=5;

        return value;
    }
    @Override
    public CharMatrix printCommonCardMatrix(){

        return  new CharMatrix()
                .appendAtBottom("Card number 11  Description:")
                .appendAtBottom("  |=| |=|       Eight tiles of the same type. There's")
                .appendAtBottom("|=| |=| |=|     no restriction about the positions")
                .appendAtBottom("|=| |=| |=|     of these tiles.");
    }
}