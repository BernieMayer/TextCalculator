
public enum FunctionType 
{
	ADD("add"),
	SUB("sub"),
	MULT("mult"),
	DIV("div");
	
	private String functionName;
	
	FunctionType(String functionName){
		this.functionName = functionName;
	}
	
	String functionName() { return functionName; }
	
}
