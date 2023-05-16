package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.controller.Controller;
import org.junit.Assert;
import org.junit.Test;

public class dummyInputTest {
    Controller c = new Controller(2);
    String inputTest = "test1";
    String inputTest1n = "ok";
    String in = "x,y,x,y,x,y,c";
    String wrongIn = "xy,x,y,x,y,c";
    String space = "f,5 , c,5, d, 6";
    String firstOk = "c,2,c,3,d,4,3";

    String wrongColumn = "c,2,c,3,9";


    /**
     * it is a test for the function dummyInput
     * @author Samuele Pietro Galli
     */
    @Test
    public void Test(){
        //rapido controllo su string banale
       Assert.assertTrue(c.dummyInput(inputTest));
       String newStr = inputTest.replace("test1", "verde");

       //cnotrollo con metodo replace
       Assert.assertTrue(c.dummyInput(newStr));

       //controllo su ingresso con meno lettere expected
       Assert.assertTrue(c.dummyInput(inputTest1n));

       //controllo sui numeri
       Assert.assertTrue(c.dummyInput(in));


       Assert.assertTrue(c.dummyInput(wrongIn));


       //controllo su una stringa ok
       Assert.assertFalse(c.dummyInput(firstOk));

       //controllo input con spazi
       Assert.assertTrue(c.dummyInput(space));

       //controllo con numero di colonna sbagliato
       Assert.assertTrue(c.dummyInput(wrongColumn));

    }

    public String getWrongColumn() {
        return wrongColumn;
    }

    public void setWrongColumn(String wrongColumn) {
        this.wrongColumn = wrongColumn;
    }

}
