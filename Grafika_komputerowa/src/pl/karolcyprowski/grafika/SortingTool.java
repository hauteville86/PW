package pl.karolcyprowski.grafika;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import pl.karolcyprowski.grafika.graphics.Cube3D;
import pl.karolcyprowski.grafika.graphics.Figure3D;
import pl.karolcyprowski.grafika.graphics.Wall3D;

public class SortingTool {
	
	
	public List<Wall3D> getWallsToDraw(List<Figure3D> figures) {
//		Collections.shuffle(figures);
//		Collections.sort(figures);
		List<Wall3D> walls = getWallsFromFigures(figures);
//		removeInvisibleWalls(walls);
		Collections.shuffle(walls);
		List<Wall3D> wallsToDraw = new ArrayList<Wall3D>();
		int numberOfWalls = walls.size();
		for (int i = 0; i < numberOfWalls; i++) {
			Wall3D wall = getMostDistant(walls);
			walls.remove(wall);
			wallsToDraw.add(wall);
		}
//		printCenterPoints(wallsToDraw);
		return wallsToDraw;
	}
	
	private Wall3D getMostDistant(List<Wall3D> walls) {
		Collections.sort(walls);
		List<Wall3D> array = createArrayList(walls);
		Iterator<Wall3D> w = walls.iterator();
		while(w.hasNext()) {
			Wall3D wall = w.next();
//			if (wall.getId() == 20) {
//				System.out.println("Checkpoint:");
//			}			
			if(isMostDistant(wall, array)) {
				return wall;
			}
		}
//		Collections.sort(walls);
		System.out.println("Sort was necessary");
		return walls.get(0);
	}
	
	private List<Wall3D> createArrayList(List<Wall3D> walls) {
		List<Wall3D> array = new ArrayList<Wall3D>();
		for(Wall3D wall : walls) {
			array.add(wall);
		}
		return array;
	}
		
	private boolean isMostDistant(Wall3D wall, List<Wall3D> walls) {
		for (Wall3D wallToCompare : walls) {
			if (wall.overlapsWith(wallToCompare)) {				
				if (wall.compareWithOld(wallToCompare) > 0 || wall.compareWith(wallToCompare) > 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private List<Wall3D> getWallsFromFigures(List<Figure3D> figures) {
		List<Wall3D> walls = new LinkedList<Wall3D>();
		for (Figure3D figure : figures) {
			if (figure instanceof Wall3D) {
				walls.add((Wall3D)figure);
			} else if (figure instanceof Cube3D) {
				List<Wall3D> cubeWalls = ((Cube3D)figure).getWalls();
				for (Wall3D w : cubeWalls) {
					if (w.isVisible()) {
						walls.add(w);
					}
					
				}
			}
		}
		return walls;
	}
	
	private void printCenterPoints(List<Wall3D> walls) {
		System.out.println("#####################");
		for (Wall3D wall : walls) {
			System.out.println(wall.getCenterPoint());
		}
		System.out.println("#####################");
	}
	
//	private void removeInvisibleWalls(List<Wall3D> walls) {
//		for (Wall3D wall : walls) {
//			if (!wall.isVisible()) {
//				walls.remove(wall);
//			}
//		}
//	}

}
