package main.sorts;

import main.VisualizeSorting;

public class InsertionSort implements Runnable {
	/*
	 * 1) Select first unsorted element
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
		// Implemented through while loop and temp variable storage
		
	}
}
