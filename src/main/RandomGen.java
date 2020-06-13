package main;

import java.util.ArrayList;
import java.util.Random;

public class RandomGen {
	// Initalize class variables
	private static ArrayList<Integer> array = new ArrayList<>();
	private int n;
	
	// Class constructor
	public RandomGen(int n) {
		this.n = n;
	}
	// Test random generation
	public static void main(String[] args) {
		RandomGen gen = new RandomGen(25);
		array = gen.Generator();
		
		for (int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i));
		}
	}
	// Generate random array
	public ArrayList<Integer> Generator() {
		array = new ArrayList<>(n);
		
		Random rand = new Random();
		for (int i=0; i<n; i++) {
			Integer num = rand.nextInt((n-1) + 1) + 1;
			array.add(num);
		}
		return array;
	}

}
