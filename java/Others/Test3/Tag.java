import java.util.ArrayList;
import java.util.List;

//Practice Test #3 Problem #2a
public class Tag implements Embedded
{
	private int startIndex;
	private int endIndex;
	private String command;
	
	public Tag(int sI, int eI, String cmd)
	{
		startIndex = sI;
		endIndex = eI;
		command = cmd;
	}

	public int getStartIndex() 
	{
		return startIndex;
	}

	public int getEndIndex() 
	{
		return endIndex;
	}

	public String getCommand() 
	{
		return command;
	}
	
	
}
