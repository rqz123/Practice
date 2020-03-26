/**
 * FrogHopperSimple.java
 * 
 * Consider a frog that comes to the bank of a river.  The frog would like 
 * to cross this river, but it is very wide (more than 40 feet).  Fortunately 
 * for the frog, stepping stones dot the river, and it might be possible to 
 * cross to the other side.  The frog would like to cross the river by 
 * hopping on as few stones as possible, but it must follow this 
 * simple rule:
 *    (1)  The frog's maximum hop length is 70 inches.
 *
 * @author Your Name Here
 * @version 1.0
 * @since 10/10/2019
 */

public class FrogHopperSimple 
{ 
	final int MAX_INCHES = 70;
	
	/**  A two-dimensional array that stores some possible stone combinations.       */
	private final int [][] POSSIBLE_STONE_PATHS = new int[][]
			{
			 {0, 40, 65, 120, 140, 155, 180, 195, 210, 240, 260, 300, 320, 370, 400, 420, 480, 500},
			 {0, 15, 30, 50, 70, 85, 145, 165, 200, 270, 300, 350, 390, 405, 420, 440, 455, 470, 500},
			 {0, 55, 70, 120, 140, 160, 200, 250, 290, 340, 355, 410, 445, 460, 475, 500},
				/*
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
			*/
			};

	/**  Used to store a particular array of stones.      */
	private int [] stones;
	
	/**  An array to be used in parallel with the stones, indicating where the frog landed.      */
	private boolean [] hops;

	/** 
	 *  Creates a FrogHopperSimple object.
	 *  Nothing to assign here, since the stones and hops arrays are assigned later.
	 */
	public FrogHopperSimple ( )
	{
	}

	/**
	 *  The main method, to run all of the stone combinations.
	 */
	public static void main(String[] args)
	{
		FrogHopperSimple hop = new FrogHopperSimple();
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
			System.out.println("\n   Path: " + i);
			printStones(stones);
			choosePath(stones, hops);
			printPath(stones, hops);
			printNumberOfhops(hops);
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
		
		System.out.printf("    ");
		for (int i = 0; i < rocks.length - 1; i ++) {
			System.out.printf("%4d", inches[i]);
		}
		System.out.println();
		
		findMinimumJumpsRule(inches, hops);
	}
	
	/**
	 * Get position with the specified maximum inches 
	 * @param inches	The array of int, indicating the distances
	 * 					of the stones.
	 * @param begin		The beginning of searching position
	 * @param max		The maximum distances allowed 
	 */
	public int getPosWithMaxInches(int [] inches, int begin, int max)
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
	 * Find the minimum jump path by rule
	 *  @param rocks    The array of int, indicating the positions
	 *                  of the stones.
	 *  @param hops    The array of boolean, indicating the positions
	 *                  where the frog lands/hops from.
	 */
	private void findMinimumJumpsRule(int [] inches, boolean [] hops)
	{
		/**
		 * Calculate maximum jumping inches based on the following conditionals:
		 * 1. The frog's maximum jump length is 70 inches. 
		 * 2. The frog cannot make consecutive jumps of 50 inches or more.  
		 */
		boolean bBigJump = true;	// try to have a big jump in beginning
		boolean bTryAgain = false;
		hops[0] = true;	// starting position is landed always
		
		for (int i = 0; i < inches.length;) {
			int j;
			
			if (bBigJump) {
				j = getPosWithMaxInches(inches, i, 71);
				if (j <= inches.length - 1) {
					if (inches[j] >= 50) {
						int jj = getPosWithMaxInches(inches, i, 50);
						
						if (jj == i) {	// special case, staying same position with normal inches
							j = jj + 1;	// keep big move (>=50)
							if (j >= inches.length)
							{
								break;
							}
							else if (inches[j] > 50)
							{
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
			else {
				j = getPosWithMaxInches(inches, i, 50);
			}
			
			if (!bTryAgain) {
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
	public void printNumberOfhops(boolean [] hops)
	{
		int hopCount = 0;											//  Count the hops and
		
		for (int i = 0; i < hops.length; i ++) {
			if (hops[i])
				hopCount ++;
		}
		System.out.println("   TOTAL HOPS: " + hopCount);			//  print them.
	}
} 
