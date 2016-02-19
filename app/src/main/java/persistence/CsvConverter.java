package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CsvConverter {
    ArrayList<String> commonCols;
    ArrayList<String> allCols;
    ArrayList<String> outCols;
    ArrayList<String> files;
    Foods foods;
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
    String[] filenames = {"extdb/FOOD NAME.csv"};

    public void main(String[] args) {
        commonCols = new ArrayList<String>();
        allCols = new ArrayList<String>();
        outCols = new ArrayList<String>();
        files = new ArrayList<String>();
        foods = new Foods(commonCols,allCols,outCols,files);

        getFiles(filenames);
        //reorganize columns
        reorderColumns();
        //pick which columns to keep
        //chooseOutColumns();
        //ready to read objects
        readObjects();
        listFoods();
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

    private void listFoods() {
        int i;
        BufferedWriter bufferedWriter;
        try {
            File file = new File("out.txt");
            if (!file.exists()) file.createNewFile();
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
        boolean firstFile = true;   //ignore food from the first file
        for (String f : files) {
            try {
                //for each file, open it first
                filein = new BufferedReader(new FileReader(f));
                //get the first line
                line =  filein.readLine();
                //split it into the columns and add it to the arraylist
                filecols = new ArrayList<String>(
                        Arrays.asList(
                                line.split(",")));

                //iterate through the rest of the file, creating/adding the objects
                String data[];
                while((line = filein.readLine()) != null) {
                    data = splitLineIntoData(line, ",");
                    if (data.length == 0) continue;
                    if (firstFile) {
                        //if its the first file, add the food directly
                        foods.addNewFood(filecols,data);
                    } else {
                        createFood(filecols, data);

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            firstFile = false;
        }
    }

    //purpose: data in the file sometimes contains information that has commas, but at least it begins with quotes
    //so delimit by quotes
    private String[] splitLineIntoData(String line, String delimiter) {
        //split the line as usual
        String[] tokens = line.split(delimiter);
        ArrayList<String> result = new ArrayList<String>();
        String commaVal = "";
        String dblOrSingQuote = "";
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
                    if (i == tokens.length)  {
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

    private void createFood(ArrayList<String> filecols, String[] data) {
        //see if this food exists first
        int indexOfFood = foods.hasFood(filecols, data);
        if (indexOfFood != -1) {
            //food exists already, add to it extra information
            foods.addUniqueData(indexOfFood, filecols,data);
        } else {
            //new food found
            foods.addNewFood(filecols,data);
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
    public void getFiles(String[] args) {
        for (String f : args) {
            //f are filenames for each passed csv
            files.add(f);
            joinColumns(
                    openCsvGetColumns(f)
            );
        }
    }

    //add the columns to the corresponding list
    //if duplicate, add to commons (duplicate = primary ID)
    public void joinColumns(String[] columns) {
        for (String c : columns) {
            if (allCols.contains(c)) {
                //common column
                commonCols.add(c);
            } else {
                //new column
                allCols.add(c);
            }
        }
    }

    /**
     open a file and read the first line, splitting it by commas
     return null on empty file
     */
    public String[] openCsvGetColumns(String filename) {
        File file = new File(filename);
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
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

}
class Foods {
    static ArrayList<Foods> entries = new ArrayList<Foods>();
    static ArrayList<String> commonCols;
    static ArrayList<String> allCols;
    static ArrayList<String> outCols;
    static ArrayList<String> files;

    public DataEntry vals;

    //every food has a data entry
    //  each food exists in the global/static entries table
    //  entries have vals (private)
    //  the vals correspond to the value pairs of the data
    //this constructor initializes the static variables
    Foods(ArrayList<String> commonCols, ArrayList<String> allCols, ArrayList<String> outCols, ArrayList<String> files) {
        if (Foods.commonCols == null) Foods.commonCols = commonCols;
        if (Foods.allCols == null) Foods.allCols = allCols;
        if (Foods.outCols == null) Foods.outCols = outCols;
        if (Foods.files == null) Foods.files = files;
    }
    Foods(DataEntry dataEntry) {
        vals = dataEntry;
    }

    //checks to see if this is a common column
    public boolean isCommonCol(String colToCheck) {
        return commonCols.indexOf(colToCheck) != -1 && vals.cols.indexOf(colToCheck) != -1 ;
    }

    /**
     *          Check the entries table, which is a table of all the foods entered so far.
     *          Return true if the food exists already.
     *          How do we do that?
     *          Check, from the common columns of filecols, that their corresponding data exists in the table
     * @param filecols has a corresponding index entry that points to its data, so they relate
     * @param data      the line of data from the file
     * @return          true if the file existrs
     */
    public int hasFood(ArrayList<String> filecols, String[] data) {
        //check the global set of food entries
        int indexOfFood = 0;
        //...if the food exists in there already
        indexOfFood = Foods.foodExists(filecols,data);
        if (indexOfFood != -1) {
            return indexOfFood;
        }

        return -1;
    }

    //check the arraylist if this food is in there already
    private static int foodExists(ArrayList<String> filecols, String[] data) {
        //data is a comma separated line of numbers, values, etc
        //  filecols tells us what each comma-separated data entry means (column headings)
        //for this food, check its data entry vals
        Skip_Food:
        for (Foods f : entries) {
            for (String c : filecols) {
                //for each (unique) column to check
                //check if this is a unique column first
                if (f.isCommonCol(c)) {
                    //this is a unique column.
                    // check if the data of this line is exactly equal
                    String myData = f.vals.data.get(f.vals.cols.indexOf(c));
                    String testData = data[filecols.indexOf(c)];
                    //check if it is equal to:
                    // the corresponding parameter data array index val
                    //this food exists in the table already
                    //return the index number in the foods table
                    //get the data from the existing database //  do this by extracting the column data from the corresponding index
                    if (myData.equalsIgnoreCase(testData)){
                        return entries.indexOf(f);
                    } else {
                        //this is likely new data, ignore checks and check next food
                        //System.out.println("continue...");
                        continue Skip_Food;
                    }
                }
            }

        }
        //if code reaches here, all data entries were checked and this data does not exist in there
        return -1;
    }

    public void addUniqueData(int indexOfFood, ArrayList<String> filecols, String[] data) {
        Foods oldFood = entries.get(indexOfFood);
        String fileData;
        //for each value pair in this food
        for (String col : filecols) {         //for each newly passed
            if (oldFood.vals.cols.indexOf(col) == -1) {        //check if the column does not exist
                //new column found, add it          //if it doesnt exist, add it to the value pairs
                oldFood.vals.cols.add(col);        //add the column
                //add the data
                fileData = filecols.indexOf(col) >= data.length || data[filecols.indexOf(col)] == null ? "" : data[filecols.indexOf(col)];
                oldFood.vals.data.add(fileData);
            } else {
                //column exists
                //todo assert if this data is the same, which it should be. hell it shouldnt even get here
                //System.out.println("Why are we here?");
            }
        }

    }

    public void addNewFood(ArrayList<String> filecols, String[] data) {
        //food didnt exist, so add this to the foods table
        ArrayList<String> newCols = new ArrayList<String>();
        ArrayList<String> newData = new ArrayList<String>();
        for (int i = 0; i < filecols.size(); i++) {
            //add the corresonding data entries
            newCols.add(filecols.get(i));
            //there are times when the column has no entry, so if data == null then add it as empty string
            if (i >= data.length || data[i] == null) {
                newData.add("");
            } else {
                newData.add(data[i]);
            }
        }
        //all the data has been added to the temp vars, create the food object with this data
        Foods newFood = new Foods(new DataEntry(newCols,newData));
        //add to the database
        entries.add(newFood);
    }

    class DataEntry {
        ArrayList<String> cols;
        ArrayList<String> data;
        DataEntry(ArrayList<String> cols, ArrayList<String> data){
            this.cols = cols;
            this.data = data;
        }
    }
}
