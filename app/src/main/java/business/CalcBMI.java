package business;

/**
 * Created by Wilson on 3/11/2016.
 */
/*
 Calculates body mass index which is weight in kg divided by height in m^2
  */
public class CalcBMI implements Calculator, ApplicationConstants {
    private double bmi;
    private double weight;
    private double height;

    private UserDataAccess user;

    public CalcBMI(double bmi, double weight, double height, UserDataAccess user) {
        this.bmi = bmi;
        this.weight = weight;
        this.height = height;
        this.user = new UserDataAccess();
    }

    @Override

    public double calculate() {
        UnitConverter uc = new UnitConverter();

        setWeight(uc.lbToKg(getUser().getWeight()));
        setHeight(uc.cmToM2(getUser().getHeight()));

        setBmi(getWeight() / getHeight());
        setBmi(Math.round(getBmi() *10)/10.0);

        return getBmi();
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
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

    public UserDataAccess getUser() {
        return user;
    }

    public void setUser(UserDataAccess user) {
        this.user = user;
    }
}
