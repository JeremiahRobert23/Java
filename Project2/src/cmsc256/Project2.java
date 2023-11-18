

package cmsc256;  // do not remove or comment out this statement

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *  CMSC 256
 *  Project 2
 *  Robert, Jeremiah
 */
// place any import statements here
public class Project2 {
    public static void main(String[] args) {
        // Test your program thoroughly before submitting.
        // For example,
        // Display appropriately labeled information for the following:
        // What is tallest height?
        // Which row has the lowest weight?
        // Calculate average height of 20-30 year age range in the data.
        Project2 proj = new Project2();
        //the above is done because there are no static methods
        String firstArgument = proj.checkArgs(args);
        System.out.println(firstArgument);
        File file = null;
        try {
             file = proj.getFile(firstArgument);
            String[][]testData = proj.readFile(file,5);
            for(String[] row: testData){
                for(String column:row){
                    System.out.print(column+' ');
                }
                System.out.println();
            }
        }
        catch(FileNotFoundException ex){
            ex.getMessage();
            ex.printStackTrace();
        }
        catch(IOException iox){
            iox.printStackTrace();
        }
    }

    /**
     * Gets the file name from command line argument;
     * If parameter is empty, call promptForFileName() method
     *
     * @param argv String array from command line argument
     * @return the name of the data file
     */
    public String checkArgs(String[] argv) {
        String filename = "";
        if(argv.length==0) {
            filename = promptForFileName();
        }
        else {
            filename = argv[0];
        }
        return filename;

    }

    /**
     * Prompt user to enter a file name
     *
     * @return user entered file name
     */
    public String promptForFileName() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a file name: ");
        return in.nextLine().trim();

    }

    /**
     * Retrieve file with the given file name.
     * Prompts user if file cannot be opened or does not exist.
     *
     * @param fileName The name of the data file
     * @return File object
     * @throws java.io.FileNotFoundException
     */
    public File getFile(String fileName) throws FileNotFoundException {
        File myfile = new File (fileName);
        while(!myfile.exists()){
            //message that file cannot be opened
            System.out.println("File could not be opened");
            //call promptForFileName
            String name = promptForFileName();
            //create a new File object
            myfile = new File (name);
        }
        return myfile;
    }

    /**
     * Reads the comma delimited file to extract the number data elements
     * provided in the second argument.
     *
     * @param file       The File object
     * @param numRecords The number of values to read from the input file
     * @return 2D array of data from the File
     * @throws IOException if any lines are missing data
     */
    public String[][] readFile(File file, int numRecords) throws IOException {
        Scanner fileReader = new Scanner(file);
        String[][]data = new String [numRecords][3];
        String line = fileReader.nextLine();
        int row = 0;

        while(fileReader.hasNextLine()&& row<numRecords){

            //read a line from the file
            line = fileReader.nextLine().trim();

            //parse the line into 3 parts
            //JR - use line.split may bring
            Scanner lineParser = new Scanner(line);
            ///// demiliter separates
            lineParser.useDelimiter(",");
            for(int column=0; column<3;column++){
                if(lineParser.hasNext()){
                    data[row][column] = lineParser.next().trim();
                }
                else{
                    //close scanner
                    lineParser.close();

                    //throw the exception
                    throw new IOException("Error reading the data in the file");
                }

            }
            row++;
            lineParser.close();
            //add the line to the 2d array

            //increment row variable

        }

        fileReader.close();

        return data;

    }

    /**
     * Determines the tallest height in the data set
     * Height is the second field in each row
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Maximum height value
     */
    public int findTallest(String[][] db) {
        int tallest = 0;
        //can also use Integer.MIN_VALUE
        for(int i=0; i<db.length; i++){

            //we are converting a string to an integer
            //the second element in each row is height
            int height = Integer.parseInt(db[i][1]);

            //tallest is set to height if the current height is higher
            if(height>tallest){
                tallest = height;

            }
        }

        return tallest;
    }

    /**
     * Returns the values in the record that have the lowest weight
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Smallest weight value
     */
    public String[] findLightestRecord(String[][] db) {
        int index = 0;
        //lightest is set to highest value in java
        int lightest = Integer.MAX_VALUE;
        for(int i=0; i<db.length;i++){

            //weight is third value of the array
            int weight = Integer.parseInt(db[i][2]);

            //lightest is set to weight if current weight is lesser
            if(weight<lightest){
                lightest= weight;
                //index is stored
                index=i;

            }

        }
        //the full row is displayed
        return db[index];


    }

    /**
     * Calculates the average height for all records with the given age range.
     *
     * @param db         2D array of data containing [age] [height] [weight]
     * @param lowerBound youngest age to include in the average
     * @param upperBound oldest age to include in the average
     * @return The average height for the given range or 0 if no
     * records match the filter criteria
     */
    public double findAvgHeightByAgeRange(String[][] db, int lowerBound, int
            upperBound) {
        double averageHeight = 0.0;
        double sum =0.0;
        double count = 0.0;

        for(int i=0; i<db.length;i++){
            //the parsed age is the first element of each row
            int age = Integer.parseInt(db[i][0]);
            //height is second element of each row
            int height = Integer.parseInt(db[i][1]);

            //if the age is greater than or equal to lowerbound and age is less than or equal to upperbound
            if(age>=lowerBound && age<=upperBound){
                //sum of height
                sum +=height;
                //count is number of times
                count++;

            }
        }
        //if the criteria is not met then return 0
        if(count==0){
            return 0.0;
        }

        //if criteria is met then return average height
        averageHeight = sum/count;
        return averageHeight;
    }
}
