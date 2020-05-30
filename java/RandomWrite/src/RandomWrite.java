import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;

public class RandomWrite
{
	private HashMap<String, ArrayList<String>> all;
	private int seedLength;
	private String fileName;
	private String theText;

	public RandomWrite(String fileName, int seedLength)
	{
		this.fileName = fileName;
		this.seedLength = seedLength;
		theText = new String("");
		all = new HashMap<String, ArrayList<String>>();
		
		makeTheText();
		setUpMap();
	}
  
	public static void main(String[] args) 
	{
		String fileName = "g.txt";
		if(args.length > 0)
		{
			fileName = args[0];
		}
		int length = 2;
		if (args.length > 1)
		{
			length = (int)(Integer.parseInt(args[1]));
		}
		int outputLength = 100;
		if (args.length > 2)
		{
			outputLength = (int)(Integer.parseInt(args[2]));
		}
		System.out.println("\n\n" + fileName + "  " + length + "  " + outputLength);
		RandomWrite write = new RandomWrite(fileName,length);
		write.printRandom(outputLength);
	}

	private void makeTheText ( ) 
	{
		//  Read all of the contents into the String theText.
		Scanner inputFile = OpenFile.openToRead(fileName);
		while(inputFile.hasNext())
		{
			theText += inputFile.nextLine();
		}
		
		//System.out.println("\n\n" + theText + "\n\n");
		System.out.println("");
	}
	
	public void setUpMap ( )
	{
		String str = theText + " " + theText.substring(0, seedLength);
		int textLen = str.length();
		
		for (int i = 0; i + seedLength < textLen; i ++)
		{
			String key = str.substring(i, i + seedLength);
			String value = str.substring(i + seedLength, i + seedLength + 1);
			
			//System.out.println(key + " " + value);
			ArrayList<String> follows = all.get(key);
			
			if (follows == null)
			{
				follows = new ArrayList<String>();
				all.put(key, follows);
			}
			follows.add(value);
		}
	}
	
	public void printRandom(int letterCount)
	{
		//  Create the random string of output.
		//System.out.println(all);
		
		int count = 0;
		Random rand = new Random();
		int randSeed = rand.nextInt(theText.length() - seedLength);
		String key = theText.substring(randSeed, randSeed + seedLength);
		
		while (letterCount > 0)
		{
			ArrayList<String> follows = all.get(key);
			//System.out.print(key + " ");

			if (follows == null)
			{
				System.out.println("cannot be found, exit the loop!");
				break;
			}
			
			//System.out.print(follows + " ");
			
			int followsLen = follows.size();
			int randFollower = rand.nextInt(followsLen);
			String follower = follows.get(randFollower);
			
			if (count > 80 && follower.charAt(0) == ' ')
			{
				count = 0;
				System.out.println();
			}
			else
			{
				count ++;
				System.out.print(follower);
			}
			
			if (seedLength > 1)
				key = key.substring(1) + follower;
			else
				key = follower;
			
			letterCount --;
		}
	}
}