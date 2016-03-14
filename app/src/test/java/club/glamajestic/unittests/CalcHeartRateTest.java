package club.glamajestic.unittests;

import junit.framework.TestCase;

import business.ApplicationConstants;
import business.CalcHeartRate;

//These tests fail because UserDataAccess Environment variable has not been mocked. Waiting for IT3

public class CalcHeartRateTest extends TestCase {

    public void testCalculate() throws Exception {
        //test lower bound
        CalcHeartRate testLowerBoundNegative = new CalcHeartRate(-1,10);
        CalcHeartRate testLowerBoundZero = new CalcHeartRate(0,10);
        CalcHeartRate testLowerBoundMax = new CalcHeartRate(Double.MAX_VALUE,10);
        CalcHeartRate testLowerBoundMin = new CalcHeartRate(Double.MIN_VALUE,10);

        //test Upper bound
        CalcHeartRate testUpperBoundNegative = new CalcHeartRate(10,-1);
        CalcHeartRate testUpperBoundZero = new CalcHeartRate(10,0);
        CalcHeartRate testUpperBoundMax = new CalcHeartRate(10, Double.MAX_VALUE);
        CalcHeartRate testUpperBoundMin = new CalcHeartRate(10,Double.MIN_VALUE);

        assertTrue("Running test: testLowerBoundNegative",(testLowerBoundNegative.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Running test: testLowerBoundZero",(testLowerBoundZero.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Running test: testLowerBoundMax",(testLowerBoundMax.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Running test: testLowerBoundMin",(testLowerBoundMin.calculate() == ApplicationConstants.BAD_CALCULATION));

        assertTrue("Running test: testUpperBoundNegative",(testUpperBoundNegative.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Running test: testUpperBoundZero",(testUpperBoundZero.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Running test: testUpperBoundMax",(testUpperBoundMax.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Running test: testUpperBoundMin",(testUpperBoundMin.calculate() == ApplicationConstants.BAD_CALCULATION));


    }
}