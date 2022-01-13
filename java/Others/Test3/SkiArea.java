import java.util.ArrayList;
import java.util.List;

//Practice Test #3 Problem #4
public class SkiArea 
{
	private int[][] alts; // = {{7,9,6,9},{8,9,9,8},{6,5,5,6},{4,6,3,5},{5,5,4,3},{2,2,3,1},{2,1,0,1}};
	
	//4a
	public static int indexOfMax(int [] arr)
	{
		int max = 0;
		for(int i = 1; i < arr.length; i++)
		{
			if(arr[i] > arr[max])
				max = i;
		}
		
		return max;
	}
	
	//4b
	public int findSummit()
	{
		return indexOfMax(alts[0]);
	}
	
	//4c
	public ArrayList<Location> findSteepestTrail()
	{
		ArrayList<Location> path = new ArrayList<Location>();
		int summit = findSummit();
		
		path.add(new Location(0, summit));
		
		int min = 0;
		
		for(int i = 1; i < alts.length; i++)
		{
			int minValue = Integer.MAX_VALUE;
			
			if(summit - 1 >= 0)
			{
				if(alts[i][summit - 1] < minValue)
				{
					minValue = alts[i][summit - 1];
					min = summit - 1;
				}
			}
			if(summit + 1 < alts[i].length)
			{
				if(alts[i][summit + 1] < minValue)
				{
					minValue = alts[i][summit + 1];
					min = summit + 1;
				}
			}
			if(alts[i][summit] < minValue)
				min = summit;
			
			path.add(new Location(i, min));
			summit = min;
		}
		
		return path;
	}
	
	/*
	public static void main(String [] args)
	{
		SkiArea run = new SkiArea();
		ArrayList<Location> path = run.findSteepestTrail();
		
		for(int i = 0; i < path.size(); i++)
			System.out.println(path.get(i));
		
	}
	*/
}
