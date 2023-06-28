package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.controller.Controller;
import org.junit.Assert;
import org.junit.Test;

/**
 * ControlCommonCardsTest tests the methods of the class Controller that manage the common cards
 */
public class dummyInputTest {
    Controller c = new Controller(2);
    String inputTest = "test1";
    String inputTest1n = "ok";
    String in = "x,y,x,y,x,y,c";
    String wrongIn = "xy,x,y,x,y,c";
    String space = "f,5 , c,5, d, 6";
    String firstOk = "c,2,c,3,d,4,3";
    String wrongColumn = "c,2,c,3,9";
    String outOfNumber = "c,2,c,9,1";


    /**
     * it is a test for the function dummyInput
     * @author Samuele Pietro Galli
     */
    @Test
    public void Test(){
        //rapido controllo su string banale
       Assert.assertTrue(c.dummyInput(inputTest));
       String newStr = inputTest.replace("test1", "verde");

       //conotrol with replace method
       Assert.assertTrue(c.dummyInput(newStr));

       //control input with less expected letters
       Assert.assertTrue(c.dummyInput(inputTest1n));

       //number control
       Assert.assertTrue(c.dummyInput(in));

       Assert.assertTrue(c.dummyInput(wrongIn));

       //control on OK string
       Assert.assertFalse(c.dummyInput(firstOk));

       //control input with spaces
       Assert.assertTrue(c.dummyInput(space));

       //control on wrong column
       Assert.assertTrue(c.dummyInput(wrongColumn));

       //control on wrong number
       Assert.assertTrue(c.dummyInput(outOfNumber));
    }

    public String getWrongColumn() {
        return wrongColumn;
    }

    public void setWrongColumn(String wrongColumn) {
        this.wrongColumn = wrongColumn;
    }

}
