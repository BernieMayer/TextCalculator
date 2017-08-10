import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTreeEvaluatorTest {


	
	@Test
	public void testEvualatorSimpleAddition()
	{
		NumberNode leftNode = new NumberNode(2);
		NumberNode rightNode = new NumberNode(2);
		
		FunctionNode functionNode = new FunctionNode(FunctionType.ADD
												,leftNode, rightNode);
		
		int result = CalculatorTreeEvaluator.evaluateTree(functionNode);
		int expected = 4;
		
		
		assertEquals(result, expected);
	}
	
	@Test
	public void testEvaluatorSimpleMultiplication()
	{
		NumberNode leftNode = new NumberNode(3);
		NumberNode rightNode = new NumberNode(4);
		
		FunctionNode multiplicationTree = new FunctionNode(FunctionType.MULT,
												leftNode,
												rightNode);
		
		int result = CalculatorTreeEvaluator.evaluateTree(multiplicationTree);
		int expected = 12;
		
		assertEquals(result,expected);
	}
	
	@Test
	public void testEvaluatorSimpleDivision()
	{
		NumberNode leftNode = new NumberNode(12);
		NumberNode rightNode = new NumberNode(4);
		
		FunctionNode divTree = new FunctionNode(FunctionType.DIV,
												leftNode,
												rightNode);
		
		int result = CalculatorTreeEvaluator.evaluateTree(divTree);
		int expected = 3;
		
		assertEquals(result,expected);
	}
	
	
	@Test
	public void testEvaluatorSimpleSubraction()
	{
		NumberNode leftNode = new NumberNode(5);
		NumberNode rightNode = new NumberNode(2);
		
		FunctionNode subtractionTree = new FunctionNode(FunctionType.SUB,
												leftNode,
												rightNode);
		
		int result = CalculatorTreeEvaluator.evaluateTree(subtractionTree);
		int expected = 3;
		
		assertEquals(result,expected);
	}
	
	@Test
	public void testSimpleExpression()
	{
		NumberNode leftNode = new NumberNode(2);
		FunctionNode rightNode = new FunctionNode(FunctionType.ADD, 
									new NumberNode(2), new NumberNode(2));
		
		FunctionNode expressionTree = new FunctionNode(FunctionType.ADD,
										   leftNode,
										   rightNode);
		
		int result = CalculatorTreeEvaluator.evaluateTree(expressionTree);
		int expected = 6;
		
		
		assertEquals(result, expected);
	}
	

}
