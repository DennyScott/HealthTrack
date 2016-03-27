package persistence;

public class DataTransactionalHistory {
    private int mId;
    private String mFoodName;
    private String mEatendate;  //TODO change this to a date object later
    private String mEatentime;
    private String  mPortionSize;
    private int mFoodTableID;

    //        transactional history (eaten)
    //          id
    //          food_name
    //          eaten_date
    //          eaten_time
    //          portion_size
    //          foodTableId

    //Transactional History Table:
    public static final String COLNAME_ID = "_id";     //reusable
    public static final String COLNAME_FOODNAME = "FoodName";
    public static final String COLNAME_EATEN_DATE = "EatenDate";
    public static final String COLNAME_EATEN_TIME = "EatenTime";
    public static final String COLNAME_PORTIONSIZE = "PortionSize";
    public static final String COLNAME_FOODTABLE_ID = "FoodTableID";

    public DataTransactionalHistory(int mId, String mFoodName, String mEatendate, String mEatentime, String mPortionSize, int mFoodTableID) {
        this.mId = mId;
        this.mFoodName = mFoodName;
        this.mEatendate = mEatendate;
        this.mEatentime = mEatentime;
        this.mPortionSize = mPortionSize;
        this.mFoodTableID = mFoodTableID;
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

    public String getmPortionSize() {
        return mPortionSize;
    }

    public void setmPortionSize(String mPortionSize) {
        this.mPortionSize = mPortionSize;
    }

    public int getmFoodTableID() {
        return mFoodTableID;
    }

    public void setmFoodTableID(int mFoodTableID) {
        this.mFoodTableID = mFoodTableID;
    }
}
