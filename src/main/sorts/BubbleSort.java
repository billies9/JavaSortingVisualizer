package main.sorts;

import main.*;
import java.util.ArrayList;

public class BubbleSort implements Runnable {

	// Initialize class variables
	private ArrayList<Integer> toSort;
	private SortingFrame frame;
	
	public BubbleSort(SortingFrame frame, ArrayList<Integer> toSort) {
		// Assign instance variables
		this.toSort = toSort;
		this.frame = frame;
	}
	
	public void run() {
		// Start sort
		sort();
		// Reset sorting
		VisualizeSorting.isSorting = false;	
	}
	
	private void sort() {
		// Initialize temp element
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
				// Thread enters wait state - graphing visualization
				Thread.sleep(VisualizeSorting.sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// No swap opportunities found - break from outer loop
			if (!swapped) break;
		}
	}
}
