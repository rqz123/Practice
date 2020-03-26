/**
 * TwoPounds.java
 * 
 * @author Joseph Zhang
 * @version 1.0
 * @since 10/3/19
 */
 
public class TwoPounds
{
	public TwoPounds () 
	{
	
	}
	
	public static void main(String [] args)
	{
		TwoPounds trial = new TwoPounds();
		//long begin = System.currentTimeMillis();
		trial.run();
		//long end = System.currentTimeMillis();
		//System.out.println("The program took " + (end-begin)/1000 + " seconds to run");
	}
	
	public void run()
	{
		int count = 1;
		
		for(int a = 0; a <= 200; a++)
		{
			for(int b = 0; b <= 200; b+=2)
			{
				for(int c = 0; c <= 200; c+=5)
				{
					for(int d = 0; d <= 200; d+=10)
					{	
						for(int e = 0; e <= 200; e+=20)
						{	
							for(int f = 0; f <= 200; f+=50)
							{
								for(int g = 0; g <= 200; g+=100)
								{		
									if(a + b + c + d + e + f + g == 200)
									{
										//System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g);
										count++;
									}
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println("\n\n\n");
		System.out.println(count);
		System.out.println("\n\n\n");
	}
}
