
/**
 * 
 * @author Bernie Mayer
 * 
 * This class represents a let node in CalculatorNode tree
 * A let node needs a variableName, a VariableValue, and the expression the 
 * variable is used in
 *
 */

public class LetNode extends CalculatorNode
{
	
	private String variableName;
	public String getVariableName() {
		return variableName;
	}


	public CalculatorNode getVariableValue() {
		return variableValue;
	}


	public CalculatorNode getVariableExpression() {
		return variableExpression;
	}


	private CalculatorNode variableValue;
	private CalculatorNode variableExpression;
	
	/**
	 * 
	 * @param The string that represents the variables name
	 * @param The value of the variable
	 * @param The expression that the variable is used in
	 */
	public LetNode(String argVariableName, 
			CalculatorNode argValue,
			CalculatorNode argExpression)
	{
		super(NodeType.LET);
		
		this.variableName = argVariableName;
		this.variableValue = argValue;
		this.variableExpression = argExpression;	
	}
	

}
