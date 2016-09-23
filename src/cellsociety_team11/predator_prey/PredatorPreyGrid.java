package cellsociety_team11.predator_prey;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class PredatorPreyGrid extends Grid<Integer> {

	public static final int EMPTY = 0;

	public PredatorPreyGrid(int height, int length, Rule<Integer> rule) {
		super(height, length, rule);
	}

	@Override
	public Cell<Integer> createNewCell(Coordinates coordinates, Rule<Integer> rule) {
		return new PredatorPreyCell(EMPTY, rule, coordinates, this);
	}

}
