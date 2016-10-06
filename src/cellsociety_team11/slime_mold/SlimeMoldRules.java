// This entire file is part of my masterpiece.
// Jacob Warner

/**
 * This code was greatly shortened, and now it has one elegant method from which you can nicely
 * see the logic of how the slime mold simulation works.  It originally had a bunch of help methods,
 * but these were migrated over to the SlimeMoldCell class (also included in this master piece).  This
 * is also a very good example of how I implement cell rules from the super class and why putting this function
 * in a seperate class was a good idea for clarity.
 */

package cellsociety_team11.slime_mold;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class SlimeMoldRules implements Rule<Integer>{

	public static final int EMPTY = 0;
	public static final int SLIME = 1;

	/**
	 * calculates the new value of a slime cell
	 */
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
