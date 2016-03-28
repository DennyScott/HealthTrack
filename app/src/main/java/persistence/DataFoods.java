package persistence;

import java.util.ArrayList;

public class DataFoods {
    public static final String TABLE_NAME_FOODS = "ExternalFoods";
    private int mId;
    private String mFoodDescription;
    private String mMeasureDescription;
    private String mConversionFactorValue;
    private String mFoodGroupName;
    private DataNutrientTable mDataNutrientTable;

    public static final String COLNAME_ID = "_id";
    public static final String COLNAME_FOODNAME = "FoodDescription";
    public static final String COLNAME_CALORIES = "ENERC_KCALValue";
    public static final String COLNAME_PROTEINS = "PROCNTValue";
    public static final String COLNAME_CARBOHYDRATES = "CHOCDFValue";
    public static final String COLNAME_FATS = "FATValue";
    public static final String COLNAME_CHOLESTROL = "CHOLEValue";
    public static final String COLNAME_SODIUM = "NAValue";



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


    /*
    Valid Nutrients:
            "ALCOHOL",
            "CAFFEINE",
            "CALCIUM",
            "CARBOHYDRATE, TOTAL (BY DIFFERENCE)",
            "CHOLESTEROL",
            "ENERGY (KILOCALORIES)",
            "ENERGY (KILOJOULES)",
            "FAT (TOTAL LIPIDS)",
            "FATTY ACIDS, MONOUNSATURATED, TOTAL",
            "FATTY ACIDS, POLYUNSATURATED, TOTAL",
            "FATTY ACIDS, SATURATED, TOTAL",
            "FATTY ACIDS, TRANS, TOTAL",
            "FIBRE, TOTAL DIETARY",
            "FOLIC ACID",
            "FRUCTOSE",
            "GALACTOSE",
            "GLUCOSE",
            "IRON",
            "LACTOSE",
            "MAGNESIUM",
            "MANGANESE",
            "MOISTURE",
            "NATURALLY OCCURRING FOLATE",
            "OXALIC ACID",
            "PHOSPHORUS",
            "POTASSIUM",
            "PROTEIN",
            "RIBOFLAVIN",
            "SODIUM",
            "STARCH",
            "SUCROSE",
            "SUGARS, TOTAL",
            "THIAMIN",
            "VITAMIN B-12",
//            "VITAMIN B12, ADDED", no tag
            "VITAMIN B-6",
            "VITAMIN C",
//            "VITAMIN D (D2 + D3)",        //has a weird tagname, VITD_ �G , messed up the SQL injection
//            "VITAMIN D (INTERNATIONAL UNITS)",
            "VITAMIN D2, ERGOCALCIFEROL",
            "VITAMIN K",
            "ZINC"
     */
    public ArrayList<String> getAllNutrients() {
        ArrayList<String> nutrients = new ArrayList<String>();
        return null;
    }
}
