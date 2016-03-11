package persistence;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Deny Raaen on 20/02/2016.
 */
public class Foods {
    static ArrayList<Foods> entries;
    static ArrayList<Foods> nutrients;
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
        if (Foods.entries == null) Foods.entries = new ArrayList<Foods>();
        if (Foods.nutrients == null) Foods.nutrients = new ArrayList<Foods>();

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
        int indexOfFood;
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
        for (Foods f : entries) {
            for (String c : filecols) {
                //find a primary key heading for this new data first
                if (f.isCommonCol(c)) {
                    //this is a unique column.
                    // check if the data of this line is exactly equal
                    String existingDataValue = f.vals.data.get(f.vals.cols.indexOf(c));
                    String checkingDataValue = data[filecols.indexOf(c)];
                    //check if it is equal to:
                    // the corresponding parameter data array index val
                    //this food exists in the table already
                    //return the index number in the foods table
                    //get the data from the existing database //  do this by extracting the column data from the corresponding index
                    if (existingDataValue.equalsIgnoreCase(checkingDataValue)){
                        return entries.indexOf(f);
                    } else {
                        //this is likely new data, ignore checks and check next food
                        //System.out.println("continue...");
                        break;
                    }
                }
            }
        }
        //if code reaches here, all data entries were checked and this data does not exist in there
        return -1;
    }
    public static void pickOnlyWantedFoods(String[] patterns){
        //go through the entries list and remove foods that do not match this pattern
        String foodname;
        boolean matchFound;
        Iterator<Foods> it = entries.iterator();
        while (it.hasNext()) {
            Foods food = it.next();
            //find the food name through the food description column
            foodname = food.vals.data.get(food.vals.cols.indexOf("FoodDescription"));
            //check if the patterns have this element corresponding
            matchFound = false;
            for (int i = 0; i < patterns.length; i++) {
                if (foodname.toLowerCase().contains(patterns[i].toLowerCase())) {
                    //this is a match, skip it
                    matchFound = true;
                    break;
                } else {
                    //this did not match, keep checking
                }
            }
            if (!matchFound) {
                //this food was non-existent, remove it
                it.remove();
            } else {
//                System.out.println("potato");
            }
        }
    }


    /**
     * If the first few files opened were the nutrient-related files, we
     *  can transfer their contents over to the other array list to append their values later.
     *  This is because the nutrients need their own colum headings
     */
    public void transferCurrentFoodsToNutrients() {

    }

    public void addUniqueData(int indexOfFood, ArrayList<String> filecols, String[] data) {
        Foods oldFood = entries.get(indexOfFood);
        String fileData;
        //for each value pair in this food
        for (String col : filecols) {
            //check if the column does not exist, or append it if its a nutrient
            if (oldFood.vals.cols.indexOf(col) == -1 || filecols.indexOf("NutrientID") != -1) {
                //in the event that this is a nutrientID, skip the column for foodID
                if (col.equalsIgnoreCase("FoodID")) continue;
                //new column found, add it          //if it doesnt exist, add it to the value pairs
                oldFood.vals.cols.add(col);        //add the column
                //add the data
                //  if there are more columns than data entries or this data value is null, then assign ""
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

        /**
         * An index-to-index mapping class for data values, where the index of
         *   the cols arraylist is the column heading for the index of the data arraylist
         * @param cols
         * @param data
         */
        DataEntry(ArrayList<String> cols, ArrayList<String> data){
            this.cols = cols;
            this.data = data;
        }
    }
}
