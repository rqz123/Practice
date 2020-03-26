import java.util.*;

public class ShuffleTest {

	public static void main(String[] args) {
		// create array list object       
		List<Float> arrlist = new ArrayList();
		
		// populate the list
		arrlist.add((float) 100.0);
		arrlist.add((float) 200.0);
		arrlist.add((float) 300.0);  
		
		System.out.println("Initial collection: "+arrlist);
		
		// shuffle the list
		Collections.shuffle(arrlist);
		
		System.out.println("Final collection after shuffle: "+arrlist);	
	}
}
