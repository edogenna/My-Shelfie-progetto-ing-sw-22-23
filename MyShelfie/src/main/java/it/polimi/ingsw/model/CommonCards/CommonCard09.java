package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;
/**
 * Contains the algorithms for the ninth common card:
 * Three columns each formed by 6 tiles of maximum three different types. One column can show the same or a different combination of another column.
 * @author Alessandro Fornara
 */
public class CommonCard09 implements CommonCardStrategy {

    private final String constant9=
            "| ■ | number 9  Description:\n"+
            "| ■ |           Three column each formed by 6 tiles\n"+
            "| ■ |  MAX 3 ≠  of maximum three different types. One\n"+
            "| ■ |    x3     column can show the same or a different\n"+
            "| ■ |           combination of another column.\n"+
            "| ■ |\n";
    @Override
    public boolean checkBookshelf(ItemEnum[][] b) {
        ItemEnum firstColor;
        ItemEnum secondColor;
        ItemEnum thirdColor;
        int counter=0;
        final int numberOfRows=b.length;
        final int numberOfColumns=b[0].length;
        int i;

        for (int j = 0; j < numberOfColumns; j++) {
            i = 0;

            //colore1
            if (!b[i][j].equals(ItemEnum.BLANK)) {
                firstColor = b[i][j];
                do {i++;}
                while (i < numberOfRows && b[i][j].equals(firstColor));

                //colore 2
                if(i!=numberOfRows) {
                    secondColor = b[i][j];
                    do {i++;}
                    while (i < numberOfRows && (b[i][j].equals(firstColor) || b[i][j].equals(secondColor)));

                    //colore 3
                    if(i!=numberOfRows) {
                        thirdColor = b[i][j];
                        do {i++;}
                        while (i < numberOfRows && (b[i][j].equals(firstColor) || b[i][j].equals(secondColor) || b[i][j].equals(thirdColor)));
                    }
                }
                if (i == numberOfRows) counter++;
            }
            if (counter == 3)
                return true;
        }
        return false;
    }

    @Override
    public void printCommonCard() {
        System.out.println(constant9);
    }
    @Override
    public CharMatrix printCommonCardMatrix(){

        return  new CharMatrix()
                .addNewLine("Card number 9    Description:")
                .addNewLine("  | ■ |          Three column each formed by 6 tiles")
                .addNewLine("  | ■ | MAX 3 ≠  of maximum three different types. One")
                .addNewLine("  | ■ |          column can show the same or a different")
                .addNewLine("  | ■ |          combination of another column.")
                .addNewLine("  | ■ |  ")
                .addNewLine("  | ■ |  x3");
    }
}


