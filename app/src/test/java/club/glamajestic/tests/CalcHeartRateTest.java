package business;

import junit.framework.TestCase;

import business.CalcHeartRate;

/**
 * Created by Robby on 3/13/2016.
 */
public class CalcHeartRateTest extends TestCase {

    public void testCalculate() throws Exception {
        //test lower bound
        CalcHeartRate testLowerBoundNegative = new CalcHeartRate(-1,10,10);
        CalcHeartRate testLowerBoundZero = new CalcHeartRate(0,10,10);
        CalcHeartRate testLowerBoundMax = new CalcHeartRate(Double.MAX_VALUE,10,10);
        CalcHeartRate testLowerBoundMin = new CalcHeartRate(Double.MIN_VALUE,10,10);

        //test Upper bound
        CalcHeartRate testUpperBoundNegative = new CalcHeartRate(10,-1,10);
        CalcHeartRate testUpperBoundZero = new CalcHeartRate(10,0,10);
        CalcHeartRate testUpperBoundMax = new CalcHeartRate(10, Double.MAX_VALUE,10);
        CalcHeartRate testUpperBoundMin = new CalcHeartRate(10,Double.MIN_VALUE,10);

    }
}