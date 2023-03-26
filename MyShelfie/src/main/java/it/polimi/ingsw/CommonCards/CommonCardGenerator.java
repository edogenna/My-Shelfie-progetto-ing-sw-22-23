package it.polimi.ingsw.CommonCards;

import java.util.Random;
/**
 * Contains Algorithms to generate a random common goal card.
 * @author Alessandro Fornara
 */
public class CommonCardGenerator {
    /**
     * Generates two random common goal cards (different from one another).
     * @return An array that contains the first card in position number 0 and the second card in position number 1.
     */
    public CommonCardStrategy[] GenerateCommonCards(){
        int[] array=new int[2];
        CommonCardStrategy[] result=new CommonCardStrategy[2];
        Random rand=new Random();

        array[0]= rand.nextInt(1,12);
        array[1]=array[0];
        while(array[1]==array[0])
            array[1]= rand.nextInt(1,12);

        switch(array[0]){
            case 1: result[0]=new CommonCard1();
                    break;
            case 2: result[0]=new CommonCard2();
                    break;
            case 3: result[0]=new CommonCard3();
                    break;
            case 4: result[0]=new CommonCard4();
                    break;
            case 5: result[0]=new CommonCard5();
                    break;
            case 6: result[0]=new CommonCard6();
                    break;
            case 7: result[0]=new CommonCard7();
                    break;
            case 8: result[0]=new CommonCard8();
                    break;
            case 9: result[0]=new CommonCard9();
                    break;
            case 10: result[0]=new CommonCard10();
                     break;
            case 11: result[0]=new CommonCard11();
                     break;
            case 12: result[0]=new CommonCard12();
                     break;
        }

        switch(array[1]){
            case 1: result[1]=new CommonCard1();
                    break;
            case 2: result[1]=new CommonCard2();
                    break;
            case 3: result[1]=new CommonCard3();
                    break;
            case 4: result[1]=new CommonCard4();
                    break;
            case 5: result[1]=new CommonCard5();
                    break;
            case 6: result[1]=new CommonCard6();
                    break;
            case 7: result[1]=new CommonCard7();
                    break;
            case 8: result[1]=new CommonCard8();
                    break;
            case 9: result[1]=new CommonCard9();
                    break;
            case 10: result[1]=new CommonCard10();
                     break;
            case 11: result[1]=new CommonCard11();
                     break;
            case 12: result[1]=new CommonCard12();
                     break;
        }

        return result;
    }

}
