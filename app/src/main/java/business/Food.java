package business;

import java.util.Map;


public class Food implements  ApplicationConstants {
    private int FoodID;
    private int FoodGroupID;
    private String FoodDescription;
    private String FoodScientificName;
    private Map<Nutrient, Double> NutrientList;

    public int getFoodID() {
        return FoodID;
    }

    public void setFoodID(int foodID) {
        FoodID = foodID;
    }

    public int getFoodGroupID() {
        return FoodGroupID;
    }

    public void setFoodGroupID(int foodGroupID) {
        FoodGroupID = foodGroupID;
    }

    public String getFoodDescription() {
        return FoodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        FoodDescription = foodDescription;
    }

    public String getFoodScientificName() {
        return FoodScientificName;
    }

    public void setFoodScientificName(String foodScientificName) {
        FoodScientificName = foodScientificName;
    }

    public Map<Nutrient, Double> getNutrientList() {
        return NutrientList;
    }

    public void setNutrientList(Map<Nutrient, Double> nutrientList) {
        NutrientList = nutrientList;
    }
}
