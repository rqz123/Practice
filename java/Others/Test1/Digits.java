//APCS A 2017 Problem #1

import java.util.ArrayList;
public class Digits 
{
	private ArrayList<Integer> digitList;
	
	//1a
	public Digits(int num)
	{
		// error for digiList
		
		if(num == 0)
			digitList.add(0);
		else
		{
			while(num != 0)
			{
				digitList.add(0, num%10);	// error
				num /= 10;
			}
		}
	}
	
	//1b
	public boolean isStrictlyIncreasing()
	{
		for(int i = 0; i < digitList.size() - 1; i++) 
		{
			if(digitList.get(i + 1) <= digitList.get(0))	// error
				return false;
		}
		return true;
	}
}
