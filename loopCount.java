package project;

import java.io.*;
import java.util.Scanner;

public class loopCount {
    public static void main(String[] args) {
        int loopCount = 0;

        try {
            File filePath = new File("C:/Users/spx98/Downloads/Test.java");
            Scanner file = new Scanner(filePath);

            // Create a StringBuilder to store the fixed code
            StringBuilder fixedCode = new StringBuilder();

            boolean insideLoop = false;

            // Scan each line in the file
            while (file.hasNextLine()) {
                String line = file.nextLine();

                // Check for 'for', 'while', and 'do' loop keywords
                if (line.contains("for (") || line.contains("while (") || line.contains("do {")) {
                    loopCount++;
                    insideLoop = true;

                    // Check if the line is missing "{"
                    if (!line.contains("{")) {
                        // Add "{" after the loop declaration
                        line = line.replace("(", " {\n");
                    }
                }

                // Check for the end of a loop
                if (insideLoop && line.contains("}")) {
                    insideLoop = false;
                }

                // Append the line to the fixed code
                fixedCode.append(line).append("\n");
            }

            file.close();

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
