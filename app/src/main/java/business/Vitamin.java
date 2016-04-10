package business;

import java.io.Serializable;


class Vitamin extends Nutrient implements Serializable, ApplicationConstants {
    private int amount;
    private String name;
    private GoalsAccess goals;

    public Vitamin()
    {
        name = "Vitamin";
        goals = new GoalsAccess();
        amount = 0;
    }

    public int getDailyAmount() {
        amount = goals.getTargetVitamin();
        if (goals.getTargetWeeks()>0) {
            amount = amount / (goals.getTargetWeeks()*7);
        }
        return amount;
    }

    public String getName() {
        return name;
    }
}
