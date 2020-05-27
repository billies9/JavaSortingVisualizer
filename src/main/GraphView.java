package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.Collections;
import java.util.ArrayList;


public class GraphView extends JPanel {
	
	private int maxY;
	private int minY = 1;
	private int[] labelsY;
	private int maxX;
	private int minX = 1;
	private int[] labelsX;
	private ArrayList<Integer> randArr = new ArrayList<>(50);
	
	public static void main(String[] args) {
		ArrayList<Integer> randArr = new ArrayList<Integer>(3);
		randArr.add(4);
		randArr.add(3);
		randArr.add(10);
		GraphView g = new GraphView(randArr);
	}
	
	public GraphView(ArrayList<Integer> randArr) {
		this.randArr = randArr;
		// xAxis 1 - randArr.length
		// yAxis 1 - max(randArr)
		System.out.println(Collections.max(this.randArr));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.white);
		
		
	}
}
