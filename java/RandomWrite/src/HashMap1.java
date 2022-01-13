import java.util.HashMap;

public class HashMap1
{
	public static void main(String [] args)
	{
		String[] data = {"Nothing", "is", "as", "easy", "as", "it", "looks"};
		HashMap<String, Integer> hash = new HashMap<String, Integer>();

		for(int i = 0; i < data.length; i++)
		{
			String key = data[i];
			Integer freq = hash.get(key);
			if(freq == null) 
			{
				freq = 1;
			}
			else
			{
				freq++;
			}
			hash.put(key, freq);
		}
		System.out.println("\n\n" + hash);
		for (String key : hash.keySet())
		{
			System.out.println("key: " + key + "    value: " + hash.get(key));
		}
		System.out.println("\n\n");
	}
}