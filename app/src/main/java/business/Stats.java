package business;

import java.util.ArrayList;
import java.util.Collections;

// 0 = day, 1 = week, 2 = month
// day by default

public class Stats implements ApplicationConstants {
    private final int MAX_SIZE = 7;
    int mode;
    private float[] values;
    private String[] keys;
    private String[] units;
    private float[] otherValues;
    private String[] otherKeys;
    private String[] otherUnits;
    private int amountOfOtherData;
    private int size = 0;
    StatsDataAccess dataAccess;
    StatsUtils utils;


    public Stats() {
        amountOfOtherData = 0;
        init(0);
    }

    public void init(int mode) {
        utils = new StatsUtils();
        dataAccess = new StatsDataAccess();
        otherKeys = new String[]{"Nothing to Show"};
        otherValues = new float[]{0};
        units = new String[]{""};
        otherUnits = new String[]{"g"};
        this.mode = mode;
        values = new float[MAX_SIZE];
        keys = new String[MAX_SIZE];
        units = new String[MAX_SIZE];
        float[] inValues = valuesFromDB(mode);
        String[] inKeys = keysFromDB(mode);
        String[] inUnits = unitsFromDB(mode);
        size = sizeOf(inKeys, inValues, inUnits);
        amountOfOtherData = size - (MAX_SIZE - 1);
        if (amountOfOtherData > 0) {
            otherValues = new float[amountOfOtherData];
            otherKeys = new String[amountOfOtherData];
            otherUnits = new String[amountOfOtherData];
        }
        ArrayList<KeyValuePair> kVP = sort(inValues, inKeys, inUnits);
        if (size > MAX_SIZE) {
            populate(kVP);
        } else if (size <= MAX_SIZE && size > 0) {
            values = inValues;
            keys = inKeys;
            units = inUnits;
        } else {
            values = new float[]{50, 50};
            keys = new String[]{"Nothing", "Nothing"};
            units = new String[]{"", ""};
        }

    }

    public float[] getValues() {
        return values;
    }

    public String[] getKeys() {
        return keys;
    }

    public String[] Units() {
        return units;
    }

    public float[] getOtherValues() {
        return otherValues;
    }

    public String[] getOtherKeys() {
        return otherKeys;
    }

    public String[] getOtherUnits() {
        return otherUnits;
    }

    private void populate(ArrayList<KeyValuePair> array) {
        for (int x = 0; x < MAX_SIZE - 1; x++) {
            values[x] = array.get(x).value;
            keys[x] = array.get(x).key;
            units[x] = array.get(x).unit;
        }

        if (amountOfOtherData > 0) {
            for (int x = MAX_SIZE - 1; x < size; x++) {
                otherValues[x - (MAX_SIZE - 1)] = array.get(x).value;
                otherKeys[x - (MAX_SIZE - 1)] = array.get(x).key;
                otherUnits[x - (MAX_SIZE - 1)] = array.get(x).unit;
            }
            int otherTotal = 0;
            for (int x = 0; x < otherValues.length; x++) {
                otherTotal += otherValues[x];
            }
            values[MAX_SIZE - 1] = otherTotal;
            keys[MAX_SIZE - 1] = "Other";
            units[MAX_SIZE - 1] = "g";
            float[] newValue = utils.GramsToAll( values,units);
            values = newValue;
        }
    }

    private ArrayList<KeyValuePair> sort(float[] values, String[] keys, String[] units) {
        ArrayList<KeyValuePair> retVal = null;
        StatsUtils util = new StatsUtils();
        if (size > 0 && values.length == size && keys.length == size) {
            ArrayList<KeyValuePair> kVP = new ArrayList<KeyValuePair>();
            ArrayList<KeyValuePair> energy = new ArrayList<KeyValuePair>();
            float[] newFloatVals =  util.allToGrams(values,units);
            for (int x = 0; x < size; x++) {
                if(units[x].equals("kJ") || units[x].equals("kCal")){
                    energy.add(new KeyValuePair(keys[x], newFloatVals[x], units[x]));
                }
                else {
                    kVP.add(new KeyValuePair(keys[x], newFloatVals[x], units[x]));
                }
            }
            Collections.sort(kVP);
            for(int x = 0; x< energy.size();x++){
                kVP.add(energy.get(x));
            }
            retVal = kVP;
        }
        return retVal;
    }

    private String[] keysFromDB(int mode) {
        String[] keys = dataAccess.getNutrientsNames(mode);
        return keys;
    }

    private float[] valuesFromDB(int mode) {
        float[] values = dataAccess.getNutrientsValues(mode);
        return values;
    }

    private String[] unitsFromDB(int mode) {
        String[] values = dataAccess.getNutrientsUnits(mode);
        return values;
    }

    public int sizeOf(String[] keys, float[] values, String[] units) {
        int returnVal = 0;
        if (keys != null && values != null && units != null) {
            int val1 = keys.length;
            int val2 = values.length;
            int val3 = units.length;

            if (val1 < val2) {
                if (val1 < val3) {
                    returnVal = val1;
                } else {
                    returnVal = val3;
                }

            } else {
                if (val2 < val3) {
                    returnVal = val2;
                } else {
                    returnVal = val3;
                }
                returnVal = val2;
            }
        }
        return returnVal;
    }
}
