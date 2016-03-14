package business;

import junit.framework.TestCase;

import business.ApplicationConstants;
import business.CalcWeightSustain;


public class CalcWeightSustainTest extends TestCase {

    public void testCalculate() throws Exception {
        CalcWeightSustain calcOverVal = new CalcWeightSustain(Double.MAX_VALUE);
        assertTrue("Testing over calculation", (calcOverVal.calculate() == ApplicationConstants.BAD_CALCULATION));

        CalcWeightSustain calcNegative = new CalcWeightSustain(-1);
        assertTrue("Testing negative calories", (calcOverVal.calculate() == ApplicationConstants.BAD_CALCULATION));

        CalcWeightSustain calcZero= new CalcWeightSustain(0);
        assertTrue("Testing zero calories", (calcOverVal.calculate() == ApplicationConstants.BAD_CALCULATION));

    }
}