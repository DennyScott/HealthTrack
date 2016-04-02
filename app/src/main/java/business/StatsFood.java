package business;

/**
 * Created by Khaled on 4/1/2016.
 */
public class StatsFood {
    String foodName;
    StatsFoodData foodData;
    public StatsFood(String foodName, StatsFoodData foodData){
        this.foodName =foodName;
        this.foodData = foodData;
    }
    public String hasNutrient(String nutriennt){
        return foodData.hasNutrient(nutriennt);
    }

}
