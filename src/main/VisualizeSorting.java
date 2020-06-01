package main;

import main.sorts.*;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;

public class VisualizeSorting {

	private static Thread thread;
	
	public static ArrayList<Integer> toSort;
	public static boolean isSorting = false;
	public static int sleep = 10;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SortingFrame frame = new SortingFrame();
			frame.setTitle("Sorting Visualizer");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
	
	public static void setArray(int n) {
		// Check to see if sorting
//		isSorting = true;
		if (isSorting) {
			// Remove control of slider while sorting
			SortingFrame.slider.setEnabled(false); 
			return;
		}
		RandomGen gen = new RandomGen(n);
		toSort = gen.Generator(); 
	}
	
	public static void startSort(String type) {
		
		if (thread == null && !isSorting) {
			isSorting = true;
			
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
