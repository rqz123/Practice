/**
 * InCircles.java
 * 
 * @author Joseph Zhang
 * @version 1.0
 * @since 10/3/19
 */
 
import java.util.Arrays;
public class InCircles 
{
	// Driver code 
	public static void main(String[] args) 
	{ 
		InCircles trial = new InCircles();
		//long begin = System.currentTimeMillis();
		trial.run();
		//long end = System.currentTimeMillis();
		//System.out.println("The program took " + (end-begin)/1000 + " seconds to run");
	} 
	
	public void run()
	{
		int count = 0;
		for(int i = 1; i < 100000; i++)
		{
			if(isPrime(i) && checkCircularNum(i))
			{
				count++;
				System.out.print(i + " ");
			}
		}
		
		System.out.println("\n\n\n");
		System.out.println(count);
		System.out.println("\n\n\n");
	}
	
	public boolean checkCircularNum(int number)
	{
		int count = countDigits(number);
//		System.out.println(number + " " + count);
		for(int i = 0; i < count + 1; i++)
		{
//			System.out.println(number);
			if(isPrime(number) == false)
				return false;
			number = rotateNum(number);
		}
		return true;
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
	
	public int rotateNum(int number) 
    { 
        int temp = number % 10;  
        
        temp *= Math.pow(10, countDigits(number));  
        number /= 10;  
        number += temp; 
        
        return number; 
    } 
	
	public int countDigits(int number) 
	{ 
        int digit = 0; 
        
        while ((number /= 10) > 0) 
            digit++; 
        
        return digit; 
	} 
  
}
