package cellsociety_team11.spreading_of_fire;

import java.util.Random;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class SpreadingOfFireRules implements Rule<Integer>{

	public static final int EMPTY = 0;
	public static final int TREE = 1;
	public static final int BURNING = 2;

	private double probCatch;

	public SpreadingOfFireRules(double probCatch) {
		this.probCatch = probCatch;
	}

	/**
	 * returns new value based on its neighbors.  Burning neighbors have a set probablity of causing the value
	 * to be burning as well
	 */
	@Override
	public Integer calculateNewValue(Cell<Integer> cell, Integer value, Grid<Integer> grid, Coordinates coordinates) {
		Random r = new Random();
		if(value == BURNING) return EMPTY;
		for(SpreadingOfFireCell land : ((SpreadingOfFireCell)cell).getNeighbors()) {
			if(value == TREE &&
			   land.getValue() == BURNING &&
			   r.nextDouble() < probCatch) {
				return BURNING;
			}
		}
		return value;
	}

}
