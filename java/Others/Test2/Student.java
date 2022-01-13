import java.util.ArrayList;
import java.util.List;

//Practice Test #2 Problem #2a
public class Student implements Person
{
	private String name;
	private String street;
	private int number;
	
	public Student(String n, String s, int num)
	{
		name = n;
		street = s;
		number = num;
	}

	public String getName() 
	{
		return null;
	}

	public String getStreet() 
	{
		return null;
	}

	public int getNumber() 
	{
		return 0;
	}
	
	//2a
	public int distance(Student other)
	{
		if(this.getStreet().equals(other.getStreet()))
			return Math.abs(this.getNumber() - other.getNumber());
		return 99999;
				
	}

}
