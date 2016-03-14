package business;

import junit.framework.TestCase;

import business.CalcCaloriesPerDay;

/**
 * Created by Robby on 3/13/2016.
 */
public class CalcCaloriesPerDayTest extends TestCase {

    public void testCalculate() throws Exception {
        //testing weights
        CalcCaloriesPerDay testNegativeWeight = new CalcCaloriesPerDay(-1,10,10,10,10);
        CalcCaloriesPerDay testZeroWeight = new CalcCaloriesPerDay(0,10,10,10);
        CalcCaloriesPerDay testMaxWeight = new CalcCaloriesPerDay(Integer.MAX_VALUE,10,10,10);
        CalcCaloriesPerDay testMinWeight = new CalcCaloriesPerDay(Integer.MIN_VALUE,10,10,10);

        //testing target weights
        CalcCaloriesPerDay testNegativeTargetWeights = new CalcCaloriesPerDay(10,-1,10,10,10);
        CalcCaloriesPerDay testZeroTargetWeights = new CalcCaloriesPerDay(10,0,10,10,10);
        CalcCaloriesPerDay testMaxTargetWeights = new CalcCaloriesPerDay(10,Integer.MAX_VALUE,10,10,10);
        CalcCaloriesPerDay testMinTargetWeights = new CalcCaloriesPerDay(10,Integer.MIN_VALUE,10,10,10);
        
        //testing target weeks
        CalcCaloriesPerDay testNegativeTargetWeeks = new CalcCaloriesPerDay(10,10,-1,10, 10);
        CalcCaloriesPerDay testZeroTargetWeeks = new CalcCaloriesPerDay(10, 10,0,10,10);
        CalcCaloriesPerDay testMaxTargetWeeks = new CalcCaloriesPerDay(10,10,Integer.MAX_VALUE,10 ,10);
        CalcCaloriesPerDay testMinTargetWeeks = new CalcCaloriesPerDay(10,10,Integer.MIN_VALUE,10 ,10);

        //testing target calories
        CalcCaloriesPerDay testNegativeTargetCalories = new CalcCaloriesPerDay(10,10,10, 10,-1);
        CalcCaloriesPerDay testZeroTargetCalories = new CalcCaloriesPerDay(10,10,10, 10,0);
        CalcCaloriesPerDay testMaxTargetCalories = new CalcCaloriesPerDay(10,10,10 ,10,Integer.MAX_VALUE);
        CalcCaloriesPerDay testMinTargetCalories = new CalcCaloriesPerDay(10,10,10 ,10,Integer.MIN_VALUE);
        
        
        //now assert

        //testing weights
        assertTrue("Testing testNegativeWeight",(testNegativeWeight == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing testZeroWeight",(testZeroWeight == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing testMaxWeight",(testMaxWeight == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing testMinWeight",(testMinWeight == ApplicationConstants.BAD_CALCULATION));

        //testing target weights
        assertTrue("Testing testNegativeTargetWeights",(testNegativeTargetWeights == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing testZeroTargetWeights",(testZeroTargetWeights == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing testMaxTargetWeights",(testMaxTargetWeights == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing testMinTargetWeights",(testMinTargetWeights == ApplicationConstants.BAD_CALCULATION));

        //testing target weeks
        assertTrue("Testing testNegativeTargetWeeks",(testNegativeTargetWeeks == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing testZeroTargetWeeks",(testZeroTargetWeeks == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing testMaxTargetWeeks",(testMaxTargetWeeks == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing testMinTargetWeeks",(testMinTargetWeeks == ApplicationConstants.BAD_CALCULATION));

        //testing target calories
        assertTrue("Testing testNegativeTargetCalories",(testNegativeTargetCalories == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing testZeroTargetCalories",(testZeroTargetCalories == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing testMaxTargetCalories",(testMaxTargetCalories == ApplicationConstants.BAD_CALCULATION));
        assertTrue("Testing testMinTargetCalories",(testMinTargetCalories == ApplicationConstants.BAD_CALCULATION));




    }
}