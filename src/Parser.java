import java.util.ArrayList;


/**
 * 
 * @author bemayer
 * This class aims to parse expressions that are in plain text.
 * Expressions that are parseable will be turned into CalculatatorNode instances
 *
 */

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
	
	/**
	 * 
	 * @param The expression to parse and get the calculationNode for
	 * @return Returns a calculationNode which can be proccessed to find the result of
	 * the expression
	 * @throws ParserException
	 */
	
	public  CalculatorNode parse(String expression) throws ParserException
	{
		return parse(expression, 0);
	}
	
	/**
	 * 
	 * @param The expression to parse 
	 * @param This is the starting point of the expression. This method will only
	 * parse past the starting point and to the end
	 * @return If the expression is parseable this method will return a CalculatorNode
	 * this node can then be used to figure out the result of the expression
	 * @throws ParserException if the expression cannot be parsed 
	 */
	private CalculatorNode parse(String expression, int startIndex) throws ParserException
	{
		 
		StringBuilder currentFunction = new StringBuilder();
		StringBuilder currentNumber = new StringBuilder();
		
		boolean isNumber = false;
		boolean isLetter = true;
		
		
		//check to see if the string matches a variable...
		if (validVariables.contains(expression.trim()))
		{
			//A expression that is passed into the parser that just has a 
			//variable will only have that variable 
			return new VariableNode(expression.trim());	
		}
		
		
		for (int i = startIndex; i < expression.length(); i++)
		{
			char currentChar = expression.charAt(i);
			
			
			
			if (isNumber && isNumber(currentChar))
			{
				currentNumber.append(currentChar); //add onto the currentNumber
			} else if (isNumber(currentChar))
			{
			
				
				currentNumber.append(currentChar);	//start to append to the currentNumber
				isNumber = true;
			} else if (isNumber && (currentChar == ' ' || currentChar == ',' 
													|| (i == (expression.length() - 1))))
			{
				String currentNumberString = currentNumber.toString();
				return new NumberNode(Integer.parseInt(currentNumberString));
				
			}
			
			
			//Add non space and non ( to the currentFunction and 
			//then eventually check if that string is valid
			if (currentChar != ' ' && currentChar != '('  )
			{
				isLetter = true;
				currentFunction.append(currentChar);
				
			}
				
			
			if (currentChar == '(' )
			{
				String functionString = currentFunction.toString();
				FunctionNode functionNode;
				FunctionType functionType = null;				
				
				if (FunctionType.isSimpleFunction(functionString)) {
					
					functionType = FunctionType.getFunctionTypeBasedOnName(functionString);
				
					
					
					//Separate out the arguments of the current function
					
					String[] arguments = searchForParameters(expression, i + 1);
					
					//check to see if the number of arguments matches 
					//the number of arguments for the function
					if  (functionType.getNumArguments() == arguments.length) {
						
						String firstParameterString = arguments[0];
						
					
						if ( ! checkParameterValidity(firstParameterString)) {
							throw new ParserException("Invalid Parameter of " + firstParameterString);
						}
					
						CalculatorNode leftNode = parse(firstParameterString);
					
						
						
						
						String secondParamaterString = arguments[1];
						
						if (! checkParameterValidity(secondParamaterString)) {
							throw new ParserException("Invalid Parameter of " + secondParamaterString);
						}
						
					
						CalculatorNode rightNode = parse(secondParamaterString);
						
						functionNode = new FunctionNode(functionType, leftNode, rightNode);
						return functionNode;
					} else {
						String message = ("The expression contained an invalid number of arguments for function " + functionType.functionName()
											+ " was expecting " + functionType.getNumArguments() + " But got " + arguments.length);
						throw new ParserException(message);
					}
				
				} else if (FunctionType.isLetFunction(functionString)) {
					return handleLetExpression(expression, i);	
				} else {
					String message = "The expression contains an invalid function " + functionString 
										+ " is not a valid function";
					throw new ParserException(message);	//Not a valid function
				}
				
			}	
		}
		
		
		//return the number at the end of the parsing 
		
		if (isNumber)
		{
			return new NumberNode(Integer.parseInt(currentNumber.toString()));
		} 
		
		
		//If the code reaches here the parser cannot handle expression entered
		String errorMessage =  "Invalid expression, please check it over and"
				+ " enter in a valid expression";
		
		throw new ParserException(errorMessage);
			
	
		
	}
	
	/**
	 * 
	 * @param The parameter to check if it is valid
	 * @return returns true if the paramater is valid. A valid parameter may either be a function
	 * or a number or a letter
	 * 
	 */
	
	private boolean checkParameterValidity(String parameter)
	{
		if (parameter.contains("(")) {
			return true;
		} else if (parameter.trim().matches("[0-9]+")) {
			return true;
		} else if (parameter.trim().matches("[a-zA-Z]+")) {
			return true;
		} else  {
			return false;
		}
	}

	/**
	 * @param expression
	 * @param i
	 * @return
	 * @throws ParserException if the expression is not valid
	 */
 	private CalculatorNode handleLetExpression(String expression, int i) throws ParserException {
		String[] arguments = searchForParameters(expression, i + 1);
		
		
		
		String variableName = arguments[0].trim();
		
		if (variableName.matches("[a-zA-Z]+")) {
			this.validVariables.add(variableName);
		} else {
			String errorMessage = "The expression entered contains an invalid variable " + variableName 
							+ " is not a valid variable ";
			throw new ParserException(errorMessage);
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



}
