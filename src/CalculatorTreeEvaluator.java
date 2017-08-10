//import FunctionNode.FunctionType;

public class CalculatorTreeEvaluator 
{
	
	
	public static int evaluateTree(CalculatorNode aNode)
	{
		if (aNode.getNodeType() == NodeType.FUNCTION)
		{
			//handle each function type here properly
			
			FunctionNode functionNode = (FunctionNode) aNode;
			
			if (functionNode.getFunctionType() == FunctionType.ADD)
			{
				if (functionNode.leftNode.getNodeType() == NodeType.NUMBER
						&& functionNode.rightNode.getNodeType() == NodeType.NUMBER)
				{
					NumberNode leftNumberNode = (NumberNode) functionNode.leftNode;
					NumberNode rightNumberNode = (NumberNode) functionNode.rightNode;
					
					return leftNumberNode.getNodeValue() + rightNumberNode.getNodeValue();
					
				}
				
			} else if (functionNode.getFunctionType() == FunctionType.SUB)
			{
				if (functionNode.leftNode.getNodeType() == NodeType.NUMBER
						&& functionNode.rightNode.getNodeType() == NodeType.NUMBER)
				{
					NumberNode leftNumberNode = (NumberNode) functionNode.leftNode;
					NumberNode rightNumberNode = (NumberNode) functionNode.rightNode;
					
					return leftNumberNode.getNodeValue() - rightNumberNode.getNodeValue();
					
				}
			} else if (functionNode.getFunctionType() == FunctionType.MULT)
			{
				if (functionNode.leftNode.getNodeType() == NodeType.NUMBER
						&& functionNode.rightNode.getNodeType() == NodeType.NUMBER)
				{
					NumberNode leftNumberNode = (NumberNode) functionNode.leftNode;
					NumberNode rightNumberNode = (NumberNode) functionNode.rightNode;
					
					return leftNumberNode.getNodeValue() * rightNumberNode.getNodeValue();
					
				}
			} else if (functionNode.getFunctionType() == FunctionType.DIV)
			{
				if (functionNode.leftNode.getNodeType() == NodeType.NUMBER
						&& functionNode.rightNode.getNodeType() == NodeType.NUMBER)
				{
					NumberNode leftNumberNode = (NumberNode) functionNode.leftNode;
					NumberNode rightNumberNode = (NumberNode) functionNode.rightNode;
					
					return leftNumberNode.getNodeValue() / rightNumberNode.getNodeValue();
					
				}
			}
			
		}
		
		return 0; //temporary while the method is being implemented
		
	}

}
