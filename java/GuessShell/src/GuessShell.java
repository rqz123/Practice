
public class GuessShell {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int NumSwaps = 3;
		int SwapList[][] = new int[NumSwaps][3];
		
		SwapList[0][0] = 1;
		SwapList[0][1] = 2;
		SwapList[0][2] = 1;

		SwapList[1][0] = 3;
		SwapList[1][1] = 2;
		SwapList[1][2] = 1;
		
		SwapList[2][0] = 1;
		SwapList[2][1] = 3;
		SwapList[2][2] = 1;
		
		int MaxScore = 0;
		
		for (int stone = 1; stone < 4; stone ++)
		{
			int score = 0;
			int moved_stone = stone;
			
			System.out.println("stone pebble pos: " + stone);
			
			for (int swap = 0; swap < NumSwaps; swap ++)
			{
				System.out.println("stone begin pos: " + moved_stone);
				
				if (SwapList[swap][0] == moved_stone)
				{
					moved_stone = SwapList[swap][1];
				}
				else if (SwapList[swap][1] == moved_stone)
				{
					moved_stone = SwapList[swap][0];
				}
				
				System.out.println("stone end pos: " + moved_stone);
	
				if (moved_stone == SwapList[swap][2])
				{
					score ++;
					
					System.out.println("score: " + score);
				}
			}
			System.out.println("\n");
			
			if (score > MaxScore)
			{
				MaxScore = score;
			}
		}
		
		System.out.println("MaxSore =" + MaxScore);
	}
}
