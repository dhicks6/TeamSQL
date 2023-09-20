import java.io.*;
import java.nio.file.*;
import java.util.List;

public class JavaToTxtConverter {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: JavaToTxtConverter <input.java> <output.txt>");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        if (!inputFilePath.endsWith(".java")) {
            System.out.println("Input file should have a .java extension.");
            return;
        }

        try {
            // Read all lines from the input Java file
            List<String> lines = Files.readAllLines(Paths.get(inputFilePath));

            // Write the lines to the output text file
            Files.write(Paths.get(outputFilePath), lines);

            System.out.println("Conversion successful!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred during the conversion.");
        }
    }
}