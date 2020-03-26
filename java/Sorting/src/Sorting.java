
public class Sorting {
	/* 
	 * A utility function to print array of size n 
	 */
	static void Print(int arr[]) 
	{ 
		int n = arr.length; 
		for (int i=0; i<n; ++i) 
			System.out.print(arr[i] + " "); 
		System.out.println(); 
	} 

	/*
	 * Bubble Sort
	 */
	void Bubble(int arr[]) 
	{ 
		int n = arr.length; 
		for (int i = 0; i < n - 1; i ++) {
			int m = n - i;
			//System.out.println("i = " + i + ", m = " + m);
			for (int j = 0; j < m - 1; j ++) {
				if (arr[j] > arr[j+1]) { 
					// swap arr[j+1] and arr[i] 
					int temp = arr[j]; 
					arr[j] = arr[j+1]; 
					arr[j+1] = temp; 
				} 
			}
		}
	} 
	
	/*
	 * Selection Sort
	 */
    void Selection(int arr[]) 
    { 
        int n = arr.length; 
  
        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n-1; i++) 
        { 
            // Find the minimum element in unsorted array 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arr[j] < arr[min_idx]) 
                    min_idx = j; 
  
            // Swap the found minimum element with the first element 
            if (min_idx != i) {
	            int temp = arr[min_idx]; 
	            arr[min_idx] = arr[i]; 
	            arr[i] = temp; 
            }
        } 
    } 
    
    /*
     * Insertion Sort
     */
    void Insertion(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
             * greater than key, to one position ahead 
             * of their current position 
             */
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        } 
    } 
    
    /* 
     * Merge Sort 
     */
    class MergeSort 
    { 
    	/*
    	 * Merges two subarrays of arr[]. 
    	 * First subarray is arr[l..m] 
    	 * Second subarray is arr[m+1..r] 
    	 */
    	void merge(int arr[], int l, int m, int r) 
    	{ 
    		// Find sizes of two subarrays to be merged 
    		int n1 = m - l + 1; 
    		int n2 = r - m; 

    		/* Create temp arrays */
    		int L[] = new int [n1]; 
    		int R[] = new int [n2]; 

    		/*Copy data to temp arrays*/
    		for (int i=0; i<n1; ++i) 
    			L[i] = arr[l + i]; 
    		for (int j=0; j<n2; ++j) 
    			R[j] = arr[m + 1+ j]; 


    		/* Merge the temp arrays */

    		// Initial indexes of first and second subarrays 
    		int i = 0, j = 0; 

    		// Initial index of merged subarry array 
    		int k = l; 
    		while (i < n1 && j < n2) 
    		{ 
    			if (L[i] <= R[j]) 
    			{ 
    				arr[k] = L[i]; 
    				i++; 
    			} 
    			else
    			{ 
    				arr[k] = R[j]; 
    				j++; 
    			} 
    			k++; 
    		} 

    		/* Copy remaining elements of L[] if any */
    		while (i < n1) 
    		{ 
    			arr[k] = L[i]; 
    			i++; 
    			k++; 
    		} 

    		/* Copy remaining elements of R[] if any */
    		while (j < n2) 
    		{ 
    			arr[k] = R[j]; 
    			j++; 
    			k++; 
    		} 
    	} 

    	// Main function that sorts arr[l..r] using 
    	// merge() 
    	void sort(int arr[], int l, int r) 
    	{ 
    		if (l < r) 
    		{ 
    			// Find the middle point 
    			int m = (l+r)/2; 

    			// Sort first and second halves 
    			sort(arr, l, m); 
    			sort(arr , m+1, r); 

    			// Merge the sorted halves 
    			merge(arr, l, m, r); 
    		} 
    	} 
    	
    	// Driver method 
    	public void Merge(int[] arr) 
    	{ 
    		sort(arr, 0, arr.length-1); 
    	} 
    } 
	
	public void Quick(int[] data, int len) {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sorting sort = new Sorting();
		int data[] = {7, 3, 6, 10, 5, 2, 9, 8, 1, 4};
		
		long start = System.currentTimeMillis();
		//sort.Bubble(data);
		//sort.Selection(data);
		//sort.Insertion(data);
		MergeSort sort2 = sort.new MergeSort();
		sort2.Merge(data);
		//sort.Quick(data, 9);
		long end = System.currentTimeMillis();
		Print(data);
		System.out.println("Total run-time is " + (end - start) / 1000f + " seconds");
	}
}
