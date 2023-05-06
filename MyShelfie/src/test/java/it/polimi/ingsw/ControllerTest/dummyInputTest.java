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

    String firstOk = "c,2,c,3,d,4,3";



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

       //controllo inu+put con spazi
       Assert.assertFalse(c.dummyInput(space));


    }
        String space = "f,5 , c,5, d, 6";

    public void setC(Controller c) {
        this.c = c;
    }

    public void setInputTest(String inputTest) {
        this.inputTest = inputTest;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public void setWrongIn(String wrongIn) {
        this.wrongIn = wrongIn;
    }

    public void setFirstOk(String firstOk) {
        this.firstOk = firstOk;
    }

    public String getFirstOk() {
        return firstOk;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getSpace() {
        return space;
    }

    public String getInputTest1n() {
        return inputTest1n;
    }

    public void setInputTest1n(String inputTest1n) {
        this.inputTest1n = inputTest1n;
    }

    public String getInputTest() {
        return inputTest;
    }

    public String getWrongIn() {
        return wrongIn;
    }

    public Controller getC() {
        return c;
    }

    public String getIn() {
        return in;
    }
}
