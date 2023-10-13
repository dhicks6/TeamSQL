/* Name: Cuong Dinh
 * Class: Principles of Programming Languages 3210
 * Last modified: 13 Oct. 2023
 * Help Link: https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/util/Scanner.html
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class KeywordCounter {
    public static void main(String[] args) {
        // Add the file path  
        String filePath = "TeamSQL/Test.java";
        // Scan and split the files in to an ArrayList
        ArrayList<String> lines = readFile(filePath);
        // Calling method 
        int count = countKeyword(lines);
        // Print the result
        System.out.println("Number of 'public' keywords: " + count);
    } // End of main

    public static ArrayList<String> readFile(String filePath) {
        ArrayList<String> lines = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            // Scan and add each line to ArrayList
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lines;
    } // End of method

    public static int countKeyword(ArrayList<String> lines) {
        // Set up the keyword
        String keyword = "public";
        // Set up the counter
        int count = 0;
        // Check if public appears in comments and muliple-line comments
        boolean inComment = false;
        // Check if public appears in quotes " "
        boolean inQuotes = false;

        for (String line : lines) { // for each line in an ArrayList of lines
            Scanner scanner = new Scanner(line);

            while (scanner.hasNext()) {
                String word = scanner.next();

                if (!inComment && !inQuotes) {

                    if (word.equals("//")) {
                        break; // Skip the rest of the line

                    } else if (word.equals("/*")) {
                        inComment = true; // Start of multiple-line comments

                    } else if (word.equals("*/")) {
                        inComment = false; // End of multiple-line comments

                    } else if (word.equals("\"")) {
                        inQuotes = true;

                    } else if (word.equals(keyword)) {
                        count++; // Adding up the counter
                    }
                }

                if (word.contains("//")) {
                    break; // Skip the rest of the line
                }

                if (word.contains("\"") && !word.endsWith("\"")) {
                    inQuotes = true;
                }             
            }

            scanner.close();
            inComment = false;
            inQuotes = false;
        }

        return count;
    } // End of method

} // End of program
