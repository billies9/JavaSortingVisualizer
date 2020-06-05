package main.sorts;

import main.*;
import java.util.ArrayList;


public class InsertionSort implements Runnable {
	/*
	 * 1) Select first 'unsorted' element
	 * 2) Swap elements to the right until sorted
	 * 3) Advance counter to the right
	 */
	
	private ArrayList<Integer> toSort;
	private SortingFrame frame;
	
	public InsertionSort(SortingFrame frame, ArrayList<Integer> toSort) {
		this.toSort = toSort;
		this.frame = frame;
	}
	
	public void run() {
		this.sort();
		VisualizeSorting.isSorting = false;
	}
	
	private void sort() {
		// Iterate through all elements of Array
		for (int i = 0; i < toSort.size(); i++) {
			// Assign first unsorted value to compare
			int k = toSort.get(i);
			// Assign break between unsorted / sorted arrays
			int j = i - 1;
			
			/* Start with comparison between 1st and 2nd element (j>=0)
			 * jth element must be greater than first unsorted value
			 */
			while (j >= 0 && toSort.get(j) > k) {
				// Move element to right (toSort[j] > k)
				toSort.set(j+1, toSort.get(j));
				// Digress one step to left
				j--;
			}
			// Move previously unsorted value to left - now 'sorted' up to iteration
			toSort.set(j + 1,  k);
			frame.repaint();
			try {
				Thread.sleep(VisualizeSorting.sleep);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
