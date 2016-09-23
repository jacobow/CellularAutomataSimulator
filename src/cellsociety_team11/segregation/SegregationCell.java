package cellsociety_team11.segregation;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class SegregationCell extends Cell<Integer>{

	public SegregationCell(Integer value, Rule<Integer> rule, Coordinates coordinates, Grid<Integer> grid) {
		super(value, rule, coordinates, grid);
	}

}
