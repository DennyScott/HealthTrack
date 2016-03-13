package business;

/**
 * Created by Wilson on 3/12/2016.
 */
/*
Information from:
http://www.calculator.net/calorie-calculator.html

Calculates how much calories per day to intake in order to reach goals from goals submission
 */

public class CaloriesPerDay implements Calculator {
    private GoalsAccess goals;
    private UserDataAccess user;
    private BMR bmr;
    private int targetWeight;
    private int targetWeeks;
    private int weight;
    private int difference;
    private double bmrValue;
    private int targetCalories; // calories per day to gain/lose to reach goal

    private int CALORIES_PER_POUND = 3500;
    private int DAYS_PER_WEEK = 7;

    @Override
    public double calculate() {
        goals = new GoalsAccess();
        user = new UserDataAccess();
        bmr = new BMR();

        targetWeight = goals.getTargetWeight();
        targetWeeks = goals.getTargetWeeks();
        weight = user.getWeight();
        difference = Math.abs(weight-targetWeight);
        targetCalories = difference * CALORIES_PER_POUND;
        targetCalories = targetCalories / targetWeeks;
        targetCalories = targetCalories / DAYS_PER_WEEK;

        // if user wants to lose weight
        if (weight > targetWeight) {
            if (targetCalories > 0)
                targetCalories = targetCalories * (-1);
        }
        // if user wants to gain weight
        else {
            if (targetCalories < 0)
                targetCalories = targetCalories * (-1);
        }

        bmrValue = bmr.calculate();
        targetCalories = targetCalories + (int)bmrValue;

        return targetCalories;
    }
}

