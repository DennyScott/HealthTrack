package business;

import java.io.Serializable;


class Iron extends Nutrient implements Serializable, ApplicationConstants {
    private int amount;
    private String name;
    private GoalsAccess goals;

    public Iron()
    {
        name = "Iron";
        goals = new GoalsAccess();
        amount = 0;
    }

    public int getGoalAmount() {
        return goals.getTargetIron();
    }

    public int getDailyAmount() {
        amount = goals.getTargetIron();
        if (goals.getTargetWeeks()>0) {
            amount = amount / (goals.getTargetWeeks()*7);
        }
        return amount;
    }

    public String getName() {
        return name;
    }
}
