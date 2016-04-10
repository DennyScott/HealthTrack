package business;

import java.io.Serializable;


class Sodium extends Nutrient implements Serializable, ApplicationConstants {
    private int amount;
    private String name;
    private GoalsAccess goals;

    public Sodium()
    {
        name = "Sodium";
        goals = new GoalsAccess();
        amount = 0;
    }

    public int getGoalAmount() {
        return goals.getTargetSodium();
    }

    public int getDailyAmount() {
        amount = goals.getTargetSodium();
        if (goals.getTargetWeeks()>0) {
            amount = amount / (goals.getTargetWeeks()*7);
        }
        return amount;
    }

    public String getName() {
        return name;
    }
}
