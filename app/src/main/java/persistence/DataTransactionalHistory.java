package persistence;

import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class DataTransactionalHistory {
    private int mId;
    private String mFoodName;
    private String mEatendate;
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
    public static final String TABLE_NAME = DatabaseDefinition.TABLE_NAME_TRANSHIST;
    public static final String COLNAME_ID = "_id";
    public static final String COLNAME_FOODNAME = DataFoods.COLNAME_FOODNAME;
    public static final String COLNAME_EATEN_DATE = DatabaseDefinition.COLNAME_EATEN_DATE;
    public static final String COLNAME_PORTIONSIZE = DatabaseDefinition.COLNAME_PORTIONSIZE;
    public static final String COLNAME_FOODTABLE_ID = DatabaseDefinition.COLNAME_FOODTABLE_ID;

    public DataTransactionalHistory(int mId, String mFoodName, String mEatendate, String mEatentime, String mPortionSize, int mFoodTableID) {
        this.mId = mId;
        this.mFoodName = mFoodName;
        this.mEatendate = mEatendate;
        this.mEatentime = mEatentime;
        this.mPortionSize = mPortionSize;
        this.mFoodTableID = mFoodTableID;
    }

    public static String[] getAllFoodNames(String dateRangeStart, String dateRangeEnd) {
        SQLiteDatabase db = DatabaseDefinition.currentDatabase.getReadableDatabase();
        ArrayList<String> names = new ArrayList<>();
        Cursor cursorTrans = db.query(
                true,
                DataTransactionalHistory.TABLE_NAME,
                null,
                COLNAME_EATEN_DATE + " >=? AND " + COLNAME_EATEN_DATE + " <=?",
                new String[] {dateRangeStart, dateRangeEnd},
                null,
                null,
                null,
                null
        );
        if (cursorTrans.getCount() > 0) {
            cursorTrans.moveToFirst();
            for (int i = 0; i < cursorTrans.getCount(); i++) {
                names.add(cursorTrans.getString(cursorTrans.getColumnIndex(COLNAME_FOODNAME)));
                cursorTrans.moveToNext();
            }
        }
        cursorTrans.close();
        db.close();
        String[] result = new String[names.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = names.get(i);
        }
        return result;
    }

    public static String[] getAllFoodNutrients(String dateRangeStart, String dateRangeEnd, String foodName) {
        SQLiteDatabase db = DatabaseDefinition.currentDatabase.getReadableDatabase();
        Cursor cursorDb;
        String[] allNutrientColumnNames;
        ArrayList<String> allNutrients;
        float[] nutrientSum = new float[0];
        String[] nutrientUnits = new String[0];
        String[] nutrientNames = new String[0];

        Cursor cursorTrans = db.query(
                DataTransactionalHistory.TABLE_NAME,
                null,
                COLNAME_EATEN_DATE + " >=? AND " + COLNAME_EATEN_DATE + " <=?" + " AND " + COLNAME_FOODNAME + " LIKE %?%",
                new String[] {dateRangeStart, dateRangeEnd, foodName},
                null,
                null,
                null
        );
        if (cursorTrans.getCount() > 0) {
            cursorTrans.moveToFirst();
            allNutrientColumnNames = DataFoods.getAllNutrientColumnNames();
            allNutrients = DataFoods.getAllNutrients();
            nutrientSum = new float[allNutrients.size()];
            nutrientUnits = new String[allNutrients.size()];
            nutrientNames = new String[allNutrients.size()];
            String currentNutrientColumn;
            int thisFoodId = cursorTrans.getInt(cursorTrans.getColumnIndex(COLNAME_FOODTABLE_ID));
            float portionSize;
            //for every nutrient, add the running total to the portion size
            for (int i = 0; i < cursorTrans.getCount(); i++ ) {
                cursorDb = db.query(
                        DataFoods.TABLE_NAME_FOODS,
                        null,
                        DataFoods.COLNAME_ID + " = " + thisFoodId,
                        null,
                        null,
                        null,
                        null
                );
                portionSize = cursorTrans.getFloat(cursorTrans.getColumnIndex(COLNAME_PORTIONSIZE));
                cursorDb.moveToFirst();
                for (int j = 0; j < allNutrientColumnNames.length; j++) {
                    currentNutrientColumn = allNutrientColumnNames[j];
                    //only set nutrient name and units once, since float sum is the changing value
                    if (nutrientNames[i] == null || nutrientNames[i].equals(""))
                        nutrientNames[i] = cursorDb.getString(cursorDb.getColumnIndex(currentNutrientColumn));
                    if (nutrientUnits[i] == null || nutrientUnits[i].equals(""))
                        nutrientUnits[i] = cursorDb.getString(cursorDb.getColumnIndex(currentNutrientColumn + "Unit"));
                    if (nutrientSum[i] <= 0) nutrientSum[i] = 0;
                    //multiple by portion size too
                    nutrientSum[i] += cursorDb.getFloat(cursorDb.getColumnIndex(currentNutrientColumn + "Value")) * portionSize;
                }

            }
        }
        cursorTrans.close();
        db.close();
        String[] result = new String[nutrientNames.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = nutrientNames[i] + " " + nutrientSum[i] + " " + nutrientUnits[i];
        }
        return result;
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
