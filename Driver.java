import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
  
    public static void main(String[] args) throws IOException {
      String inFilePath = args[0];
      ArrayList<String> fileContents = new ArrayList<>();

      File fileIn = new File(inFilePath);

      try {
        Scanner scanner = new Scanner(fileIn);
         while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileContents.add(line);
         }
                 System.out.println(Checker.IfCheck(fileContents));
         scanner.close();
      } catch(Exception e) {
        e.printStackTrace();
      }

    }



}
