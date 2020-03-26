/**
 * Ninety.java
 * 
 * This program should answer the following
 * question (creating output), making use of a loop or loops:
 * The sum of the squares of the first ten natural numbers is,
 * 		12 + 22 + ... + 102 = 385
 * The square of the sum of the first ten natural numbers is,
 * 		(1 + 2 + ... + 10)2 = 552 = 3025
 * Hence the difference between the sum of the squares of the first ten natural
 * numbers and the square of the sum is 3025 − 385 = 2640. Find the difference
 * between the sum of the squares of the first ninety natural numbers and the
 * square of the sum.
 * 
 * @author Joseph Zhang
 * @version 1.0
 * @since 8/22/2019
 */
 
 public class Ninety
 {
	/** Create field(s) up here, if necessary.   */
	private long number;
	
	/**  Creates a Sample object, assigning
	 *   values to fields, etc.
	 */
	public Ninety ( )
	{
		number = -1;
	}
	
	/**  Runs an instance of Ninety  */
	public static void main(String [] args)
	{
		Ninety run = new Ninety();
		run.method1();
		run.method2();
	}
	
	/**  Does something.  */
	public void method1 ( )
	{		
		long initial = 90;

		long squareSum = 0;
		int tempSum = 0;
		long squareTotalSum = 0;
		long difference = 0;
		
		for(int i = 1; i <= initial; i++)
		{
			squareSum += Math.pow(i, 2);
		}
		
		for(int i = 1; i <= initial; i++)
		{
			tempSum += i;
		}
		squareTotalSum = tempSum*tempSum;
		
		difference = squareTotalSum - squareSum;

		number = difference;
	}
	
	/**  Does something else.  */
	public void method2 ( )
	{		
		System.out.println("\n\n\n");
		System.out.println(number);
		System.out.println("\n\n\n");
	}
 }
