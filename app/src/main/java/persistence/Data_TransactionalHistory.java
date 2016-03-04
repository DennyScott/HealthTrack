package persistence;

public class Data_TransactionalHistory {
    private int mId;
    private String mFoodName;
    private String mEatendate;  //TODO change this to a date object later
    private String mEatentime;
    private int  mPortionSize;
    private int mEatenCalories;
    private int mEatenProtein;
    private int mEatenCarohydrates;
    private int mEatenFats;
    //        transactional history (eaten)
    //          id
    //          food_name
    //          eaten_date
    //          eaten_time
    //          portion_size
    //          calories_eaten
    //          protein_eaten
    //          carbohydrdates_eaten
    //          fats_eaten

    //Transactional History Table:
    public final String COLNAME_ID = "";     //reusable
    public final String COLNAME_FOODNAME = "";
    public static final String COLNAME_EATEN_DATE = "EatenDate";
    public static final String COLNAME_EATEN_TIME = "EatenTime";
    public final String COLNAME_EATEN_CALORIES = "EatenCalories";
    public final String COLNAME_EATEN_PROTEINS = "EatenProteins";
    public final String COLNAME_EATEN_CARBS = "EatenCarbohydrates";
    public final String COLNAME_EATEN_FATS = "EatenFats";
    public final String COLNAME_PORTIONSIZE = "PortionSize";

    public Data_TransactionalHistory(int mId, String mFoodName, String mEatendate, String mEatentime, int mPortionSize, int mEatenCalories, int mEatenProtein, int mEatenCarohydrates, int mEatenFats) {
        this.mId = mId;
        this.mFoodName = mFoodName;
        this.mEatendate = mEatendate;
        this.mEatentime = mEatentime;
        this.mPortionSize = mPortionSize;
        this.mEatenCalories = mEatenCalories;
        this.mEatenProtein = mEatenProtein;
        this.mEatenCarohydrates = mEatenCarohydrates;
        this.mEatenFats = mEatenFats;
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

    public String getmEatendate() {
        return mEatendate;
    }

    public void setmEatendate(String mEatendate) {
        this.mEatendate = mEatendate;
    }

    public String getmEatentime() {
        return mEatentime;
    }

    public void setmEatentime(String mEatentime) {
        this.mEatentime = mEatentime;
    }

    public int getmPortionSize() {
        return mPortionSize;
    }

    public void setmPortionSize(int mPortionSize) {
        this.mPortionSize = mPortionSize;
    }

    public int getmEatenCalories() {
        return mEatenCalories;
    }

    public void setmEatenCalories(int mEatenCalories) {
        this.mEatenCalories = mEatenCalories;
    }

    public int getmEatenProtein() {
        return mEatenProtein;
    }

    public void setmEatenProtein(int mEatenProtein) {
        this.mEatenProtein = mEatenProtein;
    }

    public int getmEatenFats() {
        return mEatenFats;
    }

    public void setmEatenFats(int mEatenFats) {
        this.mEatenFats = mEatenFats;
    }

    public int getmEatenCarohydrates() {
        return mEatenCarohydrates;
    }

    public void setmEatenCarohydrates(int mEatenCarohydrates) {
        this.mEatenCarohydrates = mEatenCarohydrates;
    }
}
