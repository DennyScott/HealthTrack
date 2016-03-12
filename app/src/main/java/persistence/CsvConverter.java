package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class CsvConverter {
    ArrayList<String> commonCols;
    ArrayList<String> allCols;
    ArrayList<String> outCols;
    ArrayList<String> files;
    Foods foods;
    String[] foodsPattern;
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
            File file = new File(filename);
            //if (!file.exists()) file.createNewFile();
            bufferedWriter = new BufferedWriter(new FileWriter(file));
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
                filein = new BufferedReader(new FileReader(f));
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
        File file = new File(filename);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            //read only first line
            String line = br.readLine();
            return line.split(",");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
                br = new BufferedReader(new FileReader(file));
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
                            br = new BufferedReader(new FileReader(file));
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
        arrayList.set(indexA,arrayList.get(indexB));
        arrayList.set(indexB,temp);
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
                if (b >= f.vals.cols.size()) break;
                b = i;
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

    public void deleteCorrespondingArrayElements(ArrayList<String> a, ArrayList<String> b, int index) {
        System.out.println("removing :" + a.get(index) + " and " + b.get(index));
        a.remove(index);
        b.remove(index);
    }
    //delete all nutrients that do not fit in the accepted pattern
    //TOO BUGGED
    public void deleteNonInterestingNutrients(String[] nutrientsToKeep) {
        boolean acceptThisNutrient;
        int i;
        //delete them backwards so it doesnt affect the remaining elements
        for (Foods f : Foods.entries) {
            for (i = 0; i < f.vals.cols.size(); i++) {
                //check only if this is NutrientName column
                if (!f.vals.cols.get(i).equalsIgnoreCase(f.vals.data.get(i + 5))) continue;
                //code reaching here means this a NutrientName column
                //check its value
                acceptThisNutrient = false;
                for (int j = 0; j < nutrientsToKeep.length; j++) {
                    if (f.vals.data.get(i).equalsIgnoreCase(nutrientsToKeep[j])) {
                        //accept this
                        acceptThisNutrient = true;
                        break;
                    }
                }
                if (!acceptThisNutrient) {
                    //delete this nutrient if it did not match any patterns
                    //Here is a sample of the nutrient output
//                    Column: NutrientCode                  Val: 203
//                    Column: NutrientSymbol                Val: PROT
//                    Column: NutrientUnit                  Val: g
//                    Column: NutrientName                  Val: PROTEIN
//                    Column: NutrientNameF                 Val: PROT�INES
//                    Column: Tagname                       Val: PROCNT
//                    Column: NutrientDecimals              Val: 2
                    //we assume that nutrientName has been swapped with NutrientCode
                    //we are currently at the index position of nutrientName, which is the first element
                    // of this series of 7 things to delete
                    //since the index falls forward to the next element, delete this index i 7 times
                    for (int k = 0; k < 7; k++) {
                        deleteCorrespondingArrayElements(f.vals.cols, f.vals.data, i);
                    }
                }
            }
        }
    }
}