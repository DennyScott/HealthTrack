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
public class WeightSustain implements Calculator, ApplicationConstants  {
    private final double EXTRA_ACTIVE = 1.9;
    private final double VERY_ACTIVE = 1.725;
    private final double MOD_ACTIVE = 1.55;
    private final double LIGHT_ACTIVE = 1.375;
    private final double VERY_LIGHT_ACTIVE = 1.2; // default AKA little to no exercise
    private final double NOT_ACTIVE = 1; // BMR value
    private double calories;

    @Override
    public double calculate() {
        BMR bmr = new BMR();

        calories = bmr.calculate() * VERY_LIGHT_ACTIVE;

        return calories;
    }

}
