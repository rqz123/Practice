import java.util.ArrayList;
import java.util.List;

//Practice Test #4 Problem #4
public class FloorPlan 
{
	private final int ROWS = 8;
	private final int COLS = 8;
	
	private int [][] room;
	
	public FloorPlan(int rows, int cols)
	{
		room = new int[rows][cols];
	}
	
	private boolean isEmptyRegion(int top, int bottom, int left, int right)
	{
		for(int i = top; i <= bottom; i++)
		{
			for(int j = left; j <= right; j++)
			{
				if(room[i][j] != 0)
					return false;
			}
		}
		
		return true;
	}
	
	public boolean fits(int width, int height, Location ulCorner)
	{
		int top = ulCorner.getRow();
		int bottom = ulCorner.getRow() + height - 1;
		int left = ulCorner.getCol();
		int right = ulCorner.getCol() + width - 1;
		
		if(isEmptyRegion(top, bottom, left, right) == false)
			return false;
		
		if(top - 1 >= 0)
			top -= 1;
		
		if(bottom + 1 <= ROWS)
			bottom += 1;
		
		if(left - 1 >= 0)
			left -= 1;
		
		if(right + 1 >= COLS)
			right += 1;
		
		if(isEmptyRegion(top, bottom, left, right) == false)
			return false;
		
		return true;
	}
	
	public ArrayList<Location> whereFits(int width, int height)
	{
		ArrayList<Location> fit = new ArrayList<Location>();
		
		for(int i = 0; i < ROWS - height + 1; i++)
		{
			for(int j = 0; j < COLS - width + 1; j++)
			{
				int top = i;
				int bottom = i + height - 1;
				int left = j;
				int right = j + width - 1;
				
				if(isEmptyRegion(top, bottom, left, right))
					fit.add(new Location(i, j));
			}
		}
		
		return fit;
	}
	
}
