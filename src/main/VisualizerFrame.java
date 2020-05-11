package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class VisualizerFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SortingFrame frame = new SortingFrame();
			frame.setTitle("Sorting Visualizer");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});

	}

}

class SortingFrame extends JFrame {
	private final static int DEFAULT_WIDTH = 800;
	private final static int DEFAULT_HEIGHT = 600;
	
	private JPanel buttonWrap;
	
	public SortingFrame() {
		
		//	Set app size/resize-ablility
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setResizable(false);
		
		//	Set location - managed by platform
		setLocationByPlatform(true);
		
		//	Add panels - buttons, graph
		buttonWrap = new JPanel();
		JPanel arrayWrap = new JPanel();
		
		// 	Define ActionListeners for 'sort' object
		ActionListener sortTypeListener = new sortSelection();
	   
		// Add sorting algorithm buttons
		addButton("Bubble", sortTypeListener);
		addButton("Merge", sortTypeListener);
		addButton("Insertion", sortTypeListener);
		
		// Place panel object - constraint North
		add(buttonWrap, BorderLayout.NORTH); 
		
	}
	
	//	Create button method
	private void addButton(String label, ActionListener listener) {
		JButton button = new JButton(label);
		button.addActionListener(listener);
		buttonWrap.add(button);
	}
	
	// Sorting method listener
	private class sortSelection implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			VisualizeSorting.startSort(event.getActionCommand());
		}
	}
	
}
