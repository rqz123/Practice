import java.util.ArrayList;
import java.util.List;

//Practice Test #2 Problem #2c
public class SchoolTransport 
{
	private List<SchoolBus> buses;
	
	public boolean enroll(Student student)
	{
		int min = Integer.MAX_VALUE;
		SchoolBus referenceBus = null;
		
		for(SchoolBus currentBus: buses)
		{
			if(currentBus.isFull() == false)
			{
				for(int j = 0; j < currentBus.size(); j++) //Instead of using index, create a SchoolBus reference
				{
					int distance = currentBus.get(j).distance(student);
					if(distance < min)
					{
						min = distance;
						referenceBus = currentBus;
					}
				}
			}
		}
		
		if(referenceBus != null)
		{
			referenceBus.add(student); 
			return true;
		}
		return false;
		
	}
}
