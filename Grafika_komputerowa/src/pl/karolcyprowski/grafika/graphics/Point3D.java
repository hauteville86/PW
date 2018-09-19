package pl.karolcyprowski.grafika.graphics;

import java.util.LinkedHashSet;
import java.util.Set;

public class Point3D implements Comparable<Point3D> {
	
	

	private double x;
	private double y;
	private double z;
	private double w;
	
	private double[][] projection;
	
	private Set<Point3D> connections;
	
	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 1;
		this.connections = new LinkedHashSet<Point3D>();
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
	
	public double getW() {
		return w;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
	
	public void setW(double w) {
		this.w = w;
	}
	
	public void addConnection(Point3D point) {
		connections.add(point);
		if (!point.isConnectedTo(this)) {
			point.addConnection(point);
		}
	}
	
	public void removeConnection(Point3D point) {
		connections.remove(point);
	}
	
	public boolean isConnectedTo(Point3D point) {
		return connections.contains(point);
	}
	
	public Set<Point3D> getConnections() {
		return connections;
	}
	
	public double[][] getMatrixRepresentation() {
		double[][] matrix = {{x}, {y}, {z}, {w}};
		return matrix;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "Point3D: (x=" + x + "; y=" + y +  "; z=" + z + ")";
	}

	@Override
	public int compareTo(Point3D o) {
		if(this.getZ() < o.getZ()) {
			return 1;
		} else if (this.getZ() > o.getZ()) {
			return -1;
		} else {
			return 0;
		}
	}

	public double[][] getProjection() {
		return projection;
	}

	public void setProjection(double[][] projection) {
		this.projection = projection;
	}
}
