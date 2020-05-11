package main.sorts;

import main.*;

public class MergeSort implements Runnable {
	
	public void run() {
		Integer[] toSort = VisualizeSorting.toSort;
		sort(toSort);
		VisualizeSorting.isSorting = false;
	}
	
	public void sort(Integer[] x) {
		sort(x, 0, x.length-1);
	}
	
	private void sort(Integer[] x, int first, int last) {
		int mid, right, left;
		int temp;
		
		if (first >= last) return;
		
		mid = (first + last) / 2;
		
		// Begin recursions to split arrays
		sort(x, first, mid);
		sort(x, mid+1, last);
		
		// -----------------------------------------------
		// Begin iterative process of merging split arrays
		left = first;
		right = mid+1;
		// Skip merge sorting, is possible
		if (x[mid] >= x[right])
			return;
		
		// Bitwise AND operator - first condition must be satisfied to reach second
		while (left <= mid && right <= last) {
			
		}
	}
}
