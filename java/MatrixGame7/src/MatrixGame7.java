/*
Joseph Zhang
MatrixGame.java
1/24/19
 */
import java.util.Scanner;

public class MatrixGame7
{
	public static void main(String [] args)
	{
		MatrixGame7 run = new MatrixGame7();
		run.repeatGame();
	}

	public void repeatGame()
	{
		System.out.println("\n\n");

		while(startMatrix());
		
		System.out.println("\nThank You for playing!");

		System.out.println("\n\n");
	}

	public boolean startMatrix()
	{
		Scanner in = new Scanner(System.in);
		
		int [][] matrix = new int [7][7];
		int total = 0;

		System.out.println("Here is your 7x7 matrix: ");
		
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				matrix[i][j] = (int)(Math.random()*10);
				System.out.print(" " + matrix[i][j]);
				
				total += matrix[i][j];
			}
			System.out.println();
		}

		System.out.println("The total sum of all rows/columns is " + 2*total);

		System.out.print("What pivot cell would you like to change? "); 
		
		int columnNum = in.nextInt();
		int rowNum = in.nextInt();
		
		int columnIndex = columnNum - 1;	// convert to index
		int rowIndex = rowNum - 1;	// convert to index
		
		if (columnIndex < 0 || columnIndex > 6 ||
			rowIndex < 0 || rowIndex > 6)
		{
			return false;
		}

		int randomNum = (int)(Math.random()*10);

		matrix[rowIndex][columnIndex] = randomNum;

		//right up
		for(int i = 0; i < 7; i++)
		{
			rowIndex --;
			columnIndex ++;
			
			if (columnIndex <= 6 && rowIndex <= 6 && columnIndex >= 0 && rowIndex >= 0)
			{
				matrix[rowIndex][columnIndex] = randomNum;
				continue;
			}
		}

		//reset
		columnIndex = columnNum - 1;
		rowIndex = rowNum - 1;

		//right down
		for(int i = 0; i < 7; i++)
		{
			rowIndex ++;
			columnIndex ++;
			
			if(columnIndex <= 6 && rowIndex <= 6 && columnIndex >= 0 && rowIndex >= 0)
			{
				matrix[rowIndex][columnIndex] = randomNum;
				continue;
			}
		}

		//reset
		columnIndex = columnNum - 1;
		rowIndex = rowNum - 1;

		//left up 
		for(int i = 0; i < 7; i++)
		{
			rowIndex--;
			columnIndex--;
			
			if (columnIndex <= 6 && rowIndex <= 6 && columnIndex >= 0 && rowIndex >= 0)
			{
				matrix[rowIndex][columnIndex] = randomNum;
				continue;
			}
		}

		//reset
		columnIndex = columnNum - 1;
		rowIndex = rowNum - 1;

		//left down 
		for(int i = 0; i < 7; i ++)
		{
			rowIndex++;
			columnIndex--;
			
			if(columnIndex <= 6 && rowIndex <= 6 && columnIndex >= 0 && rowIndex >= 0)
			{
				matrix[rowIndex][columnIndex] = randomNum;
				continue;
			}
		}

		System.out.println("Here is your 7x7 matrix: ");
		
		total = 0;
		
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				System.out.print(" " + matrix[i][j]);
				total += matrix[i][j];
			}
			System.out.println();
		}

		System.out.println("The total sum of all rows/columns is " + 2*total);
		
		System.out.print("Would you like to play again(yes-1, no-2)? ");
		
		if (in.nextInt() == 1)
			return true;

		return false;
	}
}
