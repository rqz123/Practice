public class MinimumJumpsRecursion {

    private int findJumps(int[] steps, int start, boolean[] jpos) {
    	//System.out.println("findJumps(start index = " + start + ")");
    	
        /*
         * Check if reached to the end
         */
        if (start == steps.length - 1) {
        	System.out.println("	reaching to end");
        	//System.out.println("return 0");
        	return 0;
        }
        
        /*
         * Check if no movement case
         */
        if (steps[start] == 0) {
            //System.out.println("	cannot move further");
            return Integer.MAX_VALUE;
        }
        
        int remainingLength = steps.length - start;

    	//System.out.println("	remaining length = " + remainingLength + ", steps[start] = " + steps[start]);

    	/*
    	 * Check if remaining length has less than current max steps
    	 */
        if (remainingLength <= steps[start]) {
        	//System.out.println("	no further recursion is required");
        	//System.out.println("return 1");
            return 1;
        }
        
        /*
         * Try each case from 1 to max steps
         */
        int jumps = Integer.MAX_VALUE;
        
        for (int i = 1; i <= steps[start]; i ++) {
        	//System.out.println("		loop i = " + i);
        	
            int ret = findJumps(steps, start + i, jpos);
            if(ret != Integer.MAX_VALUE) {
                //System.out.println("		comparing: temp = " + (ret+1) + ", jumps = " + jumps);
            	jumps = Math.min(jumps, 1 + ret);
            }
        }
        
        System.out.println("return jumps = " + jumps + " at index = " + start);
        
        return jumps;
    }

    public static void main(String[] args) 
    {
        MinimumJumpsRecursion m = new MinimumJumpsRecursion();
        
        //int steps[] = {1,3,4,8,9};
        /*
         *         0  40  65 120 140 155 180 195 210 240 260 300 320 370 400 420 480 500
   		 *            40  25  55  20  15  25  15  15  30  20  40  20  50  30  20  60  20
		 *         2   1   1   3   4   3   3   3   2   2   2   2   1   2   1   1   1
         *         0      65 120         180         240     300     370     420 480 500
         */
        int steps[] = {2   ,1   ,1   ,3   ,4   ,3   ,3   ,3   ,2   ,2   ,2   ,2   ,1   ,2   ,1   ,1   ,1};
        int jpos[] = new int[steps.length];
        
        long startTime  = System.currentTimeMillis();
        int mini = m.findJumps(steps, 0, jpos);
        
        for (int i = 0; i < steps.length; i ++) {
        	System.out.printf("%4d", steps[i]);
        }
        System.out.println();
        for (int i = 0; i < jpos.length; i ++) {
        	System.out.printf("%4d", jpos[i]);
        }
        System.out.println();
        
        System.out.println("Minimum Jumps required: " + mini);
        long end  = System.currentTimeMillis();
        System.out.println("Time taken: " + (end-startTime) + " milliseconds");
    }
}
