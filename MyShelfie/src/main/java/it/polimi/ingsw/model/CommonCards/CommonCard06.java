package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;
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
    @Override
    public boolean checkBookshelf(ItemEnum[][] b){
        int[] occurrences = new int[6];
        int pos;
        int i=0;
        boolean columnOk = true; //true finchè una colonna ha tutte le occorrenze = 1
        int counter=0; //contatore per il numero di colonne

        for (int j=0; j<5; j++) {
            setArray(occurrences, 0);
            if(!b[i][j].equals(ItemEnum.BLANK)){ //escludo le colonne non piene
                for (i=0; i<6; i++) {
                    pos = enumToInt(b[i][j]); //scorro tutta la colonna aumentando le righe
                    occurrences[pos]++;
                }
                for (int k=0; k<6; k++)
                    if(occurrences[k] != 1)
                        columnOk=false;

                if(columnOk)
                    counter++;
                if(counter == 2)
                    return true;
            }
        }
        return false;
    }


    @Override
    public void printCommonCard() {
        System.out.println(constant6);
    }

    /**
     * This function converts an ItemEnum value into an int value
     * @param b ItemEnum value
     * @return Converted int value
     */
    private int enumToInt(ItemEnum b) { //hash per array occorrenze
        int value=0;

        if(b.equals(ItemEnum.GREEN))
            value=0;
        else if(b.equals(ItemEnum.WHITE))
            value=1;
        else if(b.equals(ItemEnum.YELLOW))
            value=2;
        else if(b.equals(ItemEnum.BLUE))
            value=3;
        else if(b.equals(ItemEnum.AZURE))
            value=4;
        else if(b.equals(ItemEnum.PURPLE))
            value=5;

        return value;
    }

    /**
     * Sets all cells in an array to the indicated value
     * @param a Array
     * @param value Number to be assigned
     */
    private void setArray(int[] a, int value){
        for(int i=0; i<a.length; i++)
            a[i]=value;
    }
    @Override
    public CharMatrix printCommonCardMatrix(){

        return  new CharMatrix()
                .appendAtBottom("Card number 6   Description:")
                .appendAtBottom("  | ≠ |         Two columns each formed")
                .appendAtBottom("  | ≠ |         by 6 different")
                .appendAtBottom("  | ≠ |         types of tiles.")
                .appendAtBottom("  | ≠ |  x2     different between different groups.")
                .appendAtBottom("  | ≠ |  ")
                .appendAtBottom("  | ≠ |  ");
    }
}
