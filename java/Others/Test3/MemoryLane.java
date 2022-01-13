import java.util.ArrayList;
import java.util.List;

//Practice Test #3 Problem #3
public class MemoryLane 
{
	private Card[] cards;
	
	private int numCards;
	
	//3a
	public boolean isValidArrangement()
	{
		for(int i = 0; i < numCards; i++)
		{
			int count = 0;
			for(int j = 0; j < numCards; j++)
			{
				if(cards[i].equals(cards[j]))
					count++;
			}
			if(count > 2)	// RZ: is this requirement?
				return false;
		}
		return true;
	}
	
	//3b
	public void removeCard(int k)
	{
		for(int i = k+1; i < numCards; i++)
			cards[i-1] = cards[i];
		
		numCards--;
	}
	
	//3c
	public boolean openTwoCards(int k1, int k2)
	{
		if(cards[k1].equals(cards[k2]))
		{
			removeCard(k2);
			removeCard(k1);
			return true;
		}
		return false;
	}
}
