package business;

import java.util.ArrayList;
import java.util.Collections;

public class Stats {
    private float[] values;
    private String[] keys;
    private String[] units;
    private float[] otherValues;
    private String[] otherKeys;
    private String[] otherUnits;
    private final int MAX_SIZE = 7;
    private int amountOfOtherData;
    int mode;
    private int size =0;
    public Stats(){
        amountOfOtherData = 0;
        init(0);// will initialize to day by default.
    }
    // sets our chart information, must be called every time we switch our mode i.e day/week/month, to get new data from data base.
    public void init(int mode){
        otherKeys = new String[]{"Nothing to Show"};// init to something other than null to avert crashes
        otherValues = new float[] {0};// init to something other than null to avert crashes
        units = new String[] {""};
        this.mode = mode;
        values = new float[MAX_SIZE];
        keys = new String[MAX_SIZE];
        units = new String[MAX_SIZE];
        float[] inValues = valuesFromDB(mode);
        String[] inKeys = keysFromDB(mode);
        String[] inUnits = unitsFromDB(mode);
        size = sizeOf(inKeys,inValues, inUnits);
        amountOfOtherData = size - (MAX_SIZE-1);
        if(amountOfOtherData > 0){
            otherValues = new float[amountOfOtherData];
            otherKeys = new String[amountOfOtherData];
            otherUnits = new String[amountOfOtherData];
        }
        ArrayList<KeyValuePair> kVP = sort(inValues,inKeys,inUnits);
        if(size > MAX_SIZE) {
            populate(kVP);
        }
        else if(size <= MAX_SIZE && size > 0){
            values = inValues;
            keys = inKeys;
            units = inUnits;
        }
        else{// if size is 0 or undefined return dummy item so that stats lib wont crash.
            values = new float[] {50,50};
            keys = new String[]{"Nothing","Nothing"};
            units = new String[] {"",""};
        }

    }
    public  float[] getValues(){//mode here is day/week/month
        return values;
    }
    public  String[] getKeys(){
        return keys;
    }
    public String[] Units(){
        return units;
    }
    public  float[] getOtherValues(){//mode here is day/week/month
        return otherValues;
    }
    public  String[] getOtherKeys(){
        return otherKeys;
    }
    public  String[] getOtherUnits(){
        return otherUnits;
    }
    /*populates our "main values" - aka things visible on main chart, and populates the "other"
    * not visible on chart, but will be able to visit the detailed subsection for every element
    * in the chart later on (next iteration)
    * */
    private void populate(ArrayList<KeyValuePair> array){
        for(int x = 0; x< MAX_SIZE-1; x++){
            values[x] = array.get(x).value;
            keys[x] = array.get(x).key;
        }
        if(amountOfOtherData > 0){
            for(int x = MAX_SIZE-1; x< size; x++){
                otherValues[x-(MAX_SIZE-1)] = array.get(x).value;
                otherKeys[x-(MAX_SIZE-1)] = array.get(x).key;
                otherUnits[x-(MAX_SIZE-1)] = array.get(x).unit;
            }
            int otherTotal = 0;
            for(int x  =0 ; x< otherValues.length; x++){
                otherTotal += otherValues[x];
            }
            values[MAX_SIZE-1] = otherTotal;
            keys[MAX_SIZE-1] = "Other";
            units[MAX_SIZE-1] ="";
        }
    }
    // Sorts key value pair so we get the biggest nutritional components first
    private ArrayList<KeyValuePair> sort(float[] values, String[] keys,String[] units){
        ArrayList<KeyValuePair> retVal = null;
        if(size > 0 && values.length == size && keys.length == size) {
            ArrayList<KeyValuePair> kVP = new ArrayList<KeyValuePair>();
            for (int x = 0; x < size; x++) {
                kVP.add(new KeyValuePair(keys[x], values[x],units[x]));
            }
            Collections.sort(kVP);
            retVal = kVP;
        }
        return retVal;
    }
    private String[] keysFromDB(int mode){
        String[] keys =  StatsDataAccess.getNutrientsNames(mode);// these values will be attained from database later on
        return keys;
    }
    private float[] valuesFromDB(int mode){
        float[] values = StatsDataAccess.getNutrientsValues(mode);// these values will be attained from database later on
        return values;
    }
    private String[] unitsFromDB(int mode){
        String[] values = StatsDataAccess.getNutrientsUnits(mode);// these values will be attained from database later on
        return values;
    }
    // returns size of array from database.
    private int sizeOf(String[] keys, float[] values, String[] units){// finds smallest of two list sizes in case of mistake in DB code.
        int returnVal = 0;
        if(keys != null && values != null && units != null) {
            int val1 = keys.length;
            int val2 = values.length;
            int val3 = units.length;

            if (val1 < val2) {
                if(val1 < val3){
                    returnVal = val1;
                }
                else{
                    returnVal = val3;
                }

            } else {
                if(val2 < val3){
                    returnVal = val2;
                }
                else{
                    returnVal = val3;
                }
                returnVal = val2;
            }
        }
        return returnVal;
    }
}
