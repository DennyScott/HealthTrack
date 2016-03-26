package persistence;

public class DataExternalFoods {
    private int mId;
    private String mFoodDescription;
    private String mMeasureDescription;
    private String mConversionFactorValue;
    private String mFoodGroupName;
    private DataNutrientTable mDataNutrientTable;

    public static final String COLNAME_ID = "_id";     //reusable
    public static final String COLNAME_FOODNAME = "FoodName";
    public static final String COLNAME_CALORIES = "Calories";
    public static final String COLNAME_PROTEINS = "Proteins";
    public static final String COLNAME_CARBOHYDRATES = "Carbohydrates";
    public static final String COLNAME_FATS = "Fat";


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
}
