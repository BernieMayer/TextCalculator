

//This class is used as part of the tree that is made after parsing the expression
//This node is used to signal that a function is being used
public class FunctionNode extends CalculatorNode 
{
	

	
	FunctionType functionType;
	
	public FunctionType getFunctionType() {
		return functionType;
	}


	private CalculatorNode leftNode;
	
	public CalculatorNode getLeftNode() {
		return leftNode;
	}


	public void setLeftNode(CalculatorNode leftNode) {
		this.leftNode = leftNode;
	}


	public CalculatorNode getRightNode() {
		return rightNode;
	}


	public void setRightNode(CalculatorNode rightNode) {
		this.rightNode = rightNode;
	}


	private CalculatorNode rightNode;
	
	
	FunctionNode(FunctionType argFunctionType,
			CalculatorNode argLeftNode, 
			CalculatorNode argRightNode)
	{
		
		super(NodeType.FUNCTION);
		this.functionType = argFunctionType;
		this.leftNode = argLeftNode;
		this.rightNode = argRightNode;
	}
	
	public int performFunction()
	{
		if (this.getFunctionType() == FunctionType.ADD)
		{
			
			return CalculatorTreeEvaluator.evaluateTree(this.getLeftNode()) + CalculatorTreeEvaluator.evaluateTree(this.getRightNode());
			
		} else if (this.getFunctionType() == FunctionType.SUB)
		{
			return CalculatorTreeEvaluator.evaluateTree(this.getLeftNode()) - CalculatorTreeEvaluator.evaluateTree(this.getRightNode());

		} else if (this.getFunctionType() == FunctionType.MULT)
		{
			
			return CalculatorTreeEvaluator.evaluateTree(this.getLeftNode()) * CalculatorTreeEvaluator.evaluateTree(this.getRightNode()); 

		} else if (this.getFunctionType() == FunctionType.DIV)
		{
			
			return CalculatorTreeEvaluator.evaluateTree(this.getLeftNode()) / CalculatorTreeEvaluator.evaluateTree(this.getRightNode());
			
		}
		return 0; //If no function can be performed 0 will be returned
	}
	
	

}
