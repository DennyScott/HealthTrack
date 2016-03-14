package business;

import junit.framework.TestCase;

import business.ApplicationConstants;
import business.CalcBMI;


/**
 * Created by Robby on 3/13/2016.
 */
//calculate body mass index based on weight in kg divided by height in m^2
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
                (testNegativeWeight == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing zero weight",
                (testZeroWeight == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing max weight",
                (testMaxWeight == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing min weight",
                (testMinWeight == ApplicationConstants.BAD_CALCULATION));

        assertTrue("Testing negative height",
                (testNegativeHeight == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing zero height",
                (testZeroHeight == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing max height",
                (testMaxHeight == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing min height",
                (testMinHeight == ApplicationConstants.BAD_CALCULATION));
    }
}