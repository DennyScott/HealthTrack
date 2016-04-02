package business;


public class StatsDataAccess implements ApplicationConstants {
    ////////////////////Given the name of a nutrient ex "fat" return a list of all foods that contribute to
    ////////////////////that nutrient , how many grams they contribute each, and what units these measurments are in
    ///////////////////mode 0 returns a list for the day, mode 1 for the week, mode 2 for the month
    //////////////////////////EX(0, "fat"){ returns all foods that contributed to fat for the day..
    StatsType day;
    StatsType week;
    StatsType month;
    StatsDataAccess(){
        day = new StatsType();
        week = new StatsType();
        month = new StatsType();
        populateFromDataBase();
    }
    private void populateFromDataBase(){
        /// this will need to be replaced with a for loop that add each element from each food eaten
        day.addFoodData("Cholesterol",5,"mg");
        day.addFoodData("Sodium",10,"mg");
        day.addFoodData("Sugar",15,"g");
        day.addFoodData("Protein",30,"g");
        day.addFoodData("Fat",40,"g");
        day.addFoodData("Fiber",20,"g");
        day.addFoodData("Calcium",10,"g");
        day.addFoodData("Carbs",60,"g");

        week.addFoodData("Cholesterol",47,"mg");
        week.addFoodData("Sodium",100,"mg");
        week.addFoodData("Sugar",267,"g");
        week.addFoodData("Protein",209,"g");
        week.addFoodData("Fat",1200,"g");
        week.addFoodData("Fiber",68,"g");
        week.addFoodData("Calcium",12,"g");
        week.addFoodData("Carbs",5004,"g");

        month.addFoodData("Cholesterol",1233,"mg");
        month.addFoodData("Sodium",1116,"mg");
        month.addFoodData("Sugar",1500,"g");
        month.addFoodData("Protein",3000,"g");
        month.addFoodData("Fat",4000,"g");
        month.addFoodData("Fiber",200,"g");
        month.addFoodData("Calcium",100,"g");
        month.addFoodData("Carbs",18000,"g");
    }
    public static String[] getFoodNames(int mode, String nutrient) {
        // Have it return actual data..
        return new String[]{"Chocolate bar", "coffee", "cake", "pizza"};
    }

    public float[] getFoodValues(int mode, String nutrient) {
        //wight of fat
        return new float[]{38, 28, 56, 128};
    }

    public String[] getFoodUnits(int mode, String nutrient) {
        return new String[]{"grams", "grams", "grams", "grams"};
    }

    public String[] getNutrientsNames(int mode) {
        String[] names;
        if(mode == 0) {
            names= day.getNutrients();
        }else if(mode == 1){
            names= week.getNutrients();
        }
        else{
            names= month.getNutrients();
        }
        return names;
    }

    public float[] getNutrientsValues(int mode) {
        float[] values;
        if(mode == 0) {
            values = day.getValues();
        }else if(mode == 1){
            values = week.getValues();
        }
        else{
            values = month.getValues();
        }
        return values;
    }

    public String[] getNutrientsUnits(int mode) {
        String[] units;
        if(mode == 0) {
            units= day.getUnits();
        }else if(mode == 1){
            units= week.getUnits();
        }
        else{
            units=  month.getUnits();
        }
        return units;
    }
}
