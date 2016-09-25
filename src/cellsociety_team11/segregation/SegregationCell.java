package cellsociety_team11.segregation;

import java.util.HashSet;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class SegregationCell extends Cell<Integer>{

	public static final Integer EMPTY = 0;

	/**
	 * creates a new segregation cell
	 */
	public SegregationCell(Integer value, Coordinates coordinates, Grid<Integer> grid) {
		super(value, coordinates, grid);
	}

	/**
	 * finds the potentially 6 neighbors of a coordinate on a grid.  Edge coordinates are
	 * given empty dummy cells as some neighbors
	 */
	public HashSet<SegregationCell> getNeighbors() {
		int i = coordinates.getI();
		int j = coordinates.getJ();
		HashSet<SegregationCell> neighbors = new HashSet<SegregationCell>();
		for(int y = -1; y <= 1; y++) {
			for(int x = -1; x <= 1; x++) {
				if(y == 0 && x == 0) {
					continue;
				}
				if(0 > i+y || i+y >= grid.getHeight() || 0 > j+x || j+x >= grid.getWidth()) {
					neighbors.add(new SegregationCell(EMPTY, null, grid));
				}
				else {
					neighbors.add((SegregationCell) grid.getCell(new Coordinates(i+y, j+x)));
				}
			}
		}
		return neighbors;
	}
}
