import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	
	public Canvas getCanvas() {
		return canvas;
	}

	private String title;
	private int width, height;
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Als je dit niet doet en je sluit het venster dan sluit het programma niet mee.
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //Zodat het scherm in het center open gaat
		frame.setVisible(true); //Default is een JFrame invisible
		
		canvas = new Canvas(); //Canvas wordt gemaakt om 'op te tekenen'. Zodat er daadwerkelijk dingen in het scherm gezet kunnen worden.
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack(); //Resized het window een klein beetje zodat het canvas volledig zichtbaar is. Als dit niet gedaan wordt werkt het prima maar zie je niet het volledige canvas.
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
