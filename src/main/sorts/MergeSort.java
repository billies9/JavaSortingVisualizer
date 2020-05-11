package main.sorts;

import main.*;

public class MergeSort implements Runnable {
	
	// Initialize sorting array
	private Integer[] toSort;
	// Initialize frame to view sorting method
	// private VisualizerFrame frame;	
	
	public MergeSort(Integer[] toSort) {
		// Assign to-be-sorted array to scope of class
		this.toSort = toSort;
		// Assign frame to scope of class - Drawing & painting 
		// this.frame = frame;
	}
	
	public void run() {
		this.sort();
		VisualizeSorting.isSorting = false;
	}
	
	public void sort() {
		int mid = (toSort.length - 1) / 2;
		System.out.println(Integer.toString(mid));
	}
}
