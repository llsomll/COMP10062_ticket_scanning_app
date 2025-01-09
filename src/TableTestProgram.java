import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.io.FileInputStream;


/**
 *
 * This class provides a command-line interface for managing a text file in order to test Table class.
 * It allows users to display data, perform lookups, searches, changes, save data to the file, get single cell values, save single cell values, and perform binary searches.
 *
 * @author Dasom Kim
 * @version v1.00
 *
 */
public class TableTestProgram {
    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        String tablename = "";
        String choice = "";
        int row = -1;
        int colNum = 1;
        String key = "";
        String s_colNum = "";
        String newValue = "";
        String s_row = "";

        System.out.print("Enter the name of the tab delimited text file you wish to manage (e.g. codes.txt) > ");
        tablename = in.nextLine();
        Table t = new Table(tablename);
        System.out.println("Successfully loaded: " + t);


        while (1 == 1) {
            System.out.println("\n\nTable Testing Menu\n");

            System.out.println("1. Display all data");
            System.out.println("2. Lookup");
            System.out.println("3. Search");
            System.out.println("4. Change");
            System.out.println("5. Save data to " + tablename);
            System.out.println("6. Get Single Cell Value");
            System.out.println("7. Save Single Cell Value");
            System.out.println("8. Sort data alphabetically (optional)");
            System.out.println("9. Binary Search (optional)");
            System.out.println("10. Quit");
            System.out.print("Select > ");
            choice = in.nextLine();




            // Display all data
            if (choice.equals("1")) System.out.println(t.display());


            // Lookup
            else if (choice.equals("2")) {
                System.out.print("Enter a key > ");
                key = in.nextLine();
                row = t.lookup(key);
                if (row != -1) {
                    System.out.println(key + " found at row: " + row);
                } else {
                    System.out.println( key + " can not be found.");
                }
            }


            // Search
            else if (choice.equals("3")) {
                System.out.print("Enter a key > ");
                key = in.nextLine();
                row = t.lookup(key);
                String[] search = t.getMatches(key);
                if (row != -1) {
                    System.out.println("row " + row);
                    System.out.println("Column 0        " + "[" + search[0] + "]");
                    System.out.println("Column 1        " + "[" + search[1] + "]");
                    System.out.println("Column 2        " + "[" + search[2] + "]");
                    System.out.println("Column 3        " + "[" + search[3] + "]");
                } else {
                    System.out.println( key + " can not be found.");
                }
            }


            // Change
            else if (choice.equals("4")) {
                System.out.print("Enter a key > ");
                key = in.nextLine();
                row = t.lookup(key);

                if (row == -1) {
                    System.out.println( key + " can not be found." );
                    continue;
                }

                System.out.print("Which column do you want to make a change to? ");
                s_colNum = in.nextLine();
                colNum = Integer.parseInt(s_colNum);
                System.out.print("What's the new value? ");
                newValue = in.nextLine();
                t.change(key, colNum, newValue);
            }



            // Save data
            else if (choice.equals("5")) {
                t.save();
            }


            // Get Single Cell Value
            else if (choice.equals("6")) {
                System.out.print("Enter a key > ");
                key = in.nextLine();
                row = t.lookup(key);

                if (row == -1) {
                    System.out.println( key + " can not be found." );
                    continue;
                }

                System.out.print("Which column do you want to access to? ");
                s_colNum = in.nextLine();
                colNum = Integer.parseInt(s_colNum);
                String[] keyInfo = t.getMatches(key);
                System.out.println(keyInfo[colNum]);
            }


            // Save Single Cell Value
            else if (choice.equals("7")) {
                System.out.print("Which row do you want to save the value to? ");
                s_row = in.nextLine();
                row = Integer.parseInt(s_row);
                System.out.print("Which column do you want to save the value to? ");
                s_colNum = in.nextLine();
                colNum = Integer.parseInt(s_colNum);
                System.out.print("What's the new value? ");
                newValue = in.nextLine();
                t.change(row, colNum, newValue);
            }


            // * optional requirement *
            // Sort the table array alphabetically based on the values in the first column.
            else if (choice.equals("8")) t.sortArray();


            // * optional requirement *
            // Sort data + Binary search
            else if (choice.equals("9")) {
                System.out.print("Enter a key > ");
                key = in.nextLine();

                String[][] sortedArray = t.sortArray();
                int index = Arrays.binarySearch(sortedArray, new String[]{key, "", "", ""}, Comparator.comparing(a -> a[0]));

                if (index >= 0) {
                    System.out.println(key + " found at index " + index);
                } else {
                    System.out.println(key + " not found.");
                }
            }


            // Quit
            else if (choice.equals("10"))  break;


        }
        System.out.println("Thank-you, good bye!");


    }
}
       
       

