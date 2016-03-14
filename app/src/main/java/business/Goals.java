package business;

import java.io.Serializable;


class Goals implements Serializable, ApplicationConstants {
    private int targetWeight;
    private int targetWeeks;
    private boolean set;
    Goals(){
        setSet(false);
        setTargetWeight(0);
        setTargetWeeks(0);
    }

    public int getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(int targetWeight) {
        this.targetWeight = targetWeight;
    }

    public int getTargetWeeks() {
        return targetWeeks;
    }

    public void setTargetWeeks(int targetWeeks) {
        this.targetWeeks = targetWeeks;
    }

    public boolean isSet() {
        return set;
    }

    public void setSet(boolean set) {
        this.set = set;
    }
}
