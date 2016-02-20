package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseDefinition extends SQLiteOpenHelper {

    //for
    private static final String LOG  = "DatabaseDefinitionTest";
    /*
        SQL Tables (refer to classes for final data member order):
            personal information
                _id
                name
                gender
                age
                weight
            custom foods
                id
                food_name
                calories
                proteins
                carbohydrdates
                fats
            external foods
                id
                food_name
                calories
                proteins
                carbohydrdates
                fats
            transactional history (eaten)
                id
                food_name
                eaten_date
                eaten_time
                portion_size
                calories_eaten
                protein_eaten
                carbohydrdates_eaten
                fats_eaten
     */

    //for all tables
    private static final String DATABASE_NAME  = "HEALTHTRACK_DB";
    private static final int DATABASE_VERSION = 1;
    private final String CREATE_TABLE   = "CREATE TABLE";
    private final String IF_NOT_EXISTS  = " IF NOT EXISTS ";

    private final String DATATYPE_INT   = " INTEGER";
    private final String DATATYPE_TEXT  = " TEXT";
    private final String OPT_NOT_NULL   = " NOT NULL";
    private final String OPT_PRIM_KEY   = " PRIMARY KEY";
    private final String OPT_DEFAULT    = " DEFAULT";
    private final String OPT_UNIQUE     = " UNIQUE (";

    private final String OPT_COMMA      = ",";

    //tables
    private final String TABLE_PERS_INFO        = " PersonalInformation(";
    private final String TABLE_CUST_FOODS       = " CustomFoods(";
    private final String TABLE_EXT_FOODS        = " ExternalFoods(";
    private final String TABLE_TRANS_HIST       = " TransactionalHistory(";

    //*table values
    //PersInfo Table:
    private final String COLNAME_ID         = "_id";
    private final String COLNAME_NAME       = "Name";
    private final String COLNAME_GENDER     = "Gender";
    private final String COLNAME_AGE        = "Age";
    private final String COLNAME_WEIGHT     = "Weight";

    //CustFoods Table:
    //private final String COLNAME_ID = "";     //reusable
    private final String COLNAME_FOODNAME = "FoodName";
    private final String COLNAME_CALORIES = "Calories";
    private final String COLNAME_PROTEINS = "Proteins";
    private final String COLNAME_CARBOHYDRATES = "Carbohydrates";
    private final String COLNAME_FATS = "Fat";

    //ExtFoods Table (reused from CustFoods):
//    private final String COLNAME_ID = "";     //reusable
//    private final String COLNAME_FOODNAME = "";
//    private final String COLNAME_CALORIES = "";
//    private final String COLNAME_PROTEINS = "";
//    private final String COLNAME_CARBOHYDRATES = "";
//    private final String COLNAME_FATS = "";

    //Transactional History Table:
    //private final String COLNAME_ID = "";     //reusable
//    private final String COLNAME_FOODNAME = "";
    private final String COLNAME_EATEN_DATE = "EatenDate";
    private final String COLNAME_EATEN_TIME = "EatenTime";
    private final String COLNAME_EATEN_CALORIES = "EatenCalories";
    private final String COLNAME_EATEN_PROTEINS = "EatenProteins";
    private final String COLNAME_EATEN_CARBS = "EatenCarbohydrates";
    private final String COLNAME_EATEN_FATS = "EatenFats";
    private final String COLNAME_PORTIONSIZE = "PortionSize";


    private Context dbContext;
    private SQLiteDatabase.CursorFactory cursorFactory;


    public DatabaseDefinition(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        cursorFactory = factory;
        dbContext = context;
    }

    //only called when the database is FIRST created (by getWritable/ReadableDatabase )
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*create the tables:
        General layout of command:
        CREATE TABLE IF NOT EXISTS TestTable1 (
                _id INTEGER primary key             <--- ID needs an underscore before it
                COLNAME TEXT not null,
        );

        */
//        personal information
//          _id
//          name
//          gender
//          age
//          weight
        db.execSQL(
                CREATE_TABLE + IF_NOT_EXISTS + TABLE_PERS_INFO +
                        COLNAME_ID      + DATATYPE_INT  + OPT_PRIM_KEY + OPT_COMMA +
                        COLNAME_NAME    + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_GENDER  + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_AGE     + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_WEIGHT  + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA
        );

//        custom foods
//              id
//              food_name
//              calories
//              proteins
//              carbohydrdates
//              fats

        db.execSQL(
                CREATE_TABLE + IF_NOT_EXISTS + TABLE_CUST_FOODS +
                        COLNAME_ID              + DATATYPE_INT  + OPT_PRIM_KEY + OPT_COMMA +
                        COLNAME_FOODNAME        + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_CALORIES        + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_PROTEINS        + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_CARBOHYDRATES   + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_FATS            + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA
        );

//        external foods
//          id
//          food_name
//          calories
//          proteins
//          carbohydrdates
//          fats
        db.execSQL(
                CREATE_TABLE + IF_NOT_EXISTS + TABLE_EXT_FOODS +
                        COLNAME_ID              + DATATYPE_INT  + OPT_PRIM_KEY + OPT_COMMA +
                        COLNAME_FOODNAME        + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_CALORIES        + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_PROTEINS        + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_CARBOHYDRATES   + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_FATS            + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA
        );

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
        db.execSQL(
                CREATE_TABLE + IF_NOT_EXISTS + TABLE_TRANS_HIST +
                        COLNAME_ID              + DATATYPE_INT  + OPT_PRIM_KEY + OPT_COMMA +
                        COLNAME_FOODNAME        + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_EATEN_DATE      + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_EATEN_TIME      + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_PORTIONSIZE     + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_EATEN_CALORIES  + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_EATEN_PROTEINS  + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_EATEN_CARBS     + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                        COLNAME_EATEN_FATS      + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERS_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUST_FOODS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXT_FOODS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANS_HIST);
        onCreate(db);
    }


    //*************DATABASE INSERTIONS*************************

    public long createPersonalInfo(Data_PersonalInfo data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        //        personal information
        //          _id
        //          name
        //          gender
        //          age
        //          weight
        vals.put(COLNAME_ID, data.getmId());
        vals.put(COLNAME_NAME, data.getmName());
        vals.put(COLNAME_GENDER, data.getmGender());
        vals.put(COLNAME_AGE, data.getmAge());
        vals.put(COLNAME_WEIGHT, data.getmWeight());

        //get the ID of the resulting insertion
        return db.insert(TABLE_PERS_INFO, null, vals);
    }

    public long createCustomFoods(Data_CustomFoods data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        //        custom foods
        //              id
        //              food_name
        //              calories
        //              proteins
        //              carbohydrdates
        //              fats
        vals.put(COLNAME_ID, data.getmId());
        vals.put(COLNAME_FOODNAME, data.getmFoodName());
        vals.put(COLNAME_CALORIES, data.getmCalories());
        vals.put(COLNAME_PROTEINS, data.getmProtein());
        vals.put(COLNAME_CARBOHYDRATES, data.getmCarbohydrates());
        vals.put(COLNAME_FATS, data.getmFats());


        //get the ID of the resulting insertion
        return db.insert(TABLE_CUST_FOODS, null, vals);
    }

    public long createExternalFoods(Data_ExternalFoods data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        //        external foods
        //          id
        //          food_name
        //          calories
        //          proteins
        //          carbohydrdates
        //          fats
        vals.put(COLNAME_ID, data.getmId());
        vals.put(COLNAME_FOODNAME, data.getmFoodName());
        vals.put(COLNAME_CALORIES, data.getmCalories());
        vals.put(COLNAME_PROTEINS, data.getmProtein());
        vals.put(COLNAME_CARBOHYDRATES, data.getmCarbohydrates());
        vals.put(COLNAME_FATS, data.getmFats());


        //get the ID of the resulting insertion
        return db.insert(TABLE_EXT_FOODS, null, vals);
    }

    public long createTransactionalHistory(Data_TransactionalHistory data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
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
        vals.put(COLNAME_ID, data.getmId());
        vals.put(COLNAME_FOODNAME, data.getmFoodName());
        vals.put(COLNAME_EATEN_DATE, data.getmEatendate());
        vals.put(COLNAME_EATEN_TIME, data.getmEatentime());
        vals.put(COLNAME_PORTIONSIZE, data.getmPortionSize());
        vals.put(COLNAME_EATEN_CALORIES, data.getmEatenCalories());
        vals.put(COLNAME_EATEN_PROTEINS, data.getmEatenProtein());
        vals.put(COLNAME_EATEN_FATS, data.getmEatenFats());

        //get the ID of the resulting insertion
        return db.insert(TABLE_TRANS_HIST, null, vals);
    }

    /////////////////END DATABASE INSERTIONS////////////////////////

    //***************DATABASE SINGLE ID RETRIEVALS****************************

    public Data_PersonalInfo getPersonalInfo(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        //SELECT * FROM TABLE_ (because it SHOULD HAVE only 1 entry)
        //ToDo possibility to change this to personal preferences file vs sqlite database
        Cursor cursor = db.query(
                TABLE_PERS_INFO,
                null,
                null,
                null,
                null,
                null,
                null);
        //retreieve the data in the assumed order:
        //        personal information
        //          _id
        //          name
        //          gender
        //          age
        //          weight
        Data_PersonalInfo retrievedData = new Data_PersonalInfo(
                cursor.getInt(0), //int _id is always the first column
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(2),
                cursor.getInt(3)
        );

        return retrievedData;
    }

    public Data_CustomFoods getCustomFood(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        //SELECT * FROM TABLE_ WHERE _id = Id
        Cursor cursor = db.query(
                TABLE_CUST_FOODS,
                null,
                "_id=?",
                new String[] {Integer.toString(id)},
                null,
                null,
                null);
        //retreieve the data in the assumed order:
        //        custom foods
        //              id
        //              food_name
        //              calories
        //              proteins
        //              carbohydrdates
        //              fats
        Data_CustomFoods retrievedData = new Data_CustomFoods(
                cursor.getInt(0), //int _id is always the first column
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getInt(2),
                cursor.getInt(3),
                cursor.getInt(4)
        );

        return retrievedData;
    }

    public Data_ExternalFoods getExternalFood(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        //SELECT * FROM TABLE_ WHERE _id = Id
        Cursor cursor = db.query(
                TABLE_EXT_FOODS,
                null,
                "_id=?",
                new String[] {Integer.toString(id)},
                null,
                null,
                null);
        //retreieve the data in the assumed order:
        //        external foods
        //          id
        //          food_name
        //          calories
        //          proteins
        //          carbohydrdates
        //          fats
        Data_ExternalFoods retrievedData = new Data_ExternalFoods(
                cursor.getInt(0), //int _id is always the first column
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getInt(2),
                cursor.getInt(3),
                cursor.getInt(4)
        );

        return retrievedData;
    }

    public Data_TransactionalHistory getTransactionalHistory(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        //SELECT * FROM TABLE_ WHERE _id = id
        Cursor cursor = db.query(
                TABLE_TRANS_HIST,
                null,
                "_id=?",
                new String[] {Integer.toString(id)},
                null,
                null,
                null);
        //retreieve the data in the assumed order:
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
        Data_TransactionalHistory retrievedData = new Data_TransactionalHistory(
                cursor.getInt(0), //int _id is always the first column
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getInt(4),
                cursor.getInt(5),
                cursor.getInt(6),
                cursor.getInt(7)
        );

        return retrievedData;
    }

    /////////////////END DATABASE SINGLE ID RETRIEVALS////////////////////////

    //***************DATABASE SINGLE ID DELETIONS******************************

    public void deletePersonalInfo(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_PERS_INFO,
                "_id=?",
                new String[] {Integer.toString(id)}
        );
        db.close();
    }

    public void deleteCustomFood(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_CUST_FOODS,
                "_id=?",
                new String[] {Integer.toString(id)}
        );
        db.close();
    }

    public void deleteExternalFood(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_EXT_FOODS,
                "_id=?",
                new String[] {Integer.toString(id)}
        );
        db.close();
    }

    public void deleteTransHistoryRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_TRANS_HIST,
                "_id=?",
                new String[]{Integer.toString(id)}
        );
        db.close();
    }
    /////////////////END DATABASE SINGLE ID DELETIONS//////////////////////////

    //***************DATABASE SINGLE ID UPDATES******************************

    public int updatePersonalInfo(Data_PersonalInfo newRecord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        //update the data in the assumed order:
        //        personal information
        //          _id
        //          name
        //          gender
        //          age
        //          weight
        vals.put(COLNAME_ID, newRecord.getmId());
        vals.put(COLNAME_NAME, newRecord.getmName());
        vals.put(COLNAME_GENDER, newRecord.getmGender());
        vals.put(COLNAME_AGE, newRecord.getmAge());
        vals.put(COLNAME_WEIGHT, newRecord.getmWeight());

        //returns the number of rows affected
        return db.update(TABLE_PERS_INFO,vals,"id=?",new String[] {Integer.toString(newRecord.getmId())});
    }

    public int updateCustomFood(Data_CustomFoods newRecord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        //update the data in the assumed order:
        //        custom foods
        //              id
        //              food_name
        //              calories
        //              proteins
        //              carbohydrdates
        //              fats
        vals.put(COLNAME_ID, newRecord.getmId());
        vals.put(COLNAME_FOODNAME, newRecord.getmFoodName());
        vals.put(COLNAME_CALORIES, newRecord.getmCalories());
        vals.put(COLNAME_PROTEINS, newRecord.getmProtein());
        vals.put(COLNAME_CARBOHYDRATES, newRecord.getmCarbohydrates());
        vals.put(COLNAME_FATS, newRecord.getmFats());

        //returns the number of rows affected
        return db.update(TABLE_CUST_FOODS,vals,"id=?",new String[] {Integer.toString(newRecord.getmId())});
    }

    public int updateExternalFood(Data_ExternalFoods newRecord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        //update the data in the assumed order:
        //        external foods
        //          id
        //          food_name
        //          calories
        //          proteins
        //          carbohydrdates
        //          fats
        vals.put(COLNAME_ID, newRecord.getmId());
        vals.put(COLNAME_FOODNAME, newRecord.getmFoodName());
        vals.put(COLNAME_CALORIES, newRecord.getmCalories());
        vals.put(COLNAME_PROTEINS, newRecord.getmProtein());
        vals.put(COLNAME_CARBOHYDRATES, newRecord.getmCarbohydrates());
        vals.put(COLNAME_FATS, newRecord.getmFats());

        //returns the number of rows affected
        return db.update(TABLE_EXT_FOODS,vals,"id=?",new String[] {Integer.toString(newRecord.getmId())});
    }

    public int updateTransaHistoryRecord(Data_TransactionalHistory newRecord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        //update the data in the assumed order:
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
        vals.put(COLNAME_ID, newRecord.getmId());
        vals.put(COLNAME_FOODNAME, newRecord.getmFoodName());
        vals.put(COLNAME_EATEN_DATE, newRecord.getmEatendate());
        vals.put(COLNAME_EATEN_TIME, newRecord.getmEatentime());
        vals.put(COLNAME_PORTIONSIZE, newRecord.getmPortionSize());
        vals.put(COLNAME_EATEN_CALORIES, newRecord.getmEatenCalories());
        vals.put(COLNAME_EATEN_PROTEINS, newRecord.getmEatenProtein());
        vals.put(COLNAME_EATEN_FATS, newRecord.getmEatenFats());

        //returns the number of rows affected
        return db.update(TABLE_TRANS_HIST,vals,"id=?",new String[] {Integer.toString(newRecord.getmId())});
    }

    /////////////////END DATABASE SINGLE ID UPDATES//////////////////////////

}

