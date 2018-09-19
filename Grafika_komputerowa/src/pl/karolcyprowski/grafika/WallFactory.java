package pl.karolcyprowski.grafika;

import java.util.ArrayList;
import java.util.List;

import pl.karolcyprowski.grafika.graphics.Point3D;
import pl.karolcyprowski.grafika.graphics.Vector3D;
import pl.karolcyprowski.grafika.graphics.Wall3D;

public class WallFactory {

	public Wall3D createBackWall(Wall3D front, Vector3D vector, int wallId) {
		List<Point3D> wallPoints = front.getPoints();
		List<Point3D> transformedWallPoints = new ArrayList<Point3D>();
		for (Point3D point : wallPoints) {
			Point3D transformedPoint = createTransformedPoint(point, vector);
			point.addConnection(transformedPoint);
			transformedWallPoints.add(transformedPoint);
		}
		return new Wall3D(transformedWallPoints, wallId);
	}
	
	public List<Wall3D> createSideWalls(Wall3D front, Wall3D back, int wallId) {
		List<Point3D> frontPoints = front.getPoints();
		List<Point3D> backPoints = back.getPoints();
		List<Wall3D> walls = new ArrayList<Wall3D>();
		for (int i = 0; i < frontPoints.size(); i++) {
			List<Point3D> wallPoints = new ArrayList<Point3D>();
			Point3D a, b, c, d;
			a = frontPoints.get(i);
			d = backPoints.get(i);
			if (i < frontPoints.size() - 1) {
				b = frontPoints.get(i + 1);
				c = backPoints.get(i + 1);
			} else {
				b = frontPoints.get(0);
				c = backPoints.get(0);
			}
			a.addConnection(b);
			b.addConnection(c);
			c.addConnection(d);
			d.addConnection(a);
			wallPoints.add(a);
			wallPoints.add(b);
			wallPoints.add(c);
			wallPoints.add(d);
			walls.add(new Wall3D(wallPoints, wallId));
			wallId++;
		}
		return walls;
	}
	
	private Point3D createTransformedPoint(Point3D a, Vector3D vector) {
		double x = a.getX() + vector.getValue() * vector.getX();
		double y = a.getY() + vector.getValue() * vector.getY();
		double z = a.getZ() + vector.getValue() * vector.getZ();
		return new Point3D(x, y, z);
	}
}
