package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Document;
import javax.swing.border.EmptyBorder;


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
	private final int MIN_ARR_SIZE = 5;
	private final int MAX_ARR_SIZE = 205;
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
		ActionListener quitListener = new quitApplication();

		// Create Panels
		JPanel outerPane = new JPanel();
		GridBagLayout gbl_outer = new GridBagLayout();
		gbl_outer.columnWidths = new int[] {0, 80};
		gbl_outer.columnWeights = new double[] {Double.MIN_VALUE, 1.0};
		gbl_outer.rowHeights = new int[] {40, 40, 20};
        gbl_outer.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		outerPane.setLayout(gbl_outer);
		getContentPane().add(outerPane);
		
		// Configuration panel - Holds sort types and array size
		JPanel configPanel = new JPanel();
		GridBagLayout gbl_config = new GridBagLayout();
		gbl_config.rowHeights = new int[] {2, 1};
		gbl_config.rowWeights = new double[] {1.5, 0.5};
		configPanel.setLayout(gbl_config);
		
		// Sort panel - Define sort type buttons
		JPanel sortPanel = new JPanel(new GridLayout(NUM_SORT_TYPES, 1));
		GridBagConstraints gbc_sort = new GridBagConstraints();
		gbc_sort.fill = GridBagConstraints.BOTH;	
		
		addButton("Bubble", sortPanel, sortTypeListener);
		addButton("Merge", sortPanel, sortTypeListener);
		addButton("Insertion", sortPanel, sortTypeListener);
		configPanel.add(sortPanel, gbc_sort);
		
		// Size panel - Define size of sorting array
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
			btn.addActionListener(new colorListener());
			colorGroup.add(btn);
			sizePanel.add(btn);
		}
		
		configPanel.add(sizePanel, gbc_size);
		
		// Add Configuration panel to outer-most panel
		GridBagConstraints gbc_config = new GridBagConstraints();
		gbc_config.fill = GridBagConstraints.BOTH;
		gbc_config.gridheight = 2; 
		outerPane.add(configPanel, gbc_config);
		
		// Array panel - Holds sorting graph
		JPanel arrayPanel = new JPanel(new BorderLayout());
		arrayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		GridBagConstraints gbc_array = new GridBagConstraints();
		gbc_array.fill = GridBagConstraints.BOTH;
		gbc_array.gridx = 1;
		gbc_array.gridheight = 2;
		gbc_array.insets = new Insets(10, 10, 10, 10);
		
		ChartComponent chart = new ChartComponent();
		chart.setSize(DEFAULT_ARR_SIZE);
		arrayPanel.add(chart);
		outerPane.add(arrayPanel, gbc_array);
		
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
		    		  // Set new array size
		    		  chart.setSize(source.getValue());
		    	  }
		      	}
		    });
		sliderPanel.add(slider);
		// Add listener for change in field/slider to occur
		Document docField = textField.getDocument();
		// On change, call repaint method - Inside of TextListener.updatSlider method
		docField.addDocumentListener(new TextListener(slider));
		
		GridBagConstraints gbcSlider = new GridBagConstraints();
		gbcSlider.fill = GridBagConstraints.BOTH;
		gbcSlider.gridx = 1;
		gbcSlider.gridy = 2; 
		outerPane.add(sliderPanel, gbcSlider);
		
		// Quit panel
		JPanel quitPanel = new JPanel(new GridLayout());
		
		addButton("Quit", quitPanel, quitListener);
		GridBagConstraints gbc_quit = new GridBagConstraints();
		gbc_quit.fill = GridBagConstraints.BOTH;
		gbc_quit.gridx = 0;
		gbc_quit.gridy = 2;
		
		outerPane.add(quitPanel, gbc_quit);
	}

	
	private static class ChartComponent extends JComponent {
		
		public static Color color;
		
		private int maxY;
		private int minY = 1;
		private int minX = 1;
		private int n;
		
		private RandomGen array;
		
		public void setSize(int n) {
			this.n = n;
			
			RandomGen array = new RandomGen(n);
			this.array = array;
			repaint();
		}
		
		@Override
		public void paintComponent(Graphics g) {	
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			
			// Compute maximum axis values 
			maxY = array.max();
				
			// Compute individual barWidths
			double barWidth = (double) getWidth() / array.size();
			
			// Compute scale factor
			double scale = (double) getHeight() / maxY;
			
			// Draw the bars
			for (int i = 0; i < array.size(); i++) {
				// Get coordinates of the bar
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
		@Override
		public void actionPerformed(ActionEvent event) {
			VisualizeSorting.startSort(event.getActionCommand());
		}
	}
	
	// Quit method listener
	private class quitApplication implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}
	
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
