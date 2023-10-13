import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Assume program is indented correctly
// Assume program is indented with tabs or equivalently four spaces
// Assume no return type or method name is made of all numbers.
// Assume method declaration will always have a beginning open parantheses (
public class MethodChecker {
	
	/**
	 * Begins the process of checking java method headers, parentheses,
	 * and brackets
	 * @param fileContent String array containing contents of file
	 * @return a String[] of fixed contents
	 */
	public ArrayList<String> checkMethods(ArrayList<String> fileContent)
	{
		for (int lineNum = 0; lineNum < fileContent.size(); lineNum++)
		{
			String line = fileContent.get(lineNum);
			if (checkIfMethodDec(line))
			{
				String whiteSpaces = getWhiteSpaces(line);
				fileContent.set(lineNum, fixHeader(line));
				fileContent = fixParantheses(fileContent, lineNum, whiteSpaces);
				fileContent = fixBrackets(fileContent, lineNum, whiteSpaces);			
			}
		}
		return fileContent;
	}
	
	private ArrayList<String> fixBrackets(ArrayList<String> fileContent,
							int currentLine,
							String whiteSpaces)
	{
		boolean openBracketPlaced = false;
		boolean closeBracketPlaced = false;
		
		for (int futureLine = currentLine+1;
				futureLine < fileContent.size();
				futureLine++)
		{
			String line = fileContent.get(futureLine);
			if (!openBracketPlaced)
			{
				if (checkOpenBracket(fileContent.get(futureLine)))
				{
					openBracketPlaced = true;
				}
				else if(!checkOpenBracket(fileContent.get(futureLine))
						&& checkClosingParantheses(fileContent.get(futureLine -1)))
				{
					fileContent.add(futureLine, whiteSpaces + "{");
					openBracketPlaced = true;
				}
			}
			if (line.isBlank())
			{
				continue;
			}
			if (!closeBracketPlaced)
			{
				if (checkCloseBracket(fileContent.get(futureLine), whiteSpaces))
				{
					closeBracketPlaced = true;
				}
				else if (checkIfExitScope(fileContent.get(futureLine), whiteSpaces))
				{
					fileContent.add(futureLine,
							whiteSpaces + "}");
			        closeBracketPlaced = true;
				}
				else if(checkMatchingScope(fileContent.get(futureLine), whiteSpaces)
						&& !checkCloseBracket(fileContent.get(futureLine), whiteSpaces)
						&& !checkOpenBracket(fileContent.get(futureLine)))
				{
					fileContent.add(futureLine,
									whiteSpaces + "}");
					closeBracketPlaced = true;
				}
			}
			if (openBracketPlaced && closeBracketPlaced)
			{
				break;
			}
		}
		return fileContent;
	}
	
	private ArrayList<String> fixParantheses(ArrayList<String> fileContent,
			                                int currentLine,
			                                String whiteSpaces)
	{
		for (int futureLine = currentLine;
				futureLine < fileContent.size();
				futureLine++)
		{
			if (fileContent.get(futureLine).isBlank())
			{
				continue;
			}
			if (checkClosingParantheses(fileContent.get(futureLine)))
			{
				break;
			}
			else if (fileContent.get(futureLine).contains("(") &&
					 fileContent.get(futureLine).endsWith("("))
			{
				fileContent.set(futureLine, fileContent.get(futureLine)+")");
			}
			else if (checkOpenBracket(fileContent.get(futureLine)))
			{
				fileContent.set(futureLine - 1,
						fileContent.get(futureLine - 1) + ")");
				break;
			}
			else if (checkIfExitScope(fileContent.get(futureLine), whiteSpaces)
					 || checkIfNextScope(fileContent.get(futureLine), whiteSpaces))
			{
				fileContent.set(futureLine - 1,
						fileContent.get(futureLine - 1) + ")");
				break;
			}
			
		}
		return fileContent;
	}
	
	private boolean checkClosingParantheses(String line)
	{
		String closingParantheses = "[)]$";
		Pattern closingPara = Pattern.compile(closingParantheses);
		Matcher closingParaMatcher = closingPara.matcher(line);	
		if (closingParaMatcher.find())
		{
			return true;
		}
		return false;
	}
	
	private boolean checkOpenBracket(String line)
	{
		String openBracket = "^\s+[{]";
		Pattern openBracketPattern = Pattern.compile(openBracket);
		Matcher openBracketMatcher = openBracketPattern.matcher(line);	
		if (openBracketMatcher.find())
		{
			return true;
		}
		return false;
	}
	
	private boolean checkCloseBracket(String line, String whiteSpaces)
	{
		String whiteSpace = translateWhiteSpaceRegex(whiteSpaces);
		String closeBracket = whiteSpace+"[}]$";
		Pattern closeBracketPattern = Pattern.compile(closeBracket);
		Matcher closeBracketMatcher = closeBracketPattern.matcher(line);	
		if (closeBracketMatcher.find())
		{
			return true;
		}
		return false;
	}
	
	private boolean checkMatchingScope(String line, String whiteSpaces)
	{
		String whiteSpacesRegex = translateWhiteSpaceRegex(whiteSpaces);
		String scope = "^"+whiteSpacesRegex+"\\S+";
		Pattern scopePattern = Pattern.compile(scope);
		Matcher scopeMatcher = scopePattern.matcher(line);	
		if (scopeMatcher.find())
		{
			return true;
		}
		return false;
	}
	
	private boolean checkIfExitScope (String line, String currentScopeWhiteSpace)
	{
		if (currentScopeWhiteSpace.charAt(0) == '\t')
		{
			currentScopeWhiteSpace = currentScopeWhiteSpace.substring(1);
		}
		else if (currentScopeWhiteSpace.charAt(0) == ' ')
		{
			currentScopeWhiteSpace = currentScopeWhiteSpace.substring(4);
		}
		String whiteSpaceRegex = translateWhiteSpaceRegex(currentScopeWhiteSpace);
		Pattern whiteSpacePat = Pattern.compile("^"+whiteSpaceRegex+"\\S");
		Matcher whiteSpaceMat = whiteSpacePat.matcher(line);
		if (whiteSpaceMat.find())
		{
			return true;
		}
		return false;
	}
	
	private boolean checkIfNextScope(String line, String currentScopeWhiteSpaces)
	{
		String nextScopeTab = "^"+currentScopeWhiteSpaces+"\t\\S+";
		String nextScopeSpaces= "^"+currentScopeWhiteSpaces+"\s\s\s\s\\S+";
		Pattern nextScopeTabPat = Pattern.compile(nextScopeTab);
		Pattern nextScopeSpacePat = Pattern.compile(nextScopeSpaces);
		Matcher nextScopeTabMatch = nextScopeTabPat.matcher(line);
		Matcher nextScopeSpaceMatch = nextScopeSpacePat.matcher(line);
		if (nextScopeTabMatch.find())
		{
			return true;
		}
		if (nextScopeSpaceMatch.find())
		{
			return true;
		}
		return false;
	}
	
	public boolean checkIfMethodDec(String line)
	{
		// aaaa aaaa(
		String methodForm = "\\S+\s\\S+[(]";
		Pattern methodPattern = Pattern.compile(methodForm);
		Matcher methodMatcher = methodPattern.matcher(line);
		// Method names are preceded by tab or spaces
		// <public|private|protected> <static> <return type> <methodname>
		if (line.contains("=")
			|| line.contains("\"")
			|| line.contains("\'"))
		{
			return false;
		}
		if (methodMatcher.find())
		{
			String[] elements = line.trim().split("\s+");
			if (elements[0].contains("("))
			{
				return false;
			}
			if (elements.length == 4 ||
				elements.length == 5)
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
	
	public String fixHeader(String line)
	{
		String[] elements = line.trim().split("\s+");
		String newLine = getWhiteSpaces(line);
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
				&& !element.equals("private")
				&& !element.equals("protected"))
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
		
		if (Character.isUpperCase(element.charAt(0)))
		{
			char letter= Character.toLowerCase(element.charAt(0));
			element = letter + element.substring(1);
		}
		
		return element;
	}
	
	private String fixElement4(String element)
	{
		if (!element.equals("public")
				&& !element.equals("private")
				&& !element.equals("protected")
				&& !element.equals("static"))
			{
				element = "public";
			}
		return element;
	}
	
	private String getWhiteSpaces(String line)
	{
		String whiteSpaces = "";
		for( char character : line.toCharArray())
		{
			if(character == ' ' || character == '\t')
			{
				whiteSpaces = whiteSpaces + character;
			}
			else
			{
				break;
			}
		}
		return whiteSpaces;
	}
	
	private String translateWhiteSpaceRegex(String whiteSpace)
	{
		String whiteSpaceRegex = "";
		for(char character : whiteSpace.toCharArray())
		{
			if (character == '\t')
			{
				whiteSpaceRegex = whiteSpaceRegex + '\t';
			}
			if (character == ' ')
			{
				whiteSpaceRegex = whiteSpaceRegex + '\s';
			}
		}
		return whiteSpaceRegex;
	}
}
