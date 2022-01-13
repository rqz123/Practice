
import java.util.HashMap;

public class HashMap3
{
	public static void main(String[] args)
	{
		// Create a HashMap object called capitalCities
		HashMap<String, String> capitalCities = new HashMap<String, String>();
		
		// Add keys and values (State, City)
		capitalCities.put("Arkansas", "Little Rock");
		capitalCities.put("Kansas", "Topeka");
		capitalCities.put("California", "Sacramento");
		capitalCities.put("Montana", "Helena");
		System.out.println("\n\n" + capitalCities.size());
		System.out.println(capitalCities); 
		for(String key : capitalCities.keySet())
		{
			System.out.println(key);
		}
		for(String value : capitalCities.values())
		{
			System.out.println(value);
		}
		for (String key : capitalCities.keySet())
		{
			System.out.println("key: " + key + "    value: " + capitalCities.get(key));
		}
	}
}