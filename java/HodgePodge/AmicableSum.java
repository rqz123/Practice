/**
 * AmicableSum.java
 * 
 * @author Joseph Zhang
 * @version 1.0
 * @since 10/3/19
 */
 
public class AmicableSum 
{
	public AmicableSum () 
	{
	
	}
	
	public static void main(String [] args)
	{
		AmicableSum trial = new AmicableSum();
		//long begin = System.currentTimeMillis();
		trial.run();
		//long end = System.currentTimeMillis();
		//System.out.println("The program took " + (end-begin)/1000 + " seconds to run");
	}
	
	public void run()
	{
		System.out.println("\n\n\n");
		int sum = 0;
		int copy = 0;
		
		for(int i = 1; i <= 10000; i++)
		{
			if(i != copy)
			{
				int a = i;
				int b = findSum(a);
			
				if(a == findSum(b) && a != b)
				{
					//System.out.println(i);
                	//System.out.println("a: " + a + "; b: " + b);
                	sum += a;
				} 
            copy = b;
			}
        }
        
        System.out.println(sum);
        System.out.println("\n\n\n");
	}
	
	public int findSum(int number)
	{
		int sum = 0;
		
		for(int i = 1; i < number; i++)
		{
			if(number % i == 0)
				sum += i;
		}
		return sum;
	}
	
	
}
