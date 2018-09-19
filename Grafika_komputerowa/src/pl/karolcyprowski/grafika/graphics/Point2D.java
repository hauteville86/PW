package pl.karolcyprowski.grafika.graphics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Point2D {

	private double x;
	private double y;
	private double z;
	private boolean drawn;
	
	private Set<Point2D> connections;
	
	public Point2D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.drawn = false;
		connections = new LinkedHashSet<Point2D>();
	}
	
	public Set<Point2D> getConnetions() {
		return connections;
	}
	
	public void addConnection(Point2D point) {
		connections.add(point);
		if (!point.hasConnection(this)) {
			point.addConnection(point);
		}
	}
	
	public boolean hasConnection(Point2D point) {
		return connections.contains(point);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setDrawn(boolean drawn) {
		this.drawn = drawn;
	}
	
	public boolean isDrawn() {
		return drawn;
	}
	
	@Override
	public String toString() {
		return "Point2D: (x=" + x + "; y=" + y + "; z=" + z + ")";
	}
	
}
