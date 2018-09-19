package pl.karolcyprowski.grafika;

import pl.karolcyprowski.grafika.graphics.Point2D;

public class DrawingConstraints {
	
	private static final int STANDARD_TRANSLATION_X = 400;
	private static final int STANDARD_TRANSLATION_Y = 400;
	
	
	
	public int getX(Point2D point) {
		int x = (int)((point.getX() + 1) * STANDARD_TRANSLATION_X);
//		x += STANDARD_TRANSLATION_X;
		return x;
	}
	
	public int getY(Point2D point) {
		int y = (int)((point.getY() + 1) * STANDARD_TRANSLATION_Y); 
//		y += STANDARD_TRANSLATION_Y;
		return y;
	}
}
