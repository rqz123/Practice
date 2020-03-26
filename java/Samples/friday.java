/*
ID: josephz9
LANG: JAVA
TASK: friday
 */
import java.io.*;
import java.util.*;

/*
ID: josephz9
LANG: JAVA
TASK: friday
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

class friday {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("friday.in"));
        PrintWriter pw = new PrintWriter(new File("friday.out"));
        int years = sc.nextInt();

        int freq[] = new int[7];
        Arrays.fill(freq, 0);

        int calendarDay = 0;
        int[] days = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int[] leapDays = new int[] { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        int end = 1900 + years;
        for (int year = 1900; year < end; year++) {
            for (int month = 0; month < 12; month++) {
                int day = (calendarDay + 12) % 7;
                freq[day]++;
                calendarDay += isLeap(year) ? leapDays[month] : days[month];
            }
        }

        pw.print(freq[5]);
        pw.print(' ');
        pw.print(freq[6]);
        pw.print(' ');
        pw.print(freq[0]);
        pw.print(' ');
        pw.print(freq[1]);
        pw.print(' ');
        pw.print(freq[2]);
        pw.print(' ');
        pw.print(freq[3]);
        pw.print(' ');
        pw.println(freq[4]);

        pw.close();
    }

    public static boolean isLeap(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        } else {
            return year % 4 == 0;
        }
    }
}

/*
public class friday 
{
	public static void main(String [] args) throws IOException
	{
		//InputReader in = new InputReader(new FileReader("friday.in"));
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
		
		//int years = in.nextInt();
		int years = 5;
				
		int [] count = new int[7];
		int days = 0; //starting day is jan 13, saturday 
		count[days] += 1;
		
		int month = 1;
		boolean leap = false;
		int currentYear = 1900;
		
		for(int i = 1; i < years*12; i++)
		{
			int addition = 0;
			
			if(currentYear % 400 == 0)
				leap = false;
			else if(currentYear % 4 == 0)
				leap = true;
			else
				leap = false;
		
			if(month == 2)
				if(leap)
					addition = 29;
				else
					addition = 28;
			
			else if(month == 4 || month == 6 || month == 9 || month == 11)
				addition = 30;
			else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
				addition = 31;
			
			//System.out.println(month + " " + addition);
			
			//System.out.println("(" + days + "+"+ "(" + addition + "%7" + ")" + "%7" + ")");
			days = (days+(addition%7))%7;
			count[days] += 1;
			currentYear++;
			
			if(month % 12 == 0)
				month = 1;
			else
				month++;
		}
		
		for(int i = 0; i < count.length; i++)
		{
			if(i + 1 != count.length)
				System.out.print(count[i] + " ");
			else
				System.out.print(count[i]);
		}
		System.out.println();
		
		System.out.close();
	}
	
	static class InputReader 
	{
	    public BufferedReader reader;
	    public StringTokenizer tokenizer;
	 
	    public InputReader(FileReader fileReader) {
	        reader = new BufferedReader(fileReader, 32768);
	        tokenizer = null;
	    }
	    public String nextLine() {
	        try {
	            String str = reader.readLine();
	            return str;
	        } catch(IOException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    public String next() {
	        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
	            try {
	                tokenizer = new StringTokenizer(reader.readLine());
	            } catch (IOException e) {
	                throw new RuntimeException(e);
	            }
	        }
	        return tokenizer.nextToken();
	    }
	 
	    public int nextInt() {
	        return Integer.parseInt(next());
	    }
	 
	    public long nextLong() {
	        return Long.parseLong(next());
	    }
	 
	    public char nextChar() {
	        return next().charAt(0);
	    }
	}
}
*/
