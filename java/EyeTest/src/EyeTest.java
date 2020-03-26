import java.util.ArrayList;

interface Talk {
	public void hello();
	public void my_name_is();
	public void bye();
}
class Human implements Talk {
	int age;
	String full_name;
	
	Human() {
		System.out.println("Default construction called - Human");
	}
	public void hello() {
		System.out.println("haaoo");
	}
	public void my_name_is() {
		System.out.println("smart monkey");
	}
	public void my_age() {
		System.out.println(age);
	}
	public void bye() {
		System.out.println("ohohoh");
	}
	static public void ate() {
		System.out.println("hungry");
	}
	
	public int age() {
		return 0;
	}
}
class Parent extends Human {
	private String last_name = new String("Zhang");
	
	Parent () {
		System.out.println("Default construction called - Parent");
	}
	Parent(String name) {
		full_name = new String("Parent, ") + last_name;
		if (full_name.indexOf(name) != -1)
			System.out.println("Duplicate name with me!");
		
		age = 50;
	}
	public String last_name() {
		return last_name;
	}
	public void my_name_is() {
		System.out.println(full_name);
	}
	static public void ate() {
		System.out.println("Steamed bun");
	}
	public int age() {
		return super.age() + age;
	}
	public String reverse(String s) {
		/*
		 * Using recursion
		 */
		if (s == null || s.isEmpty()) {
			return s;
		}
		String reverse = "";
		for (int i = s.length() - 1; i >= 0; i --) {
			reverse = reverse + s.charAt(i);
		}
		return reverse;
		
		 /*
		  * Using non-recursion work?
		  */
	}
	public String change_last_name(char [] name) {
		last_name = new String(name);
		return last_name;
	}
}
class Child extends Parent {
	Child () {
		System.out.println("Default construction called - Child");
	}
	
	Child(String name) {
		super(name);
		full_name = "Child, " + super.last_name();
		
		age = 25;
	}
	static public void ate() {
		System.out.println("Bread");
	}
	public void hello() {
		System.out.println("Hello");
	}
	public void puzzle() {
		for (int i = full_name.length() - 1; i >= 0; i -= 2) {
			String str = full_name.substring(i, i + 1);
			char ch = full_name.charAt(full_name.length() - 1 - i);
			System.out.println("i = " + i + ", str = " + str + ", ch = " + ch);
			full_name = str + ch + full_name.substring(0, i);
			System.out.println(full_name);
		}
	}
}
class GrandChild extends Child {
	private String [] hobby = {"football", "tennis", "swimming" };
	
	GrandChild () {
		System.out.println("Default construction called - GrandChild");
	}
	GrandChild(String name) {
		super(name);	// remove it, OK?
		full_name = "Grandchild" + ", " + last_name();
		
		age = 1;
	}
	static public void ate() {
		System.out.println("Hambuger");
	}
	public void change_hobby(String [] hobby) {
		for (int i = 0; i < this.hobby.length; i ++) {
			if (hobby[i] != null) {
				String temp = new String("basketball");
				temp = hobby[i];
				hobby[i] = this.hobby[i];
				this.hobby[i] = temp;
			}
			else {
				hobby[i] = this.hobby[i];
			}
		}
		
		System.out.println(this.hobby[0] + "," + this.hobby[1] + "," + this.hobby[2]);
	}
	public int age() {
		int [] age1 = {50, 25, 1};
		int [] age2 = {1, 25, 50};
		int [] age3 = age1;
		age2 = age3;
		age1[0] = 100; age2[1] = 200; age3[2] = 300;
		/*
		for (int i = 0; i < 5; i ++) {
			for (int j = 0; j < i; j ++) {
				age1[0] %= 50; age2[1] /= 25; age3[2] -= 1;
				age1[0] *= (i + j); age2[1] -= j; age3[2] += i;
			}
		}
		*/
		return super.age() + age3[0] + age2[1] + age1[2];
	}
}


public class EyeTest {

	public void convercation(ArrayList human) {
		for (int i = 0; i < human.size(); i ++) {
			Human man = (Human)human.get(i);
			man.hello();
			man.my_name_is();
			man.my_age();
			man.bye();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EyeTest eye = new EyeTest();
		
		// How to use ArrayList
		ArrayList<Human> people = new ArrayList<Human>();
		
		// Inheritance 
		Human manA = new Parent("Robert");
		people.add(manA);
		Human manB = new Child("Bob");
		people.add(manB);
		GrandChild manC = new GrandChild("Joe");
		people.add(manC);
		
		// How base class working with derived class
		System.out.println("--------");
		eye.convercation(people);
		
		// Case derived class to base
		System.out.println("--------");
		Human manCC = (Human)manC;
		if (manC == manCC)
			System.out.println("manC == manCC");
		Child manBB = (Child)manCC;	// !!!
		if (manB == manCC)
			System.out.println("manB == manCC");
		Parent manAA = (Parent)manA;
		if (manA == manAA) {
			System.out.println("manA == manAA");
			
			// char[] to String
			char [] last_name = {'Y','a','n','g'};
			System.out.println(manAA.change_last_name(last_name));	// does this work?
			
		}
		
		// static overwrite method 
		System.out.println("--------");
		manC.ate();
		manCC.my_name_is();
		manCC.ate();
		manB.ate();
		manBB.my_name_is();
		manBB.ate();
		manA.ate();
		manAA.my_name_is();
		manAA.ate();
		
		// Case base (parent) class to derived (child) class
		Human manAAA = new Human();
		//manAA = (Parent)manAAA;	// does this work?
		//manAA.ate();
		
		// String array and reference
		System.out.println("--------");
		String [] hobby = {null, null, "basketball"};
		hobby[0] = new String("chess");
		
		manC.change_hobby(hobby);
		System.out.println(hobby[0] + "," + hobby[1] + "," + hobby[2]);
		
		// Is overwrite function recursion?
		System.out.println("--------");
		System.out.println("Total age is " + manC.age());
		
		// String puzzle
		System.out.println("--------");
		manBB.puzzle();
		
		// String reverse
		System.out.println("--------");
		String reverse = manAA.reverse("abcdefg");
		System.out.println(reverse);
	}
}
