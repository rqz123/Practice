/**
 * MostTriples.java
 * 
 * @author Joseph Zhang
 * @version 1.0
 * @since 10/3/19
 */
 
public class MostTriples
{
	public MostTriples () 
	{
	
	}
	
	public static void main(String [] args)
	{
		MostTriples trial = new MostTriples();
		//long begin = System.currentTimeMillis();
		trial.run();
		//long end = System.currentTimeMillis();
		//System.out.println("The program took " + (end-begin)/1000 + " seconds to run");
	}
	
	public void run()
	{
		int sum = 0;

		int count = 0;
		int max_count = 0;
		
		int a = 0;
		int b = 0;
		int c = 0;
		
		for(int x = 13; x <= 1000; x++)
		{
			sum = x;
			for(int i = 1; i <= sum/3; i++)
			{	
				for(int j = i + 1; j <= sum/2; j++)
				{
					int k = sum - i - j;
					if(i*i + j*j == k*k)
					{
						count++;
						if(count > max_count)
						{
							a = i;
							b = j;
							c = k;
							
							//System.out.println("a = " + a + " b = " + b + " c = " + c);
						}
					}
				}
			}
			
			max_count = Math.max(max_count, count);
			count = 0;
		}
		
		System.out.println("\n\n\n");
		System.out.println("p: " + max_count);
		System.out.println("number: " + (a+b+c));
		System.out.println("\n\n\n");
	}
}
