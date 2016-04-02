package business;


import java.util.ArrayList;

public class StatsDataAccess implements ApplicationConstants {
    ////////////////////Given the name of a nutrient ex "fat" return a list of all foods that contribute to
    ////////////////////that nutrient , how many grams they contribute each, and what units these measurments are in
    ///////////////////mode 0 returns a list for the day, mode 1 for the week, mode 2 for the month
    //////////////////////////EX(0, "fat"){ returns all foods that contributed to fat for the day..
    ArrayList<StatsFood> day;
    ArrayList<StatsFood> week;
    ArrayList<StatsFood> month;
    String[] allNutrientsDay;
    float[] allValuesDay;
    String[] allUnitsDay;
    String[] allNutrientsWeek;
    float[] allValuesWeek;
    String[] allUnitsWeek;
    String[] allNutrientsMonth;
    float[] allValuesMonth;
    String[] allUnitsMonth;

    StatsDataAccess(){
        day = new ArrayList<StatsFood>();
        week = new ArrayList<StatsFood>();
        month = new ArrayList<StatsFood>();
        populateFromDataBase();
        mergeData();
    }
    private void populateFromDataBase(){
        /// this will need to be replaced with a for loop that add each element from each food eaten

        //For loop adds all content for day, add all contents to food, then push to list, iterate over all foods in transactional database
        StatsFoodData food = new StatsFoodData();
        food.addFoodData("Cholesterol",5,"mg");
        food.addFoodData("Sodium",10,"mg");
        food.addFoodData("Sugar",15,"g");
        food.addFoodData("Protein",30,"g");
        day.add(new StatsFood("Chocolate", food));

        food = new StatsFoodData();
        food.addFoodData("Fat",40,"g");
        food.addFoodData("Fiber",20,"g");
        food.addFoodData("Calcium",10,"g");
        food.addFoodData("Carbs",60,"g");
        day.add(new StatsFood("Pizza", food));

        food = new StatsFoodData();
        food.addFoodData("Cholesterol",47,"mg");
        food.addFoodData("Sodium",100,"mg");
        food.addFoodData("Sugar",267,"g");
        food.addFoodData("Protein",209,"g");
        food.addFoodData("Fat",1200,"g");
        food.addFoodData("Fiber",68,"g");
        food.addFoodData("Calcium",12,"g");
        food.addFoodData("Carbs", 5004, "g");
        week.add(new StatsFood("StirFry", food)); // yes this dude ate nothign but stir fry for a week

        food = new StatsFoodData();
        food.addFoodData("Cholesterol", 1233, "mg");
        food.addFoodData("Sodium",1116,"mg");
        food.addFoodData("Sugar",1500,"g");
        food.addFoodData("Protein",3000,"g");
        food.addFoodData("Fat",4000,"g");
        food.addFoodData("Fiber",200,"g");
        food.addFoodData("Calcium",100,"g");
        food.addFoodData("Carbs",18000,"g");
        month.add(new StatsFood("Burger", food)); // yes this dude ate nothign but burgers for a month
    }
    public String[] getFoods(int mode, String nutrient) {
        ArrayList<String> foods = new  ArrayList<String>();
        if(mode == 0) {
            for (int x = 0; x < day.size(); x++) {
                String temp;
                if ((temp = day.get(x).hasNutrient(nutrient)) != null){
                    foods.add(day.get(x).foodName + ": " + temp);
                }
            }
        }else if(mode == 1){
            for (int x = 0; x < week.size(); x++) {
                String temp;
                if ((temp = week.get(x).hasNutrient(nutrient)) != null){
                    foods.add(week.get(x).foodName + ": " + temp);
                }
            }
        }
        else {
            if (mode == 0) {
                for (int x = 0; x < month.size(); x++) {
                    String temp;
                    if ((temp = month.get(x).hasNutrient(nutrient)) != null) {
                        foods.add(month.get(x).foodName + ": " + temp);
                    }
                }
            }
        }
        return toStringl(foods);
    }



    public String[] getNutrientsNames(int mode) {
        String[] names;
        if(mode == 0) {
            names= allNutrientsDay;
        }else if(mode == 1){
            names= allNutrientsWeek;
        }
        else{
            names= allNutrientsMonth;
        }
        return names;
    }

    public float[] getNutrientsValues(int mode) {
        float[] values;
        if(mode == 0) {
            values = allValuesDay;
        }else if(mode == 1){
            values = allValuesWeek;
        }
        else{
            values = allValuesMonth;
        }
        return values;
    }

    public String[] getNutrientsUnits(int mode) {
        String[] units;
        if(mode == 0) {
            units= allUnitsDay;
        }else if(mode == 1){
            units= allUnitsWeek;
        }
        else{
            units= allUnitsMonth;
        }
        return units;
    }
    public void  mergeData(){
        ArrayList<String> nutrientsDay = new ArrayList<String>();
        ArrayList<Float> valuesDay = new ArrayList<Float>();
        ArrayList<String> unitsDay = new ArrayList<String>();
        for(int x =0; x< day.size();x++){
            ArrayList<String> n = day.get(x).foodData.getNutrients();
            ArrayList<Float> v = day.get(x).foodData.getValues();
            ArrayList<String> u = day.get(x).foodData.getUnits();
            for(int a = 0; a<n.size();a++){
                if(nutrientsDay.contains(n.get(a))){
                    int index = nutrientsDay.indexOf(n.get(a));
                    valuesDay.set(index, valuesDay.get(index) + v.get(a));
                }
                else{
                    nutrientsDay.add(n.get(a));
                    valuesDay.add(v.get(a));
                    unitsDay.add(u.get(a));
                }
            }
        }
        ArrayList<String> nutrientsWeek = new ArrayList<String>();
        ArrayList<Float> valuesWeek = new ArrayList<Float>();
        ArrayList<String> unitsWeek = new ArrayList<String>();
        for(int x =0; x< week.size();x++){
            ArrayList<String> n = week.get(x).foodData.getNutrients();
            ArrayList<Float> v = week.get(x).foodData.getValues();
            ArrayList<String> u = week.get(x).foodData.getUnits();
            for(int a = 0; a<n.size();a++){
                if(nutrientsWeek.contains(n.get(a))){
                    int index = nutrientsWeek.indexOf(n.get(a));
                    valuesWeek.set(index, valuesWeek.get(index) + v.get(a));
                }
                else{
                    nutrientsWeek.add(n.get(a));
                    valuesWeek.add(v.get(a));
                    unitsWeek.add(u.get(a));
                }
            }
        }
        ArrayList<String> nutrientsMonth = new ArrayList<String>();
        ArrayList<Float> valuesMonth = new ArrayList<Float>();
        ArrayList<String> unitsMonth = new ArrayList<String>();
        for(int x =0; x< month.size();x++){
            ArrayList<String> n = month.get(x).foodData.getNutrients();
            ArrayList<Float> v = month.get(x).foodData.getValues();
            ArrayList<String> u = month.get(x).foodData.getUnits();
            for(int a = 0; a<n.size();a++){
                if(nutrientsMonth.contains(n.get(a))){
                    int index = nutrientsMonth.indexOf(n.get(a));
                    valuesMonth.set(index, valuesMonth.get(index) + v.get(a));
                }
                else{
                    nutrientsMonth.add(n.get(a));
                    valuesMonth.add(v.get(a));
                    unitsMonth.add(u.get(a));
                }
            }
        }
        allNutrientsDay = toStringl(nutrientsDay);
        allValuesDay = toFloat(valuesDay);
        allUnitsDay = toStringl(unitsDay);
        allNutrientsWeek= toStringl(nutrientsWeek);
        allValuesWeek = toFloat(valuesDay);
        allUnitsWeek = toStringl(unitsWeek);
        allNutrientsMonth= toStringl(nutrientsMonth);
        allValuesMonth = toFloat(valuesDay);
        allUnitsMonth = toStringl(unitsMonth);
    }
    public float[] toFloat(ArrayList<Float> list){
        float[] newList = new float[list.size()];
        for(int x = 0; x< list.size(); x++){
            newList[x] = list.get(x);
        }
        return newList;
    }
    public String[] toStringl(ArrayList<String> list){
        String[] newList = new String[list.size()];
        for(int x = 0; x< list.size(); x++){
            newList[x] = list.get(x);
        }
        return newList;
    }
}
