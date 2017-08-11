import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorManagerTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testWhiteSpace()
	{
		String expression = "     add(1, 1)    ";
		
		CalculatorManager manager = new CalculatorManager();
		
		int result = manager.runQuery(expression);
		int expected = 2;
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testWhiteSpaceAfterParameter()
	{
		String expression = "add( 1 , 1 )";
		
		
		CalculatorManager manager = new CalculatorManager();
		
		int result = manager.runQuery(expression);
		int expected = 2;
		
		assertEquals(expected, result);
	}
	

	
	@Test
	public void testSimpleAddition()
	{
		String expression = "add(1, 1)";
		
		CalculatorManager manager = new CalculatorManager();
		
		int result = manager.runQuery(expression);
		int expected = 2;
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testSimpleNestedFunction()
	{
		String expression = "add(1, mult(2, 3))";
		
		CalculatorManager manager = new CalculatorManager();
		
		int result = manager.runQuery(expression);
		int expected = 7;
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testNestedFunctions()
	{
		String expression = "mult(add(2,2), div(9,3))";
		
		CalculatorManager manager = new CalculatorManager();
		
		int result = manager.runQuery(expression);
		int expected = 12;
		
		assertEquals(expected, result);
		
	}
	
	@Test
	public void testSimpleLetExpression()
	{
		String expression = "let(a, 5, add(5, 5))";
		
		CalculatorManager manager = new CalculatorManager();
		
		int result = manager.runQuery(expression);
		int expected = 10;
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testDoubleLetExpression()
	{
		String expression = "let(a, 5, let(b, mult(a, 10), add(a, b)))";
		
		CalculatorManager manager = new CalculatorManager();
		
		int result = manager.runQuery(expression);
		int expected = 55;
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testReusingVariablesLetStatement()
	{
		String expression = "let(a, let(b, 10, add(b, b)), let(b, 20, add(a,b)))";
		
		CalculatorManager manager = new CalculatorManager();
		
		int result = manager.runQuery(expression);
		int expected = 55;
		
		assertEquals(expected, result);
	}

}
