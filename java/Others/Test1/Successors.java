//APCS A 2017 Problem #4
public class Successors 
{
	//4a
	public static Position findPosition(int num, int [][] intArr)
	{
		for(int i = 0; i < intArr.length; i++)
		{
			for(int j = 0; j < intArr[0].length; j++)
			{
				if(intArr[i][j] == num)
					return new Position(i, j);
			}
		}
		return null;
	}
	
	//4b
	public static Position[][] getSuccessorArray(int[][] intArr)
	{
		Position[][] array = new Position[intArr.length][intArr[0].length];
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 0; j < array[0].length; j++)
			{
				Position current = findPosition(intArr[i][j] + 1, intArr);
				array[i][j] = current;
			}
		}
		
		return array;
	}
}
