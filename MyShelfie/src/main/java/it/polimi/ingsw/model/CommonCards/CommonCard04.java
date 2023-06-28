package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.ItemEnum;

import java.util.LinkedList;

/**
 * Contains the algorithms for the fourth common card:
 * Four lines each formed by 5 tiles of maximum three different types. One line can show the same or a different combination of another line.
 * @author Edoardo Gennaretti
 * @author Alessandro Fornara
 */
public class CommonCard04 implements CommonCardStrategy {
    private final String constant4=
            "Card number 4        Description:\n"+
            "|■| |■| |■| |■| |■|  Four lines each formed by 5 tiles of\n"+
            "     MAX 3 ≠         maximum three different types.\n"+
            "       x4            Lines can be different from one another.\n";

    public final int number = 4;

    @Override
    public boolean checkBookshelf(ItemEnum[][] b){
        LinkedList<ItemEnum> list = new LinkedList<>();
        int numerOfValidRows = 0;
        boolean validity;

        for(int i=0; i<6; i++){
            list.clear();
            validity = true;

            for(int j=0; j<5; j++){
                if(b[i][j].equals(ItemEnum.BLANK)) {
                    validity = false;
                }else{
                    if(!list.contains(b[i][j].getStandardItemEnum())){
                        list.add(b[i][j].getStandardItemEnum());
                    }
                }
            }

            if(list.size()<=3 && validity){
                numerOfValidRows++;
            }
        }

        return numerOfValidRows >= 4;
    }


    /**
     * @return the string containing the common card design
     */
    @Override
    public String getCommonCardDesign() {
        return constant4;
    }
    /**
     * @return the number of the common card
     */
    @Override
    public int getNumber() {
        return number;
    }
}

