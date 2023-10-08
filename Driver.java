import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException {
      String inFilePath = args[0];
      String outFilePath = args[1];
      ArrayList<String> fileContents = new ArrayList<>();

      File fileIn = new File(inFilePath);
      File fileOut = new File(outFilePath);

      try {
        Scanner scanner = new Scanner(fileIn);
         while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileContents.add(line);
         }
         scanner.close();
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
}