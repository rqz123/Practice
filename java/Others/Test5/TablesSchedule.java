import java.util.ArrayList;
import java.util.List;

//Practice Test #4 Problem #2
public class TablesSchedule 
{
	List<String> schedules;
	
	public TablesSchedule(int numTables)
	{
		//something?
	}
	
	//2a
	public void reserve(int k, int timeSlot)
	{
		//RZ: what's the timeslot for?
		String table = schedules.get(k);
		schedules.set(k, table.substring(0, k) + "***" + table.substring(k));
	}
	
	//2b
	//RZ: get wrong question
	public int occupiedSlots(int k)
	{
		String table = schedules.get(k);
		int count = 0;
		
		while(table.indexOf("***") != -1)
		{
			count++;
			table = table.substring(table.indexOf("***") + 3);
		}
		
		return count;
	}
	
	//2c
	public int findTable(int timeSlot)
	{
		int maxCount = 0;
		int tableIndex = -1;
		
		for(int i = 0; i < schedules.size(); i++)
		{
			String table = schedules.get(i);
			if(table.substring(timeSlot, timeSlot+3).equals("..."))
			{
				int slotCount = occupiedSlots(i);
				if(slotCount > maxCount)
				{
					maxCount = slotCount;
					tableIndex = i;
				}
			}
		}
		
		return tableIndex + 1; 
	}
	
}
