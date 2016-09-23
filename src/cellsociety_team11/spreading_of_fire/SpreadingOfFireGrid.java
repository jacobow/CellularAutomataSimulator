package cellsociety_team11.spreading_of_fire;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class SpreadingOfFireGrid extends Grid<Integer>{

	public static final int EMPTY = 0;

	/**
	 *
	 * @param height
	 * @param length
	 * @param rule
	 */
	public SpreadingOfFireGrid(int height, int length, Rule<Integer> rule) {
		super(height, length, rule);
	}

	@Override
	public Cell<Integer> createNewCell(Coordinates coordinates, Rule<Integer> rule) {
		return new SpreadingOfFireCell(EMPTY, rule, coordinates, this);
	}

}
