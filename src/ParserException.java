
public class ParserException extends Exception 
{
	private String message = null;
	
	public String toString()
	{
		
			return message;
		
	}
	
	public ParserException(String message)
	{
		this.message = message;
	}
	
	public ParserException()
	{
		message = new String("The expression entered is not valid, please enter a correct expression");
	}
	
	

}
