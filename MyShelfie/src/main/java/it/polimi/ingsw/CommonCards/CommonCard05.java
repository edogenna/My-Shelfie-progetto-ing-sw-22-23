package it.polimi.ingsw.CommonCards;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;
/**
 * Contains the algorithms for the fifth common card:
 * Four tiles of the same type in the four corners of the bookshelf.
 * @author Alessandro Fornara
 */
public class CommonCard05 implements CommonCardStrategy{
    private final String constant5=
            "Card number 5     Description:\n"+
            "|=|       |=|     Four tiles of the same type in the four\n"+
            "                  corners of the bookshelf.\n"+
            "\n"+
            "|=|       |=|\n";
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
    public CharMatrix printCommonCardMatrix(){

        return  new CharMatrix()
                .addNewLine("Card number 5  Description:")
                .addNewLine("|=|       |=|  Four tiles of the same type in the four")
                .addNewLine("               corners of the bookshelf.")
                .addNewLine("               ")
                .addNewLine("|=|       |=|");
    }

    @Override
    public void printCommonCard() {
        System.out.println(constant5);
    }
}

