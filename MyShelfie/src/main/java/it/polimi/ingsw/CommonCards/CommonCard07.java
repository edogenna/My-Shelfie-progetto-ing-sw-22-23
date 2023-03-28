package it.polimi.ingsw.CommonCards;

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
        //TODO: implementation coming soon
        return false;
    }

    @Override
    public void printCommonCard() {
        System.out.println(constant7);
    }
}
