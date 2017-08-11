
public class VariableNode extends CalculatorNode 
{

	private String variableName;

	public String getVariableName() {
		return variableName;
	}
	
	public VariableNode(String varName)
	{
		super(NodeType.VARIABLE);
		this.variableName = varName;
	}
	
	
}
