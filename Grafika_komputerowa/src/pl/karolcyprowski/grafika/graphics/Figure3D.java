package pl.karolcyprowski.grafika.graphics;

public abstract class Figure3D implements Comparable<Figure3D> {
	
	public int compareTo(Figure3D o) {
		
		Point3D centerPoint = this.getCenterPoint();
		Point3D oCenterPoint = o.getCenterPoint();
		if (centerPoint.getZ() < oCenterPoint.getZ()) {
			return 1;
		} else if (centerPoint.getZ() > oCenterPoint.getZ()) {
			return -1;
		} else {
			if (getDistanceToReferencePoint(centerPoint) < getDistanceToReferencePoint(oCenterPoint)) {
				return 1;
			} else if (getDistanceToReferencePoint(centerPoint) > getDistanceToReferencePoint(oCenterPoint)) {
				return -1;
			}
		}
		return 0;
	}
	
	public abstract Point3D getCenterPoint();
	
	protected double getDistanceToReferencePoint(Point3D point) {
		double distance = 0;
		distance += Math.pow(Wall3D.getReferencePoint().getX() - point.getX(), 2);
		distance += Math.pow(Wall3D.getReferencePoint().getY() - point.getY(), 2);
		distance += Math.pow(Wall3D.getReferencePoint().getZ() - point.getZ(), 2);
		return Math.sqrt(distance);
	}
}
