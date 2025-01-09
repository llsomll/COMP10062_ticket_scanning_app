import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * The Table class manages a tab-delimited text file(e.g.  codes.txt) by storing its contents in a 2D array.
 * It provides methods for reading, writing, and manipulating data within the file.
 *
 * @author Dave selemon, Dasom Kim
 * @version v2.00
 *
 */
public class Table { //class

    //instance variables
    private String tablename;
    private int numRows;
    private int numCols;
    private String[][] grid;


    /**
     * Initialize the class with the name of the tab delimited text file you wish to manage.
     *
     * @param filename the name of tab delimited text file.
     */
    public Table(String filename) { //table
        tablename = filename;
        numRows = 0;
        numCols = 0;
        String s;
        int r;
        String[] item;


        //Pass1:  Go through the text file in order to ascertain the
        //        numRows and numCols
        try {

            Scanner theFile = new Scanner(new FileInputStream(new File(tablename)));
            while (theFile.hasNextLine()) {
                s = theFile.nextLine();
                item = s.split("\t", 0);


                if (item.length > numCols)
                    numCols = item.length;

                numRows++;

            }
            theFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Table class Error 1: file not found.");
        }


        grid = new String[numRows][numCols];


        //Pass2:  populate the grid array
        try {

            Scanner theFile = new Scanner(new FileInputStream(new File(tablename)));
            r = 0;
            while (theFile.hasNextLine()) {
                s = theFile.nextLine();
                item = s.split("\t", 0);

                for (int c = 0; c < numCols; c++) {


                    if (item[c].length() == 0)
                        grid[r][c] = "";
                    else
                        grid[r][c] = item[c];


                }
                r++;


            }
            theFile.close();
        } catch (Exception e) {
            System.out.println("Table class error 2: file not found.");
        }


    } //table


    /**
     * Display the content of the table in the console.
     */
    public String display() {
        StringBuilder output = new StringBuilder();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                output.append(grid[r][c]).append("\t");
            }
            output.append("\n");
        }
        return output.toString();
    }





    /**
     * Save the contents of the table to the specified file.
     */
    public void save() {
        try (FileWriter writer = new FileWriter(tablename)) {
            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    writer.write(grid[r][c] + "\t");
                }
                writer.write("\n");
            }
        } catch (IOException e) {}
    }




    /**
     * Get the number of rows in the table.
     *
     * @return The number of rows.
     */
    public int getNumRows() {
        return numRows;
    }





    /**
     * Get the number of columns in the table.
     *
     * @return The number of columns.
     */
    public int getNumCols() {
        return numCols;
    }




    /**
     * Change the value at the specified row and column in the table.
     *
     * @param rowNum   The row number (0-based).
     * @param colNum   The column number (0-based).
     * @param newValue The new value to set.
     */
    public void change(int rowNum, int colNum, String newValue) {
        // Check if the specified row and column numbers are within the bounds of the table
        if (rowNum < getNumRows() && colNum < getNumCols())
            grid[rowNum][colNum] = newValue; // Set the value at the specified row and column to the new value
    }




    /**
     * Change the value at the specified row and column based on a target value.
     *
     * @param target   The target value to search for in the first column.
     * @param colNum   The column number (0-based) where the value should be changed.
     * @param newValue The new value to set.
     */
    public void change(String target, int colNum, String newValue) {
        int row = lookup(target); // Look up the row number based on the target value
        if (row != -1 && colNum >= 0 && colNum < getNumCols())
            grid[row][colNum] = newValue;
    }




    /**
     * Get the values of a row in the table based on a target value.
     *
     * @param target The target value to search for in the first column.
     * @return An array containing the values of the row, or null if the target value is not found.
     */
    public String[] getMatches(String target) {
        int r = lookup(target);

        if (r == -1) {
            return null;
        }

        String[] ticketInfo = new String[numCols];

        ticketInfo[0] = grid[r][0];
        ticketInfo[1] = grid[r][1];
        ticketInfo[2] = grid[r][2];
        ticketInfo[3] = grid[r][3];

        return ticketInfo;
    }




    /**
     * Look up the row number of a target value in the table.
     *
     * @param target The target value to search for in the first column.
     * @return The row number (0-based) of the target value, or -1 if the target value is not found.
     */
    public int lookup(String target) {
        int row = -1;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][0].equals(target)) {
                    row = r;
                    break;
                }
            }
        }
        return row;
    }




    /**
     * Sort the table array alphabetically based on the values in the first column.
     *
     * @return The sorted 2D array representing the table.
     */
    public String[][] sortArray() {
        // Sort the 2D array alphabetically
        Arrays.sort(grid, (a, b) -> a[0].compareTo(b[0]));
        // Print the sorted 2D array
        return grid;
    }




    /**
     * Return a string representation of the table, including the filename and dimensions.
     *
     * @return A string representation of the table.
     */
    public String toString() {

        return ("Table: " + tablename + "  rows = " + getNumRows() + "  cols = " + getNumRows());
    }

} //class
    
    
    