
public class ParserException extends Exception 
{
	private String parserMessage = null;
	
	public String toString()
	{
		
			return parserMessage;
		
	}
	
	public ParserException(String message)
	{
		this.parserMessage = message;
	}
	
	public ParserException()
	{
		parserMessage = new String("The expression entered is not valid, please enter a correct expression");
	}
	
	

}
