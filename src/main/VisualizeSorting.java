package main;

import main.sorts.*;

import java.util.Collections;

public class VisualizeSorting {

	private static Thread thread;
	
	public static Integer[] toSort;
	public static boolean isSorting = false;
	public static int sleep = 10;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VisualizerFrame frame = new VisualizerFrame();
		resetArray();

	}
	
	public static void resetArray() {
		// Check to see if sorting
		if (isSorting) return;
		
		// 
	}
	
	public static void startSort(String type) {
		System.out.println(type);
		
		if (thread == null && !isSorting) {
			isSorting = true;
			toSort = new Integer[] {3, 4, 2, 1};
			
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
