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


    static final String foodsPattern[] = {
            "apple",
            "banana",
            "cheese",
            "fish",
            "pancake"
    };
    static final String[] filenames = {"extdb/FOOD NAME.csv",
            "extdb/NUTRIENT AMOUNT.csv",
            "extdb/CONVERSION FACTOR.csv"
    };

    //Food name has:
    //  FoodGroup ID
    //  Food Source ID
    //      ignored

    //Food Group has:
    //  No additional IDs
    //  Ties with FoodName

    //Nutrient Amount has:
    //  NutrientID
    //  has MULTIPLE Nutrient IDs  though

    //Nutrient Name has:
    //  No additional IDs
    //  Ties with NutrientAmmounr

    //ConversionFactor has
    //  MeasureID
    //  Ties with FoodID
    //  has MULTIPLE measure IDs though

    //Measure Name has
    //  Nothing
    //  Ties with NutrientID from NutAmnt

    static final String idReplacements[] = {
            "extdb/FOOD GROUP.csv",
            "extdb/NUTRIENT NAME.csv",
            "extdb/MEASURE NAME.csv"
    };

    //replacement columns to take off the garbage from the CSV files
    //  note that listColumns can also do this, but its a lot easier doing it here
    //  and this will actually remove the columns from the entries (vs listing the ones that are relevant)
    //  so listColumns does NOT modify the memory but this will
    static final String columnsToKeep[] = {
            "FoodDescription",
            "ScientificName",
            "NutrientID",   //important for connecting corresponding columns
            "NutrientValue",
            "ConversionFactorValue",
            "FoodGroupName",
            "NutrientCode",
            "NutrientSymbol",
            "NutrientUnit",
            "NutrientName",
            "NutrientNameF",
            "Tagname",      //nutrient tag names will be used as heading names
            "NutrientDecimals",
            "MeasureDescription"
    };

    static final String nutrientsToKeep[] = {
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
            "VITAMIN B12, ADDED",
            "VITAMIN B-6",
            "VITAMIN C",
            "VITAMIN D (D2 + D3)",
            "VITAMIN D (INTERNATIONAL UNITS)",
            "VITAMIN D2, ERGOCALCIFEROL",
            "VITAMIN K",
            "ZINC"
    };

    public static void main(String[] args) {
        //testing the converter
        CsvConverter converter = new CsvConverter(foodsPattern);

        converter.getFiles(filenames);
        //reorganize columns
        //converter.reorderColumns();
        //pick which columns to keep
//        converter.chooseOutColumns();
        //ready to read objects
        converter.readObjects();
        //replace the IDs with teh relevant files (n^2 search)
        converter.replaceIDs(idReplacements);
        //now remove the columns that aren't wanted
        converter.replaceColumns(columnsToKeep);
        //do some rearranging of the columns
        //  want to change the multiple "NutrientName" columns to <Nutrient>Name eg ProteinName
        converter.changeNutrientColumnNames();
        //delete the remaining nutrient-columns as they are unneeded
        converter.deleteColumnsWithString("Nutrient");
        //delete the remaining columns that arent of the desired vitamins/minerals
        converter.deleteNonInterestingNutrients(nutrientsToKeep);

        //THE FOODS ARE NOW READY
        converter.listFoods();
        //at this time, all the foods are parsed and ready to turn into SQL queries
        //but which columns do we choose?
//        converter.listFoods();

    }




}
