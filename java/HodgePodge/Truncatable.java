/**
 * Truncatable.java
 * 
 * @author Joseph Zhang
 * @version 1.0
 * @since 10/3/19
 */
 
public class Truncatable {
	
	public Truncatable () 
	{
	
	}
	
	public static void main(String [] args)
	{
		Truncatable trial = new Truncatable();
		//long begin = System.currentTimeMillis();
		trial.run();
		//long end = System.currentTimeMillis();
		//System.out.println("The program took " + (end-begin)/1000 + " seconds to run");
	}
	
	public void run()
	{
		System.out.println("\n\n\n");
		//Generate List
		int sum = 0;
		
		// Per requirement, "note: 2, 3, 5, and 7 are not considered to be “truncatable primes",
		// so starts from 23. 
		for(int i = 23; i < 1000000; i++)
		{
			if((i%10 == 3 || i%10 == 7) && isPrime(i))
			{
				if(checkLeftTruPrime(i) && checkRightTruPrime(i))
				{
					System.out.print(i + " ");
					sum += i;
				}
			}
		}
		
		System.out.println("\nsum: " + sum);
		
		System.out.println("\n\n\n");
		
		//int number = 3797;
		//System.out.println(isPrime(number) + " " + checkleftTruPrime(number) + " " + checkRightTruPrime(number));
	}
	
	public boolean isPrime(int number)
	{
		if(number == 1)
			return false;
		
		for(int i = 2; i <= Math.pow(number, 0.5); i++)
		{
			if(number % i == 0)
				return false;
		}
		return true;
	}
	
	public boolean checkLeftTruPrime(int number)
	{
		//count digit
		int count = 0;
		int copy = number; 
		
		while(copy != 0)
		{
			copy = copy/10;
			count++;
		}
		//System.out.println("count: " + count);
		
		//left to right
		for(int i = count - 1; i > 0; i--)
		{
			number = number % (int)(Math.pow(10, i));
			if(isPrime(number) == false)
				return false;
		}
		return true;
	}
	
	public boolean checkRightTruPrime(int number)
	{
		//right to left
		while(number != 0)
		{
			number = number/10;
			if(isPrime(number) == false)
				return false;
		}
		return true;
	}
}
