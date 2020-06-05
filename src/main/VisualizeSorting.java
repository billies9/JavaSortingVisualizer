package main;

import main.sorts.*;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

public class VisualizeSorting {

	private static Thread thread;
	
	public static SortingFrame frame;
	public static ArrayList<Integer> toSort;
	public static boolean isSorting = false;
	public static int sleep = 10;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			frame = new SortingFrame();
			frame.setTitle("Sorting Visualizer");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
	
	public static void setArray(int n) {
		RandomGen gen = new RandomGen(n);
		toSort = gen.Generator(); 
	}
	
	public static void startSort(String type) {
		
		if (thread == null || !isSorting) {
			isSorting = true;
			
			switch(type) {
			case "Bubble":
				thread = new Thread(new BubbleSort(frame, toSort));
				break;
			case "Merge":
				thread = new Thread(new MergeSort(frame, toSort));
				break;
			case "Insertion":
				thread = new Thread(new InsertionSort(frame, toSort));
				break;
			default:
				isSorting = false;
				break;
			
			}
			thread.start();
		}
	}
}
