package main;

import main.sorts.*;
import java.util.ArrayList;
import java.util.Collections;

public class VisualizeSorting {

	private static Thread thread;
	
	public static ArrayList<Integer> toSort;
	public static boolean isSorting = false;
	public static int sleep = 10;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VisualizerFrame frame = new VisualizerFrame();
//		resetArray();

	}
	
	public static void resetArray(int n) {
		// Check to see if sorting
//		isSorting = true;
		if (isSorting) {
			// Remove control of slider while sorting
			SortingFrame.slider.setEnabled(false); 
			return;
		}
		RandomGen gen = new RandomGen(n);
		toSort = gen.Generator();
		// 
	}
	
	public static void startSort(String type) {
		
		if (thread == null && !isSorting) {
			isSorting = true;
//			toSort = new Integer[] {3, 4, 2, 1};
			
			switch(type) {
			case "Bubble":
				thread = new Thread(new BubbleSort(toSort));
				break;
			case "Merge":
				thread = new Thread(new MergeSort());
				break;
			case "Insertion":
				thread = new Thread(new InsertionSort(toSort));
				break;
			default:
				isSorting = false;
				break;
			
			}
			thread.start();
		}
	}
}
