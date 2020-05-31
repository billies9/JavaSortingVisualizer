package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class RandomGen {
	
	private static ArrayList<Integer> array = new ArrayList<>();
	private int n;
	
	public RandomGen(int n) {
		this.n = n;
	}

	public static void main(String[] args) {
		RandomGen gen = new RandomGen(5);
		array = gen.Generator();
		
		for (int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i));
		}
	}
	
	public int max() {
		return Collections.max(array);
	}
	
	public int min() {
		return Collections.min(array);
	}

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
