import java.io.File;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
    	String inFilePath = args[0];
		String outFilePath = args[1];
		
		File file = new File(inFilePath);
		
		try {
			Scanner scanner = new Scanner(file);
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
