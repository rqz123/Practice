import java.util.ArrayList;
import java.util.List;

//Practice Test #3 Problem #1
public class ECGAnalysis 
{
	public static final int SAMPLING_RATE = 300;
	
	public static final int DELTA = SAMPLING_RATE / 10;
	
	//1a
	public static boolean isRpeak(double [] v, int r)
	{
		//v[r] > 1.0
		
		//v[r] is equal to max value
		double max = max(v, r - DELTA, r + DELTA);
		
		boolean cond31 = false;
		boolean cond32 = false;
		
		for(int k = r - DELTA; k <= r; k++)
		{
			if(v[k] < 0)
				cond31 = true;
		}
		
		for(int k = r; k <= r + DELTA; k++)
		{
			if(v[k] < 0)
				cond32 = true;
		}
		
		if(v[r] > 1.0 && v[r] == max && cond31 && cond32)
			return true;
		return false;
	}
	
	//1b
	public static ArrayList<Integer> allRpeaks(double [] v)
	{
		ArrayList<Integer> rPeaks = new ArrayList<Integer>();
		for(int i = 0; i < v.length; i++)
		{
			if(isRpeak(v, i))
			{
				rPeaks.add((Integer)i);
				i--;
				i+=DELTA;
			}
		}
		
		return rPeaks;
	}
	
	//1c
	public static int heartRate(List<Integer> rPeaks)
	{
		int first = rPeaks.get(0);	// RZ: .intValue()
		int last = rPeaks.get(rPeaks.size() - 1);
		double avgDistance = (double)(last - first)/(rPeaks.size() - 1);
		return (int)(60 * SAMPLING_RATE / avgDistance + 0.5);
		
	}
	
	private static double max(double [] v, int i, int j)
	{
		return 0.0;
	}
	
	private static double min(double [] v, int i, int j)
	{
		return 0.0;
	}
	
}
