package it.polimi.ingsw.CommonCards;

import it.polimi.ingsw.ItemEnum;

public class CommonCard5 implements CommonCardStrategy{

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
}

