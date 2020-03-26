/**
 * Least.java
 * 
 * What is the least positive number that is evenly divisible
 * (divisible with no remainder) by all the number from 1 to 25
 * 
 * @author Joseph Zhang
 * @version 1.0
 * @since 8/22/2019
 */
 
 public class Least
 {
	/** comment */
	private long number;
	
	/**  Creates a Sample object, assigning
	 *   values to fields, etc.
	 */
	public Least ()
	{
		number = -1;
	}
	
	/**  Runs an instance of Least  */
	public static void main(String [] args)
	{
		Least run = new Least();
		run.method1();
		run.method2();
	}
	
	/** comment */
	public void method1 ( )
	{	
		/** Initial Number */
		long max = 25;
		
		/** Length of the array */
		int count = 0;

		/** Find length */
		for(int i = 2; i < max; i++)
		{
			boolean condition = true;
			
				for(int j = 2; j < i; j++)
				{
					if(i%j == 0)
						condition = false;
				}
			
			if(condition == true)
			{
				//System.out.print(i + " ");
				count++;
			}
		}

		int [] prime_number = new int[count];
		int index = 0;
		
		/** Find prime numbers */
		for(int i = 2; i < max; i++)
		{
			boolean condition = true;
			
				for(int j = 2; j < i; j++)
				{
					if(i%j == 0)
						condition = false;
				}
			
			if(condition == true)
			{
				prime_number[index] = i;
				index++;
			}
		}

		int [] power = new int [count];
		
		/** Getting the power for the prime numbers */
		for(int i = 0; i < power.length; i++)
		{
			long inital = max;
			int temp = prime_number[i];
			int cifang = 0;
			
			while(inital/temp != 0)
			{
				inital = inital/temp; 
				cifang++;
			}

			power[i] = cifang;
		}

		long product1 = 1;

		/** Finding the product */
		for(int i = 0; i < prime_number.length; i++)
		{
			/** Debugging 
			System.out.println(prime_number[i] + " " + power[i]);
			System.out.println("product1: " + (long)(Math.pow(prime_number[i], power[i])));
			*/

			product1 *= (long)(Math.pow(prime_number[i], power[i]));
		}

		number = product1;
		
	}
	
	/** comment */
	public void method2 ( )
	{		
		System.out.println("\n\n\n");
		System.out.println("Number: " + number);
		System.out.println("\n\n\n");
	}
 }
