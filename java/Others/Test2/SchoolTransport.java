import java.util.ArrayList;
import java.util.List;

//Practice Test #2 Problem #2c
public class SchoolTransport 
{
	private List<SchoolBus> buses;
	
	public boolean enroll(Student student)
	{
		int busIndex = -1;
		int studentIndex = -1;
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < buses.size(); i++)
		{
			SchoolBus currentBus = buses.get(i);
			if(currentBus.isFull() == false)
			{
				for(int j = 0; j < currentBus.size(); j++)
				{
					int distance = currentBus.get(j).distance(student);
					if(distance < min)
					{
						min = distance;
						busIndex = i;
						studentIndex = j;
					}
				}
			}
		}
		
		if(busIndex != -1)
		{
			buses.get(busIndex).set(studentIndex, student);
			return true;
		}
		return false;
		
	}
}
