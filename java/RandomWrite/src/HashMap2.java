import java.util.HashMap;

public class HashMap2
{
	// Driver method to test above method 
	public static void main(String[] args) 
	{ 
		int arr[] = {10, 34, 5, 10, 3, 5, 10}; 
		createHashMap(arr); 
	}
	
	public static void createHashMap(int arr[]) 
	{ 
		//Creates an empty HashMap 
		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>(); 
  
		// Traverse through the given array 
		for (int i = 0; i < arr.length; i++) 
		{ 
			// Get if the element is present 
			Integer c = hmap.get(arr[i]); 
  
			// If this is first occurrence of element 
			// Insert the element 
			if (hmap.get(arr[i]) == null) 
			{ 
				hmap.put(arr[i], 1); 
			} 
  
			// If elements already exists in hash map 
			// Increment the count of element by 1 
			else 
			{ 
				hmap.put(arr[i], ++c); 
			} 
		} 
  
		// Print HashMap 
		System.out.println("\n\n");
		System.out.println(hmap); 
		for (Integer key : hmap.keySet())
		{
			System.out.println("key: " + key + "    value: " + hmap.get(key));
		}
		System.out.println("\n\n");
	}
} 