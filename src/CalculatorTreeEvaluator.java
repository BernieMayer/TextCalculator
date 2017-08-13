//import FunctionNode.FunctionType;

public class CalculatorTreeEvaluator 
{
	
	/**
	 * This method will take in a node and will traverse the 
	 * nodes tree to find out the result of that nodes calculations
	 * This method is recursive in nature. 
	 * 
	 * @param The currentNode that is being evaluated
	 * @return The result of evaluating the tree
	 */
	
	public static int evaluateTree(CalculatorNode aNode)
	{
		
		if (aNode.getNodeType() == NodeType.NUMBER)
		{
			return ((NumberNode) aNode).getNodeValue();
		} else if (aNode.getNodeType() == NodeType.FUNCTION)
		{
			//handle each function type here properly
			
			FunctionNode functionNode = (FunctionNode) aNode;
			
			if (functionNode.getFunctionType() == FunctionType.ADD)
			{
				
				return evaluateTree(functionNode.getLeftNode()) + evaluateTree(functionNode.getRightNode());
				
			} else if (functionNode.getFunctionType() == FunctionType.SUB)
			{
				return evaluateTree(functionNode.getLeftNode()) - evaluateTree(functionNode.getRightNode());

			} else if (functionNode.getFunctionType() == FunctionType.MULT)
			{
				
				return evaluateTree(functionNode.getLeftNode()) * evaluateTree(functionNode.getRightNode()); 
	
			} else if (functionNode.getFunctionType() == FunctionType.DIV)
			{
				
				return evaluateTree(functionNode.getLeftNode()) / evaluateTree(functionNode.getRightNode());
				
			}
			
		} else if (aNode.getNodeType() == NodeType.LET )
		{
			
			return evaluateLetNode((LetNode) aNode);
			
		}
		
		return 0; //returns 0 if the expression cannot be properly evaluated
		
	}

	/**
	 * 
	 * This method will take in a letNode and will evaluate it.
	 * Let Nodes need a bit of special processing and this method handles that 
	 * processing 
	 * 
	 * @param the letNode that needs to be evaluated
	 * @return the result of evaluating the let node
	 */
	private static int evaluateLetNode(LetNode letNode) {

		String variableName = letNode.getVariableName();
		int variableValue = evaluateTree(letNode.getVariableValue());
		CalculatorNode variableExpression = letNode.getVariableExpression();
		
		return evaluateTree(replaceVariables(variableName,
											 variableExpression,
											 variableValue));
	}
	
	/**
	 * 
	 * This method will replace and instances of a variable in a expression
	 * 
	 * For example a statement of let(a, 5, add(a, a)) would become add(5, 5)
	 * after this method since instances of a will be replaced with 5
	 * 
	 * 
	 * @param The name of the variable that is being replaced in the expression
	 * @param The expression that contains the variable
	 * @param The integer value of the variable
	 * @return The CalculatorNode returned will be an expression that can be further evualted and
	 * will have every instance of the variable replaced
	 */
	
	private static CalculatorNode replaceVariables(String varName ,CalculatorNode variableExpression, int value)
	{
		if (variableExpression.getNodeType() == NodeType.VARIABLE)
		{
			VariableNode varNode = (VariableNode) variableExpression;
			if (varNode.getVariableName().equals(varName))
			{
			return new NumberNode(value);
			} else {
				//prevents the case of a let within a let and replace the wrong variable
				
				//eg
				//let (a, 3, let(b, 4, mult(a,b))
				//If varName is a we won't replace b
				//with a's value
				return variableExpression;
			}
		} else if (variableExpression.getNodeType() == NodeType.FUNCTION) {
			
			FunctionNode functionNode = (FunctionNode) variableExpression;
			
			//This code will go into the left and right nodes of the function
			//and replace any instances of the variable
			functionNode.setLeftNode(replaceVariables(varName, functionNode.getLeftNode()  , value));
			functionNode.setRightNode(replaceVariables(varName, functionNode.getRightNode(), value));
			
			return functionNode;	
		} else if (variableExpression.getNodeType() == NodeType.LET)
		{
			
			//This block will replace nested let statments correctly
			//For example
			//let (a, 5, let(b, add(a, b), mult(a, b)) 
			// If this code is being run with a as the variable the expression will turn into
			// let(b, add(5, b), mult(a,b) 
			// at this block 
			LetNode letNode = (LetNode)variableExpression;
			CalculatorNode letValue = letNode.getVariableValue();
			letValue = replaceVariables(varName, letValue, value);
			
			CalculatorNode letExpression = letNode.getVariableExpression();
			letExpression = replaceVariables(varName, letExpression, value);
			
			//The example above will be converted to
			//let(b, add(5, b), mult(5, b)) at this point
			
			return new LetNode(letNode.getVariableName(), letValue, letExpression);
		} else {
			//only case left is number node where no action is needed
			return variableExpression;
		}
	}

}
