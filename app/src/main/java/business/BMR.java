package business;

/**
 * Created by Wilson on 3/11/2016.
 */
/*
BMR is basal metabolic rate.
Formulas found here:
http://dailyburn.com/life/health/how-to-calculate-bmr/
It's the amount of calories your body burns in 24 hours while resting.
BMR = 10 x kg weight + 6.25 x cm height – 5 x age + z where z is 5 if male and (-161) if female
 */
public class BMR implements Calculator{
    double bmr;
    double weight;
    double height;
    int age;
    boolean isMale;
    final double WEIGHT_MULTIPLIER = 10;
    final double HEIGHT_MULTIPLIER = 6.25;
    final double AGE_MULTIPLIER = 5;
    final int MALE_CONST = 5;
    final int FEMALE_CONST = -161;

    @Override
    public int calculate() {
        weight = UserInfo.getWeight();
        height = UserInfo.getHeight();
        age = UserInfo.getAge();
        isMale = UserInfo.isMale();

        bmr = WEIGHT_MULTIPLIER * weight;
        bmr = bmr + HEIGHT_MULTIPLIER * height;
        bmr = bmr - AGE_MULTIPLIER * age;

        if (isMale)
            bmr = bmr + MALE_CONST;
        else
            bmr = bmr + FEMALE_CONST;

        return 0;
    }
}
