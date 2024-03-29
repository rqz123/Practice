
public class AllCombination {
	// The method that prints all possible strings of length k. 
	// It is mainly a wrapper over recursive function printAllKLengthRec() 
	static void printAllKLength(char[] set, int k) { 
	    int n = set.length;  
	    printAllKLengthRec(set, "", n, k); 
	} 
	  
	// The main recursive method to print all possible strings of length k 
	static void printAllKLengthRec(char[] set, String prefix, int n, int k) { 
		// Base case: k is 0, print prefix 
	    if (k == 0) { 
	        System.out.println(prefix); 
	        return; 
	    } 
	  
	    // One by one add all characters from set and recursively  
	    // call for k equals to k-1 
	    for (int i = 0; i < n; ++i) {   
	    	if (prefix.indexOf(set[i]) < 0)
	    	{
		        // Next character of input added 
		        String newPrefix = prefix + set[i];  
		        // k is decreased, because we have added a new character 
		        printAllKLengthRec(set, newPrefix, n, k - 1);
	    	}
	    } 
	}
	    
	public static void main(String[] args) {
	    //char[] set = {'a','b', 'c', 'd'}; 
	    char[] set = {'r','b', 'a', 'n'}; 
	    int k = set.length; 
	    printAllKLength(set, k); 
	}
}
