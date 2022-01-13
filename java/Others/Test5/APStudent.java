import java.util.ArrayList;
import java.util.List;

//Practice Test #4 Problem #1
public class APStudent 
{
	private List<Exam> exams;
	
	//1a
	public int getAwardLevel()
	{
		int awardLevel = 0;
		
		int totalScore = 0;
		int goodExam = 0;
		int numExam = 0;
		
		for(Exam currentExam: exams)
		{
			int score = currentExam.getGrade();
			totalScore += score;
			numExam++;
			if(score >= 3)
				goodExam++;
		}
		
		//RZ: average value be better to calcuate first
		if(goodExam >= 3)
			awardLevel++;
		
		if(totalScore/numExam >= 3.25 && goodExam >= 4)
			awardLevel++;
		
		if(totalScore/numExam >= 3.25 && goodExam >= 5)
			awardLevel++;
		
		return awardLevel;
	}
}

class APStats extends APStudent
{
	//1b
	public static double [] getStats(List<APStudent> students)
	{
		int [] numGrades = new int[4];
		double [] percentage = new double[4];
		
		int total = 0;
		
		for(APStudent grade: students)
		{
			int award = grade.getAwardLevel();

			//RZ: why don't you use numGrade[award]?
			if(award == 0)
				numGrades[0]++;
			else if(award == 1)
				numGrades[1]++;
			else if(award == 2)
				numGrades[2]++;
			else if(award == 3)
				numGrades[3]++;
			total++;
		}
		
		for(int i = 0; i < 4; i++)
		{
			percentage[i] = (double)numGrades[i]*100/total;
		}
		
		return percentage;
	}
}