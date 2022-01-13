//Abstract class for Practice Test #4 Problem #3
public abstract class ChatBot 
{
	public static String readInput (String prompt)
	{
		return "";
	}
	
	private String name;
	
	public ChatBot(String nm)
	{
		name = nm;
	}
	
	public String getName()
	{
		return name;
	}
	
	public abstract String respondToGreeting(String greeting);
}
