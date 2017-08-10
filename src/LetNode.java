
public class LetNode extends CalculatorNode
{
	
	private char variableName;
	public char getVariableName() {
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
	
	
	public LetNode(char argVariableName, 
			CalculatorNode argValue,
			CalculatorNode argExpression)
	{
		super(NodeType.LET);
		
		
		//TODO maybe make sure the argVariableName is
		//valid
		
		this.variableName = argVariableName;
		this.variableValue = argValue;
		this.variableExpression = argExpression;	
	}
	

}
