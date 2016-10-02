package cellsociety_team11.spreading_of_fire;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class SpreadingOfFireCell extends Cell<Integer>{

	public static final Integer EMPTY = 0;

	/**
	 * creates a new spreading of fire cell
	 */
	public SpreadingOfFireCell(Integer value, Coordinates coordinates, Grid<Integer> grid, int shape) {
		super(value, coordinates, grid, shape);
	}

}
