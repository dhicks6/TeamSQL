import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
  
    public static void main(String[] args) throws IOException {
      String inFilePath = args[0];
      ArrayList<String> fileContents = new ArrayList<>();
      File fileIn = new File(inFilePath);
      MethodChecker methodChecker = new MethodChecker();
      FileWriter writer = new FileWriter("TeamSQL/wholeOutputFile.txt");

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
      writer.write("======== INPUT FILE ========" + System.lineSeparator());
      for (String line : fileContents)
      {
    	  writer.write(line + System.lineSeparator());
      }
      fileContents = Checker.IfCheck(fileContents);
      fileContents = methodChecker.checkMethods(fileContents);
      int publicCount = KeywordCounter.countKeyword(fileContents);

      writer.write("======== OUTPUT FILE ========" + System.lineSeparator());
      for (String line : fileContents)
      {
    	  writer.write(line + System.lineSeparator());
      }
      writer.write("Public Keyword Count = " + publicCount);
      writer.close();
    }
}
