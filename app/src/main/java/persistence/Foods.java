package persistence;

import java.util.ArrayList;

/**
 * Created by Deny Raaen on 20/02/2016.
 */
public class Foods {
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
