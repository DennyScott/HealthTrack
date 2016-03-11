package persistence;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager  {


//    String[] filenames = {
//            "extdb/FOOD NAME.csv",
//            "extdb/FOOD SOURCE.csv",
//            "extdb/FOOD GROUP.csv",
//            "extdb/CONVERSION FACTOR.csv",
//            "extdb/MEASURE NAME.csv",
//            "extdb/REFUSE AMOUNT.csv",
//            "extdb/REFUSE NAME.csv",
//            "extdb/YIELD AMOUNT.csv",
//            "extdb/YIELD NAME.csv"
//    };


    static String foodsPattern[] = {
            "apple",
            "banana",
            "cheese",
            "fish",
            "pancake"
    };
    static String[] filenames = {"extdb/FOOD NAME.csv",
        "extdb/FOOD GROUP.csv",
        "extdb/CONVERSION FACTOR.csv",
        "extdb/MEASURE NAME.csv",
        "extdb/NUTRIENT AMOUNT.csv"
};


    public static void main(String[] args) {
        //testing the converter
        CsvConverter converter = new CsvConverter(foodsPattern);

        converter.getFiles(filenames);
        //reorganize columns
        //converter.reorderColumns();
        //pick which columns to keep
//        converter.chooseOutColumns();
        converter.printPrimaryKeys();
        //ready to read objects
        converter.readObjects();
        converter.listFoods();
        //open csvs
        //  extract columns (first lines)
        //      put in a list
        //  highlight common columns
        //      put them together in another list
        //      store this list in static class of food objects
        //list columns + common ones
        //  number their output
        //      get space separated nums = columns of interest
        //  <order them>
        //      with 2 number pair swaps
        //go through every file again
        //  put data into food objects, checking for unique common column data
        //      data entry point:
    }




}
