package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;


public class SortingFrame extends JFrame {
	// Initialize class variables
	private final static int DEFAULT_WIDTH = 800;
	private final static int DEFAULT_HEIGHT = 600;
	
	private final int DEFAULT_ARR_SIZE = 55;
	private final static int MIN_ARR_SIZE = 5;
	private final static int MAX_ARR_SIZE = 205;
	private static final int NUM_SORT_TYPES = 3;
	
	public static JSlider slider;
	public JTextField textField;
	public ChartComponent chart;

	public SortingFrame() {	
		
		//	Set app size
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		// Disable resize
		setResizable(false);
		// Set location - managed by platform
		setLocationByPlatform(true);
		
		// Define Listeners
		ActionListener sortTypeListener = new sortListener();
		ActionListener quitListener = new quitListener();
		ActionListener colorListener = new colorListener();
		DocumentListener textListener = new TextListener();

		// Create Panels
		JPanel outerPane = new JPanel();
		GridBagLayout gbl_outer = new GridBagLayout();
		gbl_outer.columnWidths = new int[] {0, 80};
		gbl_outer.columnWeights = new double[] {Double.MIN_VALUE, 1.0};
		gbl_outer.rowHeights = new int[] {40, 40, 20};
        gbl_outer.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		outerPane.setLayout(gbl_outer);
		getContentPane().add(outerPane);
		
		// Configuration panel
		JPanel configPanel = new JPanel();
		GridBagLayout gbl_config = new GridBagLayout();
		gbl_config.rowHeights = new int[] {2, 1};
		gbl_config.rowWeights = new double[] {1.5, 0.5};
		configPanel.setLayout(gbl_config);
		
		// Sort buttons panel
		JPanel sortPanel = new JPanel(new GridLayout(NUM_SORT_TYPES, 1));
		GridBagConstraints gbc_sort = new GridBagConstraints();
		gbc_sort.fill = GridBagConstraints.BOTH;	
		
		addButton("Bubble", sortPanel, sortTypeListener);
		addButton("Merge", sortPanel, sortTypeListener);
		addButton("Insertion", sortPanel, sortTypeListener);
		configPanel.add(sortPanel, gbc_sort);
		
		// Array size & color panel
		JPanel sizePanel = new JPanel(new GridLayout(6, 1));
		GridBagConstraints gbc_size = new GridBagConstraints();
		gbc_size.gridy = 1;
		gbc_size.fill = GridBagConstraints.HORIZONTAL;
		gbc_size.insets = new Insets(0, 5, 0, 5);
		gbc_size.weightx = 1.0;
		
		JLabel sizeLabel = new JLabel("# of Arrays:", SwingConstants.LEFT);
		sizePanel.add(sizeLabel);
		
		textField = new JTextField(String.valueOf(DEFAULT_ARR_SIZE));
		textField.setHorizontalAlignment(JTextField.CENTER);	
		textField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		
		GridBagConstraints gbc_text = new GridBagConstraints();
		
		sizePanel.add(textField, gbc_text);

		JLabel colorLabel = new JLabel("Choose array color:", SwingConstants.LEFT);
		sizePanel.add(colorLabel);
		
		ButtonGroup colorGroup = new ButtonGroup();
		String[] colors = {"Black", "Blue", "Red"};
		for (int i = 0; i < colors.length; i++) {
			JRadioButton btn = new JRadioButton(colors[i], true);
			btn.addActionListener(colorListener);
			colorGroup.add(btn);
			sizePanel.add(btn);
		}
		configPanel.add(sizePanel, gbc_size);
		
		// Add Configuration panel to master panel
		GridBagConstraints gbc_config = new GridBagConstraints();
		gbc_config.fill = GridBagConstraints.BOTH;
		gbc_config.gridheight = 2; 
		outerPane.add(configPanel, gbc_config);
		
		// Graph panel 
		JPanel arrayPanel = new JPanel(new BorderLayout());
		arrayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		GridBagConstraints gbc_array = new GridBagConstraints();
		gbc_array.fill = GridBagConstraints.BOTH;
		gbc_array.gridx = 1;
		gbc_array.gridheight = 2;
		gbc_array.insets = new Insets(10, 10, 10, 10);
		
		ChartComponent chart = new ChartComponent();
		chart.setArray(DEFAULT_ARR_SIZE);
		arrayPanel.add(chart);
		outerPane.add(arrayPanel, gbc_array);
		
		// Slider panel
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
		    	  // Set new array size - Event fires on slide & bound field change
	    		  chart.setArray(source.getValue());
		      	}
		    });
		sliderPanel.add(slider);
		// Create document listener on input field
		Document docField = textField.getDocument();
		docField.addDocumentListener(textListener);
		
		GridBagConstraints gbcSlider = new GridBagConstraints();
		gbcSlider.fill = GridBagConstraints.BOTH;
		gbcSlider.gridx = 1;
		gbcSlider.gridy = 2; 
		outerPane.add(sliderPanel, gbcSlider);
		
		// Quit button
		JPanel quitPanel = new JPanel(new GridLayout());
		
		addButton("Quit", quitPanel, quitListener);
		GridBagConstraints gbc_quit = new GridBagConstraints();
		gbc_quit.fill = GridBagConstraints.BOTH;
		gbc_quit.gridx = 0;
		gbc_quit.gridy = 2;
		
		outerPane.add(quitPanel, gbc_quit);
	}
	
	// Create random bar array 
	public static class ChartComponent extends JComponent {
		
		public static Color color;
		
		private ArrayList<Integer> array;
		
		public void setArray(int n) {			
			VisualizeSorting.setArray(n);
			this.array = VisualizeSorting.toSort;
			repaint();
		}
		
		@Override
		public void paintComponent(Graphics g) {	
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			
			// Flip y-axis in JPanel
			Insets insets = getInsets();
			int h = getHeight() - insets.top - insets.bottom;
			
			// Scale and translate axis
			g2d.scale(1.0, -1.0);
			g2d.translate(0, -h-insets.top);
				
			// Compute individual barWidths
			double barWidth = (double) getWidth() / array.size();
			
			// Compute panel scale factor
			double scale = (double) getHeight() / Collections.max(array);
			
			// Draw bars graph
			for (int i = 0; i < array.size(); i++) {
				// Coordinates of the bar
				double x1 = i * barWidth + 1;
				double y1 = 0;
				double height = array.get(i) * scale;

				Rectangle2D rect = new Rectangle2D.Double(x1, y1, barWidth-2, height);
				g2d.setPaint(color);
				g2d.fill(rect);
				g2d.setPaint(Color.BLACK);
				g2d.draw(rect);
			}
		}
	}
	
	// Field input listener
	static class TextListener implements DocumentListener {
		
		public TextListener() {}
		
		@Override
		public void insertUpdate(DocumentEvent event) {
			updateSlider(event);
		}
	
		@Override
		public void removeUpdate(DocumentEvent event) {
			updateSlider(event);	
		}
	
		@Override
		public void changedUpdate(DocumentEvent event) {}
		
		public void updateSlider(DocumentEvent event) {	
			AbstractDocument docAbs = (AbstractDocument) event.getDocument();
		    docAbs.setDocumentFilter(new BoundsFilter());
		}
		
		private boolean verifyRange(String text) {
			if (text.isEmpty()) {
				return false;
			}
			int value = 0;
			try {
				// Check value between array bounds
				value = Integer.parseInt(text);
				if (value >= MIN_ARR_SIZE && value <= MAX_ARR_SIZE ) {
					return true;
				}
			}
			catch (NumberFormatException e) {
				return false;
			}
			return false;
		}
		
		private class BoundsFilter extends DocumentFilter {	
			@Override
			public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String arrInput, AttributeSet attr) 
					throws BadLocationException {
				// Invoked prior to replacing region of text
				Document doc = fb.getDocument();
				String text = doc.getText(0, doc.getLength());
				StringBuilder sb = new StringBuilder(text);
				
				int end = offset + length;
				sb.replace(offset, end, arrInput);	
				
				if (verifyRange(sb.toString())) {
		        	super.replace(fb, offset, length, arrInput, attr);
		        	slider.setValue(Integer.parseInt(sb.toString()));
				}
			}
			
			@Override
			public void insertString(DocumentFilter.FilterBypass fb, int offset, String arrInput, AttributeSet attr) 
					throws BadLocationException {
				// Invoked prior to insertion of text
				if (verifyRange(arrInput)) {
				   super.insertString(fb, offset, arrInput, attr);
				   slider.setValue(Integer.parseInt(arrInput));
				}
			}
			
			@Override
			public void remove(DocumentFilter.FilterBypass fb, int offset, int length) 
					throws BadLocationException {
				// Backspace keystroke
				Document doc = fb.getDocument();
				String oldText = doc.getText(0, doc.getLength());
				StringBuilder sb = new StringBuilder(oldText);
				
				sb.replace(offset, offset + length, "");
				if (verifyRange(sb.toString())) {
				   super.remove(fb, offset, length);    
				   slider.setValue(Integer.parseInt(sb.toString()));        
				}
			}
		}
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
	private class sortListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			VisualizeSorting.startSort(event.getActionCommand());
		}
	}
	
	// Quit method listener
	private class quitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}
	
	// Bar color change listener
	private class colorListener implements ActionListener {
		public Color color;
		
		@Override
		public void actionPerformed(ActionEvent event) {
			JRadioButton btn = (JRadioButton) event.getSource();
			switch (btn.getText()) {
			case "Red":
				color = Color.RED;
				break;
			case "Blue":
				color = Color.BLUE;
				break;
			case "Black":
				color = Color.BLACK;
				break;
			default:
				color = Color.RED;
				break;
			}
			ChartComponent.color = color;
			repaint();
		}
	}

}
