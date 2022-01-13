//Practice Test #4 Problem #3a
public class StudentBot extends ChatBot
{
	public StudentBot(String nm) 
	{
		super(nm);
	}

	public String respondToGreeting(String greeting) 
	{
		if(greeting.indexOf("morning") != -1)
			return "Good morning";
		else if(greeting.indexOf("afternoon") != -1)
			return "Good afternoon";
		
		return "Hello";
	}
	
	
	
}
