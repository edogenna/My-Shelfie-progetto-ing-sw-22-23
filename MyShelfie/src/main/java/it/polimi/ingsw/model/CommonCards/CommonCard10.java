package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;
/**
 * Contains the algorithms for the tenth common card:
 * Five tiles of the same type forming an X.
 * @author Alessandro Fornara
 */
public class CommonCard10 implements CommonCardStrategy{
    private final String constant10=
            "Card number 10   Description:\n" +
            "|=|   |=|        Five tiles of the same type forming an X.\n"+
            "   |=|\n"+
            "|=|   |=|\n";
    @Override
    public boolean checkBookshelf(ItemEnum[][] b){
        final int numberOfRows=b.length;
        final int numberOfColumns=b[0].length;
        ItemEnum color;
        for(int i=numberOfRows-2; i>0; i--){
            for(int j=1; j<numberOfColumns-1; j++){

                if(!b[i][j].equals(ItemEnum.BLANK)){
                    color=b[i][j];

                    if(b[i-1][j-1].equals(color)
                            && b[i-1][j+1].equals(color)
                            && b[i+1][j-1].equals(color)
                            && b[i+1][j+1].equals(color))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public void printCommonCard() {
        System.out.println(constant10);
    }
    @Override
    public CharMatrix printCommonCardMatrix(){

        return  new CharMatrix()
                .appendAtBottom("Card number 10  Description:")
                .appendAtBottom("|=|   |=|       Five tiles of the same type forming an X.")
                .appendAtBottom("   |=|     ")
                .appendAtBottom("|=|   |=|  ");
    }
}

