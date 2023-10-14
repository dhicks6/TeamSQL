package project;

import java.io.*;
import java.util.Scanner;

public class loopCount {
    public static void main(String[] args) {
        int loopCount = 0;

        try {
            File filePath = new File("Test.java");
            Scanner file = new Scanner(filePath);

            // Create a StringBuilder to store the fixed code
            StringBuilder fixedCode = new StringBuilder();

            boolean insideLoop = false;
            boolean openingBraceFound = false;

            // Scan each line in the file
            while (file.hasNextLine()) {
                String line = file.nextLine();

                // Check for 'for (', 'while (', and 'do {'
                if (line.contains("for (") || line.contains("while (") || line.contains("do {")) {
                    loopCount++;
                    insideLoop = true;
                }

                if (openingBraceFound) {
                    if (line.contains("{")) {
                        openingBraceFound = true;
                    } else {
                        // Check if the line is missing "{"
                        if (insideLoop) {
                            line = line + " {";
                            openingBraceFound = true;
                        }
                    }
                }

                // Append the line to the fixed code
                fixedCode.append(line).append("\n");
            }

            file.close();

            // Add a closing curly brace '}' at the end of each loop if it's missing
            if (!insideLoop) {
                fixedCode.append("}\n");
            }

            // Write the fixed code back to the same file
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(fixedCode.toString());
            fileWriter.close();

            // Print the fixed code
            System.out.println(fixedCode);

            // Print the number of loops found
            System.out.println("Number of loops found: " + loopCount);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error writing to the file.");
            e.printStackTrace();
        }
    }
}
