package cellsociety_team11.slime_mold;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class SlimeMoldRules implements Rule<Integer>{

	public static final int EMPTY = 0;
	public static final int SLIME = 1;

	@Override
	public Integer calculateNewValue(Cell<Integer> cell, Integer value, Grid<Integer> grid, Coordinates coordinates) {
		int newValue = value;
		SlimeMoldCell myCell = (SlimeMoldCell) cell;
		if(value == SLIME) {
			myCell.diffuse();
			if(myCell.move(grid)) newValue = EMPTY;
		}
		myCell.evaporate();
		return newValue;
	}

}
