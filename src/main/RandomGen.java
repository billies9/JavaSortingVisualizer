package main;

import java.util.ArrayList;
import java.util.Random;

public class RandomGen {
	
	private static ArrayList<Integer> array = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> arr = RandomArray(7);
		for (int i = 0; i<arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}
	
	public static ArrayList<Integer> RandomArray(int n) {
		array = new ArrayList<>(n);
		
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		for (int i=0; i<n; i++) {
			Integer num = rand.nextInt(n);
			array.add(num);
		}
		return array;
	}

}
