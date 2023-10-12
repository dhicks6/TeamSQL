import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Checker {
        
    /*
     1. Check to make sure all decision structures (if, if-else, if-else-if, switch) use curly braces
     appropriately. If not, fix it. Assume that single statement block is also required to include
     curly braces.
     */

     // Need to update IfCheck to take an arraylist of strings and return an arraylist of strings that are
     // all of the changes made by IfCheck
    public static ArrayList<String> IfCheck(ArrayList<String> in) throws IOException {

        ArrayList<String> fixedArray = new ArrayList<String>();

        // Read in the file using the Driver.java class

        int i = 0;
        for (String line : in) {
            i++;
            // Check for if statements
            if (line.contains("if")) {
                // Check for opening curly braces
                if (!line.contains("{")) {
                    // Add curly braces
                    line = line.replace(line, line + "{");
                }
                // Check for closing curly braces
                    if (line.contains("{") && !in.get(i+1).contains("}")) {
                    in.set(i+1, "} " + in.get(i+1));
                }
                
            }

            // Check for if-else statements
            if (line.contains("else") && !line.contains("else if") && !line.contains("//")) {
                // Check for opening curly braces
                if (!line.contains("{")) {
                    // Add curly braces
                    line = line.replace(line, line + "{");
                }
            //  Check for closing curly braces
                if (line.contains("{") && !in.get(i+1).contains("}")) {
                    in.set(i+1, "} " + in.get(i+1));
                }
            }

            // Check for else if statements
            if (line.contains("else if") && !line.contains("else if") && !line.contains("//")) {
                // Check for opening curly braces
                if (!line.contains("{")) {
                    // Add curly braces
                    line = line.replace(line, line + "{");
                }
            //  Check for closing curly braces
                if (line.contains("{") && !in.get(i+1).contains("}")) {
                    in.set(i+1, "} " + in.get(i+1));
                }
            }

            // Check for closing curly braces
            // Here I am going to have to find default and fix the ending curly brace 2 lines down
            // I did this one backwards so I wouldn't need to have the else statement after this one
            // because the writer.write statement wouldnt work in this if statement
            if (line.contains("default") && !in.get(i+1).contains("}")) {
                in.set(i+1, "} " + in.get(i+1));
            }

            // Check for switch statements
            if (line.contains("switch") && !line.contains("//")) {
                // Check for opening curly braces
                if (!line.contains("{")) {
                    // Add curly braces
                    line = line.replace(line, line + "{");
                    //writer.write(line + "\n");
                }
            }

            // If there are no cases we need to check for add the line to the out file
            else {
            
            //writer.write(line + "\n");
            }

        } // End of for loop
        // Write to fixed array
        for (String line : in) {
            fixedArray.add(line);
        }
        return fixedArray;
    } // End of IfCheck method



} // End of Checker class