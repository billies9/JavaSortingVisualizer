package main.sorts;

import main.*;
import java.util.ArrayList;

public class MergeSort implements Runnable {
	
	private ArrayList<Integer> toSort;
	private SortingFrame frame;
	
	public MergeSort(SortingFrame frame, ArrayList<Integer> toSort) {
		this.toSort = toSort;
		this.frame = frame;
	}
	
	public void run() {
		sort(toSort);
		VisualizeSorting.isSorting = false;
	}
	
	public void sort(ArrayList<Integer> x) {
		sort(x, 0, x.size()-1);
	}
	
	private void sort(ArrayList<Integer> x, int first, int last) {
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
		if (x.get(mid) <= x.get(right))
			return;
		
		// Bitwise AND operator - first condition must be satisfied to reach second
		while (left <= mid && right <= last) {
			// Advance - order is correct
			if (x.get(left) <= x.get(right)) {
				left++;
			}
			/*
			 * If right array element greater than left array element, replace left with right.
			 * Also, move every element left 1 by iterating backwards (j--)
			 */
			else {
				temp = x.get(right);
				
				for (int j = right - left; j > 0; j--) {
					x.set(left + j, x.get(left + j -1));
				}
				x.set(left, temp);
				
				// Move counters to reflect new positions
				left++;
				right++;
				mid++;
			}
			frame.repaint();
		}
	}
}
