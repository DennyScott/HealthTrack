package business;

import java.io.Serializable;


class Protein extends Nutrient implements Serializable, ApplicationConstants {
    private int amount;
    private String name;
    private GoalsAccess goals;

    public Protein()
    {
        name = "Protein";
        goals = new GoalsAccess();
        amount = 0;
    }

    public int getDailyAmount() {
        amount = goals.getTargetProtein();
        if (goals.getTargetWeeks()>0) {
            amount = amount / (goals.getTargetWeeks()*7);
        }
        return amount;
    }

    public String getName() {
        return name;
    }
}
