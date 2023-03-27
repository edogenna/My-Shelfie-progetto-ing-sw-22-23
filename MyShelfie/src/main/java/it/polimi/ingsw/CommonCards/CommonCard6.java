package it.polimi.ingsw.CommonCards;

import it.polimi.ingsw.ItemEnum;
/**
 * Contains the algorithms for the sixth common card:
 * Two column each formed by 6 different types of tiles.
 * @author Alessandro Fornara
 * @author Samuele Galli
 */
public class CommonCard6 implements CommonCardStrategy{

    private final String constant6=
            "| ≠ | number 6  Description:\n"+
            "| ≠ |           Two columns each formed\n"+
            "| ≠ |           by 6 different\n"+
            "| ≠ |    x2     types of tiles.\n"+
            "| ≠ |\n"+
            "| ≠ |\n";
    @Override
    public boolean checkBookshelf(ItemEnum[][] b){
        //TODO: implementation coming soon
        return false;
    }
    @Override
    public void printCommonCard() {
        System.out.println(constant6);
    }
}
