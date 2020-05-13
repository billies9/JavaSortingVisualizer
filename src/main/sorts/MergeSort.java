package main.sorts;

import main.*;
import java.util.Arrays;

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
		
		// Check to see if array size = 1
		if (first >= last) return;
		
		// Find midpoint of array
		mid = (first + last) / 2;

		// Begin recursions to split arrays
		sort(x, first, mid);
		sort(x, mid+1, last);
		
		// -----------------------------------------------
		// Begin iterative process of merging split arrays
		left = first;
		right = mid+1;
		// Skip merge sorting, if possible
		if (x[mid] <= x[right])
			return;
		
		// Bitwise AND operator - first condition must be satisfied to reach second
		while (left <= mid && right <= last) {
			// Advance - order is correct
			if (x[left] <= x[right]) {
				left++;
			}
			/*
			 * If right array element greater than left array element, replace left with right.
			 * Also, move every element left 1 by iterating backwards (j--)
			 */
			else {
				temp = x[right];
				
				for (int j = right - left; j > 0; j--) {
					x[left + j] = x[left + j - 1];
				}
				x[left] = temp;
				
				left++;
				right++;
				mid++;
			}
		}
	}
}
