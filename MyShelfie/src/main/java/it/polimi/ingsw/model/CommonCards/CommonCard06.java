package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;

import java.util.Arrays;

/**
 * Contains the algorithms for the sixth common card:
 * Two column each formed by 6 different types of tiles.
 * @author Alessandro Fornara
 * @author Samuele Galli
 */
public class CommonCard06 implements CommonCardStrategy{
    private final String constant6=
            "| ≠ | number 6  Description:\n"+
            "| ≠ |           Two columns each formed\n"+
            "| ≠ |           by 6 different\n"+
            "| ≠ |    x2     types of tiles.\n"+
            "| ≠ |\n"+
            "| ≠ |\n";

    public final int number = 6;

    @Override
    public boolean checkBookshelf(ItemEnum[][] b){
        int[] occurrences = new int[6];
        int pos;
        int i=0;
        boolean columnOk; //true finchè una colonna ha tutte le occorrenze = 1
        int counter=0; //contatore per il numero di colonne

        for (int j=0; j<5; j++) {
            Arrays.fill(occurrences, 0);
            columnOk=true;

                for (i=0; i<6; i++) {
                    if(!b[i][j].equals(ItemEnum.BLANK)) {
                        pos = enumToInt(b[i][j]); //scorro tutta la colonna aumentando le righe
                        occurrences[pos]++;
                    }
                }
                for (int k=0; k<6 && columnOk; k++)
                    if(occurrences[k] != 1)
                        columnOk=false;

                if(columnOk)
                    counter++;
                if(counter == 2)
                    return true;
            }

        return false;
    }


    @Override
    public void printCommonCard() {
        System.out.println(constant6);
    }

    /**
     * This function converts an ItemEnum value into an int value
     * @author Samuele Pietro Galli
     * @param b ItemEnum value
     * @return an integer between 0 and 5
     */
    private int enumToInt(ItemEnum b) { //hash per array occorrenze
        int value=0;

        if(b.equals(ItemEnum.GREEN1) || b.equals(ItemEnum.GREEN2) || b.equals(ItemEnum.GREEN3))
            value=0;
        else if(b.equals(ItemEnum.WHITE1) || b.equals(ItemEnum.WHITE2) || b.equals(ItemEnum.WHITE3))
            value=1;
        else if(b.equals(ItemEnum.YELLOW1) || b.equals(ItemEnum.YELLOW2) || b.equals(ItemEnum.YELLOW3))
            value=2;
        else if(b.equals(ItemEnum.BLUE1) || b.equals(ItemEnum.BLUE2) || b.equals(ItemEnum.BLUE3))
            value=3;
        else if(b.equals(ItemEnum.AZURE1) || b.equals(ItemEnum.AZURE2) || b.equals(ItemEnum.AZURE3))
            value=4;
        else if(b.equals(ItemEnum.PURPLE1) || b.equals(ItemEnum.PURPLE2) || b.equals(ItemEnum.PURPLE3))
            value=5;

        return value;
    }



    @Override
    public CharMatrix printCommonCardMatrix(){

        return  new CharMatrix()
                .newLineAtBottom("Card number 6   Description:")
                .newLineAtBottom("  | ≠ |         Two columns each formed")
                .newLineAtBottom("  | ≠ |         by 6 different")
                .newLineAtBottom("  | ≠ |         types of tiles.")
                .newLineAtBottom("  | ≠ |  x2     different between different groups.")
                .newLineAtBottom("  | ≠ |  ")
                .newLineAtBottom("  | ≠ |  ");
    }

    @Override
    public String getCommonCardDesign() {
        return constant6;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
