import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Checker {
        
    /*
     1. Check to make sure all decision structures (if, if-else, if-else-if, switch) use curly braces
     appropriately. If not, fix it. Assume that single statement block is also required to include
     curly braces.
     */

    public static void IfCheck(File in, File out) throws IOException {
        // Read in the file using the Driver.java class
        List<String> lines = Files.readAllLines(in.toPath());
        FileWriter writer = new FileWriter(out);
        int i = 0;
        for (String line : lines) {
            i++;
            // Check for if statements
            if (line.contains("if")) {
                // Check for opening curly braces
                if (!line.contains("{")) {
                    // Add curly braces
                    line = line.replace(line, line + "{");
                }
                // Check for closing curly braces
                    if (line.contains("{") && !lines.get(i+1).contains("}")) {
                    lines.set(i+1, "} " + lines.get(i+1));
                }
                
            }

            // Check for if-else statements
            if (line.contains("else") && !line.contains("else if") && !line.contains("//")) {
                // Check for opening curly braces
                if (!line.contains("{")) {
                    // Add curly braces
                    line = line.replace(line, line + "{");
                }
            //  !!!!!!!!!!!!!!!!!! Not done !!!!!!!!!!!!!!!!!!!!! Check for closing curly braces
                if (line.contains("{") && !lines.get(i+1).contains("}")) {
                    lines.set(i+1, "} " + lines.get(i+1));
                }
            }

            // Check for else if statements
            if (line.contains("else if") && !line.contains("else if") && !line.contains("//")) {
                // Check for opening curly braces
                if (!line.contains("{")) {
                    // Add curly braces
                    line = line.replace(line, line + "{");
                }
            //  !!!!!!!!!!!!!!!!!! Not done !!!!!!!!!!!!!!!!!!!!! Check for closing curly braces
                if (line.contains("{") && !lines.get(i+1).contains("}")) {
                    lines.set(i+1, "} " + lines.get(i+1));
                }
            }

            // Check for switch statements
            if (line.contains("switch") && !line.contains("//")) {
                // Check for opening curly braces
                if (!line.contains("{")) {
                    // Add curly braces
                    line = line.replace(line, line + "{");
                    writer.write(line + "\n");
                }
            //  !!!!!!!!!!!!!!!!!! Not done !!!!!!!!!!!!!!!!!!!!! Check for closing curly braces
            }

            // If there are no cases we need to check for add the line to the out file
            else {
            writer.write(line + "\n");
            }

        } // End of for loop
        // Write the file out
        writer.close();
    } // End of IfCheck method


    // Should first check for how many open and closed curly braces there are

    // We can use a stack and push them on and pop them off as we go through the file

    // If there are any left on the tack at the end, then we know there is an error

    // Or if there are more pops than there are pushes we know there is an error


    /*
    2. Check to make sure all loop structures (while, do-while, for) use curly braces
    appropriately. If not, fix it. Assume that single statement block is also required to include
    curly braces
     */

    /*
    3. Check to make sure all the method structure is syntactically correct. If not, fix it.
     */


    /*
    4. Count how many time the keyword public is used as keywords in the input program.
     */


    /*
    5. Then your program will print to a text file the original input program, the updated input
    program, and the number of time keyword print is used.
     */

} // End of Checker class