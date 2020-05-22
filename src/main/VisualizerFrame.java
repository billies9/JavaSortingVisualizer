package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


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
	
	private final int DEFAULT_SIZE = 50;
	private final int MIN_SIZE = 5;
	private final int MAX_SIZE = 200;
	private static final int NUM_SORT_TYPES = 3;
	
	private JSlider slider;

	public SortingFrame() {
		
		//	Set app size/resize-ablility
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setResizable(false);
		// Set location - managed by platform
		setLocationByPlatform(true);
		
		// Define ActionListeners
		ActionListener sortTypeListener = new sortSelection();

		// Create Panels
		JPanel outerPane = new JPanel();
		GridBagLayout gbl_outer = new GridBagLayout();
		gbl_outer.columnWidths = new int[] {0, 80};
		gbl_outer.columnWeights = new double[] {Double.MIN_VALUE, 1.0};
		gbl_outer.rowHeights = new int[] {40, 40, 20};
        gbl_outer.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		outerPane.setLayout(gbl_outer);
		outerPane.setBackground(Color.red);
		getContentPane().add(outerPane);
		
		JPanel configPanel = new JPanel();
		GridBagLayout gbl_config = new GridBagLayout();
		gbl_config.columnWeights = new double[] {Double.MIN_VALUE};
		gbl_config.rowHeights = new int[] {40, 40, 20};
		gbl_config.rowWeights = new double[] {1.0, 1.0, Double.MIN_VALUE};
		configPanel.setLayout(gbl_config);
		configPanel.setBackground(Color.green);
		
		JPanel innerConfigPanel = new JPanel(new GridLayout(NUM_SORT_TYPES, 1));
		GridBagConstraints gbc_innerConfig = new GridBagConstraints();
		gbc_innerConfig.fill = GridBagConstraints.VERTICAL;	
		
		addButton("Bubble", innerConfigPanel, sortTypeListener);
		addButton("Merge", innerConfigPanel, sortTypeListener);
		addButton("Insertion", innerConfigPanel, sortTypeListener);
		configPanel.add(innerConfigPanel, gbc_innerConfig);
		
		GridBagConstraints gbc_config = new GridBagConstraints();
		gbc_config.fill = GridBagConstraints.BOTH;
		gbc_config.gridy = 0;
		gbc_config.gridx = 0;
		gbc_config.gridheight = 3; 
		outerPane.add(configPanel, gbc_config);
		
		JPanel arrayPanel = new JPanel();
		arrayPanel.setBackground(Color.yellow);
		
		GridBagConstraints gbcArray = new GridBagConstraints();
		gbcArray.fill = GridBagConstraints.BOTH;
		gbcArray.gridx = 1;
		gbcArray.gridy = 0;
		gbcArray.gridheight = 2;
		outerPane.add(arrayPanel, gbcArray);
		
		JPanel sliderPanel = new JPanel(new BorderLayout());
		sliderPanel.add(new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_SIZE));
		sliderPanel.setBackground(Color.blue);
		
		GridBagConstraints gbcSlider = new GridBagConstraints();
		gbcSlider.fill = GridBagConstraints.BOTH;
		gbcSlider.gridx = 1;
		gbcSlider.gridy = 2; 
		gbcSlider.gridheight = 1;
		outerPane.add(sliderPanel, gbcSlider);
		

	}
	
	// Component methods - Override for different components
	private void addButton (String label,
							JPanel panel,
							ActionListener listener) {
		
		JButton button = new JButton(label);
		button.addActionListener(listener);
		panel.add(button);
	}
	
	
	// Sorting method listener
	private class sortSelection implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			VisualizeSorting.startSort(event.getActionCommand());
		}
	}
	
}
