package business;

/**
 * Created by Wilson on 3/12/2016.
 */
public class WeightSustain implements Calculator {
    private final double EXTRA_ACTIVE = 1.9;
    private final double VERY_ACTIVE = 1.725;
    private final double MOD_ACTIVE = 1.55;
    private final double LIGHT_ACTIVE = 1.375;
    private final double VERY_LIGHT_ACTIVE = 1.2;
    private final double NOT_ACTIVE = 1; // default
    private double calories;

    @Override
    public double calculate() {
        BMR bmr = new BMR();

        calories = bmr.calculate() * NOT_ACTIVE;

        return calories;
    }

}
