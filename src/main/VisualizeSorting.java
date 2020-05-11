package main;

import main.sorts.*;

import java.util.Collections;

public class VisualizeSorting {

	private static Thread thread;
	
	public static boolean isSorting = false;
	public static int sleep = 10;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void startSort(String type) {
		System.out.println(type);
		
		if (thread == null && !isSorting) {
			isSorting = true;
			
			switch(type) {
			case "Bubble":
				Integer[] array = new Integer[] {5, 4, 3, 2, 1};
				thread = new Thread(new BubbleSort(array));
				break;
	//		case "Merge":
	//			break
	//		case "Insertion":
	//			break
			
			}
			thread.start();
		}
	}
}
