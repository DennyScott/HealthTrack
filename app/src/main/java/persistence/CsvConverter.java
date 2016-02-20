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
