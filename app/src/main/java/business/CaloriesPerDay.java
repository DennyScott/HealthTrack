package business;

/**
 * Created by Wilson on 3/12/2016.
 */
/*
Information from:
http://www.calculator.net/calorie-calculator.html

Calculates how much calories per day to intake in order to reach goals from goals submission
NOTE: does not take into account user's physical activity levels from WeightSustain class yet
 */

public class CaloriesPerDay implements Calculator {
    private GoalsAccess goals;
    private UserDataAccess user;
    private WeightSustain weightSustain;
    private int targetWeight;
    private int targetWeeks;
    private int weight;
    private int difference;
    private double weightSustainValue;
    private int targetCalories;

    private int CALORIES_PER_POUND = 3500;
    private int DAYS_PER_WEEK = 7;

    @Override
    public double calculate() {
        goals = new GoalsAccess();
        user = new UserDataAccess();
        weightSustain = new WeightSustain();

        targetWeight = goals.getTargetWeight();
        targetWeeks = goals.getTargetWeeks();
        weight = user.getWeight();
        weightSustainValue = weightSustain.calculate();
        difference = Math.abs(weight-targetWeight);
        targetCalories = difference * CALORIES_PER_POUND;
        targetCalories = targetCalories / targetWeeks;
        targetCalories = targetCalories / DAYS_PER_WEEK;

        if (weight > targetWeight) {
            if (targetCalories > 0)
                targetCalories = targetCalories * (-1);
        }
        else {
            if (targetCalories < 0)
                targetCalories = targetCalories * (-1);
        }

        targetCalories = targetCalories + (int)weightSustainValue;

        return targetCalories;
    }
}

