



//This is the abstract class that is used 
//for the parse tree

/**
 * 
 * @author Bernie Mayer
 * 
 * This is the abstract class that all classes that are in
 * a CalculatorNode Tree must inherit from
 *
 */

public abstract class CalculatorNode 
{
	
	private NodeType nodeType;
	
	public NodeType getNodeType() {
		return nodeType;
	}
	
	
	/**
	 * 
	 * @param the type of node this instance of CalculatorNode is
	 */
	public CalculatorNode(NodeType aNodeType)
	{
		this.nodeType = aNodeType;
	}

}
