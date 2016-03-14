package business;


/*
Information from:
http://www.calculator.net/calorie-calculator.html

Calculates how much calories per day to intake in order to reach goals from goals submission
NOTE: does not take into account user's physical activity levels from CalcWeightSustain class yet
 */

public class CalcCaloriesPerDay implements Calculator, ApplicationConstants {
    private final static int CALORIES_PER_POUND = 3500;
    private final static int DAYS_PER_WEEK = 7;
    private GoalsAccess goals;
    private UserDataAccess user;
    private CalcWeightSustain calcWeightSustain;
    private int targetWeight;
    private int targetWeeks;
    private int weight;
    private int difference;
    private double weightSustainValue;
    private int targetCalories;

    public CalcCaloriesPerDay(GoalsAccess goals, int targetWeight, int targetWeeks, int weight, double weightSustainValue, int targetCalories) {
        this.goals = goals;
        this.targetWeight = targetWeight;
        this.targetWeeks = targetWeeks;
        this.weight = weight;
        this.weightSustainValue = weightSustainValue;
        this.targetCalories = targetCalories;
    }

    public CalcCaloriesPerDay(int targetWeight, int targetWeeks, int weight, double weightSustainValue, int targetCalories) {
        this.targetWeight = targetWeight;
        this.targetWeeks = targetWeeks;
        this.weight = weight;
        this.difference = difference;
        this.weightSustainValue = weightSustainValue;
        this.targetCalories = targetCalories;
    }

    @Override
    public double calculate() {
        goals = new GoalsAccess();
        user = new UserDataAccess();
        calcWeightSustain = new CalcWeightSustain();

        targetWeight = goals.getTargetWeight();
        targetWeeks = goals.getTargetWeeks();
        weight = user.getWeight();

        //check if the weights are in a valid range
        if (weight == 0 || weight < MIN_WEIGHT || weight > MAX_WEIGHT ||
                targetWeight < MIN_WEIGHT || targetWeight > MAX_WEIGHT ||
                targetWeeks < MIN_WEEKS || targetWeeks > MAX_WEEKS  ||
                targetCalories < MIN_CALORIES || targetCalories > MAX_CALORIES) {
            return BAD_CALCULATION;
        }

        weightSustainValue = calcWeightSustain.calculate();

        difference = Math.abs(weight - targetWeight);
        targetCalories = difference * CALORIES_PER_POUND;
        targetCalories = targetCalories / targetWeeks;
        targetCalories = targetCalories / DAYS_PER_WEEK;

        if (weight > targetWeight) {
            if (targetCalories > 0)
                targetCalories = targetCalories * (-1);
        } else {
            if (targetCalories < 0)
                targetCalories = targetCalories * (-1);
        }

        targetCalories = targetCalories + (int) weightSustainValue;

        return targetCalories;
    }
}

