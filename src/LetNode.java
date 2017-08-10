
public class LetNode extends CalculatorNode
{
	
	char variableName;
	CalculatorNode variableValue;
	CalculatorNode variableExpression;
	
	
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
