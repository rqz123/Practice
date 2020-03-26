
public class ObjectChange {

	 class TestObject {
		final double PI = 3.14;
		
		private int i;
		int j;
		
		TestObject(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		TestObject(TestObject obj) {
			int year = 0, yy = year+1;
			double i = obj.PI;
		}
		 @Override
		 public String toString() {
			 return "i = " + i + ", j = " + j;
		 }
	}
	
	int obj_int = 123;	// char, float, double, ...
	String obj_string = new String("abc");
	int [] obj_array = {1, 2, 3};
	TestObject obj_class = new TestObject(1, 2);
	
	 public ObjectChange() {
		 System.out.println(1+2+3+"B");
	 }
	 
	 public void ChangeInt(int i) {
		 i = 10000000;
	 }
	 
	 public void ChangeString(String s) {
		 s = s + "def";
		 s = new String("aldsfkjasd;lkf");
	 }
	 /*
	 public int ChangeString(String s) {
		 System.out.println(s.length());
		 return s.length();
	 }
	 */
	 
	 public void ChangeArray(int [] arr) {
		 //arr = new int[3];
		 arr[0] = 100;
		 arr[1] = 200;
		 arr[2] = 300;
	 }
	 
	 public void ChangeClass(TestObject obj) {
		 obj.i = 100;	// why? it's private
		 obj.j = 200;
	 }
	 
	 public void Change() {
		System.out.print("int: " + obj_int);
		ChangeInt(obj_int);
		System.out.println(" => " + obj_int);
		
		System.out.print("string: " + obj_string);
		ChangeString(obj_string);
		System.out.println(" => " + obj_string);
		
		System.out.print("array: " + obj_array[0] + ", " + obj_array[1] + ", " + obj_array[2]);
		ChangeArray(obj_array);
		System.out.println(" => " + obj_array[0] + ", " + obj_array[1] + ", " + obj_array[2]);
		
		System.out.print("class: " + obj_class);
		ChangeClass(obj_class);
		System.out.println(" => " + obj_class);
	 }
	 
	 public void TraceCode() {
		 int inner = 1;
		 do {
			 int counter;
			 for (counter = 1; counter < 10; counter ++) {
				 if (inner % 2 == 0)
					 System.out.print("@");
				 else if (inner % 3 == 0) {
					 System.out.print("$");
					 counter --;
				 }
				 if ((inner + counter) % 2 == 0) {
					 System.out.print("#");
					 inner += 2;
				 }
				 else 
					 System.out.print("?");
				 System.out.println();
			 }
			 
			 if (inner % 2 ==  1)
				 counter ++;
		 } while (inner < 11);
	 }
	 
	 public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjectChange obj_change = new ObjectChange();
		obj_change.Change();
		obj_change.TraceCode();
		
		final double g = 16.0;
		double t = 35.5;
		System.out.print ("");
		System.out.println(1/2*(g*t*t));
		
		int x = 0;
		System.out.println(Math.sqrt(x));
	 }

}

/*
public comedyOfErrors(double x, double y) //
{
	char value = "1";//
	int [] myList = new int[];//
	value = x; //
	boolean b = ((x < y || value) //

    if (x > value)
    	System.out.println(x + "is greater!");
	
    else
    {
    	System.out.println(value - 1 + "is greater!") //
    }
	
	do {
		value ++;
	}
	while(value < 100) //
		
	for (int i = 0; i < 10; i=i+1) //2
		if(value + i == 64)
		{
			return 100; //
		}
	
	double y = 1.5; //
	float k = 2.5;
	boolean checker = false;
	
	if (checker == value) //
		System.out.println(HELLO); //
	
	return value; //
}
*/