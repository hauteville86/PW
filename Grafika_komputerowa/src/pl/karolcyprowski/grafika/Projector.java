package pl.karolcyprowski.grafika;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.karolcyprowski.grafika.graphics.Point2D;
import pl.karolcyprowski.grafika.graphics.Point3D;
import pl.karolcyprowski.grafika.graphics.Translator;

public class Projector {
	
	private ProjectorMatrix projectionPlane;
	private Translator translator = new Translator();
	private AxisRotator axisRotator = new AxisRotator();
	
	public Projector() {
		projectionPlane = new ProjectorMatrix();
	}
	

	
	public Set<Point2D> projectTo2D(Set<Point3D> points3D) {
		Set<Point2D> result = new LinkedHashSet<Point2D>();
		Map<Point3D, Point2D> points2DMap = getPointPairs(points3D);
		for (Point3D point3D : points3D) {
			Point2D point2D = points2DMap.get(point3D);
			Set<Point3D> connections = point3D.getConnections();
			if (connections.size() != point2D.getConnetions().size()) {
				for (Point3D connectedPoint3D : connections) {
					Point2D connectedPoint2D = points2DMap.get(connectedPoint3D);
					if (connectedPoint2D != null) {
						point2D.addConnection(connectedPoint2D);
					}
				}
			}
			result.add(point2D);
		}
		return result;
	}
	
	
	
	private Map<Point3D, Point2D> getPointPairs(Set<Point3D> points3D) {
		Map<Point3D, Point2D> points2DMap = new LinkedHashMap<Point3D, Point2D>();
		for(Point3D point3D : points3D) {
			Point2D point2D = project(point3D);
			points2DMap.put(point3D, point2D);
		}
		return points2DMap;
	}
	
	private Point2D project(Point3D point) {
		double[][] pointMatrix = point.getProjection();
		double d = projectionPlane.getD();
		
		double x = pointMatrix[0][0] * d / pointMatrix[2][0];
		double y = pointMatrix[1][0] * d / pointMatrix[2][0];

		return new Point2D(x, y, pointMatrix[2][0]);	
	}
	
	public void translate() {
		double[][] translationMatrix = translator.getMatrix();
		projectionPlane.changeMatrix(translationMatrix);
	}
	
	public ProjectorMatrix getProjectionPlane() {
		return projectionPlane;
	}
	
	public Translator getTranslator() {
		return translator;
	}
	
	public AxisRotator getAxisRotator() {
		return axisRotator;
	}
	
	public void multiplyByMatrix(Point3D point) {
		double[][] matrixPoint = point.getMatrixRepresentation();
		matrixPoint = Matrix.multiply(projectionPlane.getMatrix(), matrixPoint);
		double w = matrixPoint[3][0];
		double originX = matrixPoint[0][0] / w;
		double originY = matrixPoint[1][0] / w;
		double originZ = matrixPoint[2][0] / w;
		double[][] projection = {{originX}, {originY}, {originZ}, {w}};

		point.setProjection(projection);
	}

}
