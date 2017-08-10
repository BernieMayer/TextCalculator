



//This is the abstract class that is used 
//for the parse tree

public abstract class CalculatorNode 
{
	
	private NodeType nodeType;
	
	public NodeType getNodeType() {
		return nodeType;
	}

	public CalculatorNode(NodeType aNodeType)
	{
		this.nodeType = aNodeType;
	}

}
