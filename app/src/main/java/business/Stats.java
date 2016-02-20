package business;

import java.util.ArrayList;
import java.util.Collections;

public class Stats {
    private float[] values;
    private String[] keys;
    private float[] otherValues;
    private String[] otherKeys;
    private final int MAX_SIZE = 7;
    private int amountOfOtherData = 0;
    int mode;
    private int size =0;
    public void init(int mode){
        this.mode = mode;
        values = new float[MAX_SIZE];
        keys = new String[MAX_SIZE];
        if(amountOfOtherData > 0){
            otherValues = new float[amountOfOtherData];
            otherKeys = new String[amountOfOtherData];
        }
        float[] inValues = valuesFromDB(mode);
        String[] inKeys = keysFromDB(mode);
        size = sizeOf(inKeys,inValues);
        amountOfOtherData = size - (MAX_SIZE-1);
        ArrayList<KeyValuePair> kVP = sort(inValues,inKeys );
        if(size > MAX_SIZE) {
            populate(kVP);
        }
        else if(size <= MAX_SIZE && size > 0){
            values = inValues;
            keys = inKeys;
        }
        else{// if size is 0 or undefined return dummy item so that stats lib wont crash.
            values = new float[] {50,50};
            keys = new String[]{"Nothing","Nothing"};
        }

    }
    public  float[] getValues(){//mode here is day/week/month
        return values;
    }
    public  String[] getKeys(){
        return keys;
    }
    public  float[] getOtherValues(){//mode here is day/week/month
        return otherValues;
    }
    public  String[] getOtherKeys(){
        return otherKeys;
    }
    private void populate(ArrayList<KeyValuePair> array){
        for(int x = 0; x< MAX_SIZE-1; x++){
            values[x] = array.get(x).value;
            keys[x] = array.get(x).key;
        }
        if(amountOfOtherData > 0){
            for(int x = MAX_SIZE-1; x< size; x++){
                otherValues[x-(MAX_SIZE-1)] = array.get(x).value;
                otherKeys[x-(MAX_SIZE-1)] = array.get(x).key;
            }
            int otherTotal = 0;
            for(int x  =0 ; x< otherValues.length; x++){
                otherTotal += otherValues[x];
            }
            values[MAX_SIZE-1] = otherTotal;
            keys[MAX_SIZE-1] = "Other";
        }
    }
    private ArrayList<KeyValuePair> sort(float[] values, String[] keys){
        ArrayList<KeyValuePair> retVal = null;
        if(size > 0 && values.length == size && keys.length == size) {
            ArrayList<KeyValuePair> kVP = new ArrayList<KeyValuePair>();
            for (int x = 0; x < size; x++) {
                kVP.add(new KeyValuePair(keys[x], values[x]));
            }
            Collections.sort(kVP);
            retVal = kVP;
        }
        return retVal;
    }
    private String[] keysFromDB(int mode){
        String[] keys =  {"Cholesterol", "Sodium", "Sugar", "Protein", "Fat", "Fiber", "Calcium", "Carbs"};// these values will be attained from database later on
        return keys;
    }
    private float[] valuesFromDB(int mode){
        float[] values = {5, 10, 15, 30, 40, 20, 10, 60};// these values will be attained from database later on
        return values;
    }
    private int sizeOf(String[] keys, float[] values){// finds smallest of two list sizes incase of mistake in DB code.
        int returnVal;
        int val1 = keys.length;
        int val2 = values.length;
        if(val1 < val2){
            returnVal = val1;
        }
        else{
            returnVal = val2;
        }
        return returnVal;
    }
}
