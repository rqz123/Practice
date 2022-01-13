import java.util.HashMap;

public class HashMap4
{
	public static void main(String args[]) 
	{	
		/* This is how to declare HashMap */
		HashMap<Integer, String> hmap = new HashMap<Integer, String>();

		/*Adding elements to HashMap*/
		hmap.put(12, "Chaitanya");
		hmap.put(2, "Rahul");
		hmap.put(7, "Singh");
		hmap.put(49, "Ajeet");
		hmap.put(3, "Anuj");
		
		System.out.println("\n\n");
		System.out.println(hmap.size());
		System.out.println(hmap);
		for (Integer key : hmap.keySet())
		{
			System.out.println("key: " + key + "    value: " + hmap.get(key));
		}

		/* Get values based on key*/
		String var = hmap.get(2);
		System.out.println("Value at key of 2 is: " + var);
		
		/* Remove values based on key*/
		hmap.remove(3);
		System.out.println("\nMap key and values after removal:");
		for (Integer key : hmap.keySet())
		{
			System.out.println("key: " + key + "    value: " + hmap.get(key));
		}
		System.out.println("\n\n");
	}
}