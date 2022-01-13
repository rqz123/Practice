import java.util.ArrayList;
import java.util.List;

//Practice Test #2 Problem #4
public class DistanceChart 
{
	private List<String> cityNames;
	
	private int[][] distances;
	
	public int findRemotestCity()
	{
		int index = 0;
		int sum = 0;
		
		for(int i = 0; i < distances.length; i++)
		{
			int currentSum = 0;
			for(int j = 0; j < distances[0].length; j++)
			{
				currentSum += distances[i][j];
			}
			
			if(currentSum > sum)
			{
				sum = currentSum;
				index = i;
			}
		}
		
		return index;
	}
	
	public int findNearestCity(int i, boolean [] visted)
	{
		return 0;
	}
	
	public List<String> makeItinerary()
	{
		List<String> cities = new ArrayList<String>();
		boolean [] visited = new boolean[5];
		
		int remoteIndex = findRemotestCity();
		visited[remoteIndex] = true;
	
		cities.add(cityNames.get(remoteIndex));
		
		for(int i = 0; i < cityNames.size()-1; i++)
		{
			remoteIndex = findNearestCity(remoteIndex, visited);
			visited[remoteIndex] = true;
			cities.add(cityNames.get(remoteIndex));
		}
		
		return cities;
	}
}
