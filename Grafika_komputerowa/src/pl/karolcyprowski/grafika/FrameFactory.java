package pl.karolcyprowski.grafika;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.KeyEventDispatcher;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class FrameFactory {
	
	private final static String APP_TITLE = "Grafika Komputerowa - Projekt";
	private final static int APP_WIDTH = 801;
	private final static int APP_HEIGHT = 801;
	
	
	

	public static JFrame createMainFrame() {
		JFrame mainFrame = new JFrame(APP_TITLE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setResizable(false);
		return mainFrame;
	}
	
	public static DrawingPanel createDrawingPanel() {
		DrawingPanel drawingPanel = new DrawingPanel();
		return drawingPanel;
	}
	
	public static KeyEventDispatcher createKeyEventDispatcher(DrawingPanel drawingPanel) {
		Controller controller = new Controller(drawingPanel);
		return new GrafikaProjektKeyEventDispatcher(controller);
	}
}
