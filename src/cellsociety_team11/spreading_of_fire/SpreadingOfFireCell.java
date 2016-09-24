package cellsociety_team11.spreading_of_fire;

import java.util.HashSet;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class SpreadingOfFireCell extends Cell<Integer>{

	public static final Integer EMPTY = 0;

	/**
	 * creates a new spreading of fire cell
	 */
	public SpreadingOfFireCell(Integer value, Coordinates coordinates, Grid<Integer> grid) {
		super(value, coordinates, grid);
	}

	/**
	 * gets a cell's neighbors
	 */
	public HashSet<SpreadingOfFireCell> getNeighbors() {
		int i = coordinates.getI();
		int j = coordinates.getJ();
		HashSet<SpreadingOfFireCell> neighbors = new HashSet<SpreadingOfFireCell>();
		for(int y = -1; i <= 1; y++) {
			for(int x = -1; j <= 1; x++) {
				if(y == x) {
					continue;
				}
				if(0 > i+y || i+y >= grid.getHeight() || 0 > j+x || j+x >= grid.getWidth()) {
					neighbors.add(new SpreadingOfFireCell(EMPTY, null, null));
				}
				else {
					neighbors.add((SpreadingOfFireCell) grid.getCell(new Coordinates(i+y, j+x)));
				}
			}
		}
		return neighbors;
	}
}
