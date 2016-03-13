package business;

import club.glamajestic.healthtrack.MainActivity;

/**
 * Created by Wilson on 3/11/2016.
 */
/*
CalcBMR is basal metabolic rate.
Formulas found here:
http://dailyburn.com/life/health/how-to-calculate-bmr/

It's the amount of calories your body burns in 24 hours while resting.
CalcBMR = 10 x kg weight + 6.25 x cm height – 5 x age + z where z is 5 if male and (-161) if female
 */
public class CalcBMR implements Calculator, ApplicationConstants{
    double bmr;
    double weight; // kg
    double height; // cm
    int age;
    int gender;
    final double WEIGHT_MULTIPLIER = 10;
    final double HEIGHT_MULTIPLIER = 6.25;
    final double AGE_MULTIPLIER = 5;
    final int MALE_CONST = 5;
    final int FEMALE_CONST = -161;

    UserDataAccess user = new UserDataAccess();

    @Override
    public double calculate() {
        UnitConverter uc = new UnitConverter();

        weight = uc.lbToKg(user.getWeight());
        height = user.getHeight();
        age = user.getAge();
        gender = user.getGender();

        bmr = WEIGHT_MULTIPLIER * weight;
        bmr = bmr + HEIGHT_MULTIPLIER * height;
        bmr = bmr - AGE_MULTIPLIER * age;

        if (gender==0)
            bmr = bmr + MALE_CONST;
        else
            bmr = bmr + FEMALE_CONST;

        bmr = (int) Math.round(bmr);

        return bmr;
    }
}
