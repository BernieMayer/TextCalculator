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
	

	/**
	 * This is the constructor for the Parser
	 * using this constructor will initialize the list of valid varialbes
	 */
	public Parser()
	{
		this.validVariables = new ArrayList<String> ();
	}
	
	/**
	 * 
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
	 * The parser uses a scanning system. The parser will go from the start to the end of a
	 * string. The parser will detect if the string entered in is valid
	 * and if the string is valid it will parse it and make the proper tree
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
				//This case means that the number has ended and needs
				//to be returned as NumberNode
				String currentNumberString = currentNumber.toString();
				return new NumberNode(Integer.parseInt(currentNumberString));
				
			}
			
			
			//Add non space and non ( to the currentFunction and 
			//then eventually check if that string is a valid function
			if (currentChar != ' ' && currentChar != '('  )
			{
				isLetter = true;
				currentFunction.append(currentChar);
				
			}

			//Seeing a "(" signifies that a function is starting
			if (currentChar == '(' )
			{
				return handleSeeingFunction(expression, currentFunction, i);
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
	 * @param The current expression
	 * @param The function that has been built out of the current expression
	 * @param The index to start from on the current expression
	 * @return This method will return a calculator node if one can be created.
	 * @throws ParserException is thrown if an invalid number of arguments
	 * happens or the user enters an invalid function, eg abc would not be
	 * a valid function
	 */
	private CalculatorNode handleSeeingFunction(String expression, StringBuilder currentFunction, int i)
			throws ParserException {
		
		
		String functionString = currentFunction.toString();
		FunctionType functionType = null;				
		
		if (FunctionType.isSimpleFunction(functionString)) {
			
			functionType = FunctionType.getFunctionTypeBasedOnName(functionString);
		
			
			
			//Separate out the arguments of the current function
			
			String[] arguments = searchForParameters(expression, i + 1);
			
			//check to see if the number of arguments matches 
			//the number of arguments for the function
			if  (functionType.getNumArguments() == arguments.length) {
				
				return handleSimpleFunction(functionType, arguments);
				
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

	/**
	 * @param the functionType to create for the CalculatorNode
	 * @param the arguments for the function 
	 * @return returns a functionNode based on the arguments for the function
	 * @throws ParserException is thrown if the number of arguments is invalid
	 * eg for add() if three arguments are passed in ParserException is thrown
	 */
	private CalculatorNode handleSimpleFunction(FunctionType functionType, String[] arguments) throws ParserException {
		FunctionNode functionNode;
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
		
		if (arguments.length != FunctionType.LET.getNumArguments())
		{
			throw new ParserException("A let statement in the expression has an invalid number of arguments");
		}
		
		
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
	
	/**
	 * 
	 * @param The expression to search for the parameters of. Generally for this function
	 * it is expected to be past the ( symbol. So for add(1,1) this function would expect
	 * 1,1)
	 * @param The index to start reading from on the expression
	 * @return A list of parameters based on the expression
	 * 
	 */
 	
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

	
	/**
	 * 
	 * @param the character to check to see if it a number
	 * @return this will return true if aChar is a number from 0-9
	 */
	//checks if aChar is a number
	private static boolean isNumber(char aChar)
	{
		if (((int) aChar >= (int) '0') && ((int) aChar <= (int) '9'))
		{
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param the character to check to see if it a letter
	 * @return will return true if aChar is a letter that is from a-z or A-Z
	 */
	
	//checks if aChar is a letter
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
