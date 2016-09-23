package cellsociety_team11.spreading_of_fire;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class SpreadingOfFireCell extends Cell<Integer>{

	public SpreadingOfFireCell(Integer value, Rule<Integer> rule, Coordinates coordinates, Grid<Integer> grid) {
		super(value, rule, coordinates, grid);
	}

}
