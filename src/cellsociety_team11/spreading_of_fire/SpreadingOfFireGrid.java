package cellsociety_team11.spreading_of_fire;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class SpreadingOfFireGrid extends Grid<Integer>{

	public static final int EMPTY = 0;

	/**
	 *creates a grid for the spreading of fire simulation
	 */
	public SpreadingOfFireGrid(Integer[][] valueGrid, double probCatch) {
		super(valueGrid, new SpreadingOfFireRules(probCatch));
	}

	/**
	 * creates a new cell for grid initialization
	 */
	@Override
	public Cell<Integer> createNewCell(Integer value, Coordinates coordinates) {
		return new SpreadingOfFireCell(value, coordinates, this);
	}

}
