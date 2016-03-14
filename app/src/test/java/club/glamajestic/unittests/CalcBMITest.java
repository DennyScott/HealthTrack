package club.glamajestic.unittests;

import junit.framework.TestCase;

import business.ApplicationConstants;
import business.CalcBMI;

//calculate body mass index based on weight in kg divided by height in m^2

//These tests fail because UserDataAccess Environment variable has not been mocked. Waiting for IT3
public class CalcBMITest extends TestCase {
    CalcBMI testNegativeWeight ;
    CalcBMI testZeroWeight ;
    CalcBMI testMaxWeight ;
    CalcBMI testMinWeight ;

    CalcBMI testNegativeHeight ;
    CalcBMI testZeroHeight ;
    CalcBMI testMaxHeight ;
    CalcBMI testMinHeight ;
    public void setUp() throws Exception {
        super.setUp();
        testNegativeWeight = new CalcBMI(-1,10);
        testZeroWeight = new CalcBMI(0,10);
        testMaxWeight = new CalcBMI(Double.MAX_VALUE, 10);
        testMinWeight = new CalcBMI(Double.MIN_VALUE, 10);

        testNegativeHeight = new CalcBMI(10,-1);
        testZeroHeight = new CalcBMI(10,0);
        testMaxHeight = new CalcBMI(10, Double.MAX_VALUE);
        testMinHeight = new CalcBMI(10, Double.MIN_VALUE);
    }

    public void tearDown() throws Exception {
        testNegativeWeight = null;
        testZeroWeight = null;
        testMaxWeight = null;
        testMinWeight = null;

        testNegativeHeight = null;
        testZeroHeight = null;
        testMaxHeight = null;
        testMinHeight = null;

    }

    public void testCalculate() throws Exception {
        assertTrue("Testing negative weight",
                (testNegativeWeight.calculate() != ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing zero weight",
                (testZeroWeight.calculate() != ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing max weight",
                (testMaxWeight.calculate() != ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing min weight",
                (testMinWeight.calculate() != ApplicationConstants.BAD_CALCULATION));

        assertTrue("Testing negative height",
                (testNegativeHeight.calculate() != ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing zero height",
                (testZeroHeight.calculate() != ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing max height",
                (testMaxHeight.calculate() != ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing min height",
                (testMinHeight.calculate() != ApplicationConstants.BAD_CALCULATION));
    }
}