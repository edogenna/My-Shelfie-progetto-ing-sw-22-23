package it.polimi.ingsw.CommonCards;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;
/**
 * Contains the algorithms for the eighth common card:
 * Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line.
 * @author Alessandro Fornara
 * @author Samuele Galli
 */
public class CommonCard08 implements CommonCardStrategy {

    private final String constant8=
            "Card number 8        Description:\n"+
            "|≠| |≠| |≠| |≠| |≠|  Two lines each formed by 5 different\n"+
            "                     types of tiles. One line can show the\n"+
            "       x2            the same or a different combination\n"+
            "                     of another line.\n";
    @Override
    public boolean checkBookshelf(ItemEnum[][] b) {
        //TODO: check BLANK CASE and general check
        //TODO: rethink algorithm
        int[] occurrences = new int[6];
        int pos;
        int sum;
        int j=0;
        boolean lineOk = true; //true finchè una riga ha tutte le occorrenze = 1
        int counter=0; //contatore per il numero di righe

        for (int i=0; i<6; i++) {
            setArray(occurrences, 0);
            sum=0;
                for (j=0; j<5; j++) {
                    if(!b[i][j].equals(ItemEnum.BLANK)){ //escludo le righe non piene
                        pos = enumToInt(b[i][j]); //popolo array occorrenze
                        occurrences[pos]++;
                    }
                    for (int k=0; k<6; k++) //conto le occorrenze per sapere se ci sono almeno 5 carte
                        sum += occurrences[k];
                    for (int k1=0; k1<6; k1++){//se ci sono occorrenze con 2 o più di 5 carte non va bene
                        if(occurrences[k1] >= 2 || sum < 5)
                            lineOk = false;
                    }
                    if(lineOk)
                        counter++;
                    if(counter == 2)
                        return true;
            }
        }
        return false;
    }

    private int enumToInt(ItemEnum b) { //hash per colori array occorrenze
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

    private void setArray(int[] a, int value){
        for(int i=0; i<a.length; i++)
            a[i]=value;
    }
    @Override
    public void printCommonCard() {
        System.out.println(constant8);
    }

    @Override
    public CharMatrix printCommonCardMatrix(){

        return  new CharMatrix()
                .appendAtBottom("Card number 8        Description:")
                .appendAtBottom("|≠| |≠| |≠| |≠| |≠|  Two lines each formed by 5 different")
                .appendAtBottom("   x2                types of tiles. One line can show the")
                .appendAtBottom("                     the same or a different combination")
                .appendAtBottom("                     of another line.");
    }
}
