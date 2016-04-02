package business;

import java.util.ArrayList;

/**
 * Created by Khaled on 4/1/2016.
 */
public class StatsType {
    ArrayList<String> nutrientNames;
    ArrayList<String> units;
    ArrayList<Float> values;
    public StatsType(){
        nutrientNames = new ArrayList<String>();
        units = new ArrayList<String>();
        values = new ArrayList<Float>();
    }
    public void addFoodData(String nutriennt,float value, String unit){
        if(nutrientNames.contains(nutriennt)){
            int index = nutrientNames.indexOf(nutriennt);
            units.set(index,units.get(index)+unit);

        }
        else{
            nutrientNames.add(nutriennt);
            units.add(unit);
            values.add(value);
        }
    }
    public float[] getValues(){
        float[] vals = new float[values.size()];
        for(int x = 0; x <values.size();x++ ){
            vals[x] = values.get(x);
        }
        return vals;

    }
    public String[] getNutrients(){
        String[] nms = new String[nutrientNames.size()];
        for(int x = 0; x <nutrientNames.size();x++ ){
            nms[x] = nutrientNames.get(x);
        }
        return nms;
    }
    public String[] getUnits(){
        String[] unis = new String[units.size()];
        for(int x = 0; x <units.size();x++ ){
            unis[x] = units.get(x);
        }
        return unis;
    }

}
