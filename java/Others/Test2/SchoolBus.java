import java.util.ArrayList;
import java.util.List;

//Practice Test #2 Problem #2b
public class SchoolBus extends ArrayList<Student>
{
	public SchoolBus()
	{
		//No idea. 
	}
	
	public boolean isFull()
	{
		if(this.indexOf(null) == -1)
			return true;
		return false;
	}
}
