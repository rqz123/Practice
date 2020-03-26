/**
 * Million.java
 * 
 * Find the sum of all the primes below one million
 * 
 * @author Joseph Zhang
 * @version 1.0
 * @since 8/22/2019
 */
 
 public class Million
 {
	/** Create field(s) up here, if necessary.   */
	private long number;
	
	/**  Creates a Sample object, assigning
	 *   values to fields, etc.
	 */
	public Million ( )
	{
		number = -1;
	}
	
	/**  Runs an instance of Million  */
	public static void main(String [] args)
	{
		Million run = new Million();
		run.method1();
		run.method2();
	}
	
	/** comment */
	public void method1 ( )
	{		
		long max = 1000000;
		int [] array_org = new int [(int)(max) - 1];
		int [] array1 = new int [(int)(max/2)];
		int [] array2 = new int [(int)(max/2)]; 
		boolean swap = true;
		boolean exit = false;
		
		int count = 0;
		
		/** Getting all values */
		int index_org = 0;
		for(int i = 2; i <= max; i++)
		{
			array_org[index_org] = i;
			index_org++;
			count++;
		}
		
		count = 0;
		
		/** Remove all even except 2 */
		int index1 = 0;
		for(int i = 0; i < max - 1; i++)
		{
			if(array_org[i] == 2 || array_org[i] % 2 != 0)
			{
				array1[index1] = array_org[i];
				index1++;
				count++;
			}
		}		
		
		int index2 = 0;
		int index_swap = 0;
		index1 = 0;
		count = 0;
		long count1 = 0;
		long count2 = max/2;
		
		while(exit == false)
		{
			if(swap == true && exit == false)
			{
				index_swap++;
				int temp = array1[index_swap];
			
				//Limit reached
				if(temp*temp > array_org[array_org.length-1])
					exit = true;
				
				count = 0;
				
				//Add old prime numbers
				for(int i = 0; i <= index_swap; i++)
				{
					array2[index2] = array1[i];
					index2++;
					count++;
					count1++;
				}
				//Add new prime numbers
				for(int i = index_swap + 1; i < count2; i++)
				{
					if(array1[i] % temp != 0)
					{
						array2[index2] = array1[i];
						index2++;
						count++;
						count1++;
					}
				}
				
				count2 = 0;
				//copy 2 to 1
				for(int i = 0; i < array2.length; i++)
				{
					array1[i] = array2[i];
				}
				
				
				swap = false;
				index2 = 0;
			}
			
			if(swap == false && exit == false)
			{
				index_swap++;
				int temp = array2[index_swap];
				
				/** Limit reached */
				if(temp*temp > array_org[array_org.length-1])
					exit = true;
				
				count = 0;
			
				/** Add old prime numbers */
				for(int i = 0; i <= index_swap; i++)
				{
					array1[index1] = array2[i];
					index1++;
					count++;
					count2++;
				}
				
				/** Add new prime numbers */
				for(int i = index_swap + 1; i < count1; i++)
				{
					if(array2[i] % temp != 0)
					{
						array1[index1] = array2[i];
						index1++;
						count++;
						count2++;
					}
				}
				
				count1 = 0;
				/** copy 1 to 2 */
				for(int i = 0; i < array1.length; i++)
				{
					array2[i] = array1[i];
				}
				
				swap = true;
				index1 = 0;
			}
		}
		
		long sum1 = 0;
		long sum2 = 0;
		/** Getting the sum */
		for(int i = 0; i < count; i++)
		{
			sum1 += array1[i];
			sum2 += array2[i];
		}
		number = Math.min(sum1, sum2);
	}
	
	/** comments */
	public void method2 ()
	{		
		System.out.println("\n\n\n");
		System.out.println("Final sum: " + number);
		System.out.println("\n\n\n");
	}
 }
