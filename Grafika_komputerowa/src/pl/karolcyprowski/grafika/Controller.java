package pl.karolcyprowski.grafika;


public class Controller {
	
	private DrawingPanel drawingPanel;
	
	public Controller(DrawingPanel panel) {
		this.drawingPanel = panel;
	}
	
	public void increaseD() {
		getProjector().getProjectionPlane().increaseD();
	}
	
	public void decreaseD() {
		getProjector().getProjectionPlane().decreaseD();
	}
	
	public void translateX(boolean increase) {
		getProjector().getTranslator().changeX(increase);
	}
	
	public void translateY(boolean increase) {
		getProjector().getTranslator().changeY(increase);
	}
	
	public void translateZ(boolean increase) {
		getProjector().getTranslator().changeZ(increase);
	}
	
	public void rotateX(boolean plus) {
		getProjector().getAxisRotator().setRotateX(plus);
	}
	
	public void rotateY(boolean plus) {
		getProjector().getAxisRotator().setRotateY(plus);
	}
	
	public void rotateZ(boolean plus) {
		getProjector().getAxisRotator().setRotateZ(plus);
	}
	
	public void repaint() {
		drawingPanel.repaint();
	}
	
	private Projector getProjector() {
		return drawingPanel.getDrawingTool().getWallDrawer().getProjector();
	}

}
