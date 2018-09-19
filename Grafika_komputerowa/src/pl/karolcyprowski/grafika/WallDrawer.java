package pl.karolcyprowski.grafika;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import pl.karolcyprowski.grafika.graphics.Point2D;
import pl.karolcyprowski.grafika.graphics.Point3D;
import pl.karolcyprowski.grafika.graphics.Wall3D;

public class WallDrawer {
	
	private Projector projector;
	private DrawingConstraints drawingConstraints;
	
	public WallDrawer() {
		this.drawingConstraints = new DrawingConstraints();
		this.projector = new Projector();
	}

	public void draw(Set<Point2D> points2D, Graphics g) {
		for (Point2D point2D : points2D) {
			Set<Point2D> connections = point2D.getConnetions();
			for (Point2D connectedPoint : connections) {
				if (point2D.getZ() >= 0 && connectedPoint.getZ() >= 0) {
					int startX = drawingConstraints.getX(point2D);
					int startY = drawingConstraints.getY(point2D);
					int endX = drawingConstraints.getX(connectedPoint);
					int endY = drawingConstraints.getY(connectedPoint);
					g.drawLine(startX, startY, endX, endY);
				}
			}
		}
	}
	
	public void fill(Set<Point2D> points2D, Graphics g) {
		for(Point2D point : points2D) {
			if (point.getZ() < 0) {
				return;
			}
		}
		Polygon wallPolygon = new Polygon();
		points2D.stream().forEach(p -> wallPolygon.addPoint(drawingConstraints.getX(p), drawingConstraints.getY(p)));
		g.fillPolygon(wallPolygon);
	}
	
	public Set<Point2D> getProjectedPoints(Wall3D wall) {
		Set<Point3D> points3D = new LinkedHashSet<Point3D>();
		wall.getPoints().stream().forEach(p -> points3D.add(p));
		Set<Point2D> points2D = projector.projectTo2D(points3D);
		return points2D;
	}
	
	public Projector getProjector() {
		return projector;
	}
	
	public DrawingConstraints getDrawingConstraints() {
		return drawingConstraints;
	}
}
