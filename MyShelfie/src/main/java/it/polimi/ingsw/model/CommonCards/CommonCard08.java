package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.ItemEnum;

import java.util.Arrays;

/**
 * Contains the algorithms for the eighth common card:
 * Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line.
 * @author Alessandro Fornara
 * @author Samuele Galli
 */
public class CommonCard08 implements CommonCardStrategy {

    public static final String constant8=
            "Card number 8        Description:\n"+
            "|≠| |≠| |≠| |≠| |≠|  Two lines each formed by 5 different\n"+
            "                     types of tiles. One line can show \n"+
            "       x2            the same or a different combination\n"+
            "                     of another line.\n";

    public final int number = 8;

    @Override
    public String getPath(){
        return "/graphics/commongoalcards/8.png";
    }

    @Override
    public boolean checkBookshelf(ItemEnum[][] b) {
        int[] occurrences = new int[6];
        int pos;
        int j=0;
        boolean lineOk = true; //true finchè una riga ha tutte le occorrenze = 1
        int counter=0; //contatore per il numero di righe

        for (int i=0; i<6; i++) {
            Arrays.fill(occurrences, 0);

            lineOk=true;
                for (j=0; j<5; j++) {
                    if (!b[i][j].equals(ItemEnum.BLANK)) { //escludo le righe non piene
                        pos = enumToInt(b[i][j]); //popolo array occorrenze
                        occurrences[pos]++;
                    }
                }
                bubbleSort(occurrences);
                for(int k=0; k<5 && lineOk; k++) {
                    if (occurrences[k]!=1)
                        lineOk=false;
                }
                if(lineOk)
                    counter++;
                if(counter == 2)
                    return true;

        }
        return false;
    }

    /**
     * This method hashes an ItemEnum colour with a number.
     * @author Samuele Pietro Galli
     * @param b ItemEnum
     * @return an integer between 0 and 5
     */
    private int enumToInt(ItemEnum b) { //hash per colori array occorrenze
        int value=0;

        if(b.equals(ItemEnum.GREEN1))
            value=0;
        else if(b.equals(ItemEnum.WHITE1))
            value=1;
        else if(b.equals(ItemEnum.YELLOW1))
            value=2;
        else if(b.equals(ItemEnum.BLUE1))
            value=3;
        else if(b.equals(ItemEnum.AZURE1))
            value=4;
        else if(b.equals(ItemEnum.PURPLE1))
            value=5;

        return value;
    }


    /**
     * This method sorts an array in decreasing order.
     * @author Samuele Pietro Galli
     * @param arr array.
     */
    private void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] < arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    /**
     * Returns the string containing the design of the card.
     * @return the string containing the design of the card.
     */
    @Override
    public String getCommonCardDesign() {
        return constant8;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
