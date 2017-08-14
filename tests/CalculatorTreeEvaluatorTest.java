import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTreeEvaluatorTest {


	
	@Test
	public void testEvualatorSimpleAddition()
	{
		
		//Testing the expression of
		// add(2, 2)
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
		// testing the expression of
		// mult(3, 4)
		
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
		
		//testing the expression of
		// div(12, 4)
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
		
		//testing the expression
		//sub(5, 2)
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
		
		//testing the expression of
		// add(2, add(2, 2))
		//
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
	
	@Test
	public void testMultiplicationExpression()
	{
		
		//testing the expression of 
		// mult(mult(3,2), mult(2,2))
		
		
		FunctionNode leftNode = new FunctionNode(FunctionType.MULT,
												new NumberNode(3),
												new NumberNode(2));
		FunctionNode rightNode = new FunctionNode(FunctionType.MULT, 
				new NumberNode(2), new NumberNode(2));
		
		FunctionNode exprTree = new FunctionNode(FunctionType.MULT,
									leftNode,
									rightNode);
		
		int result = CalculatorTreeEvaluator.evaluateTree(exprTree);
		int expected = 24;
		
		assertEquals(result, expected);
		
		
		
	}
	
	@Test
	public void testDivisionExpression()
	{
		//Testing the expression of
		//mult( div(6,2), mult(2, 2))
		FunctionNode leftNode = new FunctionNode(FunctionType.DIV,
												new NumberNode(6),
												new NumberNode(2));
		FunctionNode rightNode = new FunctionNode(FunctionType.MULT, 
				new NumberNode(2), new NumberNode(2));
		
		FunctionNode exprTree = new FunctionNode(FunctionType.MULT,
									leftNode,
									rightNode);
		
		int result = CalculatorTreeEvaluator.evaluateTree(exprTree);
		int expected = 12;
		
		assertEquals(result, expected);
		
		
	}
	
	@Test
	public void testLetExpression()
	{
		//The let expression will be
		//let(a, 5, add(a,a))
		
		VariableNode aNode = new VariableNode("a"); 
		FunctionNode variableAddExpression = new FunctionNode(FunctionType.ADD, aNode, aNode);
		
		LetNode letNode = new LetNode("a", new NumberNode(5), variableAddExpression);
		
		int result = CalculatorTreeEvaluator.evaluateTree(letNode);
		int expected = 10;
		
		assertEquals(result, expected);
	}
	
	@Test
	public void testNestedLetExpression()
	{
		//The expression will be
		
		// let(a, 5, let(b, mult(a, 10), add(b, a)))
		
		VariableNode aNode = new VariableNode("a");
		VariableNode bNode = new VariableNode("b");
		
		LetNode bLetNode = new LetNode("b",
								new FunctionNode(FunctionType.MULT, aNode, new NumberNode(10)),
								new FunctionNode(FunctionType.ADD, bNode, aNode));
		
		LetNode aLetNode = new LetNode("a", 
								new NumberNode(5),
								bLetNode);
		
		int result = CalculatorTreeEvaluator.evaluateTree(aLetNode);
		int expected = 55;
		
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public void testVariableReuseInLetStatement()
	{
		//The statement testing here is
		//let(a, let(b, 10, add(b, b)), let(b, 20, add(a,b))
		
		VariableNode aNode = new VariableNode("a");
		VariableNode bNode = new VariableNode("b");
		
		LetNode firstB_LetExpr = new LetNode("b", new NumberNode(10),
										new FunctionNode(FunctionType.ADD,
											bNode,
											bNode));
		
		LetNode secondB_LetExpr = new LetNode("b",
										new NumberNode(20),
										new FunctionNode(FunctionType.ADD, 
														 aNode, bNode));
		
		LetNode finalExpr = new LetNode("a", firstB_LetExpr, 
											 secondB_LetExpr);
		
		int result = CalculatorTreeEvaluator.evaluateTree(finalExpr);
		int expected = 40;
		
		assertEquals(expected, result);
				
		
	}

}
