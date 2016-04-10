package business;

import java.io.Serializable;


class Fat extends Nutrient implements Serializable, ApplicationConstants {
    private int amount;
    private String name;
    private GoalsAccess goals;

    public Fat()
    {
        name = "Fat";
        goals = new GoalsAccess();
        amount = 0;
    }

    public int getGoalAmount() {
        return goals.getTargetFat();
    }

    public int getDailyAmount() {
        amount = goals.getTargetFat();
        if (goals.getTargetWeeks()>0) {
            amount = amount / (goals.getTargetWeeks()*7);
        }
        return amount;
    }

    public String getName() {
        return name;
    }
}

