package main;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class TextListener implements DocumentListener {
	
	private JSlider slider;
	
	public TextListener(JSlider slider) {
		this.slider = slider;
	}
	
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
		Document doc = (Document) event.getDocument();
		int len = doc.getLength();
		try {
			String txt = doc.getText(0, len);
			int value = Integer.parseInt(txt.trim());
			slider.setValue(value);
		}
		catch (NumberFormatException e) {
			// Do Nothing
		}
		catch (BadLocationException e) {
			e.printStackTrace();
		}
		
	}
}
