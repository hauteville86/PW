package pl.karolcyprowski.grafika;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.karolcyprowski.grafika.graphics.Cube3D;
import pl.karolcyprowski.grafika.graphics.Figure3D;
import pl.karolcyprowski.grafika.graphics.Point3D;
import pl.karolcyprowski.grafika.graphics.Vector3D;
import pl.karolcyprowski.grafika.graphics.Wall3D;

public class DrawDataContainer {
	
	private List<Wall3D> walls;
	private List<Figure3D> figures;
	private CubeFactory cubeFactory;
	private double startZ = 200;
	private double startY = 0;
	
	public DrawDataContainer() {
		walls = new ArrayList<Wall3D>();
		cubeFactory = new CubeFactory();
		figures = new ArrayList<Figure3D>();
	}
	
	public void init() {
		
		List<Point3D> pointsForSimpleCube = new ArrayList<Point3D>();
		
		int wallId = 1;
		
		//create road
		Point3D point001 = new Point3D(-800, startY, startZ - 20);
		Point3D point002 = new Point3D(800, startY, startZ - 20);
		Point3D point003 = new Point3D(800, startY, startZ);
		Point3D point004 = new Point3D(-800, startY, startZ);
		List<Point3D> pointsForRoad = new ArrayList<Point3D>();
		pointsForRoad.add(point001);
		pointsForRoad.add(point002);
		pointsForRoad.add(point003);
		pointsForRoad.add(point004);
		addWall(new Wall3D(pointsForRoad, wallId));
		wallId++;
		
		//create first building
		Point3D point005 = new Point3D(-40, startY, startZ);
		Point3D point006 = new Point3D(-40, -300, startZ);
		Point3D point007 = new Point3D(40, -300, startZ);
		Point3D point008 = new Point3D(40, startY, startZ);
		pointsForSimpleCube.add(point005);
		pointsForSimpleCube.add(point006);
		pointsForSimpleCube.add(point007);
		pointsForSimpleCube.add(point008);
		addSimpleCube(new Wall3D(pointsForSimpleCube, wallId), new Vector3D(0, 0, 1, 100), wallId);
		wallId += 6;
		
		//create second builidng
		Point3D point009 = new Point3D(50, startY, startZ);
		Point3D point010 = new Point3D(50, -600, startZ);
		Point3D point011 = new Point3D(250, -600, startZ);
		Point3D point012 = new Point3D(250, startY, startZ);
		pointsForSimpleCube = new ArrayList<Point3D>();
		pointsForSimpleCube.add(point009);
		pointsForSimpleCube.add(point010);
		pointsForSimpleCube.add(point011);
		pointsForSimpleCube.add(point012);
		addSimpleCube(new Wall3D(pointsForSimpleCube, wallId), new Vector3D(0, 0, 1, 100), wallId);
		wallId += 6;
//		
		Point3D point013 = new Point3D(260, startY, startZ);
		Point3D point014 = new Point3D(260, -400, startZ);
		Point3D point015 = new Point3D(500, -400, startZ);
		Point3D point016 = new Point3D(500, startY, startZ);
		pointsForSimpleCube = new ArrayList<Point3D>();
		pointsForSimpleCube.add(point013);
		pointsForSimpleCube.add(point014);
		pointsForSimpleCube.add(point015);
		pointsForSimpleCube.add(point016);
		addSimpleCube(new Wall3D(pointsForSimpleCube, wallId), new Vector3D(0, 0, 1, 30), wallId);
		wallId += 6;
		
		Point3D point017 = new Point3D(-200, startY, startZ + 200);
		Point3D point018 = new Point3D(-200, -400, startZ + 200);
		Point3D point019 = new Point3D(700, -400, startZ + 200);
		Point3D point020 = new Point3D(700, startY, startZ + 200);
		pointsForSimpleCube = new ArrayList<Point3D>();
		pointsForSimpleCube.add(point017);
		pointsForSimpleCube.add(point018);
		pointsForSimpleCube.add(point019);
		pointsForSimpleCube.add(point020);
		addSimpleCube(new Wall3D(pointsForSimpleCube, wallId), new Vector3D(0, 0, 1, 200), wallId);
		wallId += 6;
		
		Point3D point021 = new Point3D(-600, startY, startZ);
		Point3D point022 = new Point3D(-600, -1000, startZ);
		Point3D point023 = new Point3D(-300, -1000, startZ);
		Point3D point024 = new Point3D(-300, startY, startZ);
		pointsForSimpleCube = new ArrayList<Point3D>();
		pointsForSimpleCube.add(point021);
		pointsForSimpleCube.add(point022);
		pointsForSimpleCube.add(point023);
		pointsForSimpleCube.add(point024);
		addSimpleCube(new Wall3D(pointsForSimpleCube, wallId), new Vector3D(0, 0, 1, 30), wallId);
		
	}
	
	public List<Wall3D> getWalls() {
		return walls;
	}
	
	public List<Figure3D> getFigures() {
		return figures;
	}
	
	private void addWall(Wall3D wall) {
		walls.add(wall);
		figures.add(wall);
	}
	
	private void addSimpleCube(Wall3D front, Vector3D vector, int wallId) {
		Cube3D cube = cubeFactory.createCube(front, vector, wallId);
		cube.getWalls().stream().forEach(w -> walls.add(w));
		figures.add(cube);
	}

}
