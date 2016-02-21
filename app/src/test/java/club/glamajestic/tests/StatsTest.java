package club.glamajestic.tests;
import junit.framework.TestCase;

import business.Stats;



import org.junit.Test;

public class StatsTest extends TestCase{
    String[] keys, otherKeys;
    float[] values, otherValues;

    @Test
    public void test() throws Exception{
        boolean passed = true;
        //////////////////////////////////////////////////////////////////////////////////testing use of public methods.
        Stats statsBus= new Stats();
        for(int x = 1; x <3; x++) {
            try {
                keys = statsBus.getKeys();
            } catch (Exception e) {
                System.out.println("failed to get keys: " + e.getMessage());
                passed = false;
                break;
            }
            try {
                otherKeys = statsBus.getOtherKeys();
            } catch (Exception e) {
                System.out.println("failed to get keys: " + e.getMessage());
                passed = false;
                break;
            }
            try {
                values = statsBus.getValues();
            } catch (Exception e) {
                System.out.println("failed to get keys: " + e.getMessage());
                passed = false;
                break;
            }
            try {
                otherValues = statsBus.getOtherValues();
            } catch (Exception e) {
                System.out.println("failed to get keys: " + e.getMessage());
                passed = false;
                break;
            }
            //////////////////////////////////////////////////////////////////////////////////Making sure non of them return null
            if (keys == null || values == null && otherKeys == null && otherValues == null) {
                passed = false;
                break;
            }
            //////////////////////////////////////////////////////////////////////////////////Making sure they all have a size of at least 1
            if (keys.length <= 0 || values.length <= 0 || otherKeys.length <= 0 || otherValues.length <= 0) {
                passed = false;
                break;
            }
            //////////////////////////////////////////////////////////////////////////////////Making sure they are equivelent to their counter parts
            if (keys.length != values.length || otherKeys.length != otherValues.length) {
                passed = false;
                break;
            }
            statsBus.init(x);// do test for all modes
        }

        assertTrue(passed);
    }

}