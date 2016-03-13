package business;

/**
 * Created by Wilson on 3/11/2016.
 */

/*
Heart rate information found at:
http://www.heart.org/HEARTORG/HealthyLiving/PhysicalActivity/FitnessBasics/Target-Heart-Rates_UCM_434341_Article.jsp#.VuLNt5wrK9I

Can calculate max heart rate, optimal heart rate for moderate activites, and optimal heart rate for high intensity (hard) activites
 */

public class HeartRate implements Calculator {
    double lowerBound;
    double upperBound;
    int max;
    final int MAX_HR = 220;
    final double MOD_LOWER_BOUND = 0.5;
    final double MOD_UPPER_BOUND = 0.69;
    final double HARD_LOWER_BOUND = 0.7;
    final double HARD_UPPER_BOUND = 0.89;
    UserDataAccess user = new UserDataAccess();

    @Override
    public double calculate() {
        max = MAX_HR - user.getAge();

        return max;
    }

    public int getModLowerRate() {
        lowerBound = calculate() * MOD_LOWER_BOUND;
        return (int) Math.round(lowerBound);
    }

    public int getModUpperRate() {
        upperBound = calculate() * MOD_UPPER_BOUND;
        return (int) Math.round(upperBound);
    }

    public int getHardLowerRate() {
        lowerBound = calculate() * HARD_LOWER_BOUND;
        return (int) Math.round(lowerBound);
    }

    public int getHardUpperRate() {
        upperBound = calculate() * HARD_UPPER_BOUND;
        return (int) Math.round(upperBound);
    }
}
