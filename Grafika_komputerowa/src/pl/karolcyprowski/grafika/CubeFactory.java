package pl.karolcyprowski.grafika;


import pl.karolcyprowski.grafika.graphics.Cube3D;
import pl.karolcyprowski.grafika.graphics.Vector3D;
import pl.karolcyprowski.grafika.graphics.Wall3D;

public class CubeFactory {
	
	private WallFactory wallFactory = new WallFactory();
	
	public Cube3D createCube(Wall3D front, Vector3D vector, int wallId) {
		Cube3D cube = new Cube3D();
		cube.addWall(front);
		wallId++;
		Wall3D back = wallFactory.createBackWall(front, vector, wallId); 
		cube.addWall(back);
		wallId++;
		wallFactory.createSideWalls(front, back, wallId).stream().forEach(w -> cube.addWall(w));
		return cube;
	}
	
	

}
