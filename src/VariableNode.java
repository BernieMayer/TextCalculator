/**
 * 
 * 
 * @author Bernie Mayer
 * 
 * This class represents a variable node in Calculator Node Tree. 
 * 
 * For example
 * let(a, 5, add(a, 2))
 * 
 * The node a in add(a, 2) would be a variable node
 *
 */

public class VariableNode extends CalculatorNode 
{

	private String variableName;

	public String getVariableName() {
		return variableName;
	}
	
	
	/**
	 * 
	 * @param The String that represents the name of the variable node
	 */
	public VariableNode(String varName)
	{
		super(NodeType.VARIABLE);
		this.variableName = varName;
	}
	
	
}
