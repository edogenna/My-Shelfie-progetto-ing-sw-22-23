package it.polimi.ingsw.CommonCards;

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
        //TODO: implementation coming soon
        return false;
    }

    @Override
    public void printCommonCard() {
        System.out.println(constant11);
    }
}