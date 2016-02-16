package business;

import java.util.ArrayList;
import java.util.Collections;

public class Stats {
    float[] values;
    String[] keys;
    float[] otherValues;
    String[] otherKeys;
    final int MAX_SIZE = 7;
    int amountOfOtherData = 0;
    int size =0;// size of hashtable received from database for now we will use a known value
    // we will show the top 6 biggest contributors and everything else will be listed under other
    public void init(int mode){
        size = 8;
        amountOfOtherData = size - (MAX_SIZE-1);
        values = new float[MAX_SIZE];
        keys = new String[MAX_SIZE];
        if(amountOfOtherData > 0){
            otherValues = new float[amountOfOtherData];
            otherKeys = new String[amountOfOtherData];
        }
        float[] inValues = {5, 10, 15, 30, 40, 20, 10, 60}; // these values will be attained from database later on
        String[] inKeys = {"Cholesterol", "Sodium", "Sugar", "Protein", "Fat", "Fiber", "Calcium", "Carbs"};// these values will be attained from database later on
        ArrayList<keyValuePair> kVP = sort(inValues,inKeys );
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
    void populate(ArrayList<keyValuePair> array){
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
    ArrayList<keyValuePair> sort(float[] values, String[] keys){
        ArrayList<keyValuePair> retVal = null;
        if(size > 0 && values.length == size && keys.length == size) {
            ArrayList<keyValuePair> kVP = new ArrayList<keyValuePair>();
            for (int x = 0; x < size; x++) {
                kVP.add(new keyValuePair(keys[x], values[x]));
            }
            Collections.sort(kVP);
            retVal = kVP;
        }
        return retVal;
    }
}
class keyValuePair implements Comparable<keyValuePair> {
    float value;
    String key;

    keyValuePair(String key, float value) {
        this.value = value;
        this.key = key;
    }

    @Override
    public boolean equals(Object other) {
        boolean retVal = false;
        if (other == this && this.key.toLowerCase().equals(((keyValuePair) other).key.toLowerCase())
                && this.value ==(((keyValuePair)other).value)) retVal = true;
        return retVal;
    }
    @Override
    public int compareTo(keyValuePair other) {
        int retVal = 0;
        if(other.value > this.value){
            retVal = 1;
        }
        else if(other.value < this.value){
            retVal = -1;
        }
        return retVal;
    }
}
