package business;

/**
 * Created by Wilson on 3/11/2016.
 */
/*
 Calculates body mass index which is weight in kg divided by height in m^2
  */
public class CalcBMI implements Calculator, ApplicationConstants {
    double bmi;
    double weight;
    double height;

    UserDataAccess user = new UserDataAccess();

    @Override
    public double calculate() {
        UnitConverter uc = new UnitConverter();

        weight = uc.lbToKg(user.getWeight());
        height = uc.cmToM2(user.getHeight());

        bmi = weight/height;
        bmi = Math.round(bmi*10)/10.0;

        return bmi;
    }
}
