package main.sorts;

import main.*;
import java.util.ArrayList;

public class BubbleSort implements Runnable {

	// Initialize sorting array
	private ArrayList<Integer> toSort;
	// Initialize frame to view sorting method
	private SortingFrame frame;
	
	public BubbleSort(SortingFrame frame, ArrayList<Integer> toSort) {
		// Assign to-be-sorted array to scope of class
		this.toSort = toSort;
		// Assign frame to scope of class - Drawing & painting 
		this.frame = frame;
	}
	
	public void run() {
		// Call BubbleSort.sort()
		sort();
		// Reset boolean to 'clean-up' sorting process
		VisualizeSorting.isSorting = false;	
	}
	
	private void sort() {
		// Initialize temp array element
		int temp = 0;
		// Initialize boolean flag
		boolean swapped = false;
		for (int i = 0; i < toSort.size() - 1; i++) {
			swapped = false;
			for (int j = 1; j < toSort.size() - i; j++) {
				if (toSort.get(j-1) > toSort.get(j)) {
					// If swap opportunity found, reassign array elements
					temp = toSort.get(j-1);
					toSort.set(j-1, toSort.get(j));
					toSort.set(j, temp);
					// Set boolean flag upon swap found
					swapped = true;
				}
			}
			frame.repaint();
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
