package business;

import java.io.Serializable;


class Carbohydrate extends Nutrient implements Serializable, ApplicationConstants {
    private int amount;
    private String name;
    private GoalsAccess goals;

    public Carbohydrate()
    {
        name = "Carbohydrate";
        goals = new GoalsAccess();
        amount = 0;
    }

    public int getGoalAmount() {
        return goals.getTargetCarbs();
    }

    public int getDailyAmount() {
        amount = goals.getTargetCarbs();
        if (goals.getTargetWeeks()>0) {
            amount = amount / (goals.getTargetWeeks()*7);
        }
        return amount;
    }

    public String getName() {
        return name;
    }
}

