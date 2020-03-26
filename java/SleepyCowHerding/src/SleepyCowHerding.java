import java.util.*;
import java.io.*;
import java.math.*;

public class SleepyCowHerding 
{
	static int min_step(long dist)
	{
		int step = -1;
		
		if (dist > 2)
			step = 2;
		else if (dist == 2)
			step = 1;
		else
			step = 0;
		
		return step;
	}
	public static void main(String [] args) throws IOException
	{
		long list[] = {1, 3, 8};
		
		long dis_left = Math.abs(list[1] - list[0]);
		long dis_right  = Math.abs(list[1] - list[2]);
		
		int step_left = min_step(dis_left);
		int step_right = min_step(dis_right);
		
		if (step_left == 0)
		{
			System.out.println(step_right + "\n" + step_right);
		}
		else if (step_right == 0)
		{
			System.out.println(step_left + "\n" + step_left);
		}
		else if (step_left < step_right)
		{
			System.out.println(step_left + "\n" + step_right);
		}
		else if (step_left > step_right)
		{
			System.out.println(step_right + "\n" + step_left);
		}
		else
		{
			System.out.println(step_left + "\n" + step_right);
		}
	}
}
