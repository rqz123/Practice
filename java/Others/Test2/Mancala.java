import java.util.ArrayList;
import java.util.List;

//Practice Test #2 Problem #1
public class Mancala 
{
	private static final int BOARD_SIZE = 14;
	
	private static final int store1 = BOARD_SIZE/2;
	private static final int store2 = 0;
	
	private int [] board;
	
	//1a
	public Mancala(int n)
	{
		board = new int[BOARD_SIZE];
		for(int i = 0; i < BOARD_SIZE; i++)
			if(i != store1 && i != store2)
				board[i] = n;
	}
	
	//1b
	public boolean move(int k)
	{
		//First player houses: 1 to 6, store: 7
		//Second player houses: 8 to 13, store: 0
		
		int seeds = board[k];
		board[k] = 0;
		
		boolean player1 = false;
		boolean player2 = false;
		
		if(k >= 1 && k <= 6)
			player1 = true;
		else if(k >= 8 && k <= 13)
			player2 = true;
		
		
		int index = k-1;
		
		while(seeds > 0)
		{
			if(player2)
			{
				if(k != store1)
				{
					board[index]++;
					seeds--;
				}
			}
			else if(player1)
			{
				if(k != store2)
				{
					board[index]++;
					seeds--;
				}
			}
		}
		
		if(player1)
		{
			if(index == store1)
				move(index);
			return true;
		}
		else if(player2)
		{
			if(index == store2)
				move(index);
			return true;
		}
		return false;
		
	}
}
