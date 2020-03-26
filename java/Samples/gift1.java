/*
ID: josephz9
LANG: JAVA
TASK: gift1
 */
import java.io.*;
import java.util.*;

public class gift1 
{
	public static void main(String [] args) throws IOException
	{
		InputReader f = new InputReader(new FileReader("gift1.in"));
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		
		int people = f.nextInt();
		
		String [] name = new String [people];
		for(int i = 0; i < people; i++)
			name[i] = f.nextLine();
		
		int [] money = new int [people];
		
		for(int i = 0; i < people; i++)
		{
			String currentName = f.nextLine();
			int withdraw = f.nextInt();
			
			int length = f.nextInt();
			
			if(length == 0)
				continue;
			
			money[getArrayNameIndex(name, currentName)] -= withdraw;
			money[getArrayNameIndex(name, currentName)] += withdraw%length;
			
			for(int j = 0; j < length; j++)
			{
				String spiltName = f.nextLine();
				money[getArrayNameIndex(name, spiltName)] += withdraw/length;
			}
		}
		
		for(int i = 0; i < people; i++)
		{
			out.println(name[i] + " " + money[i]);
		}
	
		out.close();
	}
	
	static int getArrayNameIndex(String [] s, String name)
	{
		for(int i = 0; i < s.length; i++)
		{
			if(s[i].equals(name))
				return i;
		}
		return -1;
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
