//import FunctionNode.FunctionType;

public class CalculatorTreeEvaluator 
{
	
	
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
				/*
				if (functionNode.leftNode.getNodeType() == NodeType.NUMBER
						&& functionNode.rightNode.getNodeType() == NodeType.NUMBER)
				{
					NumberNode leftNumberNode = (NumberNode) functionNode.leftNode;
					NumberNode rightNumberNode = (NumberNode) functionNode.rightNode;
					
					return leftNumberNode.getNodeValue() + rightNumberNode.getNodeValue();
					
				}*/
				
			} else if (functionNode.getFunctionType() == FunctionType.SUB)
			{
				return evaluateTree(functionNode.getLeftNode()) - evaluateTree(functionNode.getRightNode());
				/*
				if (functionNode.getLeftNode().getNodeType() == NodeType.NUMBER
						&& functionNode.getRightNode().getNodeType() == NodeType.NUMBER)
				{
					NumberNode leftNumberNode = (NumberNode) functionNode.getLeftNode();
					NumberNode rightNumberNode = (NumberNode) functionNode.getRightNode();
					
					return leftNumberNode.getNodeValue() - rightNumberNode.getNodeValue();
					
				}*/
			} else if (functionNode.getFunctionType() == FunctionType.MULT)
			{
				
				return evaluateTree(functionNode.getLeftNode()) * evaluateTree(functionNode.getRightNode()); 
				/*
				if (functionNode.getLeftNode().getNodeType() == NodeType.NUMBER
						&& functionNode.getRightNode().getNodeType() == NodeType.NUMBER)
				{
					NumberNode leftNumberNode = (NumberNode) functionNode.getLeftNode();
					NumberNode rightNumberNode = (NumberNode) functionNode.getRightNode();
					
					return leftNumberNode.getNodeValue() * rightNumberNode.getNodeValue();
					
				}*/
			} else if (functionNode.getFunctionType() == FunctionType.DIV)
			{
				
				return evaluateTree(functionNode.getLeftNode()) / evaluateTree(functionNode.getRightNode());
				/*
				if (functionNode.getLeftNode().getNodeType() == NodeType.NUMBER
						&& functionNode.getRightNode().getNodeType() == NodeType.NUMBER)
				{
					NumberNode leftNumberNode = (NumberNode) functionNode.getLeftNode();
					NumberNode rightNumberNode = (NumberNode) functionNode.getRightNode();
					
					return leftNumberNode.getNodeValue() / rightNumberNode.getNodeValue();
					
				}*/
			}
			
		} else if (aNode.getNodeType() == NodeType.LET )
		{
			LetNode letNode = (LetNode) aNode;
			
			char variableName = letNode.getVariableName();
			int variableValue = evaluateTree(letNode.getVariableValue());
			CalculatorNode variableExpression = letNode.getVariableExpression();
			
			return evaluateTree(replaceVariables(variableName,
												 variableExpression,
												 variableValue));
					
			
			
			
			
			
		}
		
		return 0; //temporary while the method is being implemented
		
	}
	
	private static CalculatorNode replaceVariables(char varName ,CalculatorNode variableExpression, int value)
	{
		if (variableExpression.getNodeType() == NodeType.VARIABLE)
		{
			VariableNode varNode = (VariableNode) variableExpression;
			if (varNode.getVariableName() == varName)
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
			LetNode letNode = (LetNode)variableExpression;
			CalculatorNode letValue = letNode.getVariableValue();
			letValue = replaceVariables(varName, letValue, value);
			
			CalculatorNode letExpression = letNode.getVariableExpression();
			letExpression = replaceVariables(varName, letExpression, value);
			
			return new LetNode(letNode.getVariableName(), letValue, letExpression);
		} else {
			//only case left is number node where no action is needed
			return variableExpression;
		}
	}

}
