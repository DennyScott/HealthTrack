package club.glamajestic.tests;
import junit.framework.TestCase;
import org.junit.Test;


import business.Stats;



import org.junit.Test;

public class StatsTest extends TestCase{
    String[] keys, otherKeys;
    float[] values, otherValues;

    @Test
    public void testNull(){
        Stats statsBus = new Stats();
        assertTrue("Keys are null", keys!=null && values!=null || otherKeys!=null || otherValues!=null);
    }
    @Test
    public void testWtf(){
        Stats statsBus = new Stats();
        assertTrue(false);
    }


    public void testLength(){
        Stats statsBus = new Stats();
        assertTrue("Keys have zero or negative length", keys.length > 0 && values.length > 0 && otherKeys.length > 0 && otherValues.length > 0);
    }

    public void testEqualLength(){
        Stats statsBus = new Stats();
        assertTrue("Keys are not equal length", keys.length == values.length && otherKeys.length == otherValues.length);
    }

    public void testNull1(){
        Stats statsBus = new Stats();
        statsBus.init(1);
        assertTrue("Keys are null", keys!=null && values!=null || otherKeys!=null || otherValues!=null);
    }

    public void testLength1(){
        Stats statsBus = new Stats();
        statsBus.init(1);
        assertTrue("Keys have zero or negative length", keys.length > 0 && values.length > 0 && otherKeys.length > 0 && otherValues.length > 0);
    }

    public void testEqualLength1(){
        Stats statsBus = new Stats();
        statsBus.init(1);
        assertTrue("Keys are not equal length", keys.length == values.length && otherKeys.length == otherValues.length);
    }

}