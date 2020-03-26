import java.util.HashSet;

class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        HashSet<Character> set = new HashSet<>();
        
        int i = 0;
        int j = 0;
        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
                maxLength = Math.max(maxLength, j - i);
            	System.out.println(s + "(+): " + s.substring(j) + " <" + j + "> "+ set.toString() + ", max length = " + maxLength);
            } else {
                set.remove(s.charAt(i));
                i++;
            	System.out.println(s + "(-): " + s.substring(j) + " <" + i + "> "+ set.toString());
            }
        }
        return maxLength;
    }
}

class Solution4 { 
    public double findMedianSortedArrays(int[] nums1, int[] nums2) { 
        int len = nums1.length + nums2.length; 
        if (len % 2 == 1) { 
            return findKth(nums1, 0, nums2, 0, len / 2 + 1); 
        } else { 
            return (findKth(nums1, 0, nums2, 0, len / 2) + findKth(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0; 
        } 
    } 
     
    public int findKth(int[] A, int startA, int[] B, int startB, int k) { 
        if (startA >= A.length) { 
            return B[startB + k - 1]; 
        } 
         
        if (startB >= B.length) { 
            return A[startA + k - 1]; 
        } 
         
        if (k == 1) { 
            return Math.min(A[startA], B[startB]); 
        } 
         
        int indexA = startA + k / 2 - 1; 
        int indexB = startB + k / 2 - 1; 
        int keyA = indexA < A.length ? A[indexA] : Integer.MAX_VALUE; 
        int keyB = indexB < B.length ? B[indexB] : Integer.MAX_VALUE; 
         
        if (keyA < keyB) { 
            return findKth(A, startA + k / 2, B, startB, k - k / 2); 
        } else { 
            return findKth(A, startA, B, startB + k / 2, k - k / 2); 
        } 
    } 
} 

class Solution70 {
	public int climbStairs(int n) {
		if (n <= 1) return 1;
		int[] dp = new int[n];
		dp[0] = 1; dp[1] = 2;
	
		for (int i = 2; i < n; ++i) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n - 1];
	}	
}

public class LeetCode {
	public static void main(String[] args) {
		// Problem 3
		//String s3_test = "pwwkew";
		String s3_test = "abcabca";
		
		Solution3 s3 = new Solution3();
		int ret3 = s3.lengthOfLongestSubstring(s3_test);
		System.out.println(ret3);
		
		// Problem 4
		int num1[] = {1, 2};
		int num2[] = {4, 5, 7};
		
		Solution4 s4 = new Solution4();
		double ret4 = s4.findMedianSortedArrays(num1, num2);
		System.out.println(ret4);
	
		Solution70 s70 = new Solution70();
		int ret5 = s70.climbStairs(4);
		System.out.println(ret5);
	}
}
