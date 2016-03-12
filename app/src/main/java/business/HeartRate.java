package business;

/**
 * Created by Wilson on 3/11/2016.
 */

/*
Heart rate information found at:
http://www.heart.org/HEARTORG/HealthyLiving/PhysicalActivity/FitnessBasics/Target-Heart-Rates_UCM_434341_Article.jsp#.VuLNt5wrK9I
 */

public class HeartRate implements Calculator {
    double lowerRate;
    double upperRate;
    int max;
    final int MAX_HR = 220;
    final double LOWER_MULTIPLIER = 0.5;
    final double UPPER_MULTIPLIER = 0.85;


    @Override
    public int calculate() {
        max = MAX_HR - UserInfo.getAge();
        lowerRate = max * LOWER_MULTIPLIER;
        upperRate = max * UPPER_MULTIPLIER;

        return 0;
    }

    public double getLowerRate() {
        return lowerRate;
    }

    public double getUpperRate() {
        return upperRate;
    }
}