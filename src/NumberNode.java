
/**
 * 
 * @author Bernie Mayer
 *
 *This class is used in a CalculatorNode tree to represent a Number Node
 *
 */

public class NumberNode extends CalculatorNode 
{
	
	int nodeValue;
	
	public int getNodeValue() {
		return nodeValue;
	}
	
	
	/**
	 * 
	 * @param The value of the number node
	 */
	public NumberNode(int argValue)
	{
		super(NodeType.NUMBER);
		this.nodeValue = argValue;
	}
	

}
