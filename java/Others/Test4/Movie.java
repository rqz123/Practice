import java.util.ArrayList;
import java.util.List;

//Practice Test #2 Problem #3
public class Movie 
{
	private String features;
	
	//3a
	public double likenessScore(Movie other)
	{
		String feature1 = features;
		String feature2 = other.features;
		int n = feature1.length();
		int count = 0;
		
		for(int i = 0; i < n - 2; i++)
			if(feature1.substring(i, i + 3).equals(feature2.substring(i, i + 3)))
				count++;
		
		return count*1.0/n/3;  
	}
	
	public static double[] getFitCoefficients(List<Movie> movies)
	{
		return null;
	}
	
	//3b
	public void removeOutliers(List<Movie> movies)
	{
		double [] coefficients = getFitCoefficients(movies);
		
		double sum = 0.0;
		int n = coefficients.length;
		for(double c : coefficients)
			sum += c;
		
		double threshold = (sum/n)/2;
		
		for(int k = n - 1; k >= 0; k--)
			if(coefficients[k] < threshold)
				movies.remove(k);
	}
}
