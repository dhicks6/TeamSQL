import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class loopCount {
    public static void main(String[] args) 
    {
    	
    	 // Add the file path  
        String filePath = "TeamSQL/TestLoops.java";
        File file = new File(filePath);
        // Scan and split the files in to an ArrayList
        ArrayList<String> fileContents = new ArrayList<>();
        // Calling method 
        try {
            Scanner scanner = new Scanner(file);
            // Scan and add each line to ArrayList
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileContents.add(line);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> newArray = checkLoops(fileContents);
    }
    
    public static ArrayList<String> checkLoops(ArrayList<String> fileContents)
    {
        try {
            boolean loopFound = false;
            boolean openingBraceFound = false;
            boolean loopDeclared = false;

            // Scan each line in the file
            for (int lineNum = 0; lineNum < fileContents.size(); lineNum++ ) {
                String line = fileContents.get(lineNum);
                // Check for 'for (', 'while (', and 'do {'
                if (line.contains("for (") || line.contains("while (") || line.contains("do {")) {
                    loopFound = true;
                    loopDeclared = true;
                    
                    if (line.contains("{")) {
                        openingBraceFound = true;
                    } else if (loopFound && !fileContents.get(lineNum+1).contains("{"))
                    {
                    	fileContents.set(lineNum, line + "{");
                    }
                    
                    if (!fileContents.get(lineNum+1).contains("}")
                    	&& !fileContents.get(lineNum+2).contains("}")
                    	&& !fileContents.get(lineNum+3).contains("}"))
                    {
                    	fileContents.add(lineNum+3,
                    			"}");
                    }
                }
            }

            // Write the fixed code back to the same file
            FileWriter fileWriter = new FileWriter("TeamSQL/outputForLoops.txt");
            for (String line : fileContents)
            {
            	fileWriter.write(line + System.lineSeparator());
            }
            fileWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error writing to the file.");
            e.printStackTrace();
        }
        
        return fileContents;
    }
}
