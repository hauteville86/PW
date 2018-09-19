package pl.karolcyprowski.grafika.graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Cube3D extends Figure3D {

	private List<Wall3D> walls;
	
	public Cube3D() {
		this.walls = new ArrayList<Wall3D>();
	}

	public List<Wall3D> getWalls() {
		return walls;
	}

	public void setWalls(List<Wall3D> walls) {
		this.walls = walls;
	}
	
	public void addWall(Wall3D wall) {
		walls.add(wall);
	}
	
	public Set<Point3D> getPoints() {
		Set<Point3D> points = new LinkedHashSet<Point3D>();
		for (Wall3D wall : walls) {
			wall.getPoints().stream().forEach(e -> points.add(e));
		}
		return points;
	}
//	
//	public void sortWalls() {
//		Collections.sort(walls, new Comparator<Wall3D>() {
//
//			@Override
//			public int compare(Wall3D o1, Wall3D o2) {
//				Point3D o1CenterPoint = o1.getCenterPoint();
//				Point3D o2CenterPoint = o2.getCenterPoint();
//				if (o1CenterPoint.getZ() < o2CenterPoint.getZ()) {
//					return 1;
//				} else if (o1CenterPoint.getZ() > o2CenterPoint.getZ()) {
//					return -1;
//				} else {
//					if (getDistanceToReferencePoint(o1CenterPoint) < getDistanceToReferencePoint(o2CenterPoint)) {
//						return 1;
//					} else if (getDistanceToReferencePoint(o1CenterPoint) > getDistanceToReferencePoint(o2CenterPoint)) {
//						return -1;
//					}
//				}
//				return 0;
//			}
//			
//		});
//	}
	
	public Point3D getCenterPoint() {
		double x = 0;
		double y = 0;
		double z = 0;
		for (Wall3D w : walls) {
			Point3D wCenterPoint = w.getCenterPoint();
			x += wCenterPoint.getX();
			y += wCenterPoint.getY();
			z += wCenterPoint.getZ();
		}
		return new Point3D(x / walls.size(), y / walls.size(), z / walls.size());
	}
	
	
}
