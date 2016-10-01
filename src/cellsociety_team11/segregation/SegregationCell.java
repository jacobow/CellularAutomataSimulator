package cellsociety_team11.segregation;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class SegregationCell extends Cell<Integer>{

	public static final Integer EMPTY = 0;

	/**
	 * creates a new segregation cell
	 */
	public SegregationCell(Integer value, Coordinates coordinates, Grid<Integer> grid, String shape) {
		super(value, coordinates, grid, shape);
	}

}
