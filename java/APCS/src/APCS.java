import java.util.ArrayList;

public class APCS {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		APCS apcs = new APCS();
		apcs.run();
	}
	
	public void printIntArray(int[] arr) {
		for (int i = 0; i < arr.length;i ++)
			System.out.print(arr[i]);
		System.out.println();
	}
	
	public void run() 
	{
		int x = 20; 
		int y = 6;
		
		if ((x>15) && (y>10))
			System.out.println("Result:"+x*y);
		else
			System.out.println("Result:"+x/y);
		
		String s1 = "new year";
		String s3 = s1;
		boolean b = s1.equals(s3);
		b = s1 == s3;
		System.out.println(b);
		
		System.out.println(workonList(6));
		System.out.println(makeString("CUPERTINO"));
		
		String words = "I am\nso ready\nfor my\nFinal Exam(s)";
		String words2 = changeit(words,"\n");
		System.out.println(words+"\n"+words);
		
		String mv1 = "I Love ";
		String mv2 = mv1;
		mv2 += "Cupertino!";
		mv2.substring(7);
		System.out.println(mv1+mv2);
		
		test1();
		
		Server server = new Tomcat("Apache Tomcat");
		server.start();
		
		int[] nums= {3,5,1,0,4,6,7,2,8};
		System.out.println(arrayop(nums));
		
		test2();
		
		schoolpride(4);
		
		test3();
		
		int [] snums = {1, 2,3,4,5,6,7, 8};
		actOnArray(snums);
		printIntArray(snums);

		test4();
		test5();
		
		System.out.println(scramble("compiler", 3));

		Chihuahua fufu = new Chihuahua();
		Dog fido = new Chihuahua();
		Dog spike = new Dog();
		
		//test6(); dead loop
		
		searchIt(94);
	}
	
	public ArrayList <Integer> workonList(int num)
	{
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		
		for (int i = 1; i <= num; i ++)
			sequence.add(new Integer(i*i+2));
		
		return sequence;
	}
	
	public String makeString(String input)
	{
		String output = new String("");
		
		for (int k = 0; k < input.length(); k=k+2)
		{
			output += input.substring(k, k + 1);
		}
		return output;
	}
	
	public String changeit(String message, String place)
	{
		int i = message.indexOf(place);
		while (i >= 0)
		{
			message = message.substring(0,i)+""+message.substring(i+place.length());
			i = message.indexOf(place);
		}
		return message;
	}
	
	public void test1() {
		int [] array = {9, 10, 5, 8, 12, 3};
		for (int k = 0; k < array.length-1; k ++)
		{
			if (array[k] < array[k+1])
				System.out.print(k+" "+array[k]+" ");
		}
	}
	
	public void test2() {
		int [] array = {1, 2,3,4,5,6,7};
		for (int k=3;k<array.length - 1; k ++)
			array[k] = array[k+1];
		printIntArray(array);
	}
	
	public int arrayop(int[] arr)
	{
		int x = 0;
		for (int k=0; k <= arr.length; k = k + 2)
			x = x + arr[k];
		return x;
	}
	
	public void schoolpride(int n) {
		if (n <= 0)
			return;
		
		schoolpride(n-1);
		
		for (int i = 0; i < n; i ++)
			System.out.print("M");
		for (int i = 0; i < n; i ++)
			System.out.print("V");
		System.out.println();
	}
	
	public void test3() {
		ArrayList<String>list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		
		for (int k = 1; k<=3;k++)
			list.remove(1);
		for (int k = 1; k <=3; k++)
			list.add("*");
		for (String word:list)
			System.out.print(word +" ");
	}
	
	public void test4() {
		String str = "String out of bounds";
		str.length();
		str.charAt(2);
		str.replace('a', 'A');
		str.equals(str);
	}
	
	public void test5() {
		int [] arr = {4,3,2,1,0};
		for (int i = 1; i < arr.length; i ++)
			arr[i-1]+=arr[i];
		printIntArray(arr);
	}
	
	public String scramble(String word, int howFar) {
		return word.substring(howFar+1,word.length()) + word.substring(0,howFar);
	}
	
	public void actOnArray(int[] nums) {
		int j=0;
		int k= nums.length - 1;
		while (j<k) {
			int x = nums[j];
			nums[j] = nums[k];
			nums[k] = x;
			j++;
			k--;
		}
	}
	
	public void test6() {
		int sum = 0;
		int k = 1;
		
		while (sum < 12 || k < 4)
			sum += k;
		
		System.out.println(sum);
	}
	
	private int [] testscores = {94, 70, 80, 90, 92, 75};
	
	public int searchIt(int target) {
		return searchItHelper(target, testscores.length - 1);
	}
	
	public int searchItHelper(int targ, int last) {
		if (last <= 0)
			return -1;
		
		if (testscores[last]==targ)
			return last;
		else
			return searchItHelper(targ, last-1);
	}
}

abstract class Server{
    protected final String name;
   
    public Server(String name){
        this.name = name;
    }
   
    public abstract boolean start();
}

class Tomcat extends Server{
   
    public Tomcat(String name){
        super(name);
    }

    @Override
    public boolean start() {
       System.out.println( this.name + " started successfully");
       return true;
    }
}

class Supercat extends Tomcat {
	public Supercat(String name) {
		super(name);
	}
}

class Dog
{
	public Dog() {
		Bark();
	}
	public void Bark() {
		System.out.print("WOOF");
	}
}

class Chihuahua extends Dog 
{
	public Chihuahua() {
		System.out.print("-");
	}
	public void Bark() {
		System.out.print("YIP");
	}
}