//Practice Test #3 Problem #2b
public class HTMLProcessor 
{
	public static Tag findFirstTag(String text)
	{
		int openBracket = text.indexOf("<"); 
		int closeBracket = text.indexOf(">"); 
		
		if(openBracket == -1 || closeBracket == -1)
			return null;
		
		String command = text.substring(openBracket + 1, closeBracket);
		
		return new Tag(openBracket, closeBracket, command);
	}
}
