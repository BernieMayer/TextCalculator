import java.util.HashSet;

/**
 * 
 * @author Bernie Mayer
 * 
 * This enum is used to keep track of the valid Function Types in this code base
 *
 */

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

	private static HashSet<String> functionNames = new HashSet<String> ();
	//private static Hashtable<String> functionNames = new Hashtable<String> ();
	
	//This static block will iterate through all of the enums's in
	//the function types and add them to a functionName HashSet
	//This makes the FunctionType code more maintable 
	//also allows for a function type to be checked to see if
	// it is valid in O(1) time instead of O(n) where n is the number of 
	//functions
	static 
	{
		for (FunctionType aFunctionType:FunctionType.values())
		{
			functionNames.add(aFunctionType.functionName().toUpperCase());
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
		return functionNames.contains(argFunctionName.toUpperCase());
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
	
	static FunctionType getFunctionTypeBasedOnName(String functionName)
	{
		for (FunctionType functionType:FunctionType.values())
		{
			if (functionType.functionName.equalsIgnoreCase(functionName))
			{
				return functionType;
			}
		}
		
		return null;
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
