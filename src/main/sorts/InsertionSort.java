package main.sorts;

import main.VisualizeSorting;

public class InsertionSort implements Runnable {
	/*
	 * 1) Select first 'unsorted' element
	 * 2) Swap elements to the right until sorted
	 * 3) Advance counter to the right
	 */
	
	private Integer[] toSort;
	
	public InsertionSort(Integer[] toSort) {
		this.toSort = toSort;
	}
	
	public void run() {
		this.sort();
		VisualizeSorting.isSorting = false;
	}
	
	private void sort() {
		// Iterate through all elements of Array
		for (int i = 0; i < toSort.length; i++) {
			// Assign first unsorted value to compare
			int k = toSort[i];
			// Assign break between unsorted / sorted arrays
			int j = i - 1;
			
			/* Start with comparison between 1st and 2nd element (j>=0)
			 * jth element must be greater than first unsorted value
			 */
			while (j >= 0 && toSort[j] > k) {
				// Move element to right (toSort[j] > k)
				toSort[j + 1] = toSort[j];
				// Digress one step to left
				j--;
			}
			// Move previously unsorted value to left - now 'sorted' up to iteration
			toSort[j + 1] = k;
			try {
				Thread.sleep(VisualizeSorting.sleep);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
