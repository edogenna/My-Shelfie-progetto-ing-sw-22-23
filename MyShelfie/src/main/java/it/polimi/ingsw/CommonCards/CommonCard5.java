package it.polimi.ingsw.CommonCards;

import it.polimi.ingsw.ItemEnum;
/**
 * Contains the algorithms for the fifth common card:
 * Four tiles of the same type in the four corners of the bookshelf.
 * @author Alessandro Fornara
 */
public class CommonCard5 implements CommonCardStrategy{
    @Override
    public boolean checkBookshelf(ItemEnum[][] b){
        final int numberOfRows=b.length;
        final int numberOfColumns=b[0].length;

        if(b[numberOfRows-1][0].equals(b[numberOfRows-1][numberOfColumns-1])
                && b[numberOfRows-1][numberOfColumns-1].equals(b[0][numberOfColumns-1])
                && b[0][numberOfColumns-1].equals(b[0][0])
                && !b[numberOfRows-1][0].equals(ItemEnum.BLANK))
            return true;
        return false;
    }

    @Override
    public void printCommonCard() {
        //TODO: implementation coming soon
    }
}

