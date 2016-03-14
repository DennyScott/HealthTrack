package business;

import junit.framework.TestCase;

import business.CalcWeightSustain;

/**
 * Created by Robby on 3/13/2016.
 */
public class CalcWeightSustainTest extends TestCase {

    public void testCalculate() throws Exception {
        CalcWeightSustain calcOverVal = new CalcWeightSustain(Double.MAX_VALUE);
        assertTrue(calcOverVal.calculate())
    }
}