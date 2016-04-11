package persistence;

import android.database.Cursor;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DataFoods {
    public static final String TABLE_NAME_FOODS = "ExternalFoods";
    public static final String COLNAME_ID = "_id";
    public static final String COLNAME_FOODNAME = "FoodDescription";
    public static final String COLNAME_CALORIES = "ENERC_KCALValue";
    public static final String COLNAME_PROTEINS = "PROCNTValue";
    public static final String COLNAME_CARBOHYDRATES = "CHOCDFValue";
    public static final String COLNAME_FATS = "FATValue";
    public static final String COLNAME_CHOLESTROL = "CHOLEValue";
    public static final String COLNAME_SODIUM = "NAValue";
    private static final Method[] allNutrientTableMethods = DataNutrientTable.class.getDeclaredMethods();
    public static String[] allNutrientNames;
    private static Method[] allNutrientNamesGetCalls;
    private static Method[] allNutrientValueGetCalls;
    private static Method[] allNutrientUnitGetCalls;
    private static Method[] allNutrientNamesSetCalls;
    private static Method[] allNutrientValueSetCalls;
    private static Method[] allNutrientUnitSetCalls;
    private int mId;
    private String mFoodDescription;
    private String mMeasureDescription;
    private String mConversionFactorValue;
    private String mFoodGroupName;
    private DataNutrientTable mDataNutrientTable;

    //when this food is being referenced/the rows are needed, just make a database query on the rows to extract
    //  relevant columns
    private int foodTableId;

    public DataFoods() {
        initializeMethodCalls();
    }

    public DataFoods(int foodTableId) {
        this();
        //search the database for this food, save the record
        this.foodTableId = foodTableId;
    }


    public static void initializeMethodCalls() {
        if (allNutrientNamesGetCalls == null)
            allNutrientNamesGetCalls = getAllGetterNutrientCalls(new String[]{"Unit", "Value"}, false);
        if (allNutrientValueGetCalls == null)
            allNutrientValueGetCalls = getAllGetterNutrientCalls(new String[]{"Value"}, true);
        if (allNutrientUnitGetCalls == null)
            allNutrientUnitGetCalls = getAllGetterNutrientCalls(new String[]{"Unit"}, true);
    }

    public static Method[] getAllNutrientNamesGetCalls() {
        if (allNutrientNamesGetCalls == null) initializeMethodCalls();
        return allNutrientNamesGetCalls;
    }

    public static Method[] getAllNutrientValueGetCalls() {
        if (allNutrientValueGetCalls == null) initializeMethodCalls();
        return allNutrientValueGetCalls;
    }

    public static Method[] getAllNutrientUnitGetCalls() {
        if (allNutrientUnitGetCalls == null) initializeMethodCalls();
        return allNutrientUnitGetCalls;
    }

    public static ArrayList<String> getAllNutrients() {

        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < allNutrientNames.length; i++) {
            result.add(allNutrientNames[i]);
        }
        return result;
    }

    /**
     * Get all the syntax names of the nutrient columns
     * @return
     */
    public static String[] getAllNutrientColumnNames() {
        Method[] nutrientMethods;
        if (allNutrientNamesGetCalls == null) initializeMethodCalls();
        String[] result = new String[allNutrientNamesGetCalls.length];
        String thisMethodName;
        for (int i = 0; i < result.length; i++) {
            thisMethodName = allNutrientNamesGetCalls[i].getName();
            //all nutrient getter methods have getm<> infront of them. so start at position 4, after the getm
            result[i] = thisMethodName.substring(4);
        }
        return result;
    }

    private static Method[] getAllGetterNutrientCalls(String[] patterns, boolean matchThese) {
        ArrayList<Method> calls = new ArrayList<>();
        String thisMethodName;
        boolean x, y;
        y = matchThese;
        //loop through the existing methods and take the ones that are getters without any "unit" or "value" in their names
        for (int i = 0; i < allNutrientTableMethods.length; i++) {
            thisMethodName = allNutrientTableMethods[i].getName();
            //only focus on getters
            if (thisMethodName.contains("getm")) {

                //using the matchThese boolean value, we change whether we allow a match or not
                //this can be simplified with boolean algebra, done this way to minimize duplicated
                //                      MATCH           NO MATCH
                //  matchThese=True     True            False
                //  matchThese=False    False           True
                //  so for the if statrement to evaluate true:
                //  let MATCH = X, NO MATCH = notX
                //  let matchThese == true = Y, matchThese == false = notY
                //  therefore, the logical boolean algebra simplification is:
                //          X AND Y   OR    notX AND notY =
                //          XY or !X!Y = 1
                x = false;

                for (int j = 0; j < patterns.length; j++) {
                    //look for a match
                    if (thisMethodName.contains(patterns[j])) {
                        x = true;
                        break;
                    }
                }
                if ((x && y) || (!x && !y)) {
                    if (!calls.contains(allNutrientTableMethods[i])) {
                        calls.add(allNutrientTableMethods[i]);
                    }
                }
            }
        }
        Method[] result = new Method[calls.size()];
        for (int i = 0; i < calls.size(); i++) {
            result[i] = calls.get(i);
        }
        return result;
    }

    public static DataFoods createFoodFromRow(Cursor cursor) {
        return null;
    }


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmFoodDescription() {
        return mFoodDescription;
    }

    public void setmFoodDescription(String mFoodDescription) {
        this.mFoodDescription = mFoodDescription;
    }

    public String getmMeasureDescription() {
        return mMeasureDescription;
    }

    public void setmMeasureDescription(String mMeasureDescription) {
        this.mMeasureDescription = mMeasureDescription;
    }

    public String getmConversionFactorValue() {
        return mConversionFactorValue;
    }

    public void setmConversionFactorValue(String mConversionFactorValue) {
        this.mConversionFactorValue = mConversionFactorValue;
    }

    public String getmFoodGroupName() {
        return mFoodGroupName;
    }

    public void setmFoodGroupName(String mFoodGroupName) {
        this.mFoodGroupName = mFoodGroupName;
    }

    public DataNutrientTable getmDataNutrientTable() {
        return mDataNutrientTable;
    }

    public void setmDataNutrientTable(DataNutrientTable mDataNutrientTable) {
        this.mDataNutrientTable = mDataNutrientTable;
    }

    public ArrayList<String> getAllNutrientsAndValues(String separator) {
        ArrayList<String> nutrientsAndValues = new ArrayList<String>();
        if (allNutrientNamesGetCalls == null) initializeMethodCalls();

        for (int i = 0; i < allNutrientNamesGetCalls.length; i++) {
            try {
                //find this corresponding nutrient's value call
                for (int j = 0; j < allNutrientValueGetCalls.length; j++) {
                    //the nutrient name calls and the value calls differe in that:
                    // getm<TAG> and getm<TAG>Value
                    //  so the Value calls contain the getter ones
                    if (allNutrientValueGetCalls[j].getName().contains(allNutrientNamesGetCalls[i].getName())) {
                        nutrientsAndValues.add((String) allNutrientNamesGetCalls[i].invoke(null) + separator + (String) allNutrientValueGetCalls[j].invoke(null));
                        //leave the search
                        break;
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(nutrientsAndValues);
        return nutrientsAndValues;
    }
}
