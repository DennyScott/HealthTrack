package persistence;

public class ExternalDatabaseGenerator {

    //foods patterns based on top 25 most common foods
    //http://www.huffingtonpost.ca/2012/05/31/most-popular-food-and-drinks_n_1560021.html\
    //https://en.wikipedia.org/wiki/Staple_food
    static final String foodsPattern[] = {
            "apple",
            "bacon",
            "banana",
            "bean",
            "beef",
            "berri",
            "berry",
            "broccoli",
            "carrot",
            "cereal",
            "cheese",
            "chicken",
            "coffee",
            "corn",
            "egg",
            "fish",
            "fruit",
            "garlic",
            "grape",
            "mango",
            "milk",
            "onion",
            "orange",
            "pizza",
            "pork",
            "potato",
            "ribs",
            "rice",
            "salad",
            "soup",
            "soy",
            "tea",
            "wheat",
            "yam",
            "yogurt",
            "bread"
    };
    static final String[] filenames = {"extdb/FOOD NAME.csv",
            "extdb/NUTRIENT AMOUNT.csv",
            "extdb/CONVERSION FACTOR.csv"
    };

//    String[] filenames = {
//            "extdb/FOOD NAME.csv",
//            "extdb/FOOD SOURCE.csv",
//            "extdb/FOOD GROUP.csv",
//            "extdb/CONVERSION FACTOR.csv",
//            "extdb/MEASURE NAME.csv",
//            "extdb/REFUSE AMOUNT.csv",
//            "extdb/REFUSE NAME.csv"
//    };
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
    static final String nutrientsToKeep[] = {
            "ALCOHOL",
            "CAFFEINE",
            "CALCIUM",
            "CARBOHYDRATE, TOTAL (BY DIFFERENCE)",
            "CHOLESTEROL",
            "ENERGY (KILOCALORIES)",
            "ENERGY (KILOJOULES)",
            "FAT (TOTAL LIPIDS)",
            //"FATTY ACIDS, MONOUNSATURATED, TOTAL",
            //"FATTY ACIDS, POLYUNSATURATED, TOTAL",
            "FATTY ACIDS, SATURATED, TOTAL",
            "FATTY ACIDS, TRANS, TOTAL",
            "FIBRE, TOTAL DIETARY",
//            "FOLIC ACID",
//            "FRUCTOSE",
//            "GALACTOSE",
//            "GLUCOSE",
            "IRON",
//            "LACTOSE",
            "MAGNESIUM",
//            "MANGANESE",
            "MOISTURE",
//            "NATURALLY OCCURRING FOLATE",
//            "OXALIC ACID",
            "PHOSPHORUS",
            "POTASSIUM",
            "PROTEIN",
//            "RIBOFLAVIN",
            "SODIUM",
            "STARCH",
//            "SUCROSE",
            "SUGARS, TOTAL",
//            "THIAMIN",
            "VITAMIN B-12",
//            "VITAMIN B12, ADDED", no tag
//            "VITAMIN B-6",
            "VITAMIN C",
//            "VITAMIN D (D2 + D3)",        //has a weird tagname, VITD_ �G , messed up the SQL injection
//            "VITAMIN D (INTERNATIONAL UNITS)",
            "VITAMIN D2, ERGOCALCIFEROL"
//            "VITAMIN K",
//            "ZINC"
    };
    private static final boolean LIST_FOODS = false;
    static String timeStartMessage;
    static long timeStart;

    public static void main(String[] args) {
        //testing the converter
        CsvConverter converter = new CsvConverter(foodsPattern);

        converter.getFiles(filenames);

        //ready to read objects
        timeStart("Reading Objects");
        converter.readObjects();
        timeEnd();
        if (LIST_FOODS) converter.listFoods("1rawObjects.txt");

        //replace the IDs with teh relevant files
        timeStart("Replacing IDs");
        converter.replaceIDs(idReplacements);
        timeEnd();
        if (LIST_FOODS) converter.listFoods("2replacedId.txt");

        //now remove the columns that aren't wanted
        timeStart("Removing unwanted columns");
        converter.replaceColumns(columnsToKeep);
        timeEnd();
        if (LIST_FOODS) converter.listFoods("3removedUnwantedColumns.txt");

        //do some rearranging of the columns
        //  want to change the multiple "NutrientName" columns to <Nutrient>Name eg ProteinName
        timeStart("Changing nutrient column names");
        converter.changeNutrientColumnNames();
        timeEnd();
        if (LIST_FOODS) converter.listFoods("4changedNutrientColumnNames.txt");

        //safe to delete tagnames
        converter.deleteColumnsWithString("Tagname");
        //delete the remaining nutrient-columns as they are unneeded (nutrientcode., id)
        converter.deleteColumnsWithString("Nutrient");

        //remove the trailing quotation marks per value
        converter.trimQuotationMarks();
        if (LIST_FOODS) converter.listFoods("5deletedTagnameNutrientQuotes.txt");

        //the 2nd column is scientific name, last is measure description. swap them
        //  to delete later
        converter.swapScientificNameMeasureNameCols();
        //now delete
        converter.deleteColumnsWithString("ScientificName");
        //delete the remaining columns that arent of the desired vitamins/minerals
        timeStart("Deleting non-interesting nutrients");
        converter.deleteNonInterestingNutrients(nutrientsToKeep);
        timeEnd();

        if (LIST_FOODS) converter.listFoods("6nutrientsAfterDeletion.txt");

        //finally, remove the columns matching the patterns:
        //  Column: PROCNT                        Val: PROTEIN
//        Column: PROCNTSymbol                  Val: PROT
//        Column: PROCNTNameF                   Val: PROT�INES
//        Column: Tagname                       Val: PROCNT
//        Column: PROCNTDecimals                Val: 2
        converter.deleteColumnsWithString("Symbol");
        converter.deleteColumnsWithString("NameF");
        converter.deleteColumnsWithString("Decimals");

        converter.deleteColumnsWithExactString("");
        converter.deleteColumnsWithExactString("Unit");
        converter.deleteColumnsWithExactString("Value");

        if (LIST_FOODS) converter.listFoods("7removalOfSymbolFrenchDecimal.txt");

        //generate SQL queries for the foods

        //create the java class object for it
        timeStart("Outputting the Java object classes");
        converter.outputJavaObjectToText("DataExternalFoodDb", "DataNutrientTable");
        timeEnd();
        //output to SQL
        timeStart("Creating the SQLite database");
        converter.createSQLiteDatabase("external_db", "ExternalFoods");
        timeEnd();
    }

    private static void timeEnd() {
        System.out.println("Finished '" + timeStartMessage + "'; Time: " +
                (System.currentTimeMillis() - timeStart));
    }

    private static void timeStart(String s) {
        timeStartMessage = s;
        timeStart = System.currentTimeMillis();
        System.out.println(s);
    }


}
