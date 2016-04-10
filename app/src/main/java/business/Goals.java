package business;

import java.io.Serializable;


class Goals implements Serializable, ApplicationConstants {
    private int targetWeight;
    private int targetWeeks;
    private int fat;
    private int carbs;
    private int protein;
    private int sodium;
    private int potassium;
    private int fiber;
    private int iron;
    private int vitamin;
    private boolean set;

    Goals() {
        setSet(false);
        setTargetWeight(0);
        setTargetWeeks(0);
        setFat(0);
        setCarbs(0);
        setProtein(0);
        setSodium(0);
        setPotassium(0);
        setFiber(0);
        setIron(0);
        setVitamin(0);
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

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPotassium() {
        return potassium;
    }

    public void setPotassium(int potassium) {
        this.potassium = potassium;
    }

    public int getFiber() {
        return fiber;
    }

    public void setFiber(int fiber) {
        this.fiber = fiber;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getVitamin() {
        return vitamin;
    }

    public void setVitamin(int vitamin) {
        this.vitamin = vitamin;
    }

    public boolean isSet() {
        return set;
    }

    public void setSet(boolean set) {
        this.set = set;
    }
}
