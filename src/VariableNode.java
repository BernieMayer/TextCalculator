
public class VariableNode extends CalculatorNode 
{

	private char variableName;

	public char getVariableName() {
		return variableName;
	}
	
	public VariableNode(char varName)
	{
		super(NodeType.VARIABLE);
		this.variableName = varName;
	}
	
	
}
