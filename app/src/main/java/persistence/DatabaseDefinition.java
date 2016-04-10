package persistence;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class DatabaseDefinition extends SQLiteOpenHelper {

    private static final String LOG  = "DatabaseDefinitionTest";
    //for all tables
    private static final String DATABASE_NAME  = "HEALTHTRACK_DB";
    private static String EXTERNAL_DATABASE_PATH = "extdb/external_db.db";
    private static final int BUFFER_SIZE = 1024;
    private static String DATABASE_PATH = ""; //updated in constructor

    private static final int DATABASE_VERSION = 1;
    public static final String CREATE_TABLE   = "CREATE TABLE";
    public static final String IF_NOT_EXISTS  = " IF NOT EXISTS ";

    public static final String DATATYPE_INT   = " INTEGER";
    public static final String DATATYPE_TEXT  = " TEXT";
    public static final String OPT_NOT_NULL   = " NOT NULL";
    public static final String OPT_PRIM_KEY   = " PRIMARY KEY";
    public static final String OPT_DEFAULT    = " DEFAULT";
    public static final String OPT_UNIQUE     = " UNIQUE (";

    public static final String OPT_COMMA      = ",";
    private static final String FOODS_TABLE_CREATE_QUERY_PART = "_id  INTEGER PRIMARY KEY,FoodDescription TEXT,MeasureDescription TEXT,ConversionFactorValue TEXT,FoodGroupName TEXT,PROCNT TEXT,PROCNTUnit TEXT,PROCNTValue TEXT,FAT TEXT,FATUnit TEXT,FATValue TEXT,CHOCDF TEXT,CHOCDFUnit TEXT,CHOCDFValue TEXT,ENERC_KCAL TEXT,ENERC_KCALUnit TEXT,ENERC_KCALValue TEXT,ALC TEXT,ALCUnit TEXT,ALCValue TEXT,WATER TEXT,WATERUnit TEXT,WATERValue TEXT,CAFFN TEXT,CAFFNUnit TEXT,CAFFNValue TEXT,ENERC_KJ TEXT,ENERC_KJUnit TEXT,ENERC_KJValue TEXT,SUGAR TEXT,SUGARUnit TEXT,SUGARValue TEXT,FIBTG TEXT,FIBTGUnit TEXT,FIBTGValue TEXT,CA TEXT,CAUnit TEXT,CAValue TEXT,FE TEXT,FEUnit TEXT,FEValue TEXT,MG TEXT,MGUnit TEXT,MGValue TEXT,P TEXT,PUnit TEXT,PValue TEXT,K TEXT,KUnit TEXT,KValue TEXT,NA TEXT,NAUnit TEXT,NAValue TEXT,ZN TEXT,ZNUnit TEXT,ZNValue TEXT,MN TEXT,MNUnit TEXT,MNValue TEXT,VITC TEXT,VITCUnit TEXT,VITCValue TEXT,THIA TEXT,THIAUnit TEXT,THIAValue TEXT,RIBF TEXT,RIBFUnit TEXT,RIBFValue TEXT,VITB6A TEXT,VITB6AUnit TEXT,VITB6AValue TEXT,VITB12 TEXT,VITB12Unit TEXT,VITB12Value TEXT,VITK TEXT,VITKUnit TEXT,VITKValue TEXT,FOLAC TEXT,FOLACUnit TEXT,FOLACValue TEXT,CHOLE TEXT,CHOLEUnit TEXT,CHOLEValue TEXT,FATRN TEXT,FATRNUnit TEXT,FATRNValue TEXT,FASAT TEXT,FASATUnit TEXT,FASATValue TEXT,FAMS TEXT,FAMSUnit TEXT,FAMSValue TEXT,FAPU TEXT,FAPUUnit TEXT,FAPUValue TEXT,FOLFD TEXT,FOLFDUnit TEXT,FOLFDValue TEXT,ERGCAL TEXT,ERGCALUnit TEXT,ERGCALValue TEXT,SUCS TEXT,SUCSUnit TEXT,SUCSValue TEXT,GLUS TEXT,GLUSUnit TEXT,GLUSValue TEXT,FRUS TEXT,FRUSUnit TEXT,FRUSValue TEXT,LACS TEXT,LACSUnit TEXT,LACSValue TEXT,GALS TEXT,GALSUnit TEXT,GALSValue TEXT,STARCH TEXT,STARCHUnit TEXT,STARCHValue TEXT,F6D0 TEXT,F6D0Unit TEXT,F6D0Value TEXT,F8D0 TEXT,F8D0Unit TEXT,F8D0Value TEXT,F10D0 TEXT,F10D0Unit TEXT,F10D0Value TEXT,F24D0 TEXT,F24D0Unit TEXT,F24D0Value TEXT,MNSAC TEXT,MNSACUnit TEXT,MNSACValue TEXT";
    private static final String FOODS_TABLE_COLUMNS = "_id,FoodDescription,MeasureDescription,ConversionFactorValue,FoodGroupName,PROCNT,PROCNTUnit,PROCNTValue,FAT,FATUnit,FATValue,CHOCDF,CHOCDFUnit,CHOCDFValue,ENERC_KCAL,ENERC_KCALUnit,ENERC_KCALValue,ALC,ALCUnit,ALCValue,WATER,WATERUnit,WATERValue,CAFFN,CAFFNUnit,CAFFNValue,ENERC_KJ,ENERC_KJUnit,ENERC_KJValue,SUGAR,SUGARUnit,SUGARValue,FIBTG,FIBTGUnit,FIBTGValue,CA,CAUnit,CAValue,FE,FEUnit,FEValue,MG,MGUnit,MGValue,P,PUnit,PValue,K,KUnit,KValue,NA,NAUnit,NAValue,ZN,ZNUnit,ZNValue,MN,MNUnit,MNValue,VITC,VITCUnit,VITCValue,THIA,THIAUnit,THIAValue,RIBF,RIBFUnit,RIBFValue,VITB6A,VITB6AUnit,VITB6AValue,VITB12,VITB12Unit,VITB12Value,VITK,VITKUnit,VITKValue,FOLAC,FOLACUnit,FOLACValue,CHOLE,CHOLEUnit,CHOLEValue,FATRN,FATRNUnit,FATRNValue,FASAT,FASATUnit,FASATValue,FAMS,FAMSUnit,FAMSValue,FAPU,FAPUUnit,FAPUValue,FOLFD,FOLFDUnit,FOLFDValue,ERGCAL,ERGCALUnit,ERGCALValue,SUCS,SUCSUnit,SUCSValue,GLUS,GLUSUnit,GLUSValue,FRUS,FRUSUnit,FRUSValue,LACS,LACSUnit,LACSValue,GALS,GALSUnit,GALSValue,STARCH,STARCHUnit,STARCHValue,F6D0,F6D0Unit,F6D0Value,F8D0,F8D0Unit,F8D0Value,F10D0,F10D0Unit,F10D0Value,F24D0,F24D0Unit,F24D0Value,MNSAC,MNSACUnit,MNSACValue";
    private static final String CUSTOM_FOOD_CREATE_QUERY_PART = ", CustomFood TEXT";

    //table creations, for when creating queries
    public static final String TABLE_CREATE_FOODS = " ExternalFoods(";
    public static final String TABLE_CREATE_TRANS_HIST = " TransactionalHistory(";


    //actual table names
    public static final String  TABLE_NAME_FOODS = "ExternalFoods";

    /*
            ExternalFoods
            transactional history (eaten)
                id
                food_name
                eaten_date
                eaten_time
                portion_size
                food_table_id
     */

    //CustFoods Table:
//    private final String COLNAME_ID = "";     //reusable
    private static final String COLNAME_FOODNAME = "FoodDescription";

    //Transactional History Table:
   public static final String COLNAME_ID = "_id";     //reusable
    public static final String COLNAME_EATEN_DATE = "EatenDate";
    public static final String COLNAME_EATEN_TIME = "EatenTime";
    public static final String COLNAME_PORTIONSIZE = "PortionSize";
    public static final String COLNAME_FOODTABLE_ID = "FoodTableID";


    private Context dbContext;

    //keep track of this database context
    public static DatabaseDefinition currentDatabase;


    public DatabaseDefinition(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        dbContext = context;
        loadDatabasePath();
        createDatabase();
        loadNutrientsStaticData();
        currentDatabase = this;
    }

    /**
     * Queries the database and fills the static info (nutrient name, nutrient units) of the DataFoods table.
     */
    private void loadNutrientsStaticData() {
        SQLiteDatabase db = getReadableDatabase();
        String[] nutrientColumnNames = DataFoods.getAllNutrientColumnNames();
        ArrayList<String> allNutrientNames = new ArrayList<String>();
        //get all the rows (so to find the one that has appropriate data)
        Cursor cursor = db.query(TABLE_NAME_FOODS,nutrientColumnNames,null,null,null,null,null);

        //algorith:
        //  for each column, find a row containing a non "" value to store into the nutrient names
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            //go to the first row to
            cursor.moveToFirst();
            while (cursor.getString(i).equals("")) {
                //break if there is no more nexxts
                if (!cursor.moveToNext()) break;
            }
            //at this point, cursor should have an actual string value, or else something went wrong
            if (!cursor.getString(i).equals("")) {
                allNutrientNames.add(cursor.getString(i));
            }
            else {
                //something went wrong, no name for this nutrient
                throw new Error("Column " + nutrientColumnNames[i] + "did not contain any valid entries");
            }
        }


        db.close();
        String[] result = new String[allNutrientNames.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = allNutrientNames.get(i);
        }
        DataFoods.allNutrientNames = result;
    }

    public DatabaseDefinition(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
        dbContext = context;
        loadDatabasePath();
        createDatabase();
        loadNutrientsStaticData();
        currentDatabase = this;
    }

    private void loadDatabasePath() {
        DATABASE_PATH = dbContext.getDatabasePath(DATABASE_NAME).getAbsolutePath();
        //
        AssetManager assets = dbContext.getAssets();
        try {
            String[] files = assets.list("");
            for (String f : files) {
                if (f.contains("external_db.db")) {
                    EXTERNAL_DATABASE_PATH = f;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean checkDatabase() {
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DATABASE_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException ignored){

        }

        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null;
    }

    /**
     * Call this to create the database as it will check if there is an existing file
     * to import as well as calling onCreate as needed.
     */
    public void createDatabase() {
        //first try to load the external database file external_db.db
        //  this file contains the CNF data entries generated from the ExternalDatabaseGenerator

        boolean extDbExist = checkDatabase();

        if (extDbExist) {

        }
        else {
            try {
                //copy database will overwrite any database created by android for this app
                copyDatabase();

                //call the oncreate method to create the db
                SQLiteDatabase db = currentDatabase.getReadableDatabase();
                //create the transcational history database


                //        transactional history (eaten)
                //          id
                //          food_name
                //          eaten_date
                //          eaten_time
                //          portion_size
                //          FoodTableID
                //  The strategy is look up the food eaten, multiple it by the portion size, and then return
                //  that value in that date range only
                //week-month2016
                db.execSQL(
                        CREATE_TABLE + IF_NOT_EXISTS + TABLE_CREATE_TRANS_HIST +
                                COLNAME_ID              + DATATYPE_INT  + OPT_PRIM_KEY + OPT_COMMA +
                                COLNAME_FOODNAME        + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                                COLNAME_EATEN_DATE      + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                                COLNAME_EATEN_TIME      + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                                COLNAME_PORTIONSIZE     + DATATYPE_TEXT + OPT_NOT_NULL + OPT_COMMA +
                                COLNAME_FOODTABLE_ID    + DATATYPE_INT + OPT_NOT_NULL + ");"
                );
                currentDatabase = this;

            }
            catch (IOException e) {
                throw new Error("Error copying external database: " + e.getMessage());
            }
        }

    }

    private void copyDatabase() throws IOException {
        //open the external database
        InputStream extDbInput = dbContext.getAssets().open(EXTERNAL_DATABASE_PATH);
        //create the local database
        SQLiteDatabase db = this.getReadableDatabase();
        OutputStream dbOutput = new FileOutputStream(DATABASE_PATH);

        //do a byte transfer
        byte[] buffer = new byte[BUFFER_SIZE];
        int buffer_length;
        while ((buffer_length = extDbInput.read(buffer)) > 0) {
            dbOutput.write(buffer,0,buffer_length);
        }
        //close the streams
        dbOutput.flush();
        dbOutput.close();
        extDbInput.close();
        currentDatabase = this;
    }

    //only called when the database is FIRST created (by getWritable/ReadableDatabase )
    @Override
    public void onCreate(SQLiteDatabase db) {
        //does nothing as its handled by createDatabase()
    }

    private String getFoodsTableParamaters() {
        return FOODS_TABLE_CREATE_QUERY_PART;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //unlikely the database will ever be updated
        // override to null
    }


    //*************DATABASE INSERTIONS*************************


    public long createTransactionalHistory(DataTransactionalHistory data) {
        SQLiteDatabase db = currentDatabase.getWritableDatabase();
        ContentValues vals = new ContentValues();
        //        transactional history (eaten)
        //          id
        //          food_name
        //          eaten_date
        //          eaten_time
        //          portion_size
        //          food_id
        vals.put(COLNAME_ID, data.getmId());
        vals.put(COLNAME_FOODNAME, data.getmFoodName());
        vals.put(COLNAME_EATEN_DATE, data.getmEatendate());
        vals.put(COLNAME_EATEN_TIME, data.getmEatentime());
        vals.put(COLNAME_PORTIONSIZE, data.getmPortionSize());
        vals.put(COLNAME_FOODTABLE_ID, data.getmFoodTableID());

        //get the ID of the resulting insertion
        return db.insert(TABLE_CREATE_TRANS_HIST, null, vals);
    }

    /////////////////END DATABASE INSERTIONS////////////////////////

    //***************DATABASE SINGLE ID RETRIEVALS****************************


    public int findFoodById(String foodName) {
        SQLiteDatabase db = currentDatabase.getWritableDatabase();
        int result = -1;
        //SELECT * FROM TABLE_ WHERE FOOD_DESCRIPTION = foodName
        Cursor cursor = db.query(
                TABLE_NAME_FOODS,
                new String[] {COLNAME_ID},
                foodName + "=" + foodName,
                null,
                null,
                null,
                null);

        if (cursor.getCount() > 0) {
            result = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return result;
    }


    public DataTransactionalHistory getTransactionalHistory(int id) {
        SQLiteDatabase db = currentDatabase.getWritableDatabase();

        //SELECT * FROM TABLE_ WHERE _id = id
        Cursor cursor = db.query(
                TABLE_CREATE_TRANS_HIST,
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
        //          foodTableID
        DataTransactionalHistory retrievedData = new DataTransactionalHistory(
                cursor.getInt(0), //int _id is always the first column
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4)
        );

        return retrievedData;
    }

    public DataTransactionalHistory getSumTransactionalHistory(int id) {
        SQLiteDatabase db = currentDatabase.getWritableDatabase();

        //SELECT * FROM TABLE_ WHERE _id = id
        Cursor cursor = db.query(
                TABLE_CREATE_TRANS_HIST,
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
        DataTransactionalHistory retrievedData = new DataTransactionalHistory(
                cursor.getInt(0), //int _id is always the first column
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4)
        );

        return retrievedData;
    }

    /////////////////END DATABASE SINGLE ID RETRIEVALS////////////////////////

    //***************DATABASE SINGLE ID DELETIONS******************************

    public void deleteCustomFood(int id) {
        SQLiteDatabase db = currentDatabase.getWritableDatabase();
        db.delete(
                TABLE_CREATE_FOODS,
                "_id=?",
                new String[] {Integer.toString(id)}
        );
        db.close();
    }

    public void deleteTransHistoryRecord(int id) {
        SQLiteDatabase db = currentDatabase.getWritableDatabase();
        db.delete(
                TABLE_CREATE_TRANS_HIST,
                "_id=?",
                new String[]{Integer.toString(id)}
        );
        db.close();
    }
    /////////////////END DATABASE SINGLE ID DELETIONS//////////////////////////

    //***************DATABASE SINGLE ID UPDATES******************************

    public int updateTransaHistoryRecord(DataTransactionalHistory newRecord) {
        SQLiteDatabase db = currentDatabase.getWritableDatabase();
        ContentValues vals = new ContentValues();
        //update the data in the assumed order:
        //        transactional history (eaten)
        //          id
        //          food_name
        //          eaten_date
        //          eaten_time
        //          portion_size
        //          foodTableId
        vals.put(COLNAME_ID, newRecord.getmId());
        vals.put(COLNAME_FOODNAME, newRecord.getmFoodName());
        vals.put(COLNAME_EATEN_DATE, newRecord.getmEatendate());
        vals.put(COLNAME_EATEN_TIME, newRecord.getmEatentime());
        vals.put(COLNAME_PORTIONSIZE, newRecord.getmPortionSize());
        vals.put(COLNAME_FOODTABLE_ID, newRecord.getmFoodTableID());

        //returns the number of rows affected
        return db.update(TABLE_CREATE_TRANS_HIST,vals,"id=?",new String[] {Integer.toString(newRecord.getmId())});
    }

    /////////////////END DATABASE SINGLE ID UPDATES//////////////////////////

    public static DataFoods getFood(int foodTableId) {
        DataFoods result = null;
        SQLiteDatabase db = currentDatabase.getReadableDatabase();
        Cursor cursor = db.query(
                DataFoods.TABLE_NAME_FOODS,
                null,
                DataFoods.COLNAME_ID + " = " + foodTableId,
                null,
                null,
                null,
                null
        );

        if (cursor.getCount() > 0) {
            //create a food from the first entry
            cursor.moveToFirst();
            result = DataFoods.createFoodFromRow(cursor);
        }
        else {
            //TODO no results returned
        }

        db.close();

        return result;
    }

}

