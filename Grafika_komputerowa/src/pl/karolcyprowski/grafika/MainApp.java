package pl.karolcyprowski.grafika;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.KeyboardFocusManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainApp {

	public static void main(String[] args) {
		JFrame mainFrame = FrameFactory.createMainFrame();			
		DrawingPanel drawingPanel = FrameFactory.createDrawingPanel();
		KeyboardFocusManager.getCurrentKeyboardFocusManager().
		addKeyEventDispatcher(FrameFactory.createKeyEventDispatcher(drawingPanel));
		
		
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				mainFrame.add(drawingPanel, BorderLayout.CENTER);
				
				mainFrame.pack();
				mainFrame.setVisible(true);
			}
		});
	}

}
