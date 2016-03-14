package persistence;

public class DataPersonalInfo {
    private int mId;
    private String mName;
    private String mGender;
    private int mAge;
    private int mWeight;

    public static final String COLNAME_ID         = "_id";
    public static final String COLNAME_NAME       = "Name";
    public static final String COLNAME_GENDER     = "Gender";
    public static final String COLNAME_AGE        = "Age";
    public static final String COLNAME_WEIGHT     = "Weight";

    public DataPersonalInfo(int mId, String mName, String mGender, int mAge, int mWeight) {
        this.mId = mId;
        this.mName = mName;
        this.mGender = mGender;
        this.mAge = mAge;
        this.mWeight = mWeight;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public int getmAge() {
        return mAge;
    }

    public void setmAge(int mAge) {
        this.mAge = mAge;
    }

    public int getmWeight() {
        return mWeight;
    }

    public void setmWeight(int mWeight) {
        this.mWeight = mWeight;
    }
}
