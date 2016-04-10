package business;

import java.io.Serializable;


class Nutrient implements Serializable, ApplicationConstants {
    private int amount;
    private String name;

    public Nutrient() {
        amount = 0;
        name = "Nutrient";
    }

    public int getDailyAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
