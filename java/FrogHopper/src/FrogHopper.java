/**
 * FrogHopper.java
 * 
 * Consider a frog that comes to the bank of a river.  The frog would like 
 * to cross this river, but it is very wide (more than 40 feet).  Fortunately 
 * for the frog, stepping stones dot the river, and it might be possible to 
 * cross to the other side.  The frog would like to cross the river by 
 * hopping on as few stones as possible, but it must follow this 
 * simple rule:
 *    (1)  The frog's maximum hop length is 70 inches.
 *
 * @author Joseph Zhang
 * @version 1.0
 * @since 10/10/2019
 */

public class FrogHopper
{ 
	final int MAX_70_INCHES = 70;
	final int MAX_50_INCHES = 50;
	
	/**  A two-dimensional array that stores some possible stone combinations.       */
	private final int [][] POSSIBLE_STONE_PATHS = new int[][]
			{
			{0, 40, 65, 120, 140, 155, 180, 195, 210, 240, 260, 300, 320, 370, 400, 420, 480, 500},
			{0, 15, 30, 50, 70, 85, 145, 165, 200, 270, 300, 350, 390, 405, 420, 440, 455, 470, 500},
			{0, 55, 70, 120, 140, 160, 200, 250, 290, 340, 355, 410, 445, 460, 475, 500},
			{0, 70, 110, 130, 150, 170, 190, 210, 240, 270, 300, 330, 350, 370, 390, 410, 430, 450, 480, 500},
			{0, 35, 70, 105, 140, 165, 190, 215, 240, 265, 290, 315, 340, 370, 400, 450, 500},
			
			{0, 30, 65, 100, 125, 150, 165, 180, 210, 225, 240, 265, 300, 315, 330, 345, 360, 380, 400, 415, 430, 445, 460, 475, 500},
			{0, 25, 45, 65, 85, 100, 120, 150, 180, 200, 230, 250, 280, 300, 320, 345, 370, 400, 430, 450, 500},
			{0, 40, 80, 120, 160, 210, 270, 310, 330, 355, 400, 420, 445, 470, 500},
			{0, 20, 60, 75, 100, 115, 130, 190, 220, 240, 255, 310, 355, 400, 420, 460, 475, 500},
			{0, 15, 30, 45, 60, 75, 90, 105, 120, 135, 150, 170, 190, 210, 230, 250, 290, 330, 370, 410, 455, 475, 500},
			
			{0, 25, 55, 80, 110, 135, 165, 190, 220, 250, 275, 305, 330, 360, 385, 415, 440, 470, 500},
			{0, 30, 60, 90, 120, 150, 180, 220, 260, 300, 350, 390, 420, 500},
			{0, 40, 55, 70, 100, 150, 190, 250, 280, 330, 390, 415, 430, 445, 480, 500},
			{0, 20, 70, 90, 140, 160, 210, 230, 250, 270, 320, 350, 365, 380, 395, 410, 425, 440, 500},
			{0, 65, 80, 100, 115, 130, 180, 200, 250, 265, 290, 305, 320, 340, 355, 370, 400, 415, 430, 445, 460, 485, 500},
			
			{0, 25, 65, 85, 105, 155, 170, 185, 200, 260, 290, 315, 365, 405, 450, 470, 485, 500},
			{0, 40, 75, 90, 110, 150, 165, 200, 220, 270, 330, 350, 370, 385, 400, 415, 430, 450, 465, 500},
			{0, 15, 30, 50, 70, 90, 105, 120, 135, 160, 175, 200, 215, 230, 255, 270, 290, 305, 320, 335, 350, 400, 430, 450, 500},
			{0, 15, 30, 50, 70, 90, 105, 120, 135, 160, 175, 200, 215, 230, 255, 270, 290, 305, 320, 335, 350, 400, 420, 500},
			{0, 15, 30, 45, 70, 85, 115, 130, 160, 185, 215, 230, 245, 275, 300, 320, 345, 365, 390, 400, 415, 430, 460, 475, 500}
			};

	private final int[] TOTAL_HOPS_ANSWER = new int[] 
			{
			 12, 11, 12, 10, 11, 
			 10, 11,  5, 11, 11, 
			 12, 10,  7, 11, 11, 
			 11,  6, 10,  8,  9
			};

	/**  Used to store a particular array of stones.      */
	private int [] stones;
	
	/**  An array to be used in parallel with the stones, indicating where the frog landed.      */
	private boolean [] hops;

	/** 
	 *  Creates a FrogHopper object.
	 *  Nothing to assign here, since the stones and hops arrays are assigned later.
	 */
	public FrogHopper ( )
	{
	}

	/**
	 *  The main method, to run all of the stone combinations.
	 */
	public static void main(String[] args)
	{
		FrogHopper hop = new FrogHopper();
		hop.runStoneCombinations();
    } 
	
	/** 
	 *  A loop to run the combinations of stones, taken from the 2D array,
	 *  POSSIBLE_STONE_PATHS.  The stones array is loaded, then the hops
	 *  array is created and a path is determined.  The original path,
	 *  along with the hopped-on stones, is displayed.  The path number
	 *  is printed, along with the total number of hops.
	 */
	public void runStoneCombinations ( )
	{
		System.out.println("\n\n\n");
		for(int i = 0; i < POSSIBLE_STONE_PATHS.length; i++)
		{
			stones = POSSIBLE_STONE_PATHS[i];
			hops = new boolean[stones.length];
			System.out.println("\n   Path: " + (i+1));
			printStones(stones);
			choosePath(stones, hops);
			printPath(stones, hops);
			printNumberOfhops(hops, i);
		}
		System.out.println("\n\n\n");
	}

	/** 
	 *  Chooses the stones that should be hoped on by the frog.
	 *  @param rocks    The array of int, indicating the positions
	 *                  of the stones.
	 *  @param hops    The array of boolean, indicating the positions
	 *                  where the frog lands/hops from.
	 */
	public void choosePath(int [] rocks, boolean [] hops)
	{
		//  Write this method.  Consider the simplified version:  The frog can
		//  hop a maximum of 70 inches on any hop (only one rule).
		//findMinimumJumpsDP(rocks, hops);
		
		//printPath(stones, hops);
		//printNumberOfhops(hops);

		//for (int i = 0; i < hops.length; i ++)
		//	hops[i] = false;
		
		// Consider the two conditions. 
		findMinimumJumpsRule(rocks, hops);
		//printPath(stones, hops);
		//printNumberOfhops(hops);
		
		//findMinimumJumpsDP2(rocks, hops);
		//findMinimumJumpsDP3(rocks, hops);
	}
	
	/** 
	 *  Prints the values from the array indicating the position
	 *  of the stones.
	 *  @param rocks    The array of int, indicating the positions
	 *                  of the stones.
	 */
	public void printStones(int [] rocks)
	{
		for(int j = 0; j < rocks.length; j++)			//  This method is complete.
		{
			System.out.printf("%4d", rocks[j]);
		}
		System.out.println();
	}
	
	/** 
	 *  Prints the values from the array of stones, but only the
	 *  values that the frog hops on are printed.
	 *  @param rocks    The array of int, indicating the positions
	 *                  of the stones.
	 *  @param hops    The array of boolean, indicating the positions
	 *                  where the frog lands/hops from.
	 */
	public void printPath(int [] rocks, boolean [] hops)
	{
		/**
		 * Print the path of the frog.
		 */
		for (int i = 0; i < rocks.length; i ++) {
			if (hops[i])
				System.out.printf("%4d", rocks[i]);
			else
				System.out.printf("    ");
		}
		System.out.println();
	}
	
	/** 
	 *  Calculates and prints the number of hops.
	 *  @param hops    The array of boolean, indicating the positions
	 *                  where the frog lands/hops from.
	 */
	public void printNumberOfhops(boolean [] hops, int index)
	{
		int hopCount = 0;											//  Count the hops and
		
		for (int i = 0; i < hops.length; i ++) {
			if (hops[i])
				hopCount ++;
		}
		System.out.println("   TOTAL HOPS: " + (hopCount - 1));		//  print them.
		if (hopCount -1 != TOTAL_HOPS_ANSWER[index])
			System.out.println("   WRONG ANSWER! THE RIGHT ANSWER IS " + TOTAL_HOPS_ANSWER[index]);
	}
	
	/** 
	 *  Find the minimum jumps by Dynamic Programming algorithm  
	 *  @param rocks    The array of int, indicating the positions
	 *                  of the stones.
	 *  @param hops    The array of boolean, indicating the positions
	 *                  where the frog lands/hops from.
	 */
	private void findMinimumJumpsDP(int [] rocks, boolean [] hops)
	{
		/**
		 * Learning DP video
		 * https://www.youtube.com/watch?v=jH_5ypQggWg&list=WL&index=18&t=0s
		 */
		
		// Showing index for debugging purpose
		for (int i = 0; i < rocks.length; i ++) {
			System.out.printf("[%02d]", i);
		}
		System.out.println();
		
		/**
		 * Create another array with the inches between stones 
		 */
		int [] inches = new int[rocks.length - 1];
		
		for (int i = 0; i < rocks.length - 1; i ++) {
			inches[i] = rocks[i + 1] - rocks[i];
		}
		
		System.out.print("    ");
		for (int i = 0; i < rocks.length - 1; i ++) {
			System.out.printf("%4d", inches[i]);
		}
		System.out.println();
		
		/**
		 * Condition (1) - the frog's maximum jump length is 70 inches. 
		 * Create maximum steps if the maximum jump is 70 inches
		 */
		int [] steps = new int[inches.length + 1];
		
		for (int i = 0; i < inches.length; i ++) {
			int total_inches = 0;
			for (int j = i; j < inches.length; j ++) {
				total_inches += inches[j];
				if (total_inches <= MAX_70_INCHES) {
					steps[i] ++;	// how far could jump
				}
				else {
					break;
				}
			}
		}
		
		for (int i = 0; i < steps.length; i ++) {
			System.out.printf("%4d", steps[i]);
		}
		System.out.println();
		
		/*
		 * Minimum number of jumps to reach end, it's in jumps[n-1]
		 */
		int jumps[] = new int[steps.length]; 
		int path[] = new int[steps.length];
			
		jumps[0] = 0;	// always starting from 0 position
		
		for (int i = 1; i < steps.length; i ++) 
		{ 
			jumps[i] = Integer.MAX_VALUE;
			
			for (int j = 0; j < i; j ++) 
			{ 
				if (i <= j + steps[j] && jumps[j] != Integer.MAX_VALUE) 
				{ 
					int min = jumps[i];
					
					jumps[i] = Math.min(jumps[i], jumps[j] + 1);
					
					if (min != jumps[i])
						path[i] = j;
					
					break; 
				} 
			} 
		}
	
		for (int i = 0; i < jumps.length; i ++) {
			if (jumps[i] != Integer.MAX_VALUE)
				System.out.printf("%4d", jumps[i]);
		}
		System.out.println();
		
		/*
		 * Print path for reference
		 */
		for (int i = 1; i < path.length; i ++) {
			System.out.printf("%4d", path[i]);
		}
		System.out.println();
		
		/*
		 * Indicating the positions where the frog lands/hops from
		 * Either we can use path array or jumps array (using jumps here)
		 */
		int k = 0;
		for (; k < jumps.length; k ++) {
			if (k < jumps.length - 1) {
				if (jumps[k] != jumps[k + 1]) {
					hops[k] = true;
				}
				
				if (jumps[k + 1] == Integer.MAX_VALUE)
					break;
			}
			else {
				if (jumps[k - 1] != jumps[k]) {
					hops[k] = true;
				}
			}
		}
		if (k >= jumps.length)
			hops[hops.length - 1] = true;	// always lands at end
	}	

	/** 
	 *  Find the minimum jumps by Dynamic Programming algorithm  
	 *  @param rocks    The array of int, indicating the positions
	 *                  of the stones.
	 *  @param hops    The array of boolean, indicating the positions
	 *                  where the frog lands/hops from.
	 */
	private void findMinimumJumpsRule(int [] rocks, boolean [] hops)
	{
		// Showing index for debugging purpose
		for (int i = 0; i < rocks.length; i ++) {
			System.out.printf("[%02d]", i);
		}
		System.out.println();
		
		/**
		 * Create another array with the inches between stones 
		 */
		int [] inches = new int[rocks.length - 1];
		
		for (int i = 0; i < rocks.length - 1; i ++) {
			inches[i] = rocks[i + 1] - rocks[i];
		}
		
		System.out.print("    ");
		for (int i = 0; i < rocks.length - 1; i ++) {
			System.out.printf("%4d", inches[i]);
		}
		System.out.println();

		/**
		 * Calculate maximum jumping inches based on the following conditionals:
		 * 1. The frog's maximum jump length is 70 inches. 
		 * 2. The frog cannot make consecutive jumps of 50 inches or more.  
		 */
		boolean bBigJump = true;	// try to have a big jump in beginning
		boolean bTryAgain = false;
		hops[0] = true;	// starting position is landed always
		
		int i = 0;
		while (i < inches.length) {
			int j;
			int total;
			
			if (bBigJump) {
				if (!IsBestChoose(inches, i, (MAX_70_INCHES + 1))) {
					bBigJump = false;
					continue;
				}
				else {
					j = getPosWithMaxInches(inches, i, (MAX_70_INCHES + 1));
					total = getTotalInches(inches, i, j);
					
					if (j <= inches.length - 1) {
						if (inches[j] >= MAX_50_INCHES) {
							int jj = getPosWithMaxInches(inches, i, MAX_50_INCHES);
							total = getTotalInches(inches, i, jj);
							
							if (jj == i) {	// special case, staying same position with normal inches
								j = jj + 1;	// keep big move (>=50)
								total = getTotalInches(inches, i, j);
								
								if (j >= inches.length) {
									break;
								}
								else if (inches[j] > MAX_50_INCHES) {
									hops[j] = true;	// rock position
									break;	// cannot move anymore
								}
							}
							else {
								bBigJump = false;
								bTryAgain = true;
							}
						}
					}
				}
			}
			else {
				j = getPosWithMaxInches(inches, i, MAX_50_INCHES);
				total = getTotalInches(inches, i, j);
				if (j == i) {	// special case, staying with same position
					hops[j] = true;
					break;	// cannot move anymore
				}
				if (total >= MAX_50_INCHES) {
					bBigJump = true;
				}
			}
			
			if (!bTryAgain) {
				if ((bBigJump && total < MAX_50_INCHES) || (!bBigJump && total >= MAX_50_INCHES)) {
					System.out.println("ERROR situation, please debug!");
					bBigJump = total < MAX_50_INCHES ? false : true;
				}
				
				bBigJump = bBigJump ? false : true;
				hops[j] = true;	// rock position
				i = j;	// next distance position
			}
			else {
				bTryAgain = false;
			}
		}
	}
	
	/**
	 * Is it the best choose to have maximum jump? 
	 * @param inches	The array of int, indicating the distances
	 * 					of the stones.
	 * @param begin		The beginning of searching position
	 * @param max		The maximum distances allowed 
	 */
	private boolean IsBestChoose(int [] inches, int begin, int max)
	{
	/*
		if (begin + 1 < inches.length && inches[begin] < MAX_50_INCHES) {
			int total_1 = 0;
			int total_2 = 0;
			
			for (int i = begin; i < inches.length; i ++) {
				if (total_1 + inches[i] < max) {
					total_1 += inches[i];
				}
				else {
					break;
				}
			}
			
			for (int i = begin + 1; i < inches.length; i ++) {
				if (total_2 + inches[i] < max) {
					total_2 += inches[i];
				}
				else {
					break;
				}
			}

			if (total_2 > total_1) {
				return false;
			}
		}
	*/
		return true;
	}
	
	/**
	 * Get position with the specified maximum inches 
	 * @param inches	The array of int, indicating the distances
	 * 					of the stones.
	 * @param begin		The beginning of searching position
	 * @param max		The maximum distances allowed 
	 */
	private int getPosWithMaxInches(int [] inches, int begin, int max)
	{
		int total = 0;
		int i = begin;
		
		for (; i < inches.length; i ++) {
			if (total + inches[i] < max) {
				total += inches[i];
			}
			else {
				break;
			}
		}
		
		return i;
	}
	
	/**
	 * Get total inches between two positions
	 * @param inches	The array of int, indicating the distances
	 * 					of the stones.
	 * @param begin		The beginning of searching position
	 * @param end		The ending positoin 
	 */
	private int getTotalInches(int [] inches, int begin, int end)
	{
		int total = 0;
		
		for (int i = begin; i < end; i ++) {
			total += inches[i];
		}
		
		return total;		
	}

	/** 
	 *  Find the minimum jumps by Dynamic Programming algorithm  
	 *  @param rocks    The array of int, indicating the positions
	 *                  of the stones.
	 *  @param hops    The array of boolean, indicating the positions
	 *                  where the frog lands/hops from.
	 */
	private void findMinimumJumpsDP2(int [] rocks, boolean [] hops)
	{
		// Showing index for debugging purpose
		for (int i = 0; i < rocks.length; i ++) {
			System.out.printf("[%02d]", i);
		}
		System.out.println();
		
		/**
		 * Calculate maximum jumping inches based on the following conditionals:
		 * 1. The frog's maximum jump length is 70 inches. 
		 * 2. The frog cannot make consecutive jumps of 50 inches or more.  
		 */
		int [] steps = new int[rocks.length];
		int [] inches = new int[rocks.length];
		
		int max_jump[] = {MAX_50_INCHES, MAX_70_INCHES + 1};
		int tag = 1;	// try large jump first	
		
		int p = 0;
		while (p < rocks.length - 1) {
			for (int j = p + 1; j < rocks.length; j ++) {
				int total_inches = rocks[j] - rocks[p];
				
				if (total_inches < max_jump[tag]) {
					steps[p] ++;	// how far could jump
					inches[p] = total_inches;
					
					if (p >= rocks.length - 2 && j >= rocks.length - 1)
						p ++;
				}
				else if (inches[p] >= MAX_50_INCHES) {
					if (rocks[j] - rocks[j - 1] >= MAX_50_INCHES) {
						steps[p] = 0;	// reset
						inches[p] = 0;
					}
					else
						p ++;
					
					tag = 0;
					break;
				}
				else {
					if (tag == 1 && inches[p] >= MAX_50_INCHES)
						tag = 0;
					else
						tag = 1;
					p ++;
					break;
				}
			}
		}
		
		for (int i = 0; i < rocks.length - 1; i ++) {
			System.out.printf("%4d", inches[i]);
		}
		System.out.println();
		
		for (int i = 0; i < steps.length; i ++) {
			System.out.printf("%4d", steps[i]);
		}
		System.out.println();
		
		/*
		 * Minimum number of jumps to reach end, it's in jumps[n-1]
		 */
		int jumps[] = new int[steps.length]; 
		int path[] = new int[steps.length];
			
		jumps[0] = 0;	// always starting from 0 position
		
		for (int i = 1; i < steps.length; i ++) 
		{ 
			jumps[i] = Integer.MAX_VALUE;
			
			for (int j = 0; j < i; j ++) 
			{ 
				//System.out.println("target stone(" + rocks[i] + "), jumping from stone(" + rocks[j] + ")");
				
				if (i <= j + steps[j] && jumps[j] != Integer.MAX_VALUE)
				{ 
					int min = jumps[i];
					
					jumps[i] = Math.min(jumps[i], jumps[j] + 1);
					
					// A potential jumping point
					if (min != jumps[i]) {
						path[i] = j;
						//System.out.println("a potential jump point(" + rocks[j] + ")");
					}
					
					break; 
				} 
			} 
		}
	
		for (int i = 0; i < jumps.length; i ++) {
			if (jumps[i] != Integer.MAX_VALUE)
				System.out.printf("%4d", jumps[i]);
		}
		System.out.println();
		
		/*
		 * Print path for reference
		 */
		for (int i = 1; i < path.length; i ++) {
			System.out.printf("%4d", path[i]);
		}
		System.out.println();
		
		/*
		 * Indicating the positions where the frog lands/hops from
		 * Either we can use path array or jumps array (using jumps here)
		 */
		int k = 0;
		for (; k < jumps.length; k ++) {
			if (k < jumps.length - 1) {
				if (jumps[k] != jumps[k + 1]) {
					hops[k] = true;
				}
				
				if (jumps[k + 1] == Integer.MAX_VALUE)
					break;
			}
			else {
				if (jumps[k - 1] != jumps[k]) {
					hops[k] = true;
				}
			}
		}
		if (k >= jumps.length)
			hops[hops.length - 1] = true;	// always lands at end
	}	
	
	private void findMinimumJumpsDP3(int [] rocks, boolean [] hops)
	{
		/**
		 * Create another array with the inches between stones 
		 */
		int [] inches = new int[rocks.length - 1];
		
		for (int i = 0; i < rocks.length - 1; i ++) {
			inches[i] = rocks[i + 1] - rocks[i];
		}
		
		for (int i = 0; i < rocks.length - 1; i ++) {
			System.out.printf("%4d", inches[i]);
		}
		System.out.println();
		
		/**
		 * Condition (1) - the frog's maximum jump length is 70 inches. 
		 * Create maximum steps if the maximum jump is 70 inches
		 */
		int [] steps = new int[inches.length + 1];
		
		for (int i = 0; i < inches.length; i ++) {
			int total_inches = 0;
			for (int j = i; j < inches.length; j ++) {
				total_inches += inches[j];
				if (total_inches <= MAX_70_INCHES) {
					steps[i] ++;	// how far could jump
				}
				else {
					break;
				}
			}
		}
		
		for (int i = 0; i < steps.length; i ++) {
			System.out.printf("%4d", steps[i]);
		}
		System.out.println();
		
		/*
		 * Minimum number of jumps to reach end, it's in jumps[n-1]
		 */
		int jumps[] = new int[steps.length]; 
		//int path[] = new int[steps.length];
			
		/*
		 * Start from the second element, move from right to left and construct 
		 * the jumps[] array where jumps[i] represents minimum number of 
	     * jumps needed to reach steps[m-1] from steps[i] 
		 */
		int n = steps.length;
		int min;
		
	    // Minimum number of jumps needed to reach last element 
		// from last elements itself is always 0 
	    jumps[n - 1] = 0; 		
	    
	    for (int i = n - 2; i >= 0; i --) 
	    { 
	    	int len = (n - 1) - i;
	    	
	        // If steps[i] is 0 then steps[n-1] can't be reached from here 
	        if (steps[i] == 0) 
	            jumps[i] = Integer.MAX_VALUE; 
	  
	        // If we can directly reach to the end point from here then 
	        // jumps[i] is 1 
	        else if (steps[i] >= len) 
	            jumps[i] = 1; 
	  
	        // Otherwise, to find out the minimum number of jumps 
	        // needed to reach steps[n-1], check all the points 
	        // reachable from here and jumps[] value for those points 
	        else
	        {  
	            // initialize min value 
	            min = Integer.MAX_VALUE;  
	  
	            // following loop checks  
	            // with all reachable points  
	            // and takes the minimum 
	            for (int j = i + 1; j < n &&  
	                     j <= steps[i] + i; j++) 
	            { 
	                if (min > jumps[j]) 
	                    min = jumps[j]; 
	            }  
	  
	            // Handle overflow  
	            if (min != Integer.MAX_VALUE) 
	                jumps[i] = min + 1; 
	            else
	                jumps[i] = min; // or Integer.MAX_VALUE 
	        } 
	    }
	    
	    System.out.println(jumps[0]);
	}
} 
