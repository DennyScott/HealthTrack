package business;

import java.util.Map;

/**
 * Created by scott on 02/03/16.
 */
public class Food implements  ApplicationConstants {
    int FoodID;
    int FoodGroupID;
    String FoodDescription;
    String FoodScientificName;
    Map<Nutrient, Double> NutrientList;
}
