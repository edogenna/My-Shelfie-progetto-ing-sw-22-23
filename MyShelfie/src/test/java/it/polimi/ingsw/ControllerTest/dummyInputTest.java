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

    String firstOk = ""
    @Test
    public void Test(){
       Assert.assertTrue(c.dummyInput(inputTest));
       String newStr = inputTest.replace("test1", "verde");
       Assert.assertTrue(c.dummyInput(newStr));
       Assert.assertTrue(c.dummyInput(inputTest1n));

       Assert.assertTrue(c.dummyInput(in));
       Assert.assertTrue(c.dummyInput(wrongIn));


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
