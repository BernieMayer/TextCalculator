import java.util.ArrayList;

public class CalculatorManager 
{
	
	ArrayList<String> queries;
	
	public CalculatorManager()
	{
		queries = new ArrayList<String> ();
	}
	
	
	public void run(String initialQuery)
	{
		queries.add(initialQuery);
		
		CalculatorNode aNode = null;
		Parser aParser = new Parser();
		try {
			aNode = aParser.parse(initialQuery);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(CalculatorTreeEvaluator.evaluateTree(aNode) );
		
		
	}

}
