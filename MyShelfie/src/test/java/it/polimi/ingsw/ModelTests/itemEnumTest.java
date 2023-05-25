package it.polimi.ingsw.ModelTests;


import it.polimi.ingsw.ItemEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * test for ItemEnum class
 * @author Edoardo Gennaretti
 */
public class itemEnumTest {

    @Test
    public void test(){
        Assert.assertTrue(ItemEnum.BLUE1 == ItemEnum.BLUE1);
        Assert.assertTrue(ItemEnum.BLUE1.getStandardItemEnum() == ItemEnum.BLUE2.getStandardItemEnum());
        Assert.assertTrue(ItemEnum.BLUE1.equals(ItemEnum.BLUE2));
        Assert.assertFalse(ItemEnum.BLUE1 == ItemEnum.BLUE2);
    }
}
