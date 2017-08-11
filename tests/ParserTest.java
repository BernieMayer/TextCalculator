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
