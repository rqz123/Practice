import java.util.ArrayList;

public class BinarySearch {
	
	public int backwards(int a, int b) {
		System.out.println("backwards(" + a + ", " + b + ")");
		if (a % 3 == 0) {
			System.out.println("return " + b);
			return b;
		}
		int r = a + b + backwards(b - 1, a - 1); 
		System.out.println("return " + r);
		return r;
	}
	
	public void printNames(String[] elements, int k) {
		System.out.println("printNames(element, " + k + ")");
		if (k > 1) {
			printNames(elements, k - 1);
			System.out.print(elements[k] + " ");
			printNames(elements, k - 2);
		}
	}
	
	public void changeIt(Integer[] list, int num) {
		list = new Integer[5];
		num = 0;
		for (int x = 0; x < list.length; x ++)
			list[x] =x + 1;
	}
	public void start() {
		Integer [] nums = new Integer[5];
		int value = 6;
		changeIt(nums, value);
		for (int k = 0; k < nums.length; k ++)
			System.out.print(nums[k].intValue()+" ");
		System.out.print(value);
	}
	
	public void test() {
		ArrayList<Integer> list = new ArrayList();
		list.add(2);
		list.add(5);
		list.add(7);
		list.add(6);
		list.add(0);
		list.add(1);
		list.set(5,9);
		
		//
		backwards(20, 10);
		
		//
		String[] students = {"Amy", "Jung", "Joe", "Neha", "Nancy" };
		printNames(students, students.length - 1);
		
		//
		String str = "abcdef";
		for (int rep = 0; rep < str.length() - 1; rep ++)
			System.out.print(str.substring(rep, rep+2));
		System.out.println();
		
		// 10.
		int [][] mat = new int[3][4];
		for (int row = 0; row < mat.length; row ++) {
			for (int col = 0; col < mat[0].length; col ++) {
				if (row < col || col == 0)
					mat[row][col] = 1;
				else if (row % col == 0)
					mat[row][col] = 2;
				if (row - 1 == col)
					mat[row][col] = 3;
			}
		}
		
		// 11.
		start();
		
		// 12.
		int [] oldArray = {1, 2,3,4,5,6,7,8,9};
		int [][] newArray = new int[3][3];
		
		int row = 0;
		int col = 0;
		for (int value : oldArray) {
			newArray[row][col]=value;
			row++;
			if ((row % 3) == 0) {
				col++;
				row = 0;
			}
		}
		System.out.println(newArray[0][2]);
		
		// 7.
		int num1 = 4, num2 = 5; 
		Integer int1 = new Integer(12); 
		Integer int2 = new Integer(63); 
		swapper(num1, num2, int1, int2); 
		System.out.println(num1 + "   " + num2 + "   " + int1 + "   " + int2 + "   ");
		
		// 8.
		mark(3, 1);
	}
	
	public void mark(int rowMark, int colMark) { 
		int [][] mat = new int[5][5]; // mat is square 
		
		for(int k = 0; k < mat.length; k++) { 
			int row = (rowMark + k) % mat.length; 
			int col = (colMark + mat.length - k) % mat.length; 
			mat[row][col] = 1; 
		}
		mat[0][0] = 0;
	}
	public void swapper(int n1, int n2, Integer i1, Integer i2) {
		int temp = n1;
		n1 = n2; 
		n2 = temp; 
		Integer tempint = i1; 
		i1 = i2; 
		i2 = tempint; 
		System.out.print(n1 + "   " + n2 + "   " + i1 + "   " + i2 + "   ");
	}
	
	public String decimal2BinaryString(int dec) {
		String bin = "";
		
		while (dec > 0) {
			int mod = dec % 2;
			bin = mod + "" + bin;
			dec = dec / 2;
		}
		return bin;
	}
	
	public String decimal2HexadecimalString(int dec) {
		final String hex_digits = "0123456789ABCDEF";
		String hex = "";
		
		while (dec > 0) {
			int mod = dec % 16;
			hex = hex_digits.charAt(mod) + "" + hex;
			dec = dec / 16;
		}
		return hex;
	}
	
	public int hexString2Decimal(String s) {
		final String hex_digits = "0123456789ABCDEF";
		
		String hex = s.toUpperCase();
		int dec = 0;
		
		for (int i = 0; i < hex.length(); i ++) {
			char c = hex.charAt(i);
			int d = hex_digits.indexOf(c);
			dec = 16 * dec + d;
		}
		
		return dec;
	}
	
	public String hexString2BinaryString(String s) {
		final String hex_digits = "0123456789ABCDEF";
		final String bin_digits[] = {"0000", "0001", "0010", "0011", 
									 "0100", "0101", "0110", "0111",
									 "1000", "1001", "1010", "1011",
									 "1100", "1101", "1110", "1111" };
		
		String hex = s.toUpperCase();
		String bin = "";
		
		for (int i = 0; i < hex.length(); i ++) {
			char c = hex.charAt(i);
			int d = hex_digits.indexOf(c);
			bin = bin + bin_digits[d]; 
		}
		
		return bin;
	}
	
	public String decimal2OctalString(int dec) {
	    String oct = ""; 
	 
	    // Digits in Octal number system
	    char oct_digits[] = {'0','1','2','3','4','5','6','7'};
	 
	    while (dec > 0) {
	       int rem = dec % 8; 
	       oct = oct_digits[rem] + oct; 
	       dec = dec / 8;
	    }
	    
	    return oct;
	}
	
	public int binaryString2Decimal(String bin) {
	    double j = 0;
	    
	    for(int i = 0;i <bin.length(); i++) {
	        if (bin.charAt(i) == '1') {
	        	j = j + Math.pow(2, bin.length() - 1 - i);
	        }
	    }
	    return (int) j;
	}
	
	public String binaryString2HexString(String bin) {
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double arr_double[] = {9.6, 24.3, 25.7, 35.7, 41.1, 53.2, 55.9, 62.9, 67.0, 84.7, 88.6, 91.8, 92.3};
		int arr_int1[] = {13, 25, 26, 27, 31, 34, 36, 38, 41, 45, 62, 68, 77, 79, 82};
		BinarySearch bSearch = new BinarySearch();
		bSearch.binarySearch(arr_double, 0, arr_double.length - 1, 94.6);
		bSearch.binarySearch(arr_int1, 0, arr_int1.length - 1, 45);
		
		String arr_string[] = {"at", "bat", "cat", "dot", "err", "foe", "get", "he", "me", "my", "or", "see", "so", "to", "we", "yes", "you" };
		bSearch.binarySearch(arr_string, 0, arr_string.length - 1, "her");
	
		int dec = 653;
		System.out.println(dec + "(10) = " + bSearch.decimal2BinaryString(dec) + "(2)");
		System.out.println(dec + "(10) = " + bSearch.decimal2HexadecimalString(dec) + "(16)");
		
		String hex = "2B9";
		System.out.println(hex + "(16) = " + bSearch.hexString2Decimal(hex) + "(10)");
		System.out.println(hex + "(16) = " + bSearch.hexString2BinaryString(hex) + "(2)");
		
		// Test
		bSearch.test();
		//Hotel hotel = new Hotel(100);
		
		String arr_string2[] = {"ACE", "AN", "BE", "CAN", "DO", "HOW", "LIE", "MINE", "NOT", "SO", "THAT", "THE", "WHO" };
		bSearch.binarySearch(arr_string2, 0, arr_string2.length - 1, "HELLO");
		
		int arr_int2[] = {4, 8, 12, 15, 23, 34, 39, 42, 61, 77, 80, 94, 106, 121, 144, 176};
		bSearch.binarySearch(arr_int2, 0, arr_int2.length - 1, 39);
		
		// 
		TestSample test = new TestSample(3);
		//System.out.println(test.getBestRatio());
		
		// 1/13 - 1.
		String arr_ch[] = {"a", "c", "f", "h", "k", "m", "n", "p", "r", "s", "u", "w", "x", "z"};
		bSearch.binarySearch(arr_ch, 0, arr_ch.length - 1, "m");
		int arr_int3[] = {-23, -15, -3, 7, 21, 24, 28, 33, 39, 45, 51, 63, 71, 85, 87};
		bSearch.binarySearch(arr_int3, 0, arr_double.length - 1, 51);
		double arr_double2[] = {5.6, 11.4, 15.1, 29.8, 31.6, 33.8, 38.4, 42.2, 47.0, 54.7, 58.6, 61.5, 72.3, 88.4};
		bSearch.binarySearch(arr_double, 0, arr_double.length - 1, 94.6);
		
		// 7.
		String bin = "1011110101";
		System.out.println(bin + "(16) = " + bSearch.binaryString2Decimal(bin) + "(10)");
		
		// 10.
		//int num = Integer.parseInt("four thousand twn hundred and ninety-five");
		
		ClientTest client = new ClientTest();
		
	}

	public int binarySearch(int arr[], int first, int last, int key) {  
		int mid = (first + last) / 2;
		
		while (first <= last) {
			System.out.println("Mid index = " + mid);
			
			if (arr[mid] < key) {  
		        first = mid + 1;     
			} 
			else if (arr[mid] == key) {
		        System.out.println("Element is found at index: " + mid);  
		        return mid;  
			} 
			else {  
		    	  last = mid - 1;  
			}  
		      
			mid = (first + last) / 2;  
		}  
		      
		System.out.println("Element is not found!");
		return -1;
	}

	public int binarySearch(double arr[], int first, int last, double key) {  
		int mid = (first + last) / 2;
		
		while (first <= last) {
			System.out.println("Mid index = " + mid);
			
			if (arr[mid] < key) {  
		        first = mid + 1;     
			} 
			else if (arr[mid] == key) {
		        System.out.println("Element is found at index: " + mid);  
		        return mid;  
			} 
			else {  
		    	  last = mid - 1;  
			}
		      
			mid = (first + last) / 2;  
		}  
		      
		System.out.println("Element is not found!");
		return -1;
	}

	public int binarySearch(String arr[], int first, int last, String key) {  
		int mid = (first + last) / 2;
		
		while (first <= last) {
			System.out.println("Mid index = " + mid);

			try {
				int result = arr[mid].compareTo(key);
			
				if (result < 0) {  
			        first = mid + 1;     
				} 
				else if (result == 0) {
			        System.out.println("Element is found at index: " + mid);  
			        return mid;  
				} 
				else {  
			    	  last = mid - 1;  
				}  
			} catch (NullPointerException e) {
				System.out.println("Null pointer");
				return -1;
			}
		      
			mid = (first + last) / 2;  
		}  
		      
		System.out.println("Element is not found!");
		return -1;
	}
}

class Reservation 
{
	private String guestName;
	private int roomNumber;
	
	public Reservation(String guestName, int roomNumber) {
		this.guestName = guestName;
		this.roomNumber = roomNumber;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
}

class Hotel
{
	private Reservation[] rooms;
	private ArrayList<String> waitList;
	
	public Hotel(int totalRooms) {
		rooms = new Reservation[totalRooms];
	}
	public Reservation requestRoom(String guestName) {
		int id = 0;
		
		for (id = 0; id < rooms.length; id ++) {
			if (rooms[id] == null) {
				rooms[id] = new Reservation(guestName, id);
			}
		}
		
		if (id >= rooms.length) {
			waitList.add(guestName);
			return null;
		}
		return rooms[id];
	}
	
	public Reservation cancelAndReassign(Reservation res) {
		int id = 0;
		
		for (id = 0; id < rooms.length; id ++) {
			if (rooms[id] != null && rooms[id].getRoomNumber() == res.getRoomNumber()) {
				rooms[id] = null;
				break;
			}
		}
		
		if (id < rooms.length && !waitList.isEmpty()) {
			rooms[id] = new Reservation(waitList.get(id), id);
			return rooms[id];
		}
		
		return null;
	}
}

class TestSample
{
	private ArrayList<Integer> samples;
	
	public TestSample(int n) {
		samples = new ArrayList<Integer>();
		for (int k = 1; k < n; k ++)
			samples.add(k);
	}
	
	public double getBestRatio() {
		double maxRatio = samples.get(1).intValue() / samples.get(0).intValue();
		
		for (int k = 0; k < samples.size(); k ++) {
			double ratio = samples.get(k+1).intValue() / samples.get(k).intValue();
			if (ratio > maxRatio)
				maxRatio = ratio;
		}
		return maxRatio;
	}
}

class Client
{
	String name;
	int id;
	
	public Client(String name, int idNum) {
		this.name = name;
		this.id = idNum;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	public int compareClient(Client other) {
		int result = this.getName().compareTo(other.getName());
		if (result == 0) {
			result = this.getID() - other.getID();
		}
		return result;
	}
}

class ClientTest
{
	Client [] list1 = new Client[8];
	Client [] list2 = new Client[7];
	Client [] result = new Client[20];
	
	public ClientTest() {
		list1[0] = new Client("Arthur", 4920);
		list1[1] = new Client("Burton", 3911);
		list1[2] = new Client("Burton", 4944);
		list1[3] = new Client("Franz", 1692);
		list1[4] = new Client("Horton", 9221);
		list1[5] = new Client("Jones", 5554);
		list1[6] = new Client("Miller", 9360);
		list1[7] = new Client("Nguyen", 4339);
		
		list2[0] = new Client("Aaron", 1729);
		list2[1] = new Client("Baker", 2921);
		list2[2] = new Client("Burton", 3911);
		list2[3] = new Client("Dillard", 6552);
		list2[4] = new Client("Jones", 5554);
		list2[5] = new Client("Miller", 9360);
		list2[6] = new Client("Noble", 3335);
		
		mergeLists(list1, list2, result);
		
		int i = 0;
		while (result[i] != null) {
			System.out.println(result[i].getName() + " : " + result[i].getID());
			i ++;
		}
	}
	
	public void mergeLists(Client [] list1, Client [] list2, Client [] result) {
		Client [] list = new Client[list1.length + list2.length];
		int index = 0;
		for (int i = 0; i < list1.length; i ++) {
			list[index] = list1[i];
			index ++;
		}
		for (int j = 0; j < list2.length; j ++) {
			list[index] = list2[j];
			index ++;
		}
		
		mergeSort(list, list.length);
		
		index = 0;
		result[index] = list[0];
		for (int k = 1; k < list.length; k ++) {
			if (result[index].compareClient(list[k]) != 0) {
				index ++;
				result[index] = list[k];
			}
		}
	}
	
	public void mergeSort(Client [] client, int n) {
	    if (n < 2) {
	        return;
	    }
	    int mid = n / 2;
	    Client[] l = new Client[mid];
	    Client[] r = new Client[n - mid];
	 
	    for (int i = 0; i < mid; i++) {
	        l[i] = client[i];
	    }
	    for (int i = mid; i < n; i++) {
	        r[i - mid] = client[i];
	    }
	    mergeSort(l, mid);
	    mergeSort(r, n - mid);
	 
	    merge(client, l, r, mid, n - mid);		
	}
	
	public void merge(Client[] a, Client[] l, Client[] r, int left, int right) {
	    int i = 0, j = 0, k = 0;
	    while (i < left && j < right) {
	    	int result = l[i].compareClient(r[j]);
	        if (result <= 0) {
	            a[k++] = l[i++];
	        }
	        else {
	            a[k++] = r[j++];
	        }
	    }
	    while (i < left) {
	        a[k++] = l[i++];
	    }
	    while (j < right) {
	        a[k++] = r[j++];
	    }
	}
}
