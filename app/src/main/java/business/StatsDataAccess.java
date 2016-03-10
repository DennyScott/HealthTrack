package business;

/**
 * Created by Khaled on 3/9/2016.
 */
public class StatsDataAccess {
    ////////////////////Given the name of a nutrient ex "fat" return a list of all foods that contribute to
    ////////////////////that nutrient , how many grams they contribute each, and what units these measurments are in
    ///////////////////mode 0 returns a list for the day, mode 1 for the week, mode 2 for the month
    //////////////////////////EX(0, "fat"){ returns all foods that contributed to fat for the day..
    public static String[] getFoodNames(int mode, String nutrient ){
        // Have it return actual data..
       return new String[]{"Chocolate bar", "coffee", "cake", "pizza"};
    }
    public static float[] getFoodValues(int mode, String nutrient){
        //wight of fat
        return new float[] {38,28,56,128};
    }
    public static String[] getFoodUnits(int mode, String nutrient){
        return new String[] {"grams","grams","grams","grams"};
    }
    ////////////////////////////////////////////////////////////////////Get Nutrients Consumed given mode, again day/week/month = 0/1/2 today, last 7 days, last 30 days
    public static String[] getNutrientsNames(int mode){
        return new String[]{"Cholesterol", "Sodium", "Sugar", "Protein", "Fat", "Fiber", "Calcium", "Carbs"};
    }
    public static float[] getNutrientsValues(int mode){
        return new float[] {5, 10, 15, 30, 40, 20, 10, 60};
    }
    public static String[] getNutrientsUnits(int mode){
        return new String[] {"mg","mg","grams","grams","grams","grams","grams","grams"};
    }
    // The above is all that is needed to get stats fully operational
}