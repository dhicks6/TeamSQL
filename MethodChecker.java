import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Assume program is indented correctly
// Assume program is indented with tabs or equivalently four spaces
// Assume no return type or method name is made of all numbers.
// Assume method declaration is one line
public class MethodChecker {
	
	public String checkLine(String line)
	{
		if(!checkIfMethodDec(line))
		{
			return line;
		}
		else
		{
			fixLine(line);
		}
		return "TODO";
	}
	
	public boolean checkIfMethodDec(String line)
	{
		// Method names are preceded by tab or spaces
				// <public|private|protected> <static> <return type> <methodname>
		if (line.contains("="))
		{
			return false;
		}
		if (line.contains("(") && line.contains(")"))
		{
			String[] elements = line.trim().split("\s+");
			if (elements.length == 4)
			{
				return true;
			}
			else if (elements.length == 3)
			{
				return true;
			}
			else if (elements.length == 2)
			{
				return true;
			}
		}

		return false;
	}
	
	public String fixLine(String line)
	{
		String[] elements = line.trim().split("\s+");
		String newLine = "";
		if (elements.length == 4)
		{
			elements[0] = fixElement1(elements[0]);
			elements[1] = fixElement2(elements[1]);
			elements[2] = fixElement3(elements[2]);
			elements[3] = fixElement3(elements[3]);
		}
		else if (elements.length == 3)
		{
			elements[0] = fixElement4(elements[0]);
			elements[1] = fixElement3(elements[1]);
			elements[2] = fixElement3(elements[2]);
		}
		else if (elements.length == 2)
		{
			elements[0] = fixElement3(elements[0]);
			elements[1] = fixElement3(elements[1]);
		}
		for (String element : elements)
		{
			if (!element.equals(elements[elements.length - 1]))
			{
				newLine = newLine.concat(element);
				newLine = newLine.concat(" ");
			}
			else
			{
				newLine = newLine.concat(element);
			}	
		}
		return newLine;
	}
	
	private String fixElement1(String element)
	{
		if (!element.equals("public")
				|| !element.equals("private")
				|| !element.equals("protected"))
			{
				element = "public";
			}
		return element;
	}
	
	private String fixElement2(String element)
	{
		if (!element.equals("static"))
		{
			element = "static";
		}
		return element;
	}
	
	private String fixElement3(String element)
	{
		while (Character.isDigit(element.charAt(0)))
		{
			element = element.substring(1);
		}
		
		return element;
	}
	
	private String fixElement4(String element)
	{
		if (!element.equals("public")
				|| !element.equals("private")
				|| !element.equals("protected")
				|| !element.equals("static"))
			{
				element = "public";
			}
		return element;
	}
}
