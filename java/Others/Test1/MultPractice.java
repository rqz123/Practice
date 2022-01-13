//APCS A 2017 Problem #2
public class MultPractice implements StudyPractice
{
	private int first;
	private int second;
	
	// error : where are first & second coming?
	
	public String getProblem() 
	{
		return first + " TIMES " + second;
	}
	
	public void nextProblem() 
	{
		second++;
	} 
}
