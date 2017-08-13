import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {

	
	
	@Test
	public void testSimpleAddExpression()
	{
		String expression = "add(1, 1)";
		
		CalculatorNode calculationNode = null;
		Parser aParser = new Parser();
		try {
			calculationNode = aParser.parse(expression);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeType expectedNodeType =  NodeType.FUNCTION;
		FunctionType expectedFunctionType = FunctionType.ADD;
		
		assertEquals(expectedNodeType, calculationNode.getNodeType());
	
		
		CalculatorNode leftNode = ((FunctionNode) calculationNode).getLeftNode();
		CalculatorNode rightNode = ((FunctionNode) calculationNode).getRightNode();
		
		assertEquals(1, ((NumberNode) leftNode).getNodeValue());
		assertEquals(1, ((NumberNode) rightNode).getNodeValue());
		
	}
	
	@Test
	public void testNestedLetStatements()
	{
		String expression = "let(a, 5, let(b, mult(a, 10), add(b,a)))";
		
		CalculatorNode calculationNode = null;
		Parser aParser = new Parser();
		try {
			calculationNode = aParser.parse(expression);
		} catch (ParserException e)
		{
			e.printStackTrace();
		}
		
		
		int result = CalculatorTreeEvaluator.evaluateTree(calculationNode);
		int expected = 55;
		
		assertEquals(expected, result);
	}
	
	@Test 
	public void testReusingVariablesLetStatement()
	{
		String expression = "let(a, let(b, 10, add(b, b)), let(b, 20, add(a,b)))";
		
		CalculatorNode calculationNode = null;
		Parser aParser = new Parser();
		try {
			calculationNode = aParser.parse(expression);
		} catch (ParserException e)
		{
			e.printStackTrace();
		}
		
		int result = CalculatorTreeEvaluator.evaluateTree(calculationNode);
		int expected = 40;
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testInvalidFunction()
	{
		String expression = " abc(1, 1)";
		
		try 
		{
			Parser aParser = new Parser();
			aParser.parse(expression);
			
		} catch (ParserException e)
		{
			e.printStackTrace();
			
			return; //An error was thrown
			//success("Error was thrown");
		}	
	}
	
	@Test
	public void testMixingOfLettersAndNumbers()
	{
		String expression = "add(a1, 1)";
		
		try 
		{
			Parser aParser = new Parser();
			aParser.parse(expression);
			
			
		} catch (ParserException e)
		{
			e.printStackTrace();
			
			return; //An error was thrown
			//success("Error was thrown");
		}
		fail("The expression should throw an exception");
	}
	
	
	
	
	@Test
	public void testMultipleLetterVariable()
	{
		String expression = "let(ab, 5, add(ab, 1))";
		
		Parser aParser = new Parser();
		try {
			CalculatorNode calculationNode = aParser.parse(expression);
			int result = CalculatorTreeEvaluator.evaluateTree(calculationNode);
			int expected = 6;
			
			assertEquals(expected, result);
			
		} catch (ParserException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInvalidNumberOfArguments()
	{
		String expression = "add(1)";
		
		try {
			Parser aParser = new Parser();
			aParser.parse(expression);
		} catch (ParserException e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	@Test
	public void testLetStatements()
	{
		String expression = "let(a, 5, add(a,a))";
		
		CalculatorNode calculationNode = null;
		Parser aParser = new Parser();
		try {
			calculationNode = aParser.parse(expression);
		} catch (ParserException e)
		{
			e.printStackTrace();
		}
		
		NodeType expectedNodeType = NodeType.LET;
		
		assertEquals(expectedNodeType, calculationNode.getNodeType());
		
		NumberNode variableValueNode = (NumberNode) ((LetNode) calculationNode).getVariableValue();
		int expectedVariableValue = 5;
		
		assertEquals(expectedVariableValue, variableValueNode.getNodeValue());
		
		int treeEvalResult = CalculatorTreeEvaluator.evaluateTree(calculationNode);
		int treeEvalExpected = 10;
		
		assertEquals(treeEvalExpected, treeEvalResult);
		
	
		
	}
	
	
	
	@Test
	public void testComplexNestedExpression()
	{
		String expression = "mult(add(2, 2), div(9, 3))";
		
		CalculatorNode calculationNode = null;
		Parser aParser = new Parser();
		try {
			calculationNode = aParser.parse(expression);
		} catch(ParserException e)
		{
			e.printStackTrace();
		}
		
		NodeType expectedRightNodeType = NodeType.FUNCTION;
		NodeType expectedLeftNodeType = NodeType.FUNCTION;
		
		CalculatorNode leftNode = ((FunctionNode) calculationNode).getLeftNode();
		CalculatorNode rightNode = ((FunctionNode) calculationNode).getRightNode();
		
		assertEquals(expectedRightNodeType, leftNode.getNodeType());
		assertEquals(expectedRightNodeType, rightNode.getNodeType());
		
		
		FunctionNode leftFunctionNode = (FunctionNode) leftNode;
		FunctionNode rightFunctionNode = (FunctionNode) rightNode;
		
		FunctionType expectedLeftFunctionType = FunctionType.ADD;
		FunctionType expectedRightFunctionType = FunctionType.DIV;
		
		
		assertEquals(expectedLeftFunctionType, leftFunctionNode.getFunctionType());
		assertEquals(expectedRightFunctionType, rightFunctionNode.getFunctionType());
		
		
		int result = CalculatorTreeEvaluator.evaluateTree(calculationNode);
		int expectedResult = 12;
		
		assertEquals(expectedResult, result);
		
		
		
		
		
	}
	
	@Test
	public void testSimpleNestedExpression()
	{
		String expression = "add(1, mult(2,3))";
		
		CalculatorNode calculationNode = null;
		Parser aParser = new Parser();
		try {
			calculationNode = aParser.parse(expression);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeType expectedNodeType =  NodeType.FUNCTION;
		FunctionType expectedFunctionType = FunctionType.ADD;
		
		assertEquals(expectedNodeType, calculationNode.getNodeType());
		
		CalculatorNode rightNode = ((FunctionNode) calculationNode).getRightNode();
		
		assertEquals(NodeType.FUNCTION, rightNode.getNodeType());
		
		NumberNode leftNode = (NumberNode) ((FunctionNode) calculationNode).getLeftNode();
		
		assertEquals(1, leftNode.getNodeValue());
		
	}
	
	

}
