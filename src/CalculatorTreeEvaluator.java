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
			
		}
		
		return 0; //temporary while the method is being implemented
		
	}

}
