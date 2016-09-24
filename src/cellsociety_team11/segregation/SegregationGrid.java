package cellsociety_team11.segregation;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class SegregationGrid extends Grid<Integer>{

	public static final int EMPTY = 0;

	/**
	 * creates a new segregation grid
	 * @param valueGrid
	 * 				grid of the values of the cells
	 * @param theshold
	 * 				a parameter that determines the tolerance of agents
	 */
	public SegregationGrid(Integer[][] valueGrid, double theshold) {
		super(valueGrid, new SegregationRules(theshold));
	}

	/**
	 * creates a new cell in the grid
	 */
	@Override
	public Cell<Integer> createNewCell(Integer value, Coordinates coordinates) {
		return new SegregationCell(value, coordinates, this);
	}

}
