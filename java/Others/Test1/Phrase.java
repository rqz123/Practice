//APCS A 2017 Problem #3
public class Phrase 
{
	private String currentPhrase;
	
	public Phrase(String p)
	{
		currentPhrase = p;
	}
	
	public int findNthOccurrence(String str, int n)
	{
		//Implementation not shown
		return 0;
	}
	
	//3a
	public void replaceNthOccurrence(String str, int n, String repl)
	{
		int index = findNthOccurrence(str, n);
		if(index != -1)
		{
			currentPhrase = currentPhrase.substring(0, index) + repl + currentPhrase.substring(index + str.length());
		}
	}
	
	//3b
	public int findLastOccurrence(String str)
	{
		return currentPhrase.lastIndexOf(str);
	}
	
	public String toString()
	{
		return currentPhrase;
	}
	
	public static void main(String [] args)
	{
		
		Phrase run = new Phrase("A cat ate late.");
		System.out.println(run.findLastOccurrence("at"));
		
	}
}
