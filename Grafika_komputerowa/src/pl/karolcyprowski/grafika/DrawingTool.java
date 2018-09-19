package pl.karolcyprowski.grafika;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import pl.karolcyprowski.grafika.graphics.Cube3D;
import pl.karolcyprowski.grafika.graphics.Figure3D;
import pl.karolcyprowski.grafika.graphics.Point2D;
import pl.karolcyprowski.grafika.graphics.Point3D;
import pl.karolcyprowski.grafika.graphics.Wall3D;

public class DrawingTool {

	public final static Color COLOR_BACKGROUND = Color.BLACK;
	public final static Color COLOR_LINE = Color.WHITE;
	public final static Color COLOR_FILL = Color.BLUE;
	
	private DrawDataContainer dataContainer;
	private WallDrawer wallDrawer;
	private SortingTool sortingTool;
	
	
	public DrawingTool() {
		this.dataContainer = new DrawDataContainer();	
		this.wallDrawer = new WallDrawer();
		this.sortingTool = new SortingTool();
	}
	
	public void init() {
		dataContainer.init();
	}
	
	public void drawFigures(Graphics g) {
		List<Wall3D> walls = dataContainer.getWalls();
		translateAndRotate(walls);
		project(walls);
		List<Figure3D> figures = dataContainer.getFigures();
		walls = sortingTool.getWallsToDraw(figures);
		System.out.println("#####################");
		for (int i = 0; i < walls.size(); i++) {
			Wall3D wall = walls.get(i);
			System.out.println(wall.getCenterPoint());
			Set<Point2D> points2D = wall.getProjection();
			g.setColor(COLOR_LINE);
			wallDrawer.draw(points2D, g);
			g.setColor(COLOR_FILL);
			wallDrawer.fill(points2D, g);
		}
		System.out.println("#####################");
	}
	

	
	
	public WallDrawer getWallDrawer() {
		return wallDrawer;
	}
	
	private void translateAndRotate(List<Wall3D> walls) {
		Set<Point3D> points = new LinkedHashSet<Point3D>();
		walls.stream().forEach(w -> w.getPoints().stream().forEach(p -> points.add(p)));
		getWallDrawer().getProjector().getAxisRotator().rotate(getWallDrawer().getProjector().getProjectionPlane());
		getWallDrawer().getProjector().getTranslator().translate(getWallDrawer().getProjector().getProjectionPlane());
//		double[][] matrix = getWallDrawer().getProjector().getProjectionPlane().getMatrix();
		points.stream().forEach(p -> getWallDrawer().getProjector().multiplyByMatrix(p));
	}
	
	private void project(List<Wall3D> walls) {
		for (Wall3D wall : walls) {
			Set<Point2D> points2D = wallDrawer.getProjectedPoints(wall);
			wall.setProjection(points2D);
		}
	}
	
	
}
