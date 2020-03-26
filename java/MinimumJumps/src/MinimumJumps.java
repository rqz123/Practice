// JAVA Code for Minimum number of jumps to reach end 
class MinimumJumps { 

		private int findJumps(int[] steps, boolean[] jpos) 
		{ 
			int jumps[] = new int[steps.length]; // jumps[n-1] will hold the result
			
			if (steps.length == 0 || steps[0] == 0) 
				return Integer.MAX_VALUE; // if first element is 0, end cannot be reached 
			
			jumps[0] = 0; 
			
			/*
			 * Find the minimum number of jumps to reach steps[i]
			 * from steps[0], and assign this value to jumps[i] 
			 */
			for (int i = 1; i < steps.length; i ++) 
			{ 
				jumps[i] = Integer.MAX_VALUE; 
				
				for (int j = 0; j < i; j ++) 
				{ 
					if (i <= j + steps[j] && jumps[j] != Integer.MAX_VALUE) 
					{ 
						jumps[i] = Math.min(jumps[i], jumps[j] + 1); 
						break; 
					} 
				} 
			} 
			
			/*
			 * Find the jumping point
			 */
			int jstep = 0;
					
			for (int i = 1; i < jumps.length; i ++) {
				if (jstep != jumps[i]) {
					jpos[i - 1] = true;
					jstep = jumps[i];
				}
			}
			jpos[jumps.length - 1] = true;
			
			return jumps[steps.length - 1]; 
	} 
	
	public static void main(String[] args) 
	{ 
        MinimumJumps m = new MinimumJumps();
        
        //int steps[] = {1,3,4,8,9};
        /*
         *         0  40  65 120 140 155 180 195 210 240 260 300 320 370 400 420 480 500
   		 *            40  25  55  20  15  25  15  15  30  20  40  20  50  30  20  60  20
		 *         2   1   1   3   4   3   3   3   2   2   2   2   1   2   1   1   1
         *         0      65 120         180         240     300     370     420 480 500
         */
        int steps[] = {2   ,1   ,1   ,3   ,4   ,3   ,3   ,3   ,2   ,2   ,2   ,2   ,1   ,2   ,1   ,1   ,1};
        boolean jpos[] = new boolean[steps.length];
        
        long startTime  = System.currentTimeMillis();
        int mini = m.findJumps(steps, jpos);
        
        for (int i = 0; i < steps.length; i ++) {
        	System.out.printf("%4d", steps[i]);
        }
        System.out.println();
        
        for (int i = 0; i < jpos.length; i ++) {
        	if (jpos[i])
        		System.out.printf("%4d", steps[i]);
        	else
        		System.out.printf("    ");
        }
        System.out.println();
        
        System.out.println("Minimum Jumps required: " + mini);
        long end  = System.currentTimeMillis();
        System.out.println("Time taken: " + (end-startTime) + " milliseconds");
	} 
} 
