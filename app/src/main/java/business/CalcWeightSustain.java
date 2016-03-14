package business;

/**
 * Created by Wilson on 3/12/2016.
 */
/*
Information from:
http://www.calculator.net/calorie-calculator.html

Rough estimate of how many calories to consume per day to maintain weight.
Varies depending on physical activity level (due to loss of calories).
 */
public class CalcWeightSustain implements Calculator, ApplicationConstants  {
    private static final double EXTRA_ACTIVE = 1.9;
    private static final double VERY_ACTIVE = 1.725;
    private static final double MOD_ACTIVE = 1.55;
    private static final double LIGHT_ACTIVE = 1.375;
    private static final double VERY_LIGHT_ACTIVE = 1.2; // default AKA little to no exercise
    private static final double NOT_ACTIVE = 1; // CalcBMR value
    private double calories;

    public CalcWeightSustain() {

    }
    public CalcWeightSustain(double calories) {
        this.calories = calories;
    }

    @Override
    public double calculate() {
        CalcBMR calcBmr = new CalcBMR();

        calories = calcBmr.calculate() * VERY_LIGHT_ACTIVE;

        return calories;
    }

}
