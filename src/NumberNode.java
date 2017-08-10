
public class NumberNode extends CalculatorNode 
{
	
	int nodeValue;
	
	public int getNodeValue() {
		return nodeValue;
	}

	public NumberNode(int argValue)
	{
		super(NodeType.NUMBER);
		this.nodeValue = argValue;
	}
	

}
