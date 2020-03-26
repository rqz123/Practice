
public class Sleep {

	public static void main(String[] args) {
		int CowList[] = {1, 2, 4, 3};
		//int CowList[] = {1, 5, 9, 3, 22, 2, 8, 10, 12, 7, 3, 4, 8};
		int NumCows = CowList.length;
		
		int Steps = 0;
		
		print(CowList, NumCows);
		
		while (!sorted(CowList, NumCows))
		{
			int pos = smaller(CowList, NumCows - 1);
			
			if (pos == 0)
			{
				shift(CowList, 0, NumCows - 1);
			}
			else 
			{
				shift(CowList, 0, pos);
			}
		
			Steps ++;
			print(CowList, NumCows);
		}
		
		System.out.println("Total steps is " + Steps);
	}

	public static int smaller(int list[], int pos)
	{
		if (pos == 0)
			return 0;
		
		if (list[0] < list[pos])
			return smaller(list, pos - 1);
		
		return pos + 1;
	}
	
	public static void shift(int list[], int pos1, int pos2)
	{
		int temp = list[pos1];
		
		for (int i = pos1; i < pos2 - 1; i ++)
		{
			list[i] = list[i + 1];
		}
		
		list[pos2 - 1] = temp;
	}
	
	public static void print(int list[], int len)
	{
		for (int i = 0; i < len; i ++)
		{
			System.out.print(list[i] + " ");
		}
		System.out.println("");		
	}
	
	public static boolean sorted(int list[], int len)
	{
		int start = list[0];
		
		for (int i = 1; i < len; i ++)
		{
			if (list[i] < start)
			{
				return false;
			}
			
			start = list[i];
		}
		
		return true;
	}
}
