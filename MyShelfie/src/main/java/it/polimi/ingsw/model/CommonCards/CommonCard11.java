package it.polimi.ingsw.model.CommonCards;

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

    public final int number = 11;
    @Override
    public boolean checkBookshelf(ItemEnum[][] b) {
        int[] occurrences = new int[6];
        int pos;

        for (int i=0; i<6; i++) {
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

    /**
     * This method hashes an ItemEnum colour with a number.
     * @author Samuele Pietro Galli
     * @param b ItemEnum
     * @return an integer between 0 and 5
     */
    private int enumToInt(ItemEnum b) { //hash per array occorrenze
        int value=0;

        if(b.equals(ItemEnum.GREEN1))
            value=0;
        else if(b.equals(ItemEnum.WHITE1))
            value=1;
        else if(b.equals(ItemEnum.YELLOW1))
            value=2;
        else if(b.equals(ItemEnum.BLUE1))
            value=3;
        else if(b.equals(ItemEnum.AZURE1))
            value=4;
        else if(b.equals(ItemEnum.PURPLE1))
            value=5;

        return value;
    }

    /**
     * Returns the string containing the design of the card.
     * @return the string containing the design of the card.
     */
    @Override
    public String getCommonCardDesign() {
        return constant11;
    }

    @Override
    public int getNumber() {
        return number;
    }
}