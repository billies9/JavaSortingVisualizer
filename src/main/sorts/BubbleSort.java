package main.sorts;

import main.*;

public class BubbleSort implements Runnable {

	// Initialize sorting array
	private Integer[] toSort;
	// Initialize frame to view sorting method
	// private VisualizerFrame frame;	
	
	public BubbleSort(Integer[] toSort) {
		// Assign to-be-sorted array to scope of class
		this.toSort = toSort;
		// Assign frame to scope of class - Drawing & painting 
//		this.frame = frame;
	}
	
	public void run() {
		// Call BubbleSort.sort()
		this.sort();
		// Reset boolean to 'clean-up' sorting process
		VisualizeSorting.isSorting = false;	
	}
	
	private void sort() {
		// Initialize temp array element
		int temp = 0;
		// Initialize boolean flag
		boolean swapped = false;
		for (int i = 0; i < toSort.length-1; i++) {
			swapped = false;
			for (int j = 1; j < toSort.length-i; j++) {
				if (toSort[j-1] > toSort[j]) {
					// If swap opportunity found, reassign array elements
					temp = toSort[j-1];
					toSort[j-1] = toSort[j];
					toSort[j] = temp;
					// Set boolean flag upon swap found
					swapped = true;
				}
			}
		
			try {
				// Puts thread into wait state for specified period
				Thread.sleep(VisualizeSorting.sleep);
			} catch (InterruptedException e) {
				// Checked exception - informs user of exception and retry
				e.printStackTrace();
			}
			// No swap opportunities found - break from outer loop
			if (!swapped) break;
		}
	}
}
