/* Name: Cuong Dinh
 * Class: Principles of Programming Languages 3210
 * Last modified: 28 Sep. 2023
 * Read more: https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/util/Scanner.html
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KeywordCounter {
    public static void main(String[] args) {
        // Create a File instance to reference a file
        File file = new File("Test.java");
        // Set up the keyword
        String keyword = "public";

        // Check if the file exists or end the program
        if (!file.exists()) {
            System.out.println("File Not Found");
            return;
        }

        try {
            Scanner lineScanner = new Scanner(file);
            int count = 0;

            // Loop through each line
            while (lineScanner.hasNextLine()) {
                String line = lineScanner.nextLine();
                // Split the line into words
                // The test file contains multiple whitespace character and lines
                String words[] = line.split("\\s+");

                // For-each loop to check the keyword
                for (String word : words) {
                    if (word.equals(keyword)) {
                        count++;
                    }
                }
            }

            lineScanner.close();

            // Print the result
            System.out.println("The keyword 'public' appears: " + count + " times.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
} // End of the class
