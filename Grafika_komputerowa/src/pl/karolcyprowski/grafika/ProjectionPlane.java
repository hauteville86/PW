package pl.karolcyprowski.grafika;

public class ProjectionPlane {
	
	private static final double ZOOM_STEP = 0.1;
	private double d;
	
	public ProjectionPlane() {
		d = 1;
	}
	
	public double getD() {
		return d;
	}
	
	public void increaseD() {
		d += ZOOM_STEP;
		System.out.println("Increased value d: " + d);
	}
	
	public void decreaseD() {
		d -= ZOOM_STEP;
		System.out.println("Decreased value d: " + d);
	}

}
