package business;

/**
 * Created by Wilson on 3/11/2016.
 */
/*
CalcBMR is basal metabolic rate.
Formulas found here:
http://dailyburn.com/life/health/how-to-calculate-bmr/

It's the amount of calories your body burns in 24 hours while resting.
CalcBMR = 10 x kg weight + 6.25 x cm height – 5 x age + z where z is 5 if male and (-161) if female
 */
public class CalcBMR implements Calculator, ApplicationConstants {
    private static final double WEIGHT_MULTIPLIER = 10;
    private static final double HEIGHT_MULTIPLIER = 6.25;
    private static final double AGE_MULTIPLIER = 5;
    public  static final int MALE_CONST = 5;
    public  static final int FEMALE_CONST = -161;
    private double bmr;
    private double weight; // kg
    private double height; // cm
    private int age;
    private int gender;
    private UserDataAccess user;

    public CalcBMR() {

    }

    public CalcBMR(double weight, double height, int age, int gender) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        this.user = new UserDataAccess();
    }

    @Override
    public double calculate() {

        //check if the weights are in a valid range
        if (weight == 0 || weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
            return BAD_CALCULATION;
        }

        UnitConverter uc = new UnitConverter();

        setWeight(uc.lbToKg(getUser().getWeight()));
        setHeight(getUser().getHeight());
        setAge(getUser().getAge());
        setGender(getUser().getGender());

        setBmr(getWEIGHT_MULTIPLIER() * getWeight());
        setBmr(getBmr() + getHEIGHT_MULTIPLIER() * getHeight());
        setBmr(getBmr() - getAGE_MULTIPLIER() * getAge());

        if (getGender() == 0)
            setBmr(getBmr() + getMALE_CONST());
        else
            setBmr(getBmr() + getFEMALE_CONST());

        setBmr((int) Math.round(getBmr()));

        return getBmr();
    }

    public double getBmr() {
        return bmr;
    }

    public void setBmr(double bmr) {
        this.bmr = bmr;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public double getWEIGHT_MULTIPLIER() {
        return WEIGHT_MULTIPLIER;
    }

    public double getHEIGHT_MULTIPLIER() {
        return HEIGHT_MULTIPLIER;
    }

    public double getAGE_MULTIPLIER() {
        return AGE_MULTIPLIER;
    }

    public int getMALE_CONST() {
        return MALE_CONST;
    }

    public int getFEMALE_CONST() {
        return FEMALE_CONST;
    }

    public UserDataAccess getUser() {
        return user;
    }

    public void setUser(UserDataAccess user) {
        this.user = user;
    }
}
