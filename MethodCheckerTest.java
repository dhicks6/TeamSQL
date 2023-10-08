import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class MethodCheckerTest {
	MethodChecker checker = new MethodChecker();
	@Test
	public void recognizeMethodFullCaseTab()
	{
		boolean method = checker.checkIfMethodDec("	public static void getStuff()");
		assertTrue(method);
	}
	
	@Test
	public void recognizeMethodFullCaseSpaces()
	{
		boolean method = checker.checkIfMethodDec("    public static void getStuff()");
		assertTrue(method);
	}
	
	@Test
	public void recognizeMethodThreePartsTab()
	{
		boolean method = checker.checkIfMethodDec("	public void getStuff()");
		assertTrue(method);
	}
	
	@Test
	public void recognizeMethodThreePartSpaces()
	{
		boolean method = checker.checkIfMethodDec("    public static void getStuff()");
		assertTrue(method);
	}
	
	@Test
	public void recognizeMethodTwoPartsSpaces()
	{
		boolean method = checker.checkIfMethodDec("	ReturnType getStuff()");
		assertTrue(method);
	}
	
	@Test
	public void recognizeNotMethodStart()
	{
		boolean method = checker.checkIfMethodDec(" public");
		assertFalse(method);
	}
	
	@Test
	public void recognizeNotMethodStart2()
	{
		boolean method = checker.checkIfMethodDec("	public int ");
		assertFalse(method);
	}
	
	@Test
	public void recognizeNotMethodStart3()
	{
		boolean method = checker.checkIfMethodDec("int count = getStuff()");
		assertFalse(method);
	}
	
	@Test
	public void recognizeNotMethodStart5()
	{
		 boolean method = checker.checkIfMethodDec("    if(number)");
		 assertFalse(method);
	}
	
	@Test
	public void recognizeNotMethodStart4()
	{
		 boolean method = checker.checkIfMethodDec("    if (number)");
		 assertFalse(method);
	}
	
	@Test
	public void testFixLineNoFixNeeded()
	{
		String input = "public static void getStuff()";
		String newLine = checker.fixHeader(input);
		assertEquals(input, newLine);
	}
	
	@Test
	public void testFixLinePublic()
	{
		String input = "	pulc static void getStuff()";
		String expected = "	public static void getStuff()";
		String newLine = checker.fixHeader(input);
		assertEquals(expected, newLine);
	}
	
	@Test
	public void testFixLineStatic()
	{
		String input = "public s void getStuff()";
		String expected = "public static void getStuff()";
		String newLine = checker.fixHeader(input);
		assertEquals(expected, newLine);
	}
	
	@Test
	public void testFixLineRemoveNumbers()
	{
		String input = "public static 64Points 100getStuff()";
		String expected = "public static Points getStuff()";
		String newLine = checker.fixHeader(input);
		assertEquals(expected, newLine);
	}
}
