package business;

import java.io.Serializable;

/*
Default = calories
 */

class Nutrient implements Serializable, ApplicationConstants {
    private int amount;
    private String name;
    private GoalsAccess goals;

    public Nutrient() {
        amount = 0;
        name = "Calories";
        goals = new GoalsAccess();
    }

    public int getGoalAmount() {
        return goals.getTargetWeight();
    }

    public int getDailyAmount() {
        CalcCaloriesPerDay ccpd = new CalcCaloriesPerDay();
        amount = (int) ccpd.calculate();
        return amount;
    }

    public String getName() {
        return name;
    }
}
