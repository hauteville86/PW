package pl.karolcyprowski.grafika;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
	
	private final static int PANEL_WIDTH = 800;
	private final static int PANEL_HEIGHT = 800;
	
	private DrawingTool drawingTool;
	
	public DrawingPanel() {
		super();
		drawingTool = new DrawingTool();
		setBackground(DrawingTool.COLOR_BACKGROUND);
		drawingTool.init();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(DrawingTool.COLOR_LINE);
		((Graphics2D)g).setStroke(new BasicStroke(2));
//		drawingTool.drawWalls(g);
		drawingTool.drawFigures(g);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
	}
	
	public DrawingTool getDrawingTool() {
		return drawingTool;
	}

}
