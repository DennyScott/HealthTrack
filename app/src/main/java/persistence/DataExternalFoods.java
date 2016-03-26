package persistence;

public class DataExternalFoods {
    private int mId;
    private String mFoodName;
    private int mCalories;
    private int mProtein;
    private int mCarbohydrates;
    private int mFats;

    public static final String COLNAME_ID = "_id";
    public static final String COLNAME_FOODNAME = "FoodName";
    public static final String COLNAME_CALORIES = "Calories";
    public static final String COLNAME_PROTEINS = "Proteins";
    public static final String COLNAME_CARBOHYDRATES = "Carbohydrates";
    public static final String COLNAME_FATS = "Fat";

    public DataExternalFoods(int mId, String mFoodName, int mCalories, int mProtein, int mCarbohydrates, int mFats) {
        this.mId = mId;
        this.mFoodName = mFoodName;
        this.mCalories = mCalories;
        this.mProtein = mProtein;
        this.mCarbohydrates = mCarbohydrates;
        this.mFats = mFats;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmFoodName() {
        return mFoodName;
    }

    public void setmFoodName(String mFoodName) {
        this.mFoodName = mFoodName;
    }

    public int getmCalories() {
        return mCalories;
    }

    public void setmCalories(int mCalories) {
        this.mCalories = mCalories;
    }

    public int getmProtein() {
        return mProtein;
    }

    public void setmProtein(int mProtein) {
        this.mProtein = mProtein;
    }

    public int getmCarbohydrates() {
        return mCarbohydrates;
    }

    public void setmCarbohydrates(int mCarbohydrates) {
        this.mCarbohydrates = mCarbohydrates;
    }

    public int getmFats() {
        return mFats;
    }

    public void setmFats(int mFats) {
        this.mFats = mFats;
    }
}
