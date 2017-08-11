import java.util.ArrayList;

public class Parser 
{
	
	enum ParserState {
		SEARCHING_FOR_FUNCTION,
		SEARCHING_FOR_CLOSING_BRACKET,
		SEARCHING_FOR_COMMA
	}
	
	public static CalculatorNode parse(String expression) throws ParserException
	{
		return parse(expression, 0);
	}
	
	private static CalculatorNode parse(String expression, int startIndex) throws ParserException
	{
		 
		StringBuilder currentFunction = new StringBuilder();
		
		for (int i = startIndex; i < expression.length(); i++)
		{
			char currentChar = expression.charAt(i);
			
			if (currentChar != ' ' && currentChar != '(')
			{
				currentFunction.append(currentChar);
				
			} 
			if (currentChar == '(')
			{
				System.out.println("Seeing a (");
				String functionString = currentFunction.toString();
				FunctionNode functionNode;
				FunctionType functionType;
				if (functionString.equalsIgnoreCase(FunctionType.ADD.functionName())) {
					functionType = FunctionType.ADD;
				} else if (functionString.equalsIgnoreCase(FunctionType.SUB.functionName())) {
					functionType = FunctionType.SUB;
				} else if (functionString.equalsIgnoreCase(FunctionType.DIV.functionName())) {
					functionType = FunctionType.DIV;
				} else if (functionString.equalsIgnoreCase(FunctionType.MULT.functionName())) {
					functionType = FunctionType.MULT;
				} else {
					System.out.println("Invalid function, " + functionString + " is not a valid function");
					throw new ParserException();
				}
				
				String[] arguments = searchForParameters(expression, i + 1);
				
				String firstParameterString = arguments[0];
				
				CalculatorNode leftNode = parseArgument(firstParameterString);
				
				
				String secondParamaterString = arguments[1];
				
				CalculatorNode rightNode = parseArgument(secondParamaterString);
				
				
				functionNode = new FunctionNode(functionType, leftNode, rightNode);
				return functionNode;

				
			}	
		}
		
		return new NumberNode(0);	//Not yet implemented
	
		
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
		
		String test = "2, (3), (4 * 5))";
		
		String[] results = searchForParameters(test, 0);
		for (String s:results)
			System.out.println("Testing searchForParameters " + s );
		
		System.out.println("ProcessNumber is with argument " + argument);
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
	
	public static void main(String[] args){
		
		System.out.println("Is k working correctly " + isLetter('k'));
		System.out.println("Is a working correctly " + isLetter('a'));
		System.out.println("Is R working correctly " + isLetter('R'));
		System.out.println("Is - working correctly " + isLetter('-'));
		
		System.out.println("Starting the parser");
		try {
			//parse("add(1,1)");
			parse("add(1, mult(2, 3))");
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
