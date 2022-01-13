//Extra class for Practice Test #3 Problem #4
public class Location 
{
	private int row;
	private int col;
	
	public Location(int r, int c)
	{
		row = r;
		col = c;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public String toString()
	{
		return "(" + row + "," + col + ")";
	}
}
