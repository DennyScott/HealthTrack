package club.glamajestic.tests;
import junit.framework.TestCase;
import org.junit.Test;


import business.Stats;

public class StatsTest extends TestCase{
    String[] keys, otherKeys;
    float[] values, otherValues;

    public void testNullKeys(){
        Stats statsBus = new Stats();
        statsBus.init(1);
        keys = statsBus.getKeys();
        assertTrue("Keys are null", keys!=null);
    }

    public void testNullOtherKeys(){
        Stats statsBus = new Stats();
        statsBus.init(1);
        otherKeys = statsBus.getOtherKeys();
        assertTrue("Other Keys are null", otherKeys!=null);
    }

    public void testNullValues(){
        Stats statsBus = new Stats();
        statsBus.init(1);
        values = statsBus.getValues();
        assertTrue("Values are null", values!=null);
    }

    public void testNullOtherValues(){
        Stats statsBus = new Stats();
        statsBus.init(1);
        otherValues = statsBus.getOtherValues();
        assertTrue("Other Values are null", otherValues!=null);
    }

    public void testLength(){
        Stats statsBus = new Stats();
        statsBus.init(1);
        keys=statsBus.getKeys();
        otherKeys=statsBus.getOtherKeys();
        values=statsBus.getValues();
        otherValues=statsBus.getOtherValues();
        assertTrue("Keys have zero or negative length", keys.length > 0 && values.length > 0 && otherKeys.length > 0 && otherValues.length > 0);
    }

    public void testEqualLength1(){
        Stats statsBus = new Stats();
        statsBus.init(1);
        keys=statsBus.getKeys();
        otherKeys=statsBus.getOtherKeys();
        values=statsBus.getValues();
        otherValues=statsBus.getOtherValues();
        assertTrue("Keys are not equal length", keys.length == values.length && otherKeys.length == otherValues.length);
    }

}