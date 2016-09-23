package cellsociety_team11.segregation;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class SegregationGrid extends Grid<Integer>{

	public static final int EMPTY = 0;

	public SegregationGrid(int height, int length, Rule<Integer> rule) {
		super(height, length, rule);
	}

	@Override
	public Cell<Integer> createNewCell(Coordinates coordinates, Rule<Integer> rule) {
		return new SegregationCell(EMPTY, rule, coordinates, this);
	}

}
