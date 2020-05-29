package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class RandomGen {
	
	private static ArrayList<Integer> array = new ArrayList<>();

	public RandomGen(int n) {
		Generator(n);
	}

	public static void main(String[] args) {
		RandomGen arr = new RandomGen(5);
		
		for (int i = 0; i<arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}
	
	public Integer get(int i) {
		return array.get(i);
	}

	public int size() {
		return array.size();
	}
	
	public int max() {
		return Collections.max(array);
	}
	
	public int min() {
		return Collections.min(array);
	}

	public static ArrayList<Integer> Generator(int n) {
		array = new ArrayList<>(n);
		
		Random rand = new Random();
		for (int i=0; i<n; i++) {
			Integer num = rand.nextInt((n-1) + 1) + 1;
			array.add(num);
		}
		return array;
	}

}
