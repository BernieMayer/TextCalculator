import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testSimpleAddExpression()
	{
		String expression = "add( 1, 1)";
		
		CalculatorNode calculationNode = null;
		try {
			calculationNode = Parser.parse(expression);
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

}