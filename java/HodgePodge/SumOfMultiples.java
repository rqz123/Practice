/**
 * SumOfMultiples.java
 * 
 * Find the sum of the multiples of 3 or 5 below 3000
 * 
 * @author Joseph Zhang
 * @version 1.0
 * @since 8/22/2019
 */
 
public class SumOfMultiples
{
	/** comment */
	private int number;
	
	/**  Creates a Sample object, assigning
	 *   values to fields, etc.
	 */
	public SumOfMultiples ( )
	{
		number = -1;
	}
	
	/**  Runs an instance of Least  */
	public static void main(String [] args)
	{
		SumOfMultiples run = new SumOfMultiples();
		run.method1();
		run.method2();
	}
	
	/** Finds the sum **/
	public void method1 ( )
	{		
		number = 0;
		int max = 3000;
		for(int i = 3; i < max; i++)
		{
			if(i % 3 == 0 || i % 5 == 0)
			{
				//System.out.print(i + " ");
				number += i;
			}
		}
	}
	
	/** Prints the sum **/
	public void method2 ( )
	{		
		System.out.println("\n\n\n");
		System.out.println("\nSum: " + number);
		System.out.println("\n\n\n");
	}
}
