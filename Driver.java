import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException {
    	String inFilePath = args[0];
		String outFilePath = args[1];
		
		File fileIn = new File(inFilePath);
		File fileOut = new File(outFilePath);

		Checker.IfCheck(fileIn, fileOut);
		
		try {
			Scanner scanner = new Scanner(fileIn);
			 while (scanner.hasNextLine())
			 {
	            String line = scanner.nextLine();
	            System.out.println(line);
		     }
		     scanner.close();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}