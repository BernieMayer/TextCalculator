
/**
 * 
 * @author Bernie Mayer
 * 
 * The starting point of the application.
 * This program requires a command line argument to be run
 * 
 * For example 
 * 
 * $java CalculatorDriver "add(1, 1)"
 * 
 * would run the program with the input of "add(1, 1)"
 * 
 * 
 *
 */

public class CalculatorDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length < 1)
		{
			System.out.println("This program needs an argument to be passed in to be run");
		} else {
			String expression = args[0];
			CalculatorManager manager = new CalculatorManager();
			manager.run(expression);
		}

	}

}
