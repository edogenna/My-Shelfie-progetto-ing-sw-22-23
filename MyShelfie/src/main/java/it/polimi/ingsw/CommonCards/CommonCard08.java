package it.polimi.ingsw.CommonCards;

import it.polimi.ingsw.ItemEnum;
/**
 * Contains the algorithms for the eighth common card:
 * Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line.
 * @author Alessandro Fornara
 * @author Samuele Galli
 */
public class CommonCard08 implements CommonCardStrategy {

    private final String constant8=
            "Card number 8        Description:\n"+
            "|≠| |≠| |≠| |≠| |≠|  Two lines each formed by 5 different\n"+
            "                     types of tiles. One line can show the\n"+
            "       x2            the same or a different combination\n"+
            "                     of another line.\n";
    @Override
    public boolean checkBookshelf(ItemEnum[][] b) {
        //TODO: implementation coming soon
        return false;
    }

    @Override
    public void printCommonCard() {
        System.out.println(constant8);
    }
}
