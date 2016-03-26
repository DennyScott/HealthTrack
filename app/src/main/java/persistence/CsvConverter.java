package persistence;

import org.sqlite.JDBC;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class CsvConverter {
    private static final int COLUMNS_BEFORE_NUTRIENT_DATA = 4;
    private static final Charset CHARACTER_SET = StandardCharsets.ISO_8859_1;
    private static final String NULL_STRING = "";
    static ArrayList<String> commonCols;
    static ArrayList<String> allCols;
    static ArrayList<String> outCols;
    static ArrayList<String> files;
    static Foods foods;
    static String[] foodsPattern;
    static ArrayList<String> allPossibleCols;
    //    String[] filenames = {
//            "extdb/FOOD NAME.csv",
//            "extdb/FOOD SOURCE.csv",
//            "extdb/FOOD GROUP.csv",
//            "extdb/CONVERSION FACTOR.csv",
//            "extdb/MEASURE NAME.csv",
//            "extdb/REFUSE AMOUNT.csv",
//            "extdb/REFUSE NAME.csv"
//    };


    public CsvConverter(String[] foodsPattern) {
        commonCols = new ArrayList<String>();
        allCols = new ArrayList<String>();
        outCols = new ArrayList<String>();
        files = new ArrayList<String>();
        foods = new Foods(commonCols, allCols, outCols, files);
        this.foodsPattern = foodsPattern;
//        getFiles();
        //reorganize columns
        //reorderColumns();
        //pick which columns to keep
//        chooseOutColumns();
        //ready to read objects
//        readObjects();
//        listFoods();
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

    public void listFoods(String filename) {
        int i;
        BufferedWriter bufferedWriter;
        try {
            //if (!file.exists()) file.createNewFile();
//            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename),CHARACTER_SET));
            for (Foods f : Foods.entries) {
                //print out the columns and entries
                for (i = 0; i < f.vals.cols.size(); i++) {
                    //if (Foods.isCommonCol(f.vals.cols.get(i))) {
                    bufferedWriter.write(String.format("Column: %-30sVal: %s\n",
                            f.vals.cols.get(i),
                            f.vals.data.get(i)));
                    //}
                }
                bufferedWriter.write("\nNext Food:\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * go through files again and read their data into the right object's vals
     */
    public void readObjects() {
        String line;
        ArrayList<String> filecols;
        BufferedReader filein;

        //if this is the first file, add all as new foods
        boolean firstFile = true;
        for (String f : files) {
            try {
                //for each file, open it first
                filein = new BufferedReader(new InputStreamReader(new FileInputStream(f),CHARACTER_SET));
//                filein = new BufferedReader(new FileReader(f));
                //get the first line
                line = filein.readLine();
                //split it into the columns and add it to the arraylist
                filecols = new ArrayList<String>(
                        Arrays.asList(
                                line.split(","))
                );

                //iterate through the rest of the file, creating/adding the objects
                String data[];
                //FOR EACH LINE THAT IS NOT THE FIRST FILE
                //  Parse every food afterwards
                while ((line = filein.readLine()) != null) {
                    data = splitLineIntoData(line, ",");
                    //skip if this line is empty
                    if (data.length == 0) break;

                    if (firstFile) {
                        //if its the first file, add the food directly because
                        //  the firts file will have no duplicate foods
                        foods.addNewFood(filecols, data);
                    } else {
                        //use this data from the file to see if
                        addUniqueFoodData(filecols, data);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (firstFile) {
                firstFile = false;
                //parse the foods to remove the ones that we don't want
                Foods.pickOnlyWantedFoods(foodsPattern);
            }
        }
    }

    private void printFoods() {
        for (Foods f : Foods.entries) {
            System.out.println(f.vals.data.get(f.vals.cols.indexOf("FoodDescription")));
        }
    }

    //purpose: data in the file sometimes contains information that has commas, but at least it begins with quotes
    //so delimit by quotes
    private String[] splitLineIntoData(String line, String delimiter) {
        //split the line as usual
        String[] tokens = line.split(delimiter);
        ArrayList<String> result = new ArrayList<String>();
        String commaVal;
        String dblOrSingQuote;
        for (int i = 0; i < tokens.length; i++) {
            if (!tokens[i].contains("\"")) {
                //if the line has no quotation marks, do not find the end-quote mark. just add this directly
                result.add(tokens[i]);
            } else {
                //this value from the CSV file contains a comma, so look for the closing quote
                commaVal = "";
                //in the event that this is a double-quotes situation, search until double quotes
                dblOrSingQuote = !tokens[i].contains("\"\"") ? "\"" : "\"\"";
                do {
                    commaVal += tokens[i];
                    i++;
                    if (i == tokens.length) {
                        break;
                    }
                    if (!tokens[i].contains(dblOrSingQuote)) {
                        //add a comma if not end of file
                        commaVal += ',';
                    }
                } while (!tokens[i].contains(dblOrSingQuote));
                //the next value has a quote, add it manually
                if (i < tokens.length) commaVal += ',' + tokens[i];
                result.add(commaVal);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    private void addUniqueFoodData(ArrayList<String> filecols, String[] data) {
        //see if this food exists first
        int indexOfFood = foods.hasFood(filecols, data);
        if (indexOfFood != -1) {
            //food exists already, add to it extra information
            foods.addUniqueData(indexOfFood, filecols, data);
        } else {
            //new food found
            //      NEW UPDATE: Don't add new foods because we deleted the other ones that didnt
            //          match the pattern
            //foods.addNewFood(filecols, data);
        }
    }

    public void chooseOutColumns() {
        listColumns();
        System.out.println("Which columns desired for final output? (space separated)");
        Scanner cin = new Scanner(System.in);
        String input = cin.nextLine();

        String[] desiredCols = input.split(" ");

        for (String c : desiredCols) {
            outCols.add(
                    allCols.get(
                            Integer.parseInt(c)
                    )
            );
        }
    }

    public void printPrimaryKeys() {
        //list the current columns in commonCols
        for (String c : commonCols) {
            System.out.println(commonCols.indexOf(c) + ": " + c);
        }
    }

    public void reorderColumns() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Re-order columns? y/n: ");
        String input = cin.nextLine();

        while (!input.equalsIgnoreCase("n")) {
            listColumns();
            promptSwap();
            System.out.println("Swap again? y/n");
            input = cin.nextLine();
        }
        listColumns();
        System.out.println("^ Final order ^");
    }

    public void promptSwap() {
        System.out.printf("Which columns to swap (space separated)?: ");
        Scanner cin = new Scanner(System.in);

        String swapNums = cin.nextLine();
        String[] tokens = swapNums.split(" ");

        //swap the allCols
        //store temp
        String temp = allCols.get(
                Integer.parseInt(
                        tokens[1])
        );

        //replace token 1's position
        allCols.set(
                Integer.parseInt(tokens[1]),
                allCols.get(
                        Integer.parseInt(tokens[0])
                )
        );
        //replace token 0's position
        allCols.set(
                Integer.parseInt(tokens[0]),
                temp
        );
    }

    public void listColumns() {
        //list the current columns in allcols
        for (String c : allCols) {
            System.out.println(allCols.indexOf(c) + ": " + c);
        }
    }

    /**
     * Gets the column headings from the CSV files and compiles them together. Calls {@link #joinColumns(String[])}
     *
     * @param filenames The files to extract the columns from
     */
    public void getFiles(String[] filenames) {
        for (String f : filenames) {
            //f are filenames for each passed csv
            files.add(f);
            joinColumns(
                    openCsvGetColumns(f)
            );
        }
    }

    //add the columns to the corresponding list
    //if duplicate, add to commons (duplicate = primary ID)

    /**
     * add the columns to the corresponding list
     * if duplicate, add to commons (duplicate = primary ID)
     *
     * @param columns
     */
    public void joinColumns(String[] columns) {
        for (String c : columns) {
            if (allCols.contains(c)) {
                //common column
                if (!commonCols.contains(c)) commonCols.add(c);
            } else {
                //new column
                allCols.add(c);
            }
        }
    }

    /**
     * open a file and read the first line, splitting it by commas
     * return null on empty file
     */
    public String[] openCsvGetColumns(String filename) {
        try {
//            (BufferedReader br = new BufferedReader(
//                new InputStreamReader(
//                        new FileInputStream(filename),CHARACTER_SET
//                )
//        ))
//            BufferedReader br = new BufferedReader(new FileReader(filename));
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),CHARACTER_SET));

            //read only first line
            String line = br.readLine();
            return line.split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method assumes that the current food entries have columns in the form of
     *  (type)ID. This will be checked in the idReplacements files and appended to each food
     * @param idReplacements A list of files to replace the IDs for in the existing food entries
     */
    public void replaceIDs(String[] idReplacements) {
        //for each food, replace its column heading with the corresponding data
        File file;
        String line;
        String[] lineColumns;
        String primaryKey;
        String foodPrimaryKeyValue;
        //initialize linevals to appease compiler...
        String[] lineVals = new String[0];
        String fileData;
        BufferedReader br;
        for (String idFile : idReplacements) {
            file = new File(idFile);
            try {
//                br = new BufferedReader(new FileReader(file));
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file),CHARACTER_SET));
                //read the first line to get the list of columns in this file
                line = br.readLine();
                lineColumns = line.split(",");
                //the first column is the primary key to be finding/assessing for each food
                primaryKey = lineColumns[0];
                //now for each food, append the correct columns for this food from this file
                for (Foods existingFood : Foods.entries) {
                    //for each column of the food (since there could be multiple columns)
                    for (int indexOfkey = 0; indexOfkey < existingFood.vals.cols.size(); indexOfkey++) {
                        //if this is the primary key we're looking for, we're ready to append
                        if (existingFood.vals.cols.get(indexOfkey).equalsIgnoreCase(primaryKey)) {
                            //get this food's corresponding primary key value
                            foodPrimaryKeyValue = existingFood.vals.data.get(indexOfkey);

                            //reset the file marker
                            br.close();
            //                br = new BufferedReader(new FileReader(file));
                            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),CHARACTER_SET));
                            //skip the first line since its just columns
                            br.readLine();

                            //now find the right ID for this food in the idPatternFile
                            // and match it to the corresponding column val
                            while ((line = br.readLine()) != null) {
                                lineVals = splitLineIntoData(line,",");
                                //the first value from the file = the primry key value to check against this food
                                if (foodPrimaryKeyValue.equals(lineVals[0])) {
                                    //we found the line to append
                                    //leave the loop to begin appending
                                    break;
                                }
                            }

                            //code reaching here means we found this food's right line to append; do that
                            //  we assume that each food has a corresponding entry
                            //skip the first column because its the primary key, append the others
                            for (int i = 1; i < lineVals.length; i++) {
                                //add the column
                                existingFood.vals.cols.add(lineColumns[i]);
                                //add the data, making sure if its null to make it an empty str at least
                                fileData = lineVals[i] == null ? "" : lineVals[i];
                                existingFood.vals.data.add(fileData);
                            }


                        }
                    }

                    //this food now has its columns appended, go onto the next one
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void swapColumns(){
        //
    }
    /**
     * For each food, loop through each column and remove the corresponding column/data entries
     * @param columnsToKeep
     */
    public void replaceColumns(String[] columnsToKeep) {

        Iterator<String> foodColumn;
        Iterator<String> foodValue;
        String curColumn;
        boolean keepThis;


        Iterator<Foods> it;
        it = Foods.entries.iterator();
        Foods thisFood;
        while (it.hasNext()) {
            thisFood = it.next();
            //for each column/heading, remove the ones that aren' relevant
            foodColumn = thisFood.vals.cols.iterator();
            foodValue = thisFood.vals.data.iterator();
            //loop through each column
            while (foodColumn.hasNext() && foodValue.hasNext()) {
                //Store the column to compare it to the accepted ones
                curColumn = foodColumn.next();
                //advance the value iterator; doesnt matter what its value is, we will remove it regardless
                foodValue.next();
                //see if this column exists in the columns to keep
                keepThis = false;
                for (int i = 0; i < columnsToKeep.length; i++) {
                    if (curColumn.equalsIgnoreCase(columnsToKeep[i])) {
                        //if this currently checking column is equal to ANY of the allowed ones, stop checking
                        keepThis = true;
                        break;
                    }
                }

                if (!keepThis) {
                    //this column wasnt in the ones to keep, so remove the iterator values
                    foodColumn.remove();
                    foodValue.remove();
                }

            }
        }
    }

    public void swapArrayListElements(ArrayList<String> arrayList, int indexA, int indexB) {
        String temp = arrayList.get(indexA);
        arrayList.set(indexA, arrayList.get(indexB));
        arrayList.set(indexB, temp);
    }

    public void swapCorrespondingColumnAndValue(ArrayList<String> listA, ArrayList<String> listB, int indexA, int indexB) {
        swapArrayListElements(listA, indexA, indexB);
        swapArrayListElements(listB, indexA, indexB);
    }

    public void changeNutrientColumnNames() {
        //since nutrient amount table is read first, nutrientID and nutrientValue exist before the
        //  nutrient names. But they appear in the exact sequential order aferwards.
        //  Algorithm:
        //      DO
            //      Use two integers, A and B
            //      Per NutrientID, let its index be A, find the corresponding NutrientCode at index B (sequentially)
            //      A+1 index == NutrientValue. Swap this with Nutrient Code at B
            //          ie swap (A+1, B)
            //      B + 3 == NutrientName column. Swap NutrientName with NutrientValue so nutrientName comes first
            //          this will simplify subsequent steps
            //          ie swap (B, B+3)
        //          Data[B] now equals the column name.
        //              Do
        //                  replace the Nutrient part of the column name with the tag name
        //                  the tagname exists at B+5
        //              while Col[B] != NutrientCode
        //      Loop until no more nutrientIDs found

        int a;
        int b;
        int i;
        int tagpos;
        for (Foods f : Foods.entries) {
            a = -1;
            b = -1;
            while (a < f.vals.cols.size()) {
                i = ++a;
                while (i < f.vals.cols.size()) {
                    if (f.vals.cols.get(i).equalsIgnoreCase("NutrientID")) {
                        break;
                    }
                    i++;
                }
                a = i;
                if (a >= f.vals.cols.size()) break;

                i = ++b;
                while (i < f.vals.cols.size()) {
                    if (f.vals.cols.get(i).equalsIgnoreCase("NutrientCode")) {
                        break;
                    }
                    i++;
                }
                b = i;
                if (b >= f.vals.cols.size()) break;
                swapCorrespondingColumnAndValue(f.vals.cols,f.vals.data,a+1,b);
                swapCorrespondingColumnAndValue(f.vals.cols,f.vals.data,b,b+3);
                tagpos = b+5;
                f.vals.cols.set(b,f.vals.data.get(tagpos));
                b++;
                do {
                    f.vals.cols.set(b,f.vals.cols.get(b).replace("Nutrient",
                            f.vals.data.get(tagpos)));
                    b++;
                } while (b < f.vals.cols.size() && !f.vals.cols.get(b).equalsIgnoreCase("NutrientCode"));
                //subtract B to offset the b+1 in the for loop
                b--;
            }
        }
    }
    //delete all columns containing a string
    public void deleteColumnsWithString(String pattern) {
        for (Foods f : Foods.entries) {
            //delete them backwards so it doesnt affect the remaining elements
            for (int i = f.vals.cols.size() - 1; i > 0; i--) {
                if (f.vals.cols.get(i).contains(pattern)) {
                    //delete these columns from the corresponding tables
                    f.vals.cols.remove(i);
                    f.vals.data.remove(i);
                    //i will now point to the element AFTER i, so no adjusting is needed
                }
            }
        }
    }

    //delete all columns MATCHING the string
    public void deleteColumnsWithExactString(String pattern) {
        for (Foods f : Foods.entries) {
            //delete them backwards so it doesnt affect the remaining elements
            for (int i = f.vals.cols.size() - 1; i > 0; i--) {
                if (f.vals.cols.get(i).equals(pattern)) {
                    //delete these columns from the corresponding tables
                    f.vals.cols.remove(i);
                    f.vals.data.remove(i);
                    //i will now point to the element AFTER i, so no adjusting is needed
                }
            }
        }
    }

    public void deleteCorrespondingArrayElements(ArrayList<String> a, ArrayList<String> b, int index) {
        System.out.println("removing :" + a.get(index) + " and " + b.get(index));
        a.remove(index);
        b.remove(index);
    }
    //delete all nutrients that do not fit in the accepted pattern
    //TOO BUGGED
    public void deleteNonInterestingNutrients(String[] nutrientsToKeep) {
        /*
            Algorithm:
                The 5th array element = first nutrient
                Adding 7 brings you to the next nutrient
                    starting from here
                    if this food matches any of the string patterns, keep it
                    if it doesnt
                        delete this and the next 7 elements

         */
        int i;
        Iterator<String> colIt;
        Iterator<String> valsIt;
        int indexOfPrev;
        String checkNutrientName;
        ArrayList<Integer> removeValues;
        int removeValsSize;
        int thisIndexToRemove;

        String col;
        String val;
        boolean match;
        for (Foods f : Foods.entries) {
            //create new instances of the removals
            removeValues = new ArrayList<Integer>();
            colIt = f.vals.cols.iterator();
            valsIt = f.vals.data.iterator();

            /*
            Previous delete attempts were met with index issues
                Algorithm:
                    To delete a sequence of nutrients, find the Symbol part of the name
                    Go back one
                    Delete until <>Decimals
                    Delete <>Decimal
                    Continue
             */

            while (colIt.hasNext()) {
                col = colIt.next();
                val = valsIt.next();

                if (col.contains("Symbol")) {
                    //this is a symbol, go back one to get the nutrient name
                    indexOfPrev = f.vals.cols.indexOf(col) - 1;
                    checkNutrientName = f.vals.data.get(indexOfPrev);

                    //now check if its in the list of keeping nutrients
                    match = false;
                    for (i = 0; i < nutrientsToKeep.length; i++) {
                        if (checkNutrientName.equalsIgnoreCase(nutrientsToKeep[i])){
                            match = true;
                            break;
                        }
                    }

                    if (!match) {
                        //dont keep this nutrient, ADD ALL TO DELETE LIST until "Decimals"
                        removeValues.add(indexOfPrev++);
                        while (!col.contains("Decimals")) {
                            //add the remaining columns until the next nutrient
                            col = colIt.next();
                            val = valsIt.next();
                            removeValues.add(indexOfPrev++);
                        }
                        //remove the decimals column now
                        if ((!col.contains("Decimals"))) throw new AssertionError();
                        if (col.contains("Decimals")) {
                            removeValues.add(indexOfPrev);
                        }
                    }
                }
            }
            //now we have an array list of columns to remove from this food. do that
            //for the values to be removed, we need to iterate through them backwards since multiple
            // values can still exist
            //store the size so it doesnt change

            removeValsSize = removeValues.size();
            for (int j = 0; j < removeValsSize; j++) {
                //get the last index and remove it
                thisIndexToRemove = removeValues.get(removeValues.size() - 1);
                removeValues.remove(removeValues.size() -1);
                f.vals.cols.remove(thisIndexToRemove);
                f.vals.data.remove(thisIndexToRemove);
            }
        }
    }

    private void advanceIterators(Iterator<String> it1, Iterator<String> it2, int numToAdvance) {
        for (int i = 0; i < numToAdvance && it1.hasNext() && it2.hasNext(); i++) {
            it1.next();
            it2.next();
        }
    }

    public void trimQuotationMarks() {
        for (Foods f : Foods.entries) {
            for (int i = 0; i < f.vals.data.size(); i++) {
                String thisData = f.vals.data.get(i);
                if (thisData.length() > 0) {
                    if (thisData.substring(0, 1).equals("\"")) {
                        //this value has leading/trailing quotes. remove them
                        thisData = thisData.substring(1, thisData.length() - 1);
                        f.vals.data.set(i, thisData);
                    }
                }
            }
        }
    }

    public void swapScientificNameMeasureNameCols() {
        for (Foods f : Foods.entries) {
            if (f.vals.cols.get(f.vals.cols.size() - 1).equals("MeasureDescription")) {
                swapCorrespondingColumnAndValue(f.vals.cols, f.vals.data, 1, f.vals.cols.size() - 1);
            }
        }
    }

    public void outputJavaObjectToText(String masterObjectClassName, String nutrientClassName) {
        //make a master list of column headings, containing all possible columns
        allPossibleCols = new ArrayList<String>();
        for (Foods f : Foods.entries) {
            for (int i = 0; i < f.vals.cols.size(); i++) {
                //check if this column exists and add if not
                if (!allPossibleCols.contains(f.vals.cols.get((i)))) {
                    allPossibleCols.add(f.vals.cols.get(i));
                }
            }
        }

        //Assume allPossibleCols are table headings
        //
        String masterBuffer = "class " + masterObjectClassName + "{\n";
        String nutrientBuffer = "class " + nutrientClassName + "{\n";
        //add the ID primary key to the buffer
        masterBuffer += "int mId;\n";
        //add the rest of the columns (4 of them) until the first nutrient as method headings
        for (int i = 0; i < COLUMNS_BEFORE_NUTRIENT_DATA; i++) {
            masterBuffer += "String m" + allPossibleCols.get(i) + ";\n";
        }
        //add the nutrient class association
        masterBuffer += nutrientClassName + " m" + nutrientClassName + ";\n";

        //create the nutrient class object's string parameters

        for (int i = COLUMNS_BEFORE_NUTRIENT_DATA; i < allPossibleCols.size(); i++) {
            nutrientBuffer += "String m" + allPossibleCols.get(i) + ";\n";
        }

        //close the braces
        masterBuffer += "}";
        nutrientBuffer += "}";

        //assume the user will do the refractoring, encapsulation, etc

        //output the to text files
        writeToFile(masterBuffer, masterObjectClassName + ".java");
        writeToFile(nutrientBuffer, nutrientClassName + ".java");

    }

    public void writeToFile(String buffer, String filename) {
        BufferedWriter bufferedWriter;
        try {
            //if (!file.exists()) file.createNewFile();
//            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename),CHARACTER_SET));
            bufferedWriter = new BufferedWriter(new FileWriter(filename));
            bufferedWriter.write(buffer);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createSQLiteDatabase(String external_db, String tableName) {

        Connection connection = null;
        //for sending statements to the db
        Statement statement = null;
        try {
            //ties with the included ilbrary sqlite-jdbc by Google for creating sqlite dbases

            Class.forName("org.sqlite.JDBC");

            //create the database file to hold the data
            connection = DriverManager.getConnection("jdbc:sqlite:" + external_db + ".db");

            //generate the table queries
            statement= connection.createStatement();
            String sql =
                    "CREATE TABLE " + tableName + "(" +
                            "_id " + DatabaseDefinition.DATATYPE_INT + DatabaseDefinition.OPT_PRIM_KEY + DatabaseDefinition.OPT_COMMA;
            //create the table headings for the rest of the columns
            //while we're at it, append the columns for making the insertions (ie INSERTS INTO (col1,col2,...)
            String columns = "_id,";
            for (int i = 0; i < allPossibleCols.size(); i++) {
                sql += allPossibleCols.get(i) + DatabaseDefinition.DATATYPE_TEXT + DatabaseDefinition.OPT_COMMA;
                columns += allPossibleCols.get(i) + ",";
            }

            //remove the trailing comma
            sql = sql.substring(0,sql.length() - 1);
            columns = columns.substring(0,columns.length() - 1);

            //add the bracket
            sql += ");";
            //execute the statement to make the sql table. use excuteUpdate because it returns nothing
//            statement.executeUpdate(sql);

            //now do the insertions

            String valuesSQL;
            String foodValue;
            String insertSQLPart = "INSERT INTO " + tableName + " (" +
                    columns + ") ";
            int id = 1;
            String thisColumnName;
            int theCorrespondingIndex;

            //BUG FIX: create a prepared statement object so that the valuess can be efficiently inserted
            //  with better error descriptions
            PreparedStatement pstate;
            //replace the columns with questionmarks (all strings not , with questoin mark)
            String preparedStatementSQL = insertSQLPart + " VALUES (" + columns.replaceAll("[^,]+","?") + ");";
            pstate = connection.prepareStatement(preparedStatementSQL);
            int currId;
            String[] values = new String[allPossibleCols.size()];
            boolean successfulInsert;
            for (Foods f : Foods.entries) {
                //make the first value an integer
                pstate.setInt(1, id++);
                for (int i = 0; i < allPossibleCols.size(); i++) {
                    thisColumnName = allPossibleCols.get(i);
                    theCorrespondingIndex = f.vals.cols.indexOf(thisColumnName);

                    if (theCorrespondingIndex != -1) {
                        //offset 1 to account for ID
                        pstate.setString(i+2, f.vals.data.get(theCorrespondingIndex));
                    } else {
                        //insert null here
                        pstate.setString(i+2,NULL_STRING);
                    }
                }
                //ready to execute the statement
                pstate.executeUpdate();
            }

            statement.close();
            connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}









