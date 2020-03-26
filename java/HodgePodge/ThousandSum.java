
/**
 * ThousandSum.java
 * 
 * Return the product abc in the equation a + b + c = 1000
 * 
 * @author Joseph Zhang
 * @version 1.0
 * @since 8/22/2019
 */
 
 public class ThousandSum
 {
	/** comment */
	private long number;
	
	/** comment */
	public ThousandSum ( )
	{
		number = -1;
	}
	
	/**  Runs an instance of Sample  */
	public static void main(String [] args)
	{
		ThousandSum run = new ThousandSum();
		run.method1();
		run.method2();
	}
	
	/** comment */
	public void method1 ( )
	{		
		int sum = 1000;
		int a = 0;
		int b = 0;
		int c  = 0;

    	for(int i = 1; i <= sum/3; i++)
    	{
        	for(int j = i + 1; j <= sum/2; j++)
        	{
         		int k = sum - i - j;
				if(i*i + j*j == k*k)
				{
					a = i;
					b = j;
					c = k;
				}
        	}
		}

		number = a*b*c;
	}
	
	/** Print the number  */
	public void method2 ( )
	{		
		System.out.println("\n\n\n");
		System.out.println(number);
		System.out.println("\n\n\n");
	}
 }
