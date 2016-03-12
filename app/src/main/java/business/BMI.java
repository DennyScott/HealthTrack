package business;

/**
 * Created by Wilson on 3/11/2016.
 */
/*
 Calculates body mass index which is weight in kg divided by height in m^2
  */
public class BMI implements Calculator {
    double bmiDouble;
    int bmi;

    @Override
    public int calculate() {
        double weight;
        double height;
        weight = UserInfo.getWeight();
        height = UserInfo.getHeight();
        /*
        TO DO: convert weight to kg and height to m^2
         */
        bmiDouble = weight/height;
        bmi = (int)bmiDouble;

        return 0;
    }

    public int getBmi() {
        return bmi;
    }
}
