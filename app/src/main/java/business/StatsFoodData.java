package business;

import java.util.ArrayList;


public class StatsFoodData {
    ArrayList<String> nutrientNames;
    ArrayList<String> units;
    ArrayList<Float> values;

    public StatsFoodData() {
        nutrientNames = new ArrayList<String>();
        units = new ArrayList<String>();
        values = new ArrayList<Float>();
    }

    public void addFoodData(String nutrient, float value, String unit) {
        nutrientNames.add(nutrient);
        units.add(unit);
        values.add(value);
    }

    public ArrayList<Float> getValues() {
        return values;
    }

    public ArrayList<String> getNutrients() {
        return nutrientNames;
    }

    public ArrayList<String> getUnits() {
        return units;
    }

    public String hasNutrient(String nutriennt) {
        String ret = null;
        if (nutrientNames.contains(nutriennt)) {
            int index = nutrientNames.indexOf(nutriennt);
            ret = values.get(index) + " " + units.get(index);
        }
        return ret;

    }

}
