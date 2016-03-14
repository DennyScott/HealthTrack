package club.glamajestic.healthtrack.test;

import junit.framework.TestCase;

import business.ApplicationConstants;
import business.UnitConverter;

public class UnitConverterTest extends TestCase implements ApplicationConstants {

    //test
    // zero,
    // negative,
    // == minimum,
    // < minimum
    // == maximum,
    // > maximum
    
    public void setUp() throws Exception {
        super.setUp();

    }
    public void testLbToKg() throws Exception {
        assertTrue("Zero values are accepted", UnitConverter.lbToKg(0f) != BAD_CALCULATION);
        assertTrue("Negative values are not accepted", UnitConverter.lbToKg(-1f) == ApplicationConstants.BAD_CALCULATION );
        assertTrue("Values equal to the minimuim are accepted", UnitConverter.lbToKg(MIN_CONVERSION_VAL) != BAD_CALCULATION);
        assertTrue("Values less than the minimuim are not accepted", UnitConverter.lbToKg(MIN_CONVERSION_VAL-1) == BAD_CALCULATION);
        assertTrue("Values equal to the maximum are accepted", UnitConverter.lbToKg(MAX_CONVERSION_VAL) != BAD_CALCULATION);
        assertTrue("Values greater than the maximum are not accepted", UnitConverter.lbToKg(MAX_CONVERSION_VAL+1) == BAD_CALCULATION);
    }

    public void testKgToLb() throws Exception {
        assertTrue("Zero values are accepted", UnitConverter.kgToLb(0f) != BAD_CALCULATION);
        assertTrue("Negative values are not accepted", UnitConverter.kgToLb(-1f) == ApplicationConstants.BAD_CALCULATION );
        assertTrue("Values equal to the minimuim are accepted", UnitConverter.kgToLb(MIN_CONVERSION_VAL) != BAD_CALCULATION);
        assertTrue("Values less than the minimuim are not accepted", UnitConverter.kgToLb(MIN_CONVERSION_VAL-1) == BAD_CALCULATION);
        assertTrue("Values equal to the maximum are accepted", UnitConverter.kgToLb(MAX_CONVERSION_VAL) != BAD_CALCULATION);
        assertTrue("Values greater than the maximum are not accepted", UnitConverter.kgToLb(MAX_CONVERSION_VAL+1) == BAD_CALCULATION);
    }

    public void testCmToM2() throws Exception {
        assertTrue("Zero values are accepted", UnitConverter.cmToM2(0f) != BAD_CALCULATION);
        assertTrue("Negative values are not accepted", UnitConverter.cmToM2(-1f) == ApplicationConstants.BAD_CALCULATION );
        assertTrue("Values equal to the minimuim are accepted", UnitConverter.cmToM2(MIN_CONVERSION_VAL) != BAD_CALCULATION);
        assertTrue("Values less than the minimuim are not accepted", UnitConverter.cmToM2(MIN_CONVERSION_VAL-1) == BAD_CALCULATION);
        assertTrue("Values equal to the maximum are accepted", UnitConverter.cmToM2(MAX_CONVERSION_VAL) != BAD_CALCULATION);
        assertTrue("Values greater than the maximum are not accepted", UnitConverter.cmToM2(MAX_CONVERSION_VAL+1) == BAD_CALCULATION);
    }

    public void testCmToInch() throws Exception {
        assertTrue("Zero values are accepted", UnitConverter.cmToInch(0f) != BAD_CALCULATION);
        assertTrue("Negative values are not accepted", UnitConverter.cmToInch(-1f) == ApplicationConstants.BAD_CALCULATION );
        assertTrue("Values equal to the minimuim are accepted", UnitConverter.cmToInch(MIN_CONVERSION_VAL) != BAD_CALCULATION);
        assertTrue("Values less than the minimuim are not accepted", UnitConverter.cmToInch(MIN_CONVERSION_VAL-1) == BAD_CALCULATION);
        assertTrue("Values equal to the maximum are accepted", UnitConverter.cmToInch(MAX_CONVERSION_VAL) != BAD_CALCULATION);
        assertTrue("Values greater than the maximum are not accepted", UnitConverter.cmToInch(MAX_CONVERSION_VAL+1) == BAD_CALCULATION);
    }
}