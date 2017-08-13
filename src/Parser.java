import java.util.ArrayList;

public class Parser 
{
	
	ArrayList<String> validVariables;
	
	enum ParserState {
		SEARCHING_FOR_FUNCTION,
		SEARCHING_FOR_CLOSING_BRACKET,
		SEARCHING_FOR_COMMA
	}
	
	public Parser()
	{
		this.validVariables = new ArrayList<String> ();
	}
	
	public  CalculatorNode parse(String expression) throws ParserException
	{
		return parse(expression, 0);
	}
	
	private CalculatorNode parse(String expression, int startIndex) throws ParserException
	{
		 
		StringBuilder currentFunction = new StringBuilder();
		StringBuilder currentNumber = new StringBuilder();
		
		boolean isNumber = false;
		
		
		//check to see if the string matches a variable...
		
		if (validVariables.contains(expression.trim()))
		{
			return new VariableNode(expression.trim());
		}
		
		
		for (int i = startIndex; i < expression.length(); i++)
		{
			char currentChar = expression.charAt(i);
			
			if (isNumber && isNumber(currentChar))
			{
				currentNumber.append(currentChar);
			} else if (isNumber(currentChar))
			{
				currentNumber.append(currentChar);
				isNumber = true;
			} else if (isNumber && (currentChar == ' ' || currentChar == ',' 
													|| (i == (expression.length() - 1))))
			{
				return new NumberNode(Integer.parseInt(currentNumber.toString()));
			}
			
			
			if (currentChar != ' ' && currentChar != '(')
			{
				
				currentFunction.append(currentChar);
				
			} 
			if (currentChar == '(' )
			{
				String functionString = currentFunction.toString();
				FunctionNode functionNode;
				FunctionType functionType = null;
				
				if (functionString.equalsIgnoreCase(FunctionType.ADD.functionName())) {
					functionType = FunctionType.ADD;
				} else if (functionString.equalsIgnoreCase(FunctionType.SUB.functionName())) {
					functionType = FunctionType.SUB;
				} else if (functionString.equalsIgnoreCase(FunctionType.DIV.functionName())) {
					functionType = FunctionType.DIV;
				} else if (functionString.equalsIgnoreCase(FunctionType.MULT.functionName())) {
					functionType = FunctionType.MULT;
				} else if (functionString.equalsIgnoreCase(FunctionType.LET.functionName())) {
					//handle any let statement code here...
					return handleLetExpression(expression, i);
					
					
				} else {
					System.out.println("Invalid function, " + functionString + " is not a valid function");
					throw new ParserException();
				}
				
				String[] arguments = searchForParameters(expression, i + 1);
				if  (functionType.getNumArguments() == arguments.length) {
					
					
					String firstParameterString = arguments[0];
				
					CalculatorNode leftNode = parse(firstParameterString);
					
					String secondParamaterString = arguments[1];
				
					CalculatorNode rightNode = parse(secondParamaterString);
					
					functionNode = new FunctionNode(functionType, leftNode, rightNode);
					return functionNode;
				} else {
					System.out.println("Not valid number of arguments for function " + functionType.functionName()
										+ " was expecting " + functionType.getNumArguments() + " But got " + arguments.length);
					throw new ParserException();
				}
				
			}	
		}
		
		if (isNumber)
		{
			return new NumberNode(Integer.parseInt(currentNumber.toString()));
		}
		
		return new NumberNode(0);	//Not yet implemented
	
		
	}

	/**
	 * @param expression
	 * @param i
	 * @return
	 * @throws ParserException
	 */
	private CalculatorNode handleLetExpression(String expression, int i) throws ParserException {
		String[] arguments = searchForParameters(expression, i + 1);
		
		
		
		String variableName = arguments[0].trim();
		
		if (variableName.matches("[a-zA-Z]+")) {
			this.validVariables.add(variableName);
		}
		
		//now handle the value part of the let statement
		CalculatorNode valueOfVariable = parse(arguments[1]);
		
		//now handle the expression part of the let statement
		
		CalculatorNode letExpression = parse(arguments[2]);
		
		LetNode letNode = new LetNode(variableName, valueOfVariable, letExpression);
		return letNode;
	}
	
	
	//Note this method will search for an argument in
	//The string passed in and then will
	//extract that argument
	//The string passed in should be without a "("
	//So using the string mult(3, 2)
	//Only the string "3, 2)" should be 
	//passed in
	private static String searchForParameter(String expression, int startIndex) throws ParserException
	{
		int numOpenBrackets = 0;
		int finalIndex;
		
		for (int i = startIndex; i < expression.length(); i++)
		{
			char currentChar = expression.charAt(i);
			//System.out.println("Current char is " + currentChar);
			if (currentChar == ',' && (numOpenBrackets == 0))
			{
				return expression.substring(startIndex, i);
			} else if (currentChar == ')')
			{
				numOpenBrackets--;
			} else if (currentChar == '(')
			{
				numOpenBrackets++;
			}
		}
		
		throw new ParserException();
	}
	
	private static String[] searchForParameters(String expression, int startIndex)
	{
		int numOpenBrackets = 0;
		int finalIndex;
		int currentArgumentStart = startIndex;
		ArrayList<String> argumentList = new ArrayList<String>();
		
		for (int i = startIndex; i < expression.length(); i++)
		{
			char currentChar = expression.charAt(i);
			
			if (currentChar == ',' && (numOpenBrackets == 0))
			{
				argumentList.add(expression.substring(currentArgumentStart, i));
				currentArgumentStart = i + 1;
			} else if (i == (expression.length() - 1) && (numOpenBrackets == 0))
			{
				//This case gets in the last argument into the list
				argumentList.add(expression.substring(currentArgumentStart, i));
			} else if (currentChar == ')')
			{
				numOpenBrackets--;
				if (numOpenBrackets == -1)
				{
					argumentList.add(expression.substring(currentArgumentStart, i));
				}
			} else if (currentChar == '(')
			{
				numOpenBrackets++;
			}
			
			
		}
		
		return argumentList.toArray( new String[0]);
		
		
	}
	
	private static CalculatorNode parseArgument(String argument) throws ParserException
	{
		boolean seeNumber = false;
		
		
		
		for (int i = 0; i < argument.length(); i++) 
		{
			char currentChar = argument.charAt(i);
			
			if (currentChar != ' ' )
			{
				//check if the char is a number if it is then set seeNumber to be true
				
				if (isNumber(currentChar))
				{
					//seeNumber = true;
					
					//check if the argument passed 
					
					//check if the argument has a 
					
					String argumentSubstring = argument.substring(i, argument.length());
					
					return processNumber(argumentSubstring);
					
					
				} 
				
				
				
			}
		}
		
		throw new ParserException();
	}
	
	private static CalculatorNode processNumber(String argument) throws ParserException
	{

		StringBuilder currentNum = new StringBuilder();
		for (int i = 0; i < argument.length(); i++)
		{
			char currentChar = argument.charAt(i);
			if (isNumber(currentChar) )
			{
				currentNum.append(currentChar);
			} else if (currentChar == ' ' || currentChar == ',')
			{
				//stop building the number and make the node
				int number = Integer.parseInt( currentNum.toString());
				
				return new NumberNode(number);	
				
			} else {
				//Should not happent
				throw new ParserException();
			}
		}
		
		int number = Integer.parseInt( currentNum.toString());
		
		return new NumberNode(number);	
		//throw new ParserException();
		
	}
	
	
	private static boolean isNumber(char aChar)
	{
		if (((int) aChar >= (int) '0') && ((int) aChar <= (int) '9'))
		{
			return true;
		} else {
			return false;
		}
	}
	
	
	private static boolean isLetter(char aChar)
	{
		
		if ( (((int) aChar >= (int) 'a') && ((int) aChar <= (int) 'z')) ||
		(((int) aChar >= (int) 'A') && ((int) aChar <= (int) 'Z')) )
		{
			return true;
		} else {
			return false;
		}
		
	}
	/*
	private static CalculatorNode parseArgument(String expression, int startIndex, int finalIndex)
	{
		
		for (int i = 0; i < finalIndex)
		
	}*/
	
	public static void main(String[] args)
	{
		Parser aParser = new Parser();
		
		String[] functionArgs = aParser.searchForParameters("1, 2)  ", 0);
		for (String arg:functionArgs)
			System.out.println(arg);
		
	
	}

}
