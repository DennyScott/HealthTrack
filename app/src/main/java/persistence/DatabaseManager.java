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
            "cheese"
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
//            "VITAMIN B12, ADDED", no tag
            "VITAMIN B-6",
            "VITAMIN C",
            "VITAMIN D (D2 + D3)",
//            "VITAMIN D (INTERNATIONAL UNITS)",
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
        timeStart("Reading Objects");
        converter.readObjects();
        timeEnd();
        //replace the IDs with teh relevant files
        timeStart("Replacing IDs");
        converter.replaceIDs(idReplacements);
        timeEnd();
        //now remove the columns that aren't wanted
        timeStart("Removnig unwatned columns");
        converter.replaceColumns(columnsToKeep);
        timeEnd();
        //converter.listFoods();
        //do some rearranging of the columns
        //  want to change the multiple "NutrientName" columns to <Nutrient>Name eg ProteinName

        timeStart("Changing nutrient column names");
        converter.changeNutrientColumnNames();
        timeEnd();
        //delete the remaining nutrient-columns as they are unneeded
        converter.deleteColumnsWithString("Nutrient");
        //converter.listFoods("deleteBeforeNutrients.txt");

        //remove the trailing quotation marks per value
        converter.trimQuotationMarks();

        //the 2nd column is scientific name, last is measure description. swap them
        //  to delete later
        converter.swapScientificNameMeasureNameCols();
        //delete the remaining columns that arent of the desired vitamins/minerals
        converter.deleteNonInterestingNutrients(nutrientsToKeep);

        converter.listFoods("nutrientsAfterDeletion.txt");

        //finally, remove the columns matching the patterns:
        //  Column: PROCNT                        Val: PROTEIN
//        Column: PROCNTSymbol                  Val: PROT
//        Column: PROCNTNameF                   Val: PROT�INES
//        Column: Tagname                       Val: PROCNT
//        Column: PROCNTDecimals                Val: 2
        converter.deleteColumnsWithString("Symbol");
        converter.deleteColumnsWithString("NameF");
        converter.deleteColumnsWithString("Tagname");
        converter.deleteColumnsWithString("Decimals");

        converter.listFoods("removalOfSymbolFrenchTagDecimal.txt");

        //generate SQL queries for the foods

        //create the java class object for it
        converter.outputJavaObjectToText("DataExternalFoodDb","DataNutrientTable");

        //output to SQL
        converter.createSQLiteDatabase("external_db","ExternalFoods");
    }

    private static void timeEnd() {
        System.out.println("Finished '" + timeStartMessage + "'; Time: " +
                (System.currentTimeMillis() - timeStart));
    }

    static String timeStartMessage;
    static long timeStart;
    private static void timeStart(String s) {
        timeStartMessage = s;
        timeStart = System.currentTimeMillis();
        System.out.println(s);
    }



}
