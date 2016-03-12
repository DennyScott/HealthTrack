package business;

/**
 * Created by Wilson on 3/11/2016.
 */
/*
 Calculates body mass index which is weight in kg divided by height in m^2
  */
public class BMI implements Calculator {
    double bmi;
    double weight;
    double height;
    final double LB_TO_KG = 0.453592;
    final double CM_TO_M = 0.01;

    UserDataAccess user = new UserDataAccess();

    @Override
    public double calculate() {
        weight = user.getWeight();
        weight = weight * LB_TO_KG;
        height = user.getHeight();
        height = height * CM_TO_M;
        height = height * height;
        /*
        TO DO: convert weight to kg and height to m^2
         */
        bmi = weight/height;
        bmi = Math.round(bmi*10)/10.0;

        return bmi;
    }
}
