package business;

import junit.framework.TestCase;

import business.CalcBMR;

/**
 * Created by Robby on 3/13/2016.
 */
public class CalcBMRTest extends TestCase {
    //testing weight
    testNegativeWeightMale ;
    testNegativeWeightFemale ;

    testZeroWeightMale ;
    testZeroWeightFemale ;

    testMaxWeightMale ;
    testMaxWeightFemale ;

    testMinWeightMale ;
    testMinWeightFemale ;

    //Testing height
    testNegativeHeightMale ;
    testNegativeHeightFemale ;

    testZeroHeightMale ;
    testZeroHeightFemale ;

    testMaxHightMale ;
    testMaxHeightFemale ;

    testMinHeightMale ;
    testMinHeightFemale ;

    //testing age
    testNegativeAgeMale ;
    testNegativeAgeFemale ;

    testZeroAgeMale ;
    testZeroAgeFemale ;

    testMaxAgeMale ;
    testMaxAgeFemale ;

    testMinAgeMale ;
    testMinAgeFemale ;
    public void setUp() throws Exception {
        super.setUp();
        //testing weight
        testNegativeWeightMale = new CalcBMR(-1,10,10,CalcBMR.MALE_CONST);
        testNegativeWeightFemale = new CalcBMR(-1,10,10,CalcBMR.FEMALE_CONST);

        testZeroWeightMale = new CalcBMR(0,10,10,CalcBMR.MALE_CONST);
        testZeroWeightFemale = new CalcBMR(0,10,10,CalcBMR.FEMALE_CONST);

        testMaxWeightMale = new CalcBMR(Double.MAX_VALUE, 10,10,CalcBMR.MALE_CONST);
        testMaxWeightFemale = new CalcBMR(Double.MAX_VALUE, 10,10,CalcBMR.FEMALE_CONST);

        testMinWeightMale = new CalcBMR(Double.MIN_VALUE, 10, 10, CalcBMR.MALE_CONST);
        testMinWeightFemale = new CalcBMR(Double.MIN_VALUE, 10, 10, CalcBMR.FEMALE_CONST);

        //Testing height
        testNegativeHeightMale = new CalcBMR(10,-1,10,CalcBMR.MALE_CONST);
        testNegativeHeightFemale = new CalcBMR(10,-1,10,CalcBMR.FEMALE_CONST);

        testZeroHeightMale = new CalcBMR(10,0,10,CalcBMR.MALE_CONST);
        testZeroHeightFemale = new CalcBMR(10,0,10,CalcBMR.FEMALE_CONST);

        testMaxHightMale = new CalcBMR(10,Double.MAX_VALUE,10,CalcBMR.MALE_CONST);
        testMaxHeightFemale = new CalcBMR(10,Double.MAX_VALUE,10,CalcBMR.FEMALE_CONST);

        testMinHeightMale = new CalcBMR(10,Double.MIN_VALUE, 10, CalcBMR.MALE_CONST);
        testMinHeightFemale = new CalcBMR(10, Double.MIN_VALUE, 10, CalcBMR.FEMALE_CONST);


        //testing age
        testNegativeAgeMale = new CalcBMR(10,10,-1,CalcBMR.MALE_CONST);
        testNegativeAgeFemale = new CalcBMR(10,10,-1,CalcBMR.FEMALE_CONST);

        testZeroAgeMale = new CalcBMR(10,10,0,CalcBMR.MALE_CONST);
        testZeroAgeFemale = new CalcBMR(10,10.0,CalcBMR.FEMALE_CONST);

        testMaxAgeMale = new CalcBMR(10,10,Double.MAX_VALUE,CalcBMR.MALE_CONST);
        testMaxAgeFemale = new CalcBMR(10,10,Double.MAX_VALUE,CalcBMR.FEMALE_CONST);

        testMinAgeMale = new CalcBMR(10,10,Double.MIN_VALUE, CalcBMR.MALE_CONST);
        testMinAgeFemale = new CalcBMR(10,10,Double.MIN_VALUE, CalcBMR.FEMALE_CONST);
        
    }

    public void tearDown() throws Exception {
        //testing weight
        testNegativeWeightMale = null;
        testNegativeWeightFemale = null;

        testZeroWeightMale = null;
        testZeroWeightFemale = null;

        testMaxWeightMale = null;
        testMaxWeightFemale = null;

        testMinWeightMale = null;
        testMinWeightFemale = null;

        //Testing height
        testNegativeHeightMale = null;
        testNegativeHeightFemale = null;

        testZeroHeightMale = null;
        testZeroHeightFemale = null;

        testMaxHightMale = null;
        testMaxHeightFemale = null;

        testMinHeightMale = null;
        testMinHeightFemale = null;

        //testing age
        testNegativeAgeMale = null;
        testNegativeAgeFemale = null;

        testZeroAgeMale = null;
        testZeroAgeFemale = null;

        testMaxAgeMale = null;
        testMaxAgeFemale = null;

        testMinAgeMale = null;
        testMinAgeFemale = null;

    }

    public void testCalculate() throws Exception {
        //testing weight
        assertTrue("Testing NegativeWeight for Males",(testNegativeWeightMale.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing NegativeWeight for Females",(testNegativeWeightFemale.calculate() == ApplicationConstants.BAD_CALCULATION));

        assertTrue("Testing ZeroWeight for Males",(testZeroWeightMale.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing ZeroWeight for Females",(testZeroWeightFemale.calculate() == ApplicationConstants.BAD_CALCULATION));

        assertTrue("Testing MaxWeight for Males",(testMaxWeightMale.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing MaxWeight for Females",(testMaxWeightFemale.calculate() == ApplicationConstants.BAD_CALCULATION));

        assertTrue("Testing MinWeight for Males",(testMinWeightMale.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing MinWeight for Females",(testMinWeightFemale.calculate() == ApplicationConstants.BAD_CALCULATION));

        //Testing height
        assertTrue("Testing NegativeHeight for Males",(testNegativeHeightMale.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing NegativeHeight for Females",(testNegativeHeightFemale.calculate() == ApplicationConstants.BAD_CALCULATION));

        assertTrue("Testing ZeroHeight for Males",(testZeroHeightMale.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing ZeroHeight for Females",(testZeroHeightFemale.calculate() == ApplicationConstants.BAD_CALCULATION));

        assertTrue("Testing MaxHight for Males",(testMaxHightMale.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing MaxHeight for Females",(testMaxHeightFemale.calculate() == ApplicationConstants.BAD_CALCULATION));

        assertTrue("Testing MinHeight for Males",(testMinHeightMale.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing MinHeight for Females",(testMinHeightFemale.calculate() == ApplicationConstants.BAD_CALCULATION));


        //testing age
        assertTrue("Testing NegativeAge for Males",(testNegativeAgeMale.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing NegativeAge for Females",(testNegativeAgeFemale.calculate() == ApplicationConstants.BAD_CALCULATION));

        assertTrue("Testing ZeroAge for Males",(testZeroAgeMale.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing ZeroAge for Females",(testZeroAgeFemale.calculate() == ApplicationConstants.BAD_CALCULATION));

        assertTrue("Testing MaxAge for Males",(testMaxAgeMale.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing MaxAge for Females",(testMaxAgeFemale.calculate() == ApplicationConstants.BAD_CALCULATION));

        assertTrue("Testing MinAge for Males",(testMinAgeMale.calculate() == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing MinAge for Females",(testMinAgeFemale.calculate() == ApplicationConstants.BAD_CALCULATION));

    }
}