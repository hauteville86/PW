package pl.karolcyprowski.grafika.graphics;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Wall3D extends Figure3D{
	
	private static Point3D referencePoint = new Point3D(0, 0, 0);
	
	private List<Point3D> points;
	private Set<Point2D> projection;
	private int id;
	
	public Wall3D(List<Point3D> points) {
		this.points = points;
		for (int i = 0; i < points.size(); i++) {
			Point3D point = points.get(i);
			Point3D nextPoint;
			if (i < points.size() - 1) {
				nextPoint = points.get(i + 1);
			} else {
				nextPoint = points.get(0);
			}
			point.addConnection(nextPoint);
		}
	}
	
	public Wall3D(List<Point3D> points, int id) {
		this(points);
		this.id= id;
	}
	
	public List<Point3D> getPoints() {
		return points;
	}
	
	/**
	 * 
	 * 
	 */

	@Override
	public int compareTo(Figure3D o) {
		
		if (o instanceof Wall3D) {
			
			Point3D centerPoint = getCenterPoint();
			Point3D oCenterPoint = o.getCenterPoint();
			
			
			if (centerPoint.getZ() < oCenterPoint.getZ()) {
				return 1;
			} else if (centerPoint.getZ() > oCenterPoint.getZ()) {
				return -1;
			}
			
//			Plane3D plane = getPlane();
//			Plane3D oPlane = ((Wall3D)o).getPlane();
//			
//			double distanceFromPlaneToO = getDistance(plane, oCenterPoint);
//			double distanceFromPlaneToReferencePoint = getDistance(plane, referencePoint);
//			
//			double distanceFromOPlaneToCenter = getDistance(oPlane, centerPoint);
//			double distanceFromOPlaneToReferencePoint = getDistance(oPlane, referencePoint);
//			
//			
//			if (distanceFromPlaneToO >= 0 && distanceFromPlaneToReferencePoint >= 0) {
//				return -1;
//			} else if (distanceFromPlaneToO <= 0 && distanceFromPlaneToReferencePoint <= 0) {
//				return -1;
//			} 
//			
//			if (distanceFromOPlaneToCenter <= 0 && distanceFromOPlaneToReferencePoint <= 0) {
//				return 1;
//			} else if (distanceFromOPlaneToCenter >= 0 && distanceFromOPlaneToReferencePoint >= 0) {
//				return 1;
//			}
			
			return 0;
		}
		
		return 0;

		
	}
	
	public int compareWith(Wall3D o) {
		
		
		double minZ = getMinimalZ();
		double oMinZ = ((Wall3D) o).getMinimalZ();
		
		double maxZ = getMaximalZ();
		double oMaxZ = ((Wall3D) o).getMaximalZ();
		
		
		
		if (minZ >= oMaxZ) {
			return -1;
		}
		
		if (oMinZ >= maxZ) {
			return 1;
		}
		
		Point3D centerPoint = getCenterPoint();
		Point3D oCenterPoint = o.getCenterPoint();
		
		if (centerPoint.getX() == oCenterPoint.getX()) {
			if (centerPoint.getY() == oCenterPoint.getY()) {
				if(centerPoint.getZ() == oCenterPoint.getZ()) {
					return 0;
				}
			}
		}
		
		Plane3D plane = getPlane();
		Plane3D oPlane = ((Wall3D)o).getPlane();
		
		Point3D fromCenterToOPlaneIntersect = getIntersection(centerPoint, oPlane);
		double z1 = Vector3D.getScalarProduct(oPlane.getVector(), new Vector3D(fromCenterToOPlaneIntersect, centerPoint)); 
		double z2 = Vector3D.getScalarProduct(oPlane.getVector(), new Vector3D(fromCenterToOPlaneIntersect, referencePoint));
		
		
		
		
		Point3D fromOCenterToPlaneIntersect = getIntersection(oCenterPoint, plane);
		double z3 = Vector3D.getScalarProduct(plane.getVector(), new Vector3D(fromOCenterToPlaneIntersect, oCenterPoint)); 
		double z4 = Vector3D.getScalarProduct(plane.getVector(), new Vector3D(fromOCenterToPlaneIntersect, referencePoint));
		
		
		
		
		
		
		if (z1 > 0 && z2 < 0) {
			if (checkOtherPoints(this, true, oPlane, fromCenterToOPlaneIntersect)) {
				return -1;
			}
		} 
		if (z1 < 0 && z2 > 0) {
			if (checkOtherPoints(this, false, oPlane, fromCenterToOPlaneIntersect)) {
				return -1;
			}
		}
		
		if (z3 > 0 && z4 > 0) {
			if (checkOtherPoints(o, true, plane, fromOCenterToPlaneIntersect)) {
				return -1;
			}
		}
		if(z3 < 0 && z4 < 0) {
			if (checkOtherPoints(o, false, plane, fromOCenterToPlaneIntersect)) {
				return -1;
			}
		}
		
		if (z1 > 0 && z2 > 0) {
			if (checkOtherPoints(this, true, oPlane, fromCenterToOPlaneIntersect)) {
				return 1;
			}
		} 
		if (z1 < 0 && z2 < 0) {
			if (checkOtherPoints(this, false, oPlane, fromCenterToOPlaneIntersect)) {
				return 1;
			}
		}
		
		if (z3 > 0 && z4 < 0) {
			if (checkOtherPoints(o, true, plane, fromOCenterToPlaneIntersect)) {
				return 1;
			}
		}
		if(z3 < 0 && z4 > 0) {
			if (checkOtherPoints(o, false, plane, fromOCenterToPlaneIntersect)) {
				return 1;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		if (getDistance(centerPoint, referencePoint) < getDistance(oCenterPoint, referencePoint)) {
//			return 1;
//		} else if (getDistance(centerPoint, referencePoint) > getDistance(oCenterPoint, referencePoint)) {
//			return -1;
//		}
		
		
		System.out.println("Cannot determine order: " + centerPoint.toString() + "; " + oCenterPoint.toString());
		return 0;

		
	}
	
	private boolean checkOtherPoints(Wall3D wall, boolean plus, Plane3D plane, Point3D intersect) {
		for (Point3D point : wall.getPoints()) {
			double pointDistance = Vector3D.getScalarProduct(plane.getVector(), new Vector3D(intersect, point));
			if (plus) {
				if (pointDistance <= 0) {
					return false;
				}
			} else {
				if (pointDistance >= 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int compareWithOld(Wall3D o) {
		Point3D centerPoint = getCenterPoint();
		Point3D oCenterPoint = o.getCenterPoint();
		
		if (centerPoint.getX() == oCenterPoint.getX()) {
			if (centerPoint.getY() == oCenterPoint.getY()) {
				if(centerPoint.getZ() == oCenterPoint.getZ()) {
					return 0;
				}
			}
		}
		
		double minZ = getMinimalZ();
		double oMinZ = ((Wall3D) o).getMinimalZ();
		
		double maxZ = getMaximalZ();
		double oMaxZ = ((Wall3D) o).getMaximalZ();
		
		if (minZ >= oMaxZ) {
			return -1;
		}
		
		if (oMinZ >= maxZ) {
			return 1;
		}
		
		Plane3D plane = getPlane();
		Plane3D oPlane = ((Wall3D)o).getPlane();
		
		double distanceFromPlaneToO = getDistance(plane, oCenterPoint);
		double distanceFromPlaneToReferencePoint = getDistance(plane, referencePoint);
		
		double distanceFromOPlaneToCenter = getDistance(oPlane, centerPoint);
		double distanceFromOPlaneToReferencePoint = getDistance(oPlane, referencePoint);
		
		
		if (distanceFromPlaneToO >= 0 && distanceFromPlaneToReferencePoint >= 0) {
			return -1;
		} else if (distanceFromPlaneToO <= 0 && distanceFromPlaneToReferencePoint <= 0) {
			return -1;
		} 
		
		if (distanceFromOPlaneToCenter <= 0 && distanceFromOPlaneToReferencePoint <= 0) {
			return 1;
		} else if (distanceFromOPlaneToCenter >= 0 && distanceFromOPlaneToReferencePoint >= 0) {
			return 1;
		}
		
		return 0;
		
	}
	
//	public static void translateReferencePoint(double x, double y, double z) {
//		referencePoint.setX(referencePoint.getX() - x);
//		referencePoint.setY(referencePoint.getY() - y);
//		referencePoint.setZ(referencePoint.getZ() - z);
//		System.out.println(referencePoint.toString());
//	}
	
	public double getMinimalZ() {
		double minimalZ = Double.MAX_VALUE;
		for (Point3D point : points) {
			double z = point.getProjection()[2][0];
			if (z < minimalZ) {
				minimalZ = z;
			}
		}
		return minimalZ;
	}
	
	public double getMaximalZ() {
		double maximalZ = Double.NEGATIVE_INFINITY;
		for (Point3D point : points) {
			double z = point.getProjection()[2][0];
			if (z > maximalZ) {
				maximalZ = z;
			}
		}
		return maximalZ;
	}
	
	public double getMinimalX() {
		double minimalX = Double.MAX_VALUE;
		for (Point3D point : points) {
			double x = point.getProjection()[0][0];
			if (x < minimalX) {
				minimalX = x;
			}
		}
		return minimalX;
	}
	
	public double getMaximalX() {
		double maximalX = Double.NEGATIVE_INFINITY;
		for (Point3D point : points) {
			double x = point.getProjection()[0][0];
			if (x > maximalX) {
				maximalX = x;
			}
		}
		return maximalX;
	}
	
	public double getProjectionMinimalX() {
		Set<Point2D> projPoints = getProjection();
		double minimalX = Double.MAX_VALUE;
		for (Point2D point : projPoints) {
			double x = point.getX();
			if (x < minimalX) {
				minimalX = x;
			}
		}
		return minimalX;
	}
	
	public double getProjectionMaximalX() {
		double maximalX = Double.NEGATIVE_INFINITY;
		Set<Point2D> projPoints = getProjection();
		for (Point2D point : projPoints) {
			double x = point.getX();
			if (x > maximalX) {
				maximalX = x;
			}
		}
		return maximalX;
	}
	
	public double getProjectionMinimalY() {
		double minimalY = Double.MAX_VALUE;
		Set<Point2D> projPoints = getProjection();
		for (Point2D point : projPoints) {
			double y = point.getY();
			if (y < minimalY) {
				minimalY = y;
			}
		}
		return minimalY;
	}
	
	public double getProjectionMaximalY() {
		double maximalY = Double.NEGATIVE_INFINITY;
		Set<Point2D> projPoints = getProjection();
		for (Point2D point : projPoints) {
			double y = point.getY();
			if (y > maximalY) {
				maximalY = y;
			}
		}
		return maximalY;
	}
	
	public double getMinimalY() {
		double minimalY = Double.MAX_VALUE;
		for (Point3D point : points) {
			double y = point.getProjection()[1][0];
			if (y < minimalY) {
				minimalY = y;
			}
		}
		return minimalY;
	}
	
	public double getMaximalY() {
		double maximalY = Double.NEGATIVE_INFINITY;
		for (Point3D point : points) {
			double y = point.getProjection()[1][0];
			if (y > maximalY) {
				maximalY = y;
			}
		}
		return maximalY;
	}
	
	public boolean overlapsWith(Wall3D o) {
		double maxX = getProjectionMaximalX();
		double minX = getProjectionMinimalX();
		double oMaxX = o.getProjectionMaximalX();
		double oMinX = o.getProjectionMinimalX();
		if (minX >= oMaxX || oMinX >= maxX) {
			return false;
		}
		double maxY = getProjectionMaximalY();
		double minY = getProjectionMinimalY();
		double oMaxY = o.getProjectionMaximalY();
		double oMinY = o.getProjectionMinimalY();
		if (minY >= oMaxY || oMinY >= maxY) {
			return false;
		}
		return true;
	}
	
	
	
	
	public Point3D getCenterPoint() {
		double z = 0;
		double x = 0;
		double y = 0;
		for (Point3D point : points) {
			double[][] projection = point.getProjection();
			x += projection[0][0];
			y += projection[1][0];
			z += projection[2][0];
		}
		x = x / points.size();
		y = y / points.size();
		z = z / points.size();
		return new Point3D(x, y, z);
	}
	
	public Plane3D getPlane() {
		Point3D centerPoint = getCenterPoint();
		Vector3D vectorA = new Vector3D(points.get(0), points.get(2));
		Vector3D vectorB = new Vector3D(points.get(1), points.get(3));
		Vector3D vectorC = Vector3D.getCrossProduct(vectorA, vectorB);
		return new Plane3D(centerPoint, vectorC);
	}
	
	public double getDistance(Plane3D plane, Point3D point) {
		double nomX = plane.getX() * point.getX();
		double nomY = plane.getY() * point.getY();
		double nomZ = plane.getZ() * point.getZ();
		double nom = nomX + nomY + nomZ + plane.getD();
		double denomX = Math.pow(plane.getX(), 2);
		double denomY = Math.pow(plane.getY(), 2);
		double denomZ = Math.pow(plane.getZ(), 2);
		double denom = Math.sqrt(denomX + denomY + denomZ);
		return nom / denom;
	}
	
	private double getDistance(Point3D point, Point3D oPoint) {
		double x = Math.pow(point.getX() - oPoint.getX(), 2);
		double y = Math.pow(point.getY() - oPoint.getY(), 2);
		double z = Math.pow(point.getZ() - oPoint.getZ(), 2);
		return Math.sqrt(x + y + z);
	}
	
	public Point3D getIntersection(Point3D point, Plane3D plane) {
		double t = getTForIntersection(point, plane);
		double x = plane.getX() * (point.getX() + t);
		double y = plane.getY() * (point.getY() + t);
		double z = plane.getZ() * (point.getZ() + t);
		return new Point3D(x, y, z);
	}
	
	private double getTForIntersection(Point3D point, Plane3D plane) {
		double nomX = plane.getX() * point.getX();
		double nomY = plane.getY() * point.getY();
		double nomZ = plane.getZ() * point.getZ();
		double nom = plane.getD() - nomX - nomY - nomZ;
		double denom = plane.getX() + plane.getY() + plane.getZ();
		return nom / denom;
	}
	
	public Set<Point2D> getProjection() {
		return projection;
	}
	
	public void setProjection(Set<Point2D> projection) {
		this.projection = projection;
	}
	
	public static Point3D getReferencePoint() {
		return referencePoint;
	}
	
	public boolean isVisible() {
		if(getProjectionMaximalX() < -1 || getProjectionMaximalY() < -1) {
			return false;
		}
		if (getProjectionMinimalX() > 1 || getProjectionMinimalX() > 1) {
			return false;
		}	
		if(getProjectionMaximalY() < -1 || getProjectionMaximalY() < -1) {
			return false;
		}
		if (getProjectionMinimalY() > 1 || getProjectionMinimalY() > 1) {
			return false;
		}		
		return true;
	}
	
	public int getId() {
		return id;
	}

}
