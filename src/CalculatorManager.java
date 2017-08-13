import java.util.ArrayList;

/**
 * 
 * @author Bernie Mayer
 * 
 * Calculator Manager is in charge of using the Parser
 * and then after parsing getting those expressions evaluated
 * This class can also be modified so that the user can keep entering in queries at 
 * runtime
 * 
 */

public class CalculatorManager 
{
	
	ArrayList<String> queries;
	
	public CalculatorManager()
	{
		queries = new ArrayList<String> ();
	}
	
	
	/**
	 * 
	 * This function will evaluate the initial query and then print it to
	 * the console. It can also be modified so that more queries can evualted at 
	 * runtime
	 * 
	 * @param The first query a user wants to enter into this function
	 */
	
	public void run(String initialQuery)
	{
		queries.add(initialQuery);
		
		CalculatorNode aNode = null;
		Parser aParser = new Parser();
		try {
			aNode = aParser.parse(initialQuery);
			System.out.println(CalculatorTreeEvaluator.evaluateTree(aNode) );
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	/**
	 * 
	 * 
	 * @param The query the user wants to find the result of
	 * @return Returns the result of the query, 
	 */
	public int runQuery(String query)
	{
		CalculatorNode aNode = null;
		Parser aParser = new Parser();
		
		try {
			aNode = aParser.parse(query);
		} catch (ParserException e)
		{
			e.printStackTrace();
		}
		
		return CalculatorTreeEvaluator.evaluateTree(aNode);
		
	}

}
