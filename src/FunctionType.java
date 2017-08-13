import java.util.ArrayList;

public enum FunctionType 
{
	ADD("add",   2),
	SUB("sub",   2),
	MULT("mult", 2),
	DIV("div",   2),
	LET("let",   3);
	
	private String functionName;
	private int numArguments;
	
	/**
	 * @return the number of arguments the functionType instance has
	 */
	public int getNumArguments() {
		return numArguments;
	}

	private static ArrayList<String> functionNames = new ArrayList<String> ();
	//This static block will iterate through all of the enums's in
	//the function types and add them to a functionNames list
	//This makes the FunctionType code more maintainable
	static 
	{
		for (FunctionType aFunctionType:FunctionType.values())
		{
			functionNames.add(aFunctionType.functionName());
		}
	}
	
	
	
	
	FunctionType(String functionName, int argNumArguments){
		this.functionName = functionName;
		this.numArguments = argNumArguments;
	}
	
	String[] getListOfValidFunctionNames()
	{
		return (String[]) functionNames.toArray(new String[0] );
	}
	
	String functionName() { return functionName; }
	 
	
	/**
	 * 
	 * @param The function name you want to check to see if it is valid or not 
	 * @return returns true if argFunctionName is a function name in FunctionType
	 */
	static boolean isValidFunction(String argFunctionName)
	{
		//Iterate through all of the functions 
		//then return true if the functionName is in
		//the list of functions
		
		for (String validFunctionName:functionNames )
		{
			if (validFunctionName.equalsIgnoreCase(argFunctionName))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * 
	 * @param The function name you want to check if it is a simple function or not
	 * @return returns true if the function name is simple. A simple function in this case
	 * is add, mult, div, sub
	 * 
	 */
	
	static boolean isSimpleFunction(String argFunctionName)
	{
		if (argFunctionName.equalsIgnoreCase(FunctionType.ADD.functionName())) {
			return true;
		} else if (argFunctionName.equalsIgnoreCase(FunctionType.SUB.functionName())) {
			return true;
		} else if (argFunctionName.equalsIgnoreCase(FunctionType.DIV.functionName())) {
			return true;
		} else if (argFunctionName.equalsIgnoreCase(FunctionType.MULT.functionName())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param The function name you want to check if it is a let function
	 * @return Returns true if argFunctionName is a let function, otherwise returns false
	 */
	
	static boolean isLetFunction(String argFunctionName)
	{
		return FunctionType.LET.functionName().equalsIgnoreCase(argFunctionName);
	}
	
	
	
	
	
	
	
	
	
}
