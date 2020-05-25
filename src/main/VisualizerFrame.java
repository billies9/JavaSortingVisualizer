package main;

import main.TextListener;
import java.awt.*;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;



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
	
	private final int DEFAULT_ARR_SIZE = 50;
	private final int MIN_ARR_SIZE = 0;
	private final int MAX_ARR_SIZE = 200;
	private static final int NUM_SORT_TYPES = 3;
	
	private JSlider slider;
	private JTextField textField;

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
		
		// Configuration panel - Holds sort types and array size
		JPanel configPanel = new JPanel();
		GridBagLayout gbl_config = new GridBagLayout();
		gbl_config.rowHeights = new int[] {2, 1};
		gbl_config.rowWeights = new double[] {2.0, 1.0};
		configPanel.setLayout(gbl_config);
		configPanel.setBackground(Color.green);
		
		// Sort panel - Define sort type buttons
		JPanel sortPanel = new JPanel(new GridLayout(NUM_SORT_TYPES, 1));
		GridBagConstraints gbc_sort = new GridBagConstraints();
		gbc_sort.fill = GridBagConstraints.VERTICAL;	
		
		addButton("Bubble", sortPanel, sortTypeListener);
		addButton("Merge", sortPanel, sortTypeListener);
		addButton("Insertion", sortPanel, sortTypeListener);
		configPanel.add(sortPanel, gbc_sort);
		
		// Size panel - Define size of sorting array
		JPanel sizePanel = new JPanel(new GridLayout(2, 1));
		GridBagConstraints gbc_size = new GridBagConstraints();
		gbc_size.gridy = 1;
		gbc_size.fill = GridBagConstraints.HORIZONTAL;
		gbc_size.weightx = 1.0;
		
		JLabel sizeLabel = new JLabel("# of Arrays:", SwingConstants.CENTER);
		sizePanel.add(sizeLabel);
		
		textField = new JTextField(String.valueOf(DEFAULT_ARR_SIZE));
		textField.setColumns(4);
		textField.setHorizontalAlignment(JTextField.CENTER);	
		textField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		
		sizePanel.add(textField);
		configPanel.add(sizePanel, gbc_size);
		
		// Add Configuration panel to outer-most panel
		GridBagConstraints gbc_config = new GridBagConstraints();
		gbc_config.fill = GridBagConstraints.BOTH;
		gbc_config.gridheight = 2; 
		outerPane.add(configPanel, gbc_config);
		
		// Array panel - Holds sorting graph
		JPanel arrayPanel = new JPanel();
		arrayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		arrayPanel.setBackground(Color.yellow);
		
		GridBagConstraints gbcArray = new GridBagConstraints();
		gbcArray.fill = GridBagConstraints.BOTH;
		gbcArray.gridx = 1;
		gbcArray.gridheight = 2;
		gbcArray.insets = new Insets(10, 10, 10, 10);
		outerPane.add(arrayPanel, gbcArray);
		
		// Slider panel - Defines array size to sort; Value displayed in Configuration panel
		JPanel sliderPanel = new JPanel(new BorderLayout());
		slider = new JSlider(MIN_ARR_SIZE, MAX_ARR_SIZE, DEFAULT_ARR_SIZE);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(10);
		slider.addChangeListener(new ChangeListener() {
		      public void stateChanged(ChangeEvent event) {
		    	  JSlider source = (JSlider) event.getSource();
		    	  if (source.getValueIsAdjusting()) {
		    		  textField.setText(String.valueOf(source.getValue()));
		    	  }
		      	}
		    });
		sliderPanel.add(slider);
		// Add listener for change in field/slider to occur
		Document docField = textField.getDocument();
		docField.addDocumentListener(new TextListener(slider));
		
		GridBagConstraints gbcSlider = new GridBagConstraints();
		gbcSlider.fill = GridBagConstraints.BOTH;
		gbcSlider.gridx = 1;
		gbcSlider.gridy = 2; 
		outerPane.add(sliderPanel, gbcSlider);
	}
	
	// Button creation method
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
