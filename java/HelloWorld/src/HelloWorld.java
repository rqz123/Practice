import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


class WhyDidTheCowCrossTheRoad2 {
	public void run()
	{	
		String cross = "ABCCABDDEEFFGGHHIIJJKKLLMMNNOOPPQQRRSSTTUUVVWWXXYYZZ";
		int[] crossArray = new int[cross.length()];
		for(int i = 0; i < cross.length(); i++) {
			crossArray[i] = cross.charAt(i) - 'A';
		}
		
		int numPairs = 0;
		
		for(int i = 0; i < 26; i++) {
			int first = getFirstAppearance(crossArray, i);
			int last = getLastAppearance(crossArray, i);
			numPairs += getNumberOfSingles(crossArray, i, first, last);
		}
		
		System.out.println("Number of pairs: " + numPairs);
	}
	
	public int getFirstAppearance(int[] crossArray, int Value) {
		for(int i = 0; i < crossArray.length; i++) {
			if(crossArray[i] == Value) {
				return i;
			}
		}
		return -1;
	}
	
	public int getLastAppearance(int[] crossArray, int Value) {
		for(int i = crossArray.length-1; i >= 0; i--) {
			if(crossArray[i] == Value) {
				return i;
			}
		}
		return -1;
	}
	
	public int getNumberOfSingles(int[] crossArray, int greaterThan, int leftIndex, int rightIndex) {
		int numSingles = 0;
		int[] appear = new int[26];
		for(int i = leftIndex+1; i < rightIndex; i++) {
			appear[crossArray[i]]++;
		}
		for(int i = greaterThan + 1; i < appear.length; i++) {
			if(appear[i] == 1) {
				numSingles++;
			}
		}
		return numSingles;
	}
}

//USACO 2017 Dec. Prob3
class MilkMeasure {
	public void run()
	{
		int today = 4;
		int[] days = new int[100];
		
		for (int i = 0; i < 100; i ++)
			days[i] = 7;
		
		// read input
		days[7-1] += 3;
		days[4-1] -= 1;
		days[9-1] -= 1;
		days[1-1] += 2;
		
		// looking for current high
		int cur_high = 0;
		int i;
		for (i = 0; i < today; i ++)
		{
			if (days[i] > cur_high)
				cur_high = days[i];
		}
		
		// looking for future high
		for (i = today; i < 100; i ++)
		{
			if (days[i] > cur_high)
				break;
		}
		
		System.out.println(i+1-today);
	}
}

//USACO 2017 Dec Prob2 
class TheBovineShuffle {
	public void run()
	{
		int n = 5;
		int[] moveTo = {1, 3, 4, 5, 2};
		int[] finalLocs = {1234567, 2222222, 3333333, 4444444, 5555555};

		int[] originalLocations = new int[n+1];

		for(int finalPosition = 0; finalPosition < n; finalPosition++) {
			int currentLocation = finalPosition;

			//reverse three shuffles
			for(int iter = 1; iter <= 3; iter++) {
				currentLocation = moveTo[currentLocation];
			}

			//store the original location of the cow that ended up in finalPosition
			originalLocations[currentLocation] = finalLocs[finalPosition];
		}
	
		for(int i = 1; i <= n; i++)
		{
			System.out.print(originalLocations[i] + " ");
		}
	}
}

//USACO 2018 Jan Prob2
class LifeGuards {
	public void run()
	{
		//input number of cows
		int n = 3;

		//input lifeguard start and end time
		int start[] = {5, 1, 3};
		int end[] = {9, 4, 7};

		int max = 0;
		
		for (int i = 0; i < n; i ++)
		{
			for (int j = i + 1 ; j < n; j ++)
			{
				int t = 0;
				
				if ((start[j] > start[i] && start[j] < end[i]) ||
					(end[j] > start[i] && end[j] < end[i]))
				{
					int x = Math.min(start[i], start[j]);
					int y = Math.max(end[i], end[j]);
				
					t = y - x;
				}
				else 
				{
					int x = end[i] - start[i];
					int y = end[j] - start[j];
					
					t = x + y;
				}
				
				if (t > max)
				{
					max = t;
				}
			}
		}

		System.out.println("The maximum amount of time: " + max);
	}
}

class ThreeNames
{
	public String reverseString(String str)
	{
		String revStr = "";
		
		for(int i = str.length(); i > 0; i --)
			revStr += str.substring(i - 1, i);
		return revStr;
	} 
	
	public String addSpaceBetween(String str) {
		int space = str.indexOf(" ");
		String first = str.substring(0, space);
		
		space = str.lastIndexOf(" ");
		String last = str.substring(space + 1, str.length());
		
		int len = first.length() + last.length();
		for (int i = 0; i < len; i ++) {
			last = last + " ";
		}
		return last + first;
	}
	
	public String printScale(int len) {
		String scale = "";
		
		for (int i = 0; i <= len; i ++)
			scale = scale + (char) ('0' + (i + 1) % 10);
		return scale;
	}
	
	public void processName(String name) {
		name = addSpaceBetween(reverseString(name.trim()));
		System.out.print(name); 
		System.out.print("\n");
		System.out.print(printScale(name.length()));
		System.out.print("\n");
	}
	
	public String getName(String str) {
		int comma = str.indexOf(",");
		return str.substring(0, comma);
	}
	
	public void askNames()
	{
		String name1, name2, name3;
		Scanner in = new Scanner(System.in);

		System.out.printf("\n\n\n");
		System.out.printf("Input: ");
		String namelist = in.nextLine();
		System.out.printf("\n");
		
		name1 = getName(namelist);
		processName(name1);
		namelist = namelist.substring(name1.length()+1, namelist.length());
		
		name2 = getName(namelist);
		name3 = namelist.substring(name2.length()+1, namelist.length());

		processName(name1);
		processName(name2);
		processName(name3);
	}
}

class CowTip
{
	int tipArraySize;
	int[][] tipArray;
	
	public void getTipArray()
	{
		String curPath = System.getProperty("user.dir");
		
		FileReader inFile = null;
		try {
			inFile = new FileReader(curPath + "/bin/cowtip.in");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader bufRd = new BufferedReader(inFile);
		
		FileWriter outFile = null;
		try {
			outFile = new FileWriter(curPath + "/bin/cowtip.out");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bufWt = new BufferedWriter(outFile);
		
		String str;
		try {
			str = bufRd.readLine();
			tipArraySize = Integer.parseInt(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tipArray = new int[tipArraySize][tipArraySize];
		
		try {
			int j = 0;
			
			while ((str = bufRd.readLine()) != null) {
				System.out.println(str);				
				//bufWt.write(str + "\n");				
				StringTokenizer st = new StringTokenizer(str);
				
				for (int i = 0; i < tipArraySize; i ++)
				{ 
					tipArray[j][i] = (int)(str.charAt(i) - 48);
				}
				
				j ++;
			}
			//bufWt.close();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	
		int numTips = 0;
		
		for (int y = tipArraySize; y > 0; y --)
		{
			for (int x = tipArraySize; x > 0; x --)
			{
				if (flipRectangle(x, y))
				{
					printTipArray();
					numTips ++;
				}
			}
		}
		
		System.out.println(numTips);
	}
	
	public boolean flipRectangle(int x, int y)
	{
		if (x > tipArraySize || y > tipArraySize)
			return false;	// wrong rectangle
		
		if (tipArray[y-1][x-1] == 0)
			return false;	// do nothing
		
		for (int i = 0; i < y; i ++) 
		{
			for (int j = 0; j < x; j ++)
			{
				if (tipArray[i][j] >= 1)
					tipArray[i][j] = 0;
				else
					tipArray[i][j] = 1;
			}
		}
		
		return true;
	}
	
	public void printTipArray()
	{
		for (int i = 0; i < tipArraySize; i ++)
		{
			for (int j = 0; j < tipArraySize; j ++)
			{
				System.out.print(tipArray[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}

class CowMilks
{
	int numCows;
	
	String[] cowName;
	int[] cowMilk;
	int[] cowCount;
	
	CowMilks() {
		numCows = 0;
		cowName = new String [7];
		cowMilk = new int[7];
		cowCount = new int[7];
		
		for (int i = 0; i < 7; i++) {
			cowName[i] = "";
			cowMilk[i] = 0;
			cowCount[i] = 0;
		}
	}
	
	public void addCowInfo(String name, int milk) {
		for (int i = 0; i < numCows; i ++) {
			if (cowName[i].equals(name)) {
				cowMilk[i] += milk;
				cowCount[i] += 1;
				return;
			}
		}
		
		cowName[numCows] = name;
		cowMilk[numCows] = milk;
		cowCount[numCows] += 1;
		
		numCows ++;
	}
	
	public int secondSmallest() {
		float smallest = (float) 10000.0;
		float second = (float) (smallest - 0.1);
		
		int index;
		
		for (index = 0; index < numCows; index ++) {
			float value = (float)cowMilk[index];	// / cowCount[index];
			if (value < smallest) 
				smallest = value;
		}
		
		for (index = 0; index < numCows; index ++) {
			float value = (float)cowMilk[index];	// / cowCount[index];
			if (value > smallest && value < second) 
				second = value;
		}

		for (index = 0; index < numCows; index ++) {
			float value = (float)cowMilk[index];	// / cowCount[index];
			if (value == second) 
				break;
		}

		return index;
	}
	
	public void getCowMilks()
	{
		String curPath = System.getProperty("user.dir");
		
		FileReader inFile = null;
		try {
			inFile = new FileReader(curPath + "/bin/notlast.in");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader bufRd = new BufferedReader(inFile);
		
		FileWriter outFile = null;
		try {
			outFile = new FileWriter(curPath + "/bin/notlast.out");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bufWt = new BufferedWriter(outFile);
		
		String str;
		try {
			str = bufRd.readLine();
			Integer.parseInt(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while ((str = bufRd.readLine()) != null) {
				System.out.println(str);				
				//bufWt.write(str + "\n");				
				StringTokenizer st = new StringTokenizer(str);
				
				String name = st.nextToken();
				int milk = Integer.parseInt(st.nextToken());
				
				addCowInfo(name, milk);
			}
			//bufWt.close();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		str = cowName[secondSmallest()];
		System.out.println(str);
		try {
			bufWt.write(str + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bufWt.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class NameInBits7
{
	public void getString()
	{
		Scanner in = new Scanner(System.in);

		System.out.println("\n\n");

		System.out.println("Please enter a String(any length) and an integer: ");

		String word = in.next();

		int times = in.nextInt();


		System.out.println("\nOutput:");


		int runningTime = 0;

		while (runningTime < times)
		{
			int indexStart = getRandomInt(word.length());
			int indexEnd = getRandomInt(word.length());

			if (indexStart < indexEnd)
			{
				System.out.print(reverseString(word.substring(indexStart, indexEnd), 0));

				System.out.println("\nrunningTimes / times: " + runningTime + "/" + times);
				System.out.println("index start, end: " + indexStart + " ~ " + indexEnd);

				if (runningTime != times - 1)
				{
					System.out.print(", ");
				}

				runningTime ++;
			}
		}
	}

	public int getRandomInt(int length)
	{
		return (int)(Math.random()*length);
	}

	public String reverseString(String n, int i)
	{
		if (n == null)
			return "";

		i = n.length();

		if (n.length() == 0)
			return "";

		else
			return n.substring(i - 1, i) + reverseString(n.substring(0, i - 1), i);
	}
}

class MathRecursion2
{
	public double c_avgDigitsHard(int n) {
		int len = (""+n).length();
		if (len == 1)
			return n;
		
		int last = n % 10;
		int rest = n / 10;
		
		double ret = c_avgDigitsHard(rest);
		return (last + (len - 1) * ret) / len;
	}
}

class MathRecursion
{
	private String output;
	
	public void askQuestion()
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("\n\n\n");
		System.out.print("Would you like to run Method 1(sum) or 2(String)? ");
		
		int answer = in.nextInt();
		in.nextLine();
		
		if (answer == 1) {
			System.out.print("\nEnter number: ");
			
			int num = in.nextInt();
			int even = returnEven(num);
			
			System.out.println("\nThe sum of the even numbers less than " + num + " is " + even);
		}
		else if (answer == 2) {
			System.out.print("\n\nEnter a String: ");
			
			String input = in.nextLine();
			output = "";
			
			returnNonVowel(input);
			System.out.println("\nYour new String is " + output);
		}	
		else {
			System.out.println("\nWrong input selection.");
		}
	}
	
	public int returnEven(int n)
	{
		if (n == 0)
			return 0;

		if (n % 2 == 0)
			return n - 2 + returnEven(n - 2);
		
		return (n - 1) + returnEven(n - 1);
	}
	
	public void returnNonVowel(String word)
	{
		if (0 == word.length())
			return;
		
		if (checkVowel(word.charAt(0)))
		{
			output += word.charAt(0);
		}
		
		returnNonVowel(word.substring(1, word.length()));
	}
	
	public boolean checkVowel(char part)
	{
		if (part == 97 || part == 101 || part == 105 || part == 111 || part == 117 || 
			part == 65 || part == 69 || part == 73 || part == 79 || part == 85)
			return false;
			
		return true;
	}
}

class PrimeNumber
{
	private ArrayList<Integer> prime_number = new ArrayList<Integer>();
	private int loop_counter;
	
	PrimeNumber() {
		clearMember();
	}
	
	public void getInfo()
	{
		Scanner in = new Scanner(System.in);
		int counter = 0;
		boolean condition = false;
		
		System.out.println("\n\n");
		System.out.print("What is the max number? ");
		int answer = in.nextInt();
		
		//Non-efficient way
		/*
		for(int i = 2; i <= answer; i++)
		{
			temp = false;
			for(int j = 2; j < i; j++)
			{
				if(i%j == 0)
					temp = true;
				counter++;
			}
			if(temp == false)
				System.out.print("\t" + i);
		} 
		*/
		
		for(int i = 2; i <= answer; i++)
		{
			condition = false;
			for(int j = 2; j < i/2; j++)
			{
				if(i%j == 0)
					condition = true;
				counter++;
			}
			if(i == 4)
			{
				condition = true;
			}
			else if(condition == false)
			{
				System.out.print("\t" + i);
				/*
				if(counter%10 == 0)
				{
					System.out.println();
				}
				*/
			} 
		}
		System.out.println("\n\n" + counter);
	}
	
	public void clearMember() {
		prime_number.clear();
		loop_counter = 0;		
	}
	
	public void runProgram() {

		Scanner in = new Scanner(System.in);
		
		System.out.println("\n\n");
		System.out.print("What is the max number? ");
		
		int max = in.nextInt();
		
		// Method 1: brute force
		findPrimeNumber1(max);
		printPrimeNumber();
		
		// Method 2:
		// (1) When a number is not a prime, this number can be factored into two factors namely a and b i.e. 
		// number = a * b. If both a and b were greater than the square root of n, a*b would be greater than n.
		// So at least one of those factors must be less than or equal the square root of a number and to check 
		// if a number is prime, we only need to test for factors lower than or equal to the square root of the 
		// number being checked.
		// (2) Prime numbers can never be an even number as even numbers are all divisible by 2.
		clearMember();		
		findPrimeNumber2(max);
		printPrimeNumber();
		
		// Method 3: using recursion + method 2
		clearMember();
		prime_number.add(2);
		findPrimeNumber2(3, max);
		printPrimeNumber();

		getInfo();
	}
	
	public void findPrimeNumber1(int max) {
		if (max <= 1)
			return;	// 1 is not prime number
		
	    for (int i = 2; i <= max; i ++) {
	    	if (isPrimeNumber1(i)) {
	    		prime_number.add(i);
	        }
	    }
	}
	
	public boolean isPrimeNumber1(int number) {
		for (int i = 2; i < number; i ++) {
			loop_counter ++;
			
	        if (number % i == 0) {
	            return false;
	        }
	    }
	    return true;		
	}
	
	public void findPrimeNumber2(int max) {
		if (max <= 1)
			return;		// 1 is not prime number

		if (max >= 2)
			prime_number.add(2);
		
	    for (int i = 3; i <= max; i += 2) {
	    	if (isPrimeNumber2(i)) {
	    		prime_number.add(i);
	        }
	    }
	}
	
	public void findPrimeNumber2(int number, int max) {
		if (number > max)
			return;
		
		if (isPrimeNumber2(number))
    		prime_number.add(number);
			
		findPrimeNumber2(number + 2, max);
	}
	
	public boolean isPrimeNumber2(int number) {
		for (int i = 2; i * i <= number; i ++) {
			loop_counter ++;
			
	        if (number % i == 0) {
	            return false;
	        }
	    }
	    return true;		
	}
	
	public void printPrimeNumber() {
		System.out.print("\t");	// leave space for 1
		
		for (int i = 0, p = 2; i < prime_number.size(); i ++ ) {
			System.out.print(prime_number.get(i) + "\t");
			
			if (p ++ % 10 == 0)
				System.out.print("\n");
		}
		
		System.out.println("\n\nTotal loops: " + loop_counter);
	}
}

class SeparateNumbers
{
	private ArrayList<Integer> number_list = new ArrayList<Integer>();
	private ArrayList<String> string_list = new ArrayList<String>();
	
	public SeparateNumbers() {
		number_list.clear();
		string_list.clear();
	}
	
	public void parseInput() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("\n\n");
		System.out.println("What is your input?");
		
		String answer = in.nextLine();		
		
		boolean isNum = false;	
		String temp = "";
		boolean start = false;
		
		for (int i = 0; i < answer.length(); i ++) {
			if (answer.charAt(i) >= '0' && answer.charAt(i) <= '9') {
				if (start) {
					if (!isNum) {
						string_list.add(temp);
						temp = "";	// clear temp buffer
					}
				}
				else
					start = true;
				
				isNum = true;
			}
			else { 
				if (start) {
					if (isNum) {
						number_list.add(Integer.parseInt(temp));
						temp = "";	// clear temp buffer
					}
				}
				else
					start = true;
				
				isNum = false;
			}
			
			temp += answer.charAt(i);
		}
	}
	
	public void printResult() {
		System.out.println("\nNon-Integers:");
		
		for (int i = 0; i < string_list.size(); i ++) {
			System.out.println("\t" + string_list.get(i));
		}
		
		System.out.println("\nIntegers:");
		
		for (int i = 0; i < number_list.size(); i ++) {
			System.out.println("\t" + number_list.get(i));
		}
		
		System.out.println("\n");
	}
}

class NumberSequence 
{
	private int[][] num_seq_list;
	private String[] seq_name;

	NumberSequence() {
		generateSequence();
	}
	
	public void generateSequence() {
		// Generate number sequence 
		num_seq_list[0][0] = 0;
		
		for (int i = 1; i < 6; i ++) {
			num_seq_list[0][i] = num_seq_list[0][i - 1] * 2;
		}
		seq_name[0] = "time 2";
		
		// Generate number sequence 2
		num_seq_list[1][0] = 0;
		
		for (int i = 1; i < 6; i ++) {
			num_seq_list[1][i] = num_seq_list[1][i - 1] * 3 - 1;
		}
		seq_name[1] = "time 3 mins 1";
		
		// Generate number sequence 3
		num_seq_list[3][0] = 1;
		
		for (int i = 1; i < 6; i ++) {
			num_seq_list[1][i] = num_seq_list[1][i - 1] * 2 - 1;
		}
		seq_name[2] = "time 2 mins 1";
	}
	
	public void runGame() {
		boolean find_answer = false;
		
		Scanner in = new Scanner(System.in);
		
		int index = (int)((Math.random() * 100 % 3) + 1);
		
		printSequence(index);
		
		for (int i = 0; i < 3; i ++) {
			System.out.print("What's the next number? ");
			int number = in.nextInt();
			
			if (number == num_seq_list[index][5]) {
				find_answer = true;
				break;
			}
			
			System.out.print("Try again. ");
		}
		
		if (find_answer) {
			System.out.println("What's the pattern name?");
			String answer = in.nextLine();
			
			if (!answer.equalsIgnoreCase(seq_name[index])) {
				System.out.print("Incorrect. It is " + seq_name[index]);
				System.out.println("\n");
			}
		}
		
		System.out.println("\n\n\n");
	}
	
	public void printSequence(int index) {
		if (index >= 0 && index < 3) {
			System.out.print("The number sequence name is " + seq_name[index]);
			System.out.println("\n");
			
			for (int i = 0; i < 5; i ++) {
				System.out.print(num_seq_list[index][i] + ", ");
			}
			System.out.println("guess number?");
		}
	}
}
	
class GoingOut {
	private int randNum;
	private double money;
	private String decision;
	
	Scanner in = new Scanner(System.in);

	public void decide() {
		System.out.println("\n\n\n");
		System.out.println("How much money do you have?");
		
		money = in.nextDouble();
		randNum = (int)(Math.random() * 100 % 3 + 3);
		
		if (randNum == 3) {
			if (money <= 15.0)
				decision = "bowl at Homestead Lanes";
			else if (money > 15.0)
				decision = "blow at Bowlmor";
		}
		else if (randNum == 4) {
			if (money <= 15.0)
				decision = "dine at Tam Cafe";
			else if (money > 15.0)
				decision = "dine at Elephant Bar";
		}
		else if (randNum == 5) {
			decision = "stay in and watch TV";
		}
	}
	
	public void printout() {
		System.out.printf("Based on our random number %d and the $%.2f you have, %s!", randNum, money, decision);
		System.out.println("\n\n\n");
	}
	
	public GoingOut() {
		randNum = 0;
		money = 0.0;
		decision = "";
	}
}

class Pet {
	String type;
	boolean whiskers;
	String hobby;
	String sound;
	
	public Pet(String t, boolean w, String h, String s) {
		System.out.println("Class Pet constructor be called");
		
		this.type = t;
		this.whiskers = w;
		this.hobby = h;
		this.sound = s;
	}
	
	public String myTypeIs() {
		return "I am nobody";
	}
}

class Dog extends Pet {
	public Dog() {
		super("Dog", true, "shit", "barks");
		
		System.out.println("Class Dog constructor be called");
	}
	
	public String myTypeIs() {
		return "I am a dog";
	}
}

class Cat extends Pet {
	public Cat() {
		super("Cat", true, "play", "meow");
		
		System.out.println("Class Cat constructor be called");
	}
	
	public String myTypeIs() {
		return "I am a cat";
	}
}

class BuckleMyShoe
{
	Scanner in = new Scanner(System.in);
	int input; 
	String output;
	
	public String toString() {
		return output;
	}
	
	public void runProgram()
	{
		System.out.println("\n\n");
		System.out.print("Please enter an integer from 1 to 10: ");
		input = in.nextInt();
		if(input >= 1 && input <= 2)
			output = "1, 2 Buckle my shoe";
		else if(input >= 3 && input <= 4)
			output = "3, 4 Shut the door";
		else if(input >= 5 && input <= 6)
			output = "5, 6 Pick up sticks";
		else if(input >= 7 && input <= 8)
			output = "7, 8 Lay them straight";
		else if(input >= 9 && input <= 10)
			output = "9, 10 A big fat hen";
			
		if(input < 1 || input >10)
			System.out.println("Sorry, inappropriate input.");
		else
		{
			for(int i = 1; i <= input; i++)
			{
			/*
				if(i == 10 && input == 10)
					System.out.println(i + ". " + output);
				else if(input == 10)
					System.out.println(i + ".  " + output);
				else
					System.out.println(i + ". " + output);
			*/
				System.out.printf("%-2d. %s\n", i, output);
			}
		}
		System.out.println("\n\n");
	}
}

class Parent {
	boolean allowance, drive, keyToCar;
	
	public Parent(boolean b1) {
		allowance = false;
		drive = false;
		keyToCar = false;
		if (b1) {
			System.out.println("You called Parent constructor");
		}
	}
	
	public String parentMethod() {
		if (allowance) {
			return "You get $50.49 per month.";
		}
		else if (drive) {
			return "Okey, I'll drive you to school.";
		}
		else if (keyToCar) {
			return "Yes, you can borrow my Porsche.";
		}
		else {
			return "";
		}
	}
}

class Child extends Parent {
	public Child(boolean b3) {
		super(b3);
		
		if (b3) {
			System.out.println("You called Child constructor");
		}
	}
}

class GrandChild extends Child {
	boolean garbage, cleanRoom, grades;
	
	public GrandChild(boolean b2) {
		super(b2);
		
		garbage = false;
		cleanRoom = false;
		grades = false;
		
		if (b2) {
			System.out.println("You called GrandChild constructor");
		}
		else
		{
			System.out.println("You called GrandChild constructor");
			System.out.println("You called Child constructor");
		}
	}
	
	public double grandChildMethod() {
		if (garbage) {
			return 2.5;
		}
		else if (cleanRoom) {
			return 1.50;
		}
		else if (grades) {
			return 50.49;
		}
		else {
			return 0.0;
		}
	}
}

class Ride {
	private int distance;
	
	public Ride(int miles) {
		distance = miles;
	}
	
	public String whereAmI() {
		if (distance == 1 || distance == 30 )
			return "Cupertino";
		else if ((distance >= 2 && distance <= 6) || (distance >= 24 && distance <= 29))
			return "Los Altos";
		else if ((distance >= 7 && distance <= 11) || (distance >= 19 && distance <= 23))
			return "Palo Alto";
		else if (distance >= 12 && distance <= 13)
			return "Menlo Park";
		else if (distance >= 14 && distance <= 18)
			return "Portola Valley";
		else 
			return "I lost";
	}
}
class ExecuteRide {
	private int miles;
	
	public ExecuteRide() {
		miles = 0;
	}
	
	public void runRide() {
	//	do {
			input();			
	//	} while (miles < 1 || miles > 30);
		
		Ride ride = new Ride(miles);
		output(ride.whereAmI());
	}
	
	public void input() {
		Scanner bk = new Scanner(System.in);
		System.out.println("Please enter how many miles you will tavel.");
		
		miles = bk.nextInt();
	}
	
	public void output(String city) {
		if (miles > 0 && miles <= 30)
			System.out.println("You'll be in " + city + " after " + miles + " miles.");
		else
			System.out.println("Reread the prompt");
	}
}
class PrimeNumber2 {
	public PrimeNumber2() {
	}
	
	public boolean isPrime(long number) {
		if(number <= 1) 
			return false; 
		
	    long size = (long)Math.sqrt(number);
	    long i = 2;
	    //System.out.printf("Number %d ", number);
	    
	    while (i <= size) {
	        if((number % i) == 0) {
	            //System.out.println("is not a Prime");
	            return false;
	        }
	        i ++;
	    }
	    //System.out.println("is a Prime");
	    return true;
	}
	
	public int CountPrime(long maxNumber) {
		int cnt = 0;
	    while(maxNumber > 1) {
	        if(isPrime(maxNumber - 1)) {
	            cnt ++;
	        }
	        maxNumber --;
	    }
	    return cnt;
	}
	
	public long SumPrime(long maxNumber) {
		long sum = 0;
	    while(maxNumber > 1) {
	    	long num = maxNumber - 1;
	        if(isPrime(num)) {
	            sum += num;
	        }
	        maxNumber --;
	    }
	    return sum;
	}
		
	public int CountPrimeSIEVE(long maxNumber) {
		if(maxNumber <= 2) 
			return 0;
	    
	    int cnt = 0;
	    boolean[] answer = new boolean[(int)(maxNumber - 1)];
	    Arrays.fill(answer, Boolean.FALSE);

	    answer[0] = Boolean.TRUE;
	    
	    long i = 2; //range of i: 2 to sqr(n - 1)
	    long sqr = (long)Math.sqrt(maxNumber - 1);

	    while(i <= sqr){
	        if(answer[(int) (i - 1)] == Boolean.FALSE) {
	            long j = 0;
	            long index = i * i;
	            while(index <= (maxNumber -1)){
	                answer[(int) (index - 1)] = Boolean.TRUE;
	                j ++;
	                index = i * i + j * i;
	            }
	        }
	        i++;
	    }

	    for(int k = 0; k < (maxNumber - 1); k++) {
	        if (answer[k] == Boolean.FALSE) 
	        	cnt ++;
	    }
	    return cnt;
	}
	
	public long SumPrimeSIEVE(long maxNumber) {
		if(maxNumber <= 2) 
			return 0;
	    
	    long sum = 0;
	    boolean[] answer = new boolean[(int)(maxNumber - 1)];
	    Arrays.fill(answer, Boolean.FALSE);

	    answer[0] = Boolean.TRUE;
	    
	    long i = 2; //range of i: 2 to sqr(n - 1)
	    long sqr = (long)Math.sqrt(maxNumber - 1);

	    while(i <= sqr){
	        if(answer[(int) (i - 1)] == Boolean.FALSE) {
	            long j = 0;
	            long index = i * i;
	            while(index <= (maxNumber -1)){
	                answer[(int) (index - 1)] = Boolean.TRUE;
	                j ++;
	                index = i * i + j * i;
	            }
	        }
	        i++;
	    }

	    for(int k = 0; k < (maxNumber - 1); k++) {
	        if (answer[k] == Boolean.FALSE) 
	        	sum += k;
	    }
	    return sum;
	}
}

class Human {
	public void say() {
		System.out.println("I am a Human");
	}
	static public void ok() {
		System.out.println("OK");
	}
}
class Chinese extends Human {
	public void say() {
		System.out.println("I am a Chinese");
	}
	static public void ok() {
		System.out.println("Hao");
	}
}
class US extends Human {
	public void say() {
		System.out.println("I am a US");
	}
}

public class HelloWorld {

	public static void main(String [] args)
	{
		// sub-class test
		Human man1 = new Chinese();
		Human man2 = new US();
		Human man3;
		Chinese man4;
		
		man1.say();
		man1.ok();
		man2.say();
		man3 = man1;
		man3.say();
		man3 = new Human();
		man3.say();
		//man4 = (Chinese)man3;
		//man4.say();
		man4 = new Chinese();
		man4.ok();
		
		// Check CS homework
		int ahab = 5; 
		int moby = 6; 
		int stubb = 11; 
		do {
			if(moby % ahab > 2) { 
				ahab++; 
			}
			else if(moby % ahab < 2) {
				ahab--; 
				moby++; 
			}
			if(stubb / ahab == 2) {
				stubb--;
			}
			else {
				stubb++;
				moby += 2; 
			}
			System.out.println (ahab + " " + moby + " " + stubb);
		} while(stubb / moby >= 1);
		
		int result = 67 / 8 % 7 * 2 % 3 * 6 - 3;
		System.out.println(result);
		
		result = 7+59/7%12*4;
		if (35.7 >= result) {
			result = 1;
		}
		
		result = 35 + 12 % 13 / 5 * 7 - 13;
		
		if ((double) 68 / 3 != (double)(68 / 3))
			result = 1;
		
		for (int row = 1; row <= 5; row ++) {
			int col= 1; 
			while (col <= 5) {
				if (row% col== 1) { 
					System.out.print(col);
				}
				else {
					System.out.print("*");
				}
				col++;
			}
			System.out.println();
		}
		
		// CS homework
		
		PrimeNumber2 primer = new PrimeNumber2();
		long max = 1000000;
		
		long start = System.currentTimeMillis();
		int count = primer.CountPrime(max);
		long end = System.currentTimeMillis();
		System.out.printf("From 1 to %d, prime count is %d, cost %d(ms) \n", max, count, (end-start));
		
		start = System.currentTimeMillis();
		count = primer.CountPrimeSIEVE(max);
		end = System.currentTimeMillis();
		System.out.printf("From 1 to %d, prime count is %d, cost %d(ms) \n", max, count, (end-start));
		
		start = System.currentTimeMillis();
		long sum = primer.SumPrime(max);
		end = System.currentTimeMillis();
		System.out.printf("From 1 to %d, sum of prime is %d, cost %d(ms) \n", max, sum, (end-start));

		start = System.currentTimeMillis();
		sum = primer.SumPrimeSIEVE(max);
		end = System.currentTimeMillis();
		System.out.printf("From 1 to %d, sum of prime is %d, cost %d(ms) \n", max, sum, (end-start));
/*
		int x = 1;
		x = x << 1;
		System.out.println(x);
		x = x << 2;
		System.out.println(x);
		x = x >> 1;
		System.out.println(x);
		x = x | 1;
		System.out.println(x);
		x = x & 1;
		System.out.println(x);
		
		WhyDidTheCowCrossTheRoad2 findPair = new WhyDidTheCowCrossTheRoad2();
		findPair.run();

		MilkMeasure milkMeasure = new MilkMeasure();
		milkMeasure.run();
		
		TheBovineShuffle bovineShuffle = new TheBovineShuffle();
		bovineShuffle.run();

		LifeGuards lifeGuard = new LifeGuards();
		lifeGuard.run();

		ThreeNames threeName = new ThreeNames();
		threeName.askNames();
		
		CowTip cowTip = new CowTip();
		cowTip.getTipArray();
		
		CowMilks cowMilk = new CowMilks();
		cowMilk.getCowMilks();
		
		NameInBits7 nameRev = new NameInBits7();
		nameRev.getString();
		
		MathRecursion2 recursion = new MathRecursion2();
		recursion.c_avgDigitsHard(123);

		MathRecursion recursion = new MathRecursion();
		recursion.askQuestion();
		
		PrimeNumber prime = new PrimeNumber();
		prime.runProgram();
		
		SeparateNumbers parse = new SeparateNumbers();
		parse.parseInput();
		parse.printResult();
		
		GoingOut gout = new GoingOut();
		gout.decide();
		gout.printout();
		
		BuckleMyShoe buckle = new BuckleMyShoe();
		buckle.runProgram();
		System.out.println(buckle);
		
		ExecuteRide ride = new ExecuteRide();
		ride.runRide();
*/
		HelloWorld run = new HelloWorld();
		run.runIt();
		
/*
		Dog dog;
		Cat cat;
	
		dog = new Dog();
		cat = new Cat();
		
		System.out.println(run.yourNameIs(dog));
		System.out.println(run.yourNameIs(cat));
*/		
		//System.out.println(findRepeatNumber(0.11231123));
		//System.out.println(findRepeatNumber(0.12121212));
		
		int [] array = {4, 8, 1, 9, 3, 6, 2, 5, 7, 0};
		
		for(int i = 0; i < array.length; i++)
		{
			System.out.println(array[i]);
		}
		
		// System.out.println("Bubble Sort");
		// run.bubbleSort(array);
		
		// System.out.println("Selection Sort");
		// run.selectionSort(array);
		
		System.out.println("Quick sort");
		run.quickSort(array);
		
		for(int i = 0; i < array.length; i++)
		{
			System.out.println(array[i]);
		}
	}
/*	
	public String yourNameIs(Pet pet) {
		return pet.myTypeIs();
	}
*/	
	public void runIt() {
		Scanner in = new Scanner(System.in);
		
		int answer1 = 0;
		
		System.out.println("Do you want to talk to your parent(1) or the grandchild(2)");
		answer1 = in.nextInt();
		
		if (answer1 == 1) {
			System.out.println("Would you like to ask them about allowance(1), a ride(2), or keys to the car(3)?");
			int answer2 = in.nextInt();
			
			Child child = new Child(true);
			String result = "";
	
			switch (answer2) {
			case 1:
				child.allowance = true;
				result = child.parentMethod();
				break;
			case 2:
				child.drive = true;
				result = child.parentMethod();
				break;
			case 3:
				child.keyToCar = true;
				result = child.parentMethod();
				break;
			}
			
			if (result != "") {
				System.out.println("\n"+result);
			}
		}
		else if (answer1 == 2) {
			System.out.println("\nWould you like to take them out the garbage(1), clean their room(2), or reward them for good grades(3)?");
			int answer2 = in.nextInt();

			GrandChild grandChild = new GrandChild(false);
			double result = 0.0;
			
			switch (answer2) {
			case 1:
				grandChild.garbage = true;
				result = grandChild.grandChildMethod();
				System.out.println("\nYou get $"+result+" for taking them out the garbage");
				break;
			case 2:
				grandChild.cleanRoom = true;
				result = grandChild.grandChildMethod();
				System.out.println("\nYou get $"+result+" for cleaning their room");
				break;
			case 3:
				grandChild.grades = true;
				result = grandChild.grandChildMethod();
				System.out.println("\nYou get $"+result+" for getting good grades");
				break;
			}
		}
	}
	public void bubbleSort(int [] array)
	{
		for(int i = 0; i < array.length - 1; i++)
		{
			for(int j = 0; j < array.length - 1 - i; j++)	// for(int j = 0; j < array.length - 1; j++)
			{
				if(array[j] < array[j+1])
				{
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}
	
	public void selectionSort(int [] array)
	{
		for(int j = 0; j < array.length - 1; j++)
		{
			int index = j;	// int index = 1;
			for (int i = j + 1; i < array.length; i ++)	// for(int i = 0; i < array.length -1; i++)
			{
				if (array[i] > array[index])
				{
					index = i;	// index = j;
				}
			}
			int temp = array[j];
			array[j] = array[index];
			array[index] = temp;
		}
	}
	
	public void quickSort(int [] array)
	{
		helper(0, array.length - 1, array);
	}
	
	public void helper(int start, int end, int [] array)
	{
		// condition check
		if (start >= end)
		{
			return;
		}
		
		// get middle number(pivot)
		int l = start;
		int h = end;
		int pivotIndex = l + (h - l) / 2;	// int pivotIndex = array[l + (h - l)/2];
		int pivot = array[pivotIndex];
		
		while (l < h)	// while(l <= h)
		{
			while (array[l] < pivot)	// if(array[l] < pivot)
			{
				l++;
			}
			while (array[h] > pivot)	// if(array[h] > pivot)
			{
				h--;
			}
			
			if (l != h)
			{
				if (array[l] == array[h])
				{
					l ++;
					h --;
				}
				else
				{
					int temp = array[l];	// int temp = array[h];
					array[l] = array[h];
					array[h] = temp;
				}
			}
		}
		
		if (start < l)	// 
		{
			helper(start, l - 1, array);	// helper(0, l, array);
		}
		if (h < end)	//
		{
			helper(h + 1, end, array);	// helper(h, array.length - 1, array);
		}
	}

	public static int findRepeatNumber(double number)
	{
		String numString = String.valueOf(number);	
		int pos = numString.indexOf('.');
		int patrpt = 0;
		
		pos ++;	// find the first pattern
		String pattern = "" + numString.charAt(pos);
		String pattemp = "";
		
		while (pos < numString.length() - 1)
		{
			String compstr = numString.substring(pos + 1, pos + 1 + pattern.length());
			if(!compstr.equals(pattern))
			{
				compstr = compstr.substring(0, 1);
				pattern += pattemp + compstr;
				pattemp = "";
				patrpt = 0;
			}
			else
			{
				pattemp += compstr;
				patrpt ++;
			}
			pos += compstr.length();
		}
		
		if (pattern.length() == numString.length() - numString.indexOf('.') - 1)
		{
			patrpt = 0;
		}
		else
		{
			patrpt ++;
		}
		
		return patrpt;
	}
}
